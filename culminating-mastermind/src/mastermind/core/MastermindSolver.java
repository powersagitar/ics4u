package src.mastermind.core;

import src.mastermind.utils.*;

public abstract class MastermindSolver {
    public enum Status {
        Win, Lose, Continue
    }

    protected int attempts = 0;
    protected Code previousGuess = null;

    /**
     * First guess the solver will make
     */
    public abstract Code guess();

    /**
     * Subsequent guesses the solver will make
     */
    public abstract Tuple2<Status, Code> guess(final Response response);
}
