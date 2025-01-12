package mastermind.core.solvers;

import mastermind.core.Code;
import mastermind.core.Response;
import mastermind.utils.Tuple2;

/**
 * An abstract class representing a Mastermind algorithm.
 * <p>
 * It extends the {@code MastermindSolver} class and provides 2 abstract
 * methods, {@code guess()} and {@code guess(Response response)},
 * representing first and subsequent guesses, respectively.
 */
public abstract class MastermindAlgorithm extends MastermindSolver {
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