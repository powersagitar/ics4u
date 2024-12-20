package src.mastermind;

import java.util.*;

public class MastermindSolver {
    public enum Status {
        Win, Lose, Continue
    }

    private HashSet<Code> permutations;
    private int attempts = 0;
    private Code previousGuess;

    public MastermindSolver() {
        generatePermutations();
    }

    /**
     * Step 1
     * Generates all possible permutations of the secret code.
     */
    private void generatePermutations() {
        final int possibilities = (int) Math.pow(Mastermind.TOTAL_COLORS, Mastermind.CODE_LENGTH);
        permutations = new HashSet<>(possibilities);

        for (int i = 0; i < possibilities; ++i) {
            final ArrayList<Integer> codeInDigits = MathUtil.digitsFromBase(i, Mastermind.TOTAL_COLORS,
                Mastermind.CODE_LENGTH);
            final Code code = new Code(codeInDigits);
            permutations.add(code);
        }
    }

    /**
     * Step 2
     * Initial guess
     */
    public Code guess() {
        if (attempts > 0) {
            throw new IllegalCallerException("guess() is meant for the first guess.");
        }

        ++attempts;

        final Code nextGuess = new Code(Arrays.asList(0, 0, 1, 1));
        previousGuess = nextGuess;

        return nextGuess;
    }

    /**
     * Steps 3-6
     *
     * @param response The response/validation from the previous guess.
     * @return (status, code) where status is the game status and code is the next
     * guess.
     */
    public Tuple2<Status, Code> guess(final Response response) {
        if (attempts <= 0) {
            throw new IllegalCallerException("guess(Response) is meant for subsequent guesses.");
        }

        final Tuple2<Integer, Integer> validation = response.getResponse();
        final int correctCount = validation.first;

        if (correctCount >= Mastermind.CODE_LENGTH) {
            return new Tuple2<>(Status.Win, previousGuess);
        } else if (attempts >= Mastermind.MAX_GUESSES) {
            return new Tuple2<>(Status.Lose, previousGuess);
        }

        ++attempts;

        reducePermutations(response);

        final Code nextGuess = findNextGuess();
        previousGuess = nextGuess;

        return new Tuple2<>(Status.Continue, nextGuess);
    }

    /**
     * Step 5
     * Remove from permutations any code that would not give that response of colored and white pegs.
     */
    private void reducePermutations(final Response response) {
        System.out.println("permutations: " + this.permutations.size());
        this.permutations.removeIf(permutation -> {
            final Response testResponse = new Response(permutation, this.previousGuess);
            System.out.println("previous guess: " + this.previousGuess.getColors() + " permutation: " + permutation.getColors());
            System.out.println("received response: " + response.getResponse().first + " " + response.getResponse().second);
            System.out.println("test response: " + testResponse.getResponse().first + " " + testResponse.getResponse().second);
            System.out.println("received response == test response: " + testResponse.equals(response));
            return !testResponse.equals(response);
        });
    }

    /**
     * Step 6
     * Find the best guess from the remaining permutations.
     *
     * <p>
     * The algorithm implements:
     * <a href="https://stackoverflow.com/a/62430592/20143641">Stack Overflow Answer</a>
     * </p>
     *
     * @return The best guess.
     */
    private Code findNextGuess() {
        TreeMap<Integer, Code> guessScores = new TreeMap<>();

        for (final Code guess : this.permutations) {
            HashMap<Response, Tuple2<Code, Integer>> responses = new HashMap<>();

            for (final Code assumedCode : this.permutations) {
                final Response response = new Response(assumedCode, guess);
                final int prevOccurrence = responses.getOrDefault(response, new Tuple2<>(null, 0)).second;
                responses.put(response, new Tuple2<>(assumedCode, prevOccurrence + 1));
            }

            int maxOccurrence = Integer.MIN_VALUE;
            Code codeWithMaxOccurrence = null;

            for (final Response response : responses.keySet()) {
                final Tuple2<Code, Integer> codeAndOccurrence = responses.get(response);
                final Code code = codeAndOccurrence.first;
                final int occurrence = codeAndOccurrence.second;

                if (occurrence > maxOccurrence) {
                    maxOccurrence = occurrence;
                    codeWithMaxOccurrence = code;
                }
            }

            guessScores.put(maxOccurrence, codeWithMaxOccurrence);
        }

        return guessScores.firstEntry().getValue();
    }
}
