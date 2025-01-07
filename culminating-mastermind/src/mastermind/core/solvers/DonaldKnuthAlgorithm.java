package mastermind.core.solvers;

import java.util.*;

import mastermind.Mastermind;
import mastermind.utils.*;
import mastermind.core.*;

public class DonaldKnuthAlgorithm extends MastermindAlgorithm {
    private Code previousGuess = null;
    private HashSet<Code> permutations;

    /**
     * Constructs a new instance of the `DonaldKnuthAlgorithm` class.
     * This constructor initializes the algorithm by generating all possible
     * permutations of the secret code, as required by Donald Knuth's Mastermind solving algorithm.
     */
    public DonaldKnuthAlgorithm() {
        generatePermutations();
    }

    /**
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


    @Override
    public Code guess() {
        if (!isInitialGuess()) {
            throw new IllegalCallerException("guess() is meant for the first guess.");
        }

        hasExceededMaxGuesses();

        final Code nextGuess = new Code(Arrays.asList(0, 0, 1, 1));
        previousGuess = nextGuess;

        return nextGuess;
    }

    /**
     * Produces the next guess in the Mastermind game based on the feedback
     * from the previous guess.
     *
     * <p>
     * This method is only intended for subsequent guesses after the initial guess.
     * It evaluates the feedback provided in the form of a {@code Response} object,
     * determines the current game status, and updates the state of the algorithm.
     * </p>
     *
     * <p>
     * The algorithm executes the following steps:
     * 1. Checks if the method is being called correctly for subsequent guesses.
     *    Throws an {@code IllegalCallerException} if called for the initial guess.
     * 2. Retrieves the validation counts (e.g., correct positions) from the response.
     * 3. If the number of correct positions equals the secret code length
     *    ({@code Mastermind.CODE_LENGTH}), declares the player as a winner by
     *    returning a {@code Tuple2} containing status {@code Status.Win} and the
     *    last guess.
     * 4. If the maximum allowable guesses are exceeded and no match is found,
     *    declares the player as a loser by returning a {@code Tuple2} containing
     *    status {@code Status.Lose} and the last guess.
     * 5. Filters out invalid permutations that do not align with the response
     *    feedback using the {@code reducePermutations} method.
     * 6. Finds the next optimized guess using the {@code findNextGuess} method.
     * 7. Updates the last guess with the next guess and returns a {@code Tuple2}
     *    containing status {@code Status.Continue} and the next guess.
     * </p>
     *
     * @param response The feedback received for the previous guess,
     *                 containing the number of correct positions and other match details.
     * @return A {@code Tuple2<Status, Code>} object where {@code Status} represents
     *         the current game status (e.g., Win, Lose, Continue), and {@code Code}
     *         represents the next guess to be made.
     * @throws IllegalCallerException If this method is invoked for the initial guess.
     */
    @Override
    public Tuple2<Status, Code> guess(final Response response) {
        if (isInitialGuess()) {
            throw new IllegalCallerException("guess(Response) is meant for subsequent guesses.");
        }

        final Tuple2<Integer, Integer> validation = response.getResponse();
        final int correctCount = validation.first;

        if (correctCount >= Mastermind.CODE_LENGTH) {
            return new Tuple2<>(Status.Win, previousGuess);
        } else if (hasExceededMaxGuesses()) {
            return new Tuple2<>(Status.Lose, previousGuess);
        }

        reducePermutations(response);

        final Code nextGuess = findNextGuess();
        previousGuess = nextGuess;

        return new Tuple2<>(Status.Continue, nextGuess);
    }

    /**
     * Remove from permutations any code that would not give that response of colored and white pegs.
     */
    private void reducePermutations(final Response response) {
        this.permutations.removeIf(permutation -> {
            final Response testResponse = new Response(permutation, this.previousGuess);
            return !testResponse.equals(response);
        });
    }

    /**
     * Determines the next best guess for the Mastermind game using the minimax strategy.
     *
     * <p>
     * This method evaluates all possible code permutations and simulates responses
     * against each potential guess to identify the most optimal guess. The strategy
     * aims to minimize the maximum possible size of groups of remaining codes after
     * a guess-response pair. By doing so, the algorithm ensures that the worst-case
     * scenario yields the smallest group of codes, enabling more efficient resolution
     * of the game.
     * </p>
     *
     * <p>
     * The process is as follows:
     * 1. For each possible guess from the current permutations, generate all possible
     *    responses when compared against every possible assumed secret permutation.
     * 2. Calculate the frequency of each response for the given guess.
     * 3. Identify the response with the highest frequency, representing the "max group size"
     *    of remaining codes for that guess.
     * 4. Save the guess along with its maximum group size into a {@code TreeMap}, ensuring
     *    guesses are ranked in ascending order by their maximum group size.
     * 5. Select the guess with the smallest maximum group size as the next optimal guess.
     * </p>
     *
     * <p>
     * The selected code is the one that, in the worst case, eliminates the largest number
     * of invalid permutations, thus improving the efficiency of the algorithm in arriving
     * at the correct solution in fewer steps.
     * </p>
     *
     * <p>
     * Also see this
     * <a href="https://stackoverflow.com/a/62430592/20143641">Stack Overflow Answer</a>.
     * </p>
     *
     * @return The next optimal code to guess, determined by the minimax strategy.
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
