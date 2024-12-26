package src.mastermind.core.solvers;

import src.mastermind.core.*;
import src.mastermind.Mastermind;

public abstract class MastermindSolver {
    public enum Status {
        Win, Lose, Continue
    }

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
     * To ensure {@code isLosing} checks the losing status correctly, the method
     * must be called by both {@link MastermindAlgorithm#guess()} and
     * {@link MastermindAlgorithm#guess(Response)}.
     * </p>
     *
     * @return true if the solver fails to guess the code, and false otherwise
     */
    protected boolean isLosing() {
        if (attempts >= Mastermind.MAX_GUESSES) {
            return true;
        } else {
            ++attempts;
            return false;
        }
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
