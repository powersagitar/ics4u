package mastermind.core.solvers;

import mastermind.core.Code;
import mastermind.core.Response;
import mastermind.utils.Tuple2;

public abstract class MastermindAlgorithm extends MastermindSolver {
    /**
     * Generates a new guess based on the algorithm's logic.
     * This method will be implemented by subclasses to determine
     * their specific strategy for producing a guess.
     *
     * <p>
     * This is the first guess an algorithm will make.
     * </p>
     *
     * @return The next guess as an instance of {@code Code}.
     */
    public abstract Code guess();

    /**
     * Produces a new guess based on the provided response from the previous guess.
     * This method uses the feedback encapsulated in the {@code Response} object
     * to refine the algorithm's logic and generate the next guess.
     *
     * <p>
     * This is an abstract method and must be implemented by subclasses to define
     * the strategy for generating the next guess based on prior feedback.
     * </p>
     *
     * <p>
     * This is the subsequent guesses an algorithm will make.
     * </p>
     *
     * @param response The feedback from the last guess, containing information
     *                 used to compute the next guess.
     * @return A {@code Tuple2} containing the {@code Status} and the generated {@code Code}.
     *         The {@code Status} represents the algorithm's state, and {@code Code} is the
     *         next guess for evaluation.
     */
    public abstract Tuple2<Status, Code> guess(final Response response);
}