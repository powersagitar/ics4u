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

public class EasyAlgorithm extends MastermindAlgorithm {
    private Code previousGuess = null;
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
     *     The algorithm generates a random guess by selecting a random color for
     *     each of the four positions in the code. If the generated guess has
     *     already been made, the algorithm generates a new guess until a unique
     *     guess is found.
     * <p>
     * @return The initial guess to be made by the algorithm.
     */
    @Override
    public Code guess() {
        if (!isInitialGuess()) {
            throw new IllegalCallerException("guess() is meant for the first guess.");
        }

        hasExceededMaxGuesses();

        Code nextGuess = findNextGuess();
        previousGuess = nextGuess;
        previousGuesses.add(previousGuess);

        return nextGuess;
    }

    /**
     * Determines the next guess for the Mastermind game.
     * <p>
     *     The algorithm generates a random guess by selecting a random color for
     *     each of the four positions in the code. If the generated guess has
     *     already been made, the algorithm generates a new guess until a unique
     *     guess is found.
     * <p>
     * @param response The response to the previous guess made by the algorithm.
     * @return A tuple containing the status of the game and the next guess to be
     *         made by the algorithm.
     * @throws IllegalCallerException If this method is invoked for the initial
     *                                guess.
     */
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

        final Code nextGuess = findNextGuess();
        previousGuess = nextGuess;

        return new Tuple2<>(Status.Continue, nextGuess);
    }

    /**
     * Finds the next guess for the Mastermind game by randomly generating a
     * code and checking to ensure that it has not been guessed before.
     * @return The next guess to be made by the algorithm.
     */
    private Code findNextGuess() {
        Code code;
        do {
            code = CodeFactory.fromColorIndices(new ArrayList<>(List.of(
                getRandomNumber(0, 5),
                getRandomNumber(0, 5),
                getRandomNumber(0, 5),
                getRandomNumber(0, 5))));
        } while (previousGuesses.contains(code));
        previousGuesses.add(code);
        return code;

    }

    /**
     * Generates a random number within the specified range.
     * @param low The lower bound of the random number.
     * @param high The upper bound of the random number.
     * @return A random number within the specified range.
     */
    private int getRandomNumber(final int low,final int high) {
        return (int) (Math.random() * (high - low + 1) + low);
    }
}
