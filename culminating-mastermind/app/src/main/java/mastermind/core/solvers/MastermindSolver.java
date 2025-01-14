package mastermind.core.solvers;

import mastermind.Mastermind;
import mastermind.core.Response;

/**
 * The ultimate base class, all solvers are derived from this class.
 * <p>
 * It defines a few utility methods that are common to all solvers.
 */
public abstract class MastermindSolver {
    /**
     * The number of attempts made by the solver.
     */
    private int attempts = 0;

    /**
     * Retrieves the number of attempts made by the solver.
     *
     * @return the current number of attempts
     */
    public int getAttempts() {
        return this.attempts;
    }

    /**
     * Whether the solver guesses the code within {@link Mastermind#MAX_GUESSES}.
     *
     * <p>
     * To ensure {@code hasExceededMaxGuesses} checks the losing status
     * correctly, the method must be called by both
     * {@link MastermindAlgorithm#guess()} and
     * {@link MastermindAlgorithm#guess(Response)}.
     *
     * @return true if the solver fails to guess the code, and false otherwise
     */
    protected boolean hasExceededMaxGuesses() {
        if (attempts >= Mastermind.MAX_GUESSES) {
            return true;
        } else {
            ++attempts;
            return false;
        }
    }

    /**
     * The status of the solver after guessing the code.
     */
    public enum Status {
        /**
         * The solver has guessed the code correctly.
         */
        Win,

        /**
         * The solver failed to guess the code.
         */
        Lose,

        /**
         * The solver has made a guess, but the game is not over yet.
         */
        Continue
    }

    /**
     * Whether the guess the solver will make is its first ever guess
     *
     * @return true if the solver will make its first guess, false otherwise
     */
    protected boolean isInitialGuess() {
        return attempts <= 0;
    }
}
