package mastermind.core.solvers;

import mastermind.core.Code;
import mastermind.core.Response;
import mastermind.utils.Tuple2;

/**
 * The standard interface for all algorithmic solvers.
 * <p>
 * It extends the {@code MastermindSolver} class and exposes 2
 * methods, {@link #guess()} and {@link #guess(Response)},
 * representing first and subsequent guesses, respectively.
 * <p>
 * The GUI depends on above interface to communicate with the solver.
 */
public abstract class MastermindAlgorithm extends MastermindSolver {
    /**
     * Default constructor.
     */
    public MastermindAlgorithm() {
        super();
    }

    /**
     * This is the first guess an algorithm will make.
     *
     * @return The first guess as an instance of {@code Code}.
     */
    public abstract Code guess();

    /**
     * Produces a new guess based on the provided response from the previous
     * guess.
     *
     * @param response The feedback from the last guess.
     * @return A {@code Tuple2} containing the {@code Status} and the generated
     * {@code Code}.
     */
    public abstract Tuple2<Status, Code> guess(final Response response);
}
