package mastermind.solvers;

import mastermind.core.Code;
import mastermind.core.CodeFactory;
import mastermind.core.Response;
import mastermind.core.solvers.DonaldKnuthAlgorithm;
import mastermind.core.solvers.Status;
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
    void testGuesses() {
        // Secret code is purple yellow blue red
        final Code secretCode = CodeFactory.fromColorIndices(List.of(5, 3, 2, 1));
        final DonaldKnuthAlgorithm solver = new DonaldKnuthAlgorithm();

        Tuple2<Status, Code> result = new Tuple2<>(Status.Continue, solver.guess());

        while (result.first() == Status.Continue) {
            final Response hints = new Response(secretCode, result.second());
            result = solver.guess(hints);
        }

        if (result.first() == Status.Lose) {
            fail("Donald Knuth algorithm failed to solve the secret code");
        }

        assertTrue(solver.getAttempts() <= 6);
    }
}
