package mastermind.core.solvers;

import mastermind.Mastermind;
import mastermind.core.Code;
import mastermind.core.Response;
import mastermind.utils.Tuple2;

public class HumanSolver extends MastermindSolver {
    private final Code secretCode;

    /**
     * Constructs a HumanSolver instance with the given secret code.
     *
     * @param secretCode the code to be guessed during the game
     */
    public HumanSolver(final Code secretCode) {
        this.secretCode = secretCode;
    }

    /**
     * Evaluates a guess against the secret code and determines the game status.
     *
     * <p>
     * This method processes a given guess and generates a response indicating the
     * correctness of the guess in terms of the secret code. Based on the number
     * of correct matches and the player's remaining attempts, the method returns
     * a tuple containing the game status and a response object.
     * </p>
     *
     * <p>
     * The game status can be one of the following:
     * - {@code Status.Win}: If the number of correct matches equals the code length.
     * - {@code Status.Lose}: If the maximum number of guesses is reached without success.
     * - {@code Status.Continue}: If the game is ongoing and further attempts are allowed.
     * </p>
     *
     * @param guess the code guessed by the player
     * @return a tuple containing the game status and the response
     */
    public Tuple2<Status, Response> guess(final Code guess) {
        // isLosing is mainly to increment the attempt counter here
        // the losing status is first indicated by
        // getAttempts() == Mastermind.MAX_GUESSES
        if (hasExceededMaxGuesses()) {
            return new Tuple2<>(Status.Lose, null);
        }

        final Response response = new Response(secretCode, guess);
        final int correctCount = response.getResponse().first();

        if (correctCount >= Mastermind.CODE_LENGTH) {
            return new Tuple2<>(Status.Win, response);
        } else if (getAttempts() == Mastermind.MAX_GUESSES) {
            return new Tuple2<>(Status.Lose, null);
        } else {
            return new Tuple2<>(Status.Continue, response);
        }
    }
}