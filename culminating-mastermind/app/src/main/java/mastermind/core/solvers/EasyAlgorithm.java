package mastermind.core.solvers;

import mastermind.Mastermind;
import mastermind.core.Code;
import mastermind.core.CodeFactory;
import mastermind.core.Response;
import mastermind.utils.Tuple2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * An implementation of the MastermindAlgorithm class that provides a simple
 * algorithm for solving the Mastermind game, by randomly choosing codes that
 * have not guessed before.
 */
public class EasyAlgorithm extends MastermindAlgorithm {

    /**
     * The previous guess made by the algorithm.
     */
    private Code previousGuess = null;

    /**
     * A set containing all previous guesses made by the algorithm.
     */
    private final Set<Code> previousGuesses;

    /**
     * Constructs a new instance of the `EasyAlgorithm` class.
     * This constructor initializes the algorithm by instantiating a HashMap to store all previous guesses that the algorithm made.
     */
    public EasyAlgorithm() {
        previousGuesses = new HashSet<>();
    }

    /**
     * This simple guess algorithm generates a random guess that has not been
     * made before.
     * <p>
     * The algorithm generates a random guess by selecting a random color for
     * each of the four positions in the code. If the generated guess has
     * already been made, the algorithm generates a new guess until a unique
     * guess is found.
     *
     * @return The initial guess to be made by the algorithm.
     */
    @Override
    public Code guess() {
        // Check if guess() is called for not the initial guess: should not
        // occur and throws exception
        if (!isInitialGuess()) {
            throw new IllegalCallerException("guess() is meant for the first guess.");
        }

        // Check if the maximum number of guesses has been exceeded
        hasExceededMaxGuesses();

        // Generate a random guess that has not been made before
        Code nextGuess = findNextGuess();
        previousGuess = nextGuess;
        previousGuesses.add(previousGuess);

        return nextGuess; // return the generated guess
    }

    /**
     * Determines the next guess for the Mastermind game.
     * <p>
     * The algorithm generates a random guess by selecting a random color for
     * each of the four positions in the code. If the generated guess has
     * already been made, the algorithm generates a new guess until a unique
     * guess is found.
     *
     * @param response The response to the previous guess made by the algorithm.
     * @return A tuple containing the status of the game and the next guess to be
     * made by the algorithm.
     * @throws IllegalCallerException If this method is invoked for the initial
     *                                guess.
     */
    public Tuple2<Status, Code> guess(final Response response) {
        // checks if guess(Response) is called for the initial guess: should
        // not occur
        if (isInitialGuess()) {
            throw new IllegalCallerException("guess(Response) is meant for subsequent guesses.");
        }

        final Tuple2<Integer, Integer> validation = response.getResponse(); // get the response to the previous guess
        final int correctCount = validation.first(); // get the number of correct colors in the correct positions

        // check if the game has been won or lost and return the appropriate status
        if (correctCount >= Mastermind.CODE_LENGTH) {
            return new Tuple2<>(Status.Win, previousGuess);
        } else if (hasExceededMaxGuesses()) {
            return new Tuple2<>(Status.Lose, previousGuess);
        }

        // generate the next guess
        final Code nextGuess = findNextGuess();
        previousGuess = nextGuess;

        // return the status of the game and the next guess to be made
        return new Tuple2<>(Status.Continue, nextGuess);
    }

    /**
     * Finds the next guess for the Mastermind game by randomly generating a
     * code and checking to ensure that it has not been guessed before.
     *
     * @return The next guess to be made by the algorithm.
     */
    private Code findNextGuess() {
        // Generate a random guess that has not been made before
        Code code;
        do {
            code = CodeFactory.fromColorIndices(new ArrayList<>(List.of(
                getRandomNumber(0, 5),
                getRandomNumber(0, 5),
                getRandomNumber(0, 5),
                getRandomNumber(0, 5))));
        } while (previousGuesses.contains(code));
        // add the generated guess to the set of previous guesses
        previousGuesses.add(code);
        // return the generated guess
        return code;
    }

    /**
     * Generates a random number within the specified range.
     *
     * @param low  The lower bound of the random number.
     * @param high The upper bound of the random number.
     * @return A random number within the specified range.
     */
    private int getRandomNumber(final int low, final int high) {
        return (int) (Math.random() * (high - low + 1) + low); // generate a random number within the specified range
    }
}