package mastermind.solvers;

import mastermind.Mastermind;
import mastermind.core.Code;
import mastermind.core.CodeFactory;
import mastermind.core.Response;
import mastermind.core.solvers.DonaldKnuthAlgorithm;
import mastermind.core.solvers.Status;
import mastermind.utils.MathUtil;
import mastermind.utils.Tuple2;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DonaldKnuthAlgorithmTest {
    @Test
    void testFirstGuess() {
        final DonaldKnuthAlgorithm solver = new DonaldKnuthAlgorithm();
        final Code firstGuess = solver.guess();
        final Code expectedFirstGuess = CodeFactory.fromColorIndices(List.of(0, 0, 1, 1));

        assertEquals(expectedFirstGuess.getColors(), firstGuess.getColors());
    }

    @Test
    void testAllGuesses() {
        final int possibilities = (int) Math.pow(
            Mastermind.TOTAL_COLORS, Mastermind.CODE_LENGTH);

        for (int i = 0; i < possibilities; ++i) {
            final List<Integer> colorIndices = MathUtil.digitsFromBase(
                i, Mastermind.TOTAL_COLORS, Mastermind.CODE_LENGTH);

            final Code secretCode = CodeFactory.fromColorIndices(colorIndices);

            testGuess(secretCode);
        }
    }

    void testGuess(final Code secretCode) {
        final DonaldKnuthAlgorithm solver = new DonaldKnuthAlgorithm();

        Tuple2<Status, Code> result =
            new Tuple2<>(Status.Continue, solver.guess());

        while (result.first() == Status.Continue) {
            final Response hints = new Response(secretCode, result.second());
            result = solver.guess(hints);
        }

        if (result.first() == Status.Lose) {
            fail("Donald Knuth algorithm failed to solve secret code: " + secretCode);
        }

        // Donald Knuth algorithm relies on a minimax technique, where the
        // score of each potential guess is calculated, and the most number
        // of guesses it would make to solve the code is 5.
        // However, on the event where there's a tie in the scores, the
        // algorithm may make up to 6 guesses.
        // On top of that, solver.getAttempts() is incremented after each
        // guess. So if the algorithm makes 6 guesses, after performing the
        // last one, solver.getAttempts() would return 7.
        assertTrue(solver.getAttempts() <= 7);
    }
}
