package mastermind.core.solvers;

import mastermind.Mastermind;
import mastermind.core.Code;
import mastermind.core.CodeFactory;
import mastermind.core.Response;
import mastermind.utils.MathUtil;
import mastermind.utils.Tuple2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * An implementation of the medium algorithm, which is a modified and
 * less-optimal version of the Donald-Knuth algorithm, for solving the
 * Mastermind game.
 *
 * It will first guess 1 color for all 4 positions for the first 3 attempts.
 * It will reduce the permutations after every guess and response. Then, it
 * will guess the first permutation in the set of remaining permutations.
 */
public class MediumAlgorithm extends MastermindAlgorithm {
    /**
     *
     */
    private Code previousGuess = null;
    private HashSet<Code> permutations;

    /**
     * Constructs a new instance of the `MediumAlgorithm` class.
     * This constructor initializes the algorithm by generating all possible
     * permutations of the secret code, as required by Donald Knuth's Mastermind
     * solving algorithm.
     */
    public MediumAlgorithm() {
        generatePermutations();
    }

    /**
     * Generates all possible permutations of the secret code, and stores it
     * in permutations, which is a HashSet of Code objects.
     */
    private void generatePermutations() {
        final int possibilities = (int) Math.pow(Mastermind.TOTAL_COLORS, Mastermind.CODE_LENGTH);
        permutations = new HashSet<>();
        for (int i = 0; i < possibilities; i++) {
            final ArrayList<Integer> codeInDigits = MathUtil.digitsFromBase(i, Mastermind.TOTAL_COLORS, Mastermind.CODE_LENGTH);
            final Code code = CodeFactory.fromColorIndices(codeInDigits);
            permutations.add(code);
        }
    }

    /**
     * Filters out invalid permutations that do not align with the response
     * feedback.
     * @param response The feedback from the last guess.
     */
    private void reducePermutations(final Response response) {
        this.permutations.removeIf(permutation -> {
            final Response testResponse = new Response(permutation, this.previousGuess);
            return !response.equals(testResponse);
        });
    }

    /**
     * Determines the subsequent guesses for the Mastermind game.
     * If the number of attempts is less than 4, the next guess is determined
     * by the number of attempts made so far. To elaborate, the color from
     * index numAttempts will be guessed for all four positions.
     * Otherwise, the next guess is determined by the first element in the
     * permutations set, which contains all the possible remaining
     * permutations from the set.
     *
     * @param numAttempts The number of attempts made so far.
     * @return The next guess to be made by the algorithm.
     *
     */
    private Code findNextGuess(int numAttempts) {
        if (numAttempts < 4) {
            return CodeFactory.fromColorIndices(List.of(
                numAttempts, numAttempts, numAttempts, numAttempts));
        }
        for (Code code : permutations) { // returns the first element in the
            // set, regardless of efficiency or optimality
            return code;
        }

        return null;
    }

    /**
     * Determines the initial guess for the Mastermind game.
     * @return The initial guess to be made by the algorithm.
     */
    @Override
    public Code guess() {
        if (!isInitialGuess()) {
            throw new IllegalCallerException("guess() is meant for the first guess.");
        }

        hasExceededMaxGuesses();

        final Code nextGuess = CodeFactory.fromColorIndices(List.of(0, 0, 0, 0));
        previousGuess = nextGuess;

        return nextGuess;
    }

    /**
     * Determines the subsequent guesses for the Mastermind game.
     * The algorithm executes the following steps:
     * 1.   Checks if the method is being called correctly for subsequent guesses.
     *      Throws an {@code IllegalCallerException} if called for the initial guess.
     * 2.   Retrieves the validation counts (e.g., correct positions) from the response.
     * 3.   If the number of correct positions equals the secret code length, the game
     *      status is set to Win, and the previous guess is returned.
     *      If the maximum number of guesses has been exceeded, the game status is set to
     *      Lose, and the previous guess is returned.
     *      Otherwise, the algorithm proceeds to the next step.
     *  4.  Filters out invalid permutations that do not align with the response feedback
     *      using the {@code reducePermutations} method.
     *  5.  Finds the next guess using the {@code findNextGuess} method.
     *  6.  Updates the last guess with the next guess and returns a {@code Tuple2}
     *      containing status {@code Status.Continue} and the next guess.
     *
     * @param response The feedback from the last guess.
     * @return  A {@code Tuple2<Status, Code>} object where {@code Status} represents
     *          the current game status (e.g., Win, Lose, Continue), and {@code Code}
     *          represents the next guess to be made.
     * @throws IllegalCallerException   If this method is invoked for the
     *                                  initial guess.
     */
    @Override
    public Tuple2<Status, Code> guess(final Response response) {
        if (isInitialGuess()) {
            throw new IllegalCallerException("guess(Response) is meant for subsequent guesses.");
        }

        final Tuple2<Integer, Integer> validation = response.getResponse();
        final int correctCount = validation.first();

        if (correctCount >= Mastermind.CODE_LENGTH) {
            return new Tuple2<>(Status.Win, previousGuess);
        } else if (hasExceededMaxGuesses()) {
            return new Tuple2<>(Status.Lose, previousGuess);
        }

        reducePermutations(response);
        final Code nextGuess = findNextGuess(getAttempts());
        previousGuess = nextGuess;

        return new Tuple2<>(Status.Continue, nextGuess);
    }

}
