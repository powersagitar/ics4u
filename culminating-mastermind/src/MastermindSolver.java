import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.TreeMap;

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
     * Step 3-6
     *
     * @param response The response/validation from the previous guess.
     * @return (status, code) where status is the game status and code is the next
     *         guess.
     */
    public Tuple2<Status, Code> guess(final Response response) {
        if (attempts <= 0) {
            throw new IllegalCallerException("guess(Response) is meant for subsequent guesses.");
        }

        final Tuple2<Integer, Integer> validation = response.validate();
        final int correctCount = validation.first;

        if (correctCount >= Mastermind.CODE_LENGTH) {
            return new Tuple2<>(Status.Win, previousGuess);
        } else if (attempts >= Mastermind.MAX_GUESSES) {
            return new Tuple2<>(Status.Lose, previousGuess);
        }

        // reducePermutations(permutations, previousGuess, correctIndices,
        // misplacedIndices, incorrectIndices);

        ++attempts;

        final Code nextGuess = findNextGuess();
        previousGuess = nextGuess;

        return new Tuple2<>(Status.Continue, nextGuess);
    }

    private HashSet<Code> possiblePermutations(final Response response) {
        HashSet<Code> possiblePermutations = (HashSet<Code>) permutations.clone();

        return possiblePermutations;
    }

    /**
     * Step 6
     * Find the best guess from the remaining permutations.
     *
     * @return The best guess.
     */
    private Code findNextGuess() {
        TreeMap<Integer, Code> scores = new TreeMap<>();

        for (final Code guess : permutations) {
            int guessScore = Integer.MIN_VALUE;

            for (final Code assumedCode : permutations) {
                final Response response = new Response(assumedCode, guess);
                final HashSet<Code> permutations = possiblePermutations(response);
                final int responseScore = permutations.size();
                guessScore = Math.max(guessScore, responseScore);
            }
        }

        // code with lowest score
        return scores.firstEntry().getValue();
    }
}
