package src.mastermind.core.solvers;

import src.mastermind.utils.*;
import src.mastermind.core.*;
import src.mastermind.Mastermind;

public abstract class MastermindSolver {
    public enum Status {
        Win, Lose, Continue
    }

    private int attempts = 0;

    /**
     * Whether the solver guesses the code within {@link Mastermind#MAX_GUESSES}.
     *
     * <p>
     * To ensure {@code isLosing} checks the losing status correctly, the method
     * must be called by both {@link MastermindSolver#guess()} and
     * {@link MastermindSolver#guess(Response)}.
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

    /**
     * First guess the solver will make
     */
    public abstract Code guess();

    /**
     * Subsequent guesses the solver will make
     */
    public abstract Tuple2<Status, Code> guess(final Response response);
}
