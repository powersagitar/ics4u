package mastermind.core.solvers;

import mastermind.Mastermind;
import mastermind.core.*;
import mastermind.utils.*;
import java.util.*;

public class EasyAlgorithm extends MastermindAlgorithm {
    private Code previousGuess = null;
    private Set<Code> previousGuesses;

    /**
     * Constructs a new instance of the `EasyAlgorithm` class.
     * This constructor initializes the algorithm by instantiating a HashMap to store all previous guesses that the algorithm made.
     */
    public EasyAlgorithm() {
        previousGuesses = new HashSet<>();
    }

    /**
     *
     * @return
     */
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

    public Tuple2<MastermindSolver.Status, Code> guess(final Response response) {
        if (isInitialGuess()) {
            throw new IllegalCallerException("guess(Response) is meant for subsequent guesses.");
        }

        final Tuple2<Integer, Integer> validation = response.getResponse();
        final int correctCount = validation.first;

        if (correctCount >= Mastermind.CODE_LENGTH) {
            return new Tuple2<>(MastermindSolver.Status.Win, previousGuess);
        } else if (hasExceededMaxGuesses()) {
            return new Tuple2<>(MastermindSolver.Status.Lose, previousGuess);
        }

        final Code nextGuess = findNextGuess();
        previousGuess = nextGuess;

        return new Tuple2<>(MastermindSolver.Status.Continue, nextGuess);
    }

    private Code findNextGuess() {
        Code code;
        do {
            code = new Code(Arrays.asList(getRandomNumber(0, 5), getRandomNumber(0, 5), getRandomNumber(0, 5), getRandomNumber(0, 5)));
        } while (previousGuesses.contains(code));
        previousGuesses.add(code);
        return code;

    }

    private int getRandomNumber(int low, int high) {
        return (int) (Math.random() * (high - low + 1) + low);
    }
}