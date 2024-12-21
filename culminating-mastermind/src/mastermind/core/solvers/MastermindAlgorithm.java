package src.mastermind.core.solvers;

import src.mastermind.core.Code;
import src.mastermind.core.Response;
import src.mastermind.utils.Tuple2;

public abstract class MastermindAlgorithm extends MastermindSolver {
    /**
     * First guess the solver will make
     */
    public abstract Code guess();

    /**
     * Subsequent guesses the solver will make
     */
    public abstract Tuple2<Status, Code> guess(final Response response);
}