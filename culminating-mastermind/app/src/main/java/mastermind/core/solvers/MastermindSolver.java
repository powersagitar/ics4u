/*
    Authors: Mohan Dong, Kenneth Chen
    Date: 01/15/2024
    Title: MastermindSolver.java
 */

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
     * Default constructor.
     */
    public MastermindSolver() {
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
        /*If the solver has made the maximum number of guesses, it has lost
        otherwise, it has not lost, and increments the number of attempts */
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
