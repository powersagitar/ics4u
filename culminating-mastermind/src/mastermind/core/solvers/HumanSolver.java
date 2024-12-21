package src.mastermind.core.solvers;

import src.mastermind.Mastermind;
import src.mastermind.core.Code;
import src.mastermind.core.Response;
import src.mastermind.utils.Tuple2;

public class HumanSolver extends MastermindSolver {
    private final Code secretCode;

    public HumanSolver(final Code secretCode) {
        this.secretCode = secretCode;
    }

    public Tuple2<Status, Response> validate(final Code guess) {
        // isLosing is mainly to increment the attempt counter here
        // the losing status is first indicated by
        // getAttempts() == Mastermind.MAX_GUESSES - 1
        if (isLosing()) {
            return new Tuple2<>(Status.Lose, null);
        }

        final Response response = new Response(secretCode, guess);
        final int correctCount = response.getResponse().first;

        if (correctCount >= Mastermind.CODE_LENGTH) {
            return new Tuple2<>(Status.Win, response);
        } else if (getAttempts() == Mastermind.MAX_GUESSES - 1) {
            return new Tuple2<>(Status.Lose, null);
        } else {
            return new Tuple2<>(Status.Continue, response);
        }
    }
}