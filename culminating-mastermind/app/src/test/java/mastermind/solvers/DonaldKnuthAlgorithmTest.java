package mastermind.solvers;

import mastermind.core.Code;
import mastermind.core.Response;
import mastermind.core.solvers.DonaldKnuthAlgorithm;
import mastermind.core.solvers.MastermindSolver;
import mastermind.utils.Tuple2;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DonaldKnuthAlgorithmTest {
    @Test
    void testFirstGuess() {
        final DonaldKnuthAlgorithm solver = new DonaldKnuthAlgorithm();
        final Code firstGuess = solver.guess();
        final Code expectedFirstGuess = new Code(List.of(0, 0, 1, 1));

        assertEquals(expectedFirstGuess.getColors(), firstGuess.getColors());
    }

    @Test
    void testGuesses() {
        // Secret code is purple yellow blue red
        final Code secretCode = new Code(List.of(5, 3, 2, 1));
        final DonaldKnuthAlgorithm solver = new DonaldKnuthAlgorithm();

        Tuple2<MastermindSolver.Status, Code> result = new Tuple2<>(MastermindSolver.Status.Continue, solver.guess());

        while (result.first() == MastermindSolver.Status.Continue) {
            final Response hints = new Response(secretCode, result.second());
            result = solver.guess(hints);
        }

        if (result.first() == MastermindSolver.Status.Lose) {
            fail("Donald Knuth algorithm failed to solve the secret code");
        }

        assertTrue(solver.getAttempts() <= 6);
    }
}
