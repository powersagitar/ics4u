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
        // Create an instance of the DonaldKnuthAlgorithm solver
        final DonaldKnuthAlgorithm solver = new DonaldKnuthAlgorithm();

        // Get the first guess from the solver
        final Code firstGuess = solver.guess();

        // Define the expected first guess based on the algorithm's strategy
        final Code expectedFirstGuess = CodeFactory.fromColorIndices(List.of(0, 0, 1, 1));

        // Assert that the first guess matches the expected first guess
        assertEquals(expectedFirstGuess.getColors(), firstGuess.getColors());
    }

    @Test
    void testAllGuesses() {
        // Calculate the total number of possible codes based on the number of colors and code length
        final int possibilities = (int) Math.pow(Mastermind.TOTAL_COLORS, Mastermind.CODE_LENGTH);

        // Iterate over all possible codes
        for (int i = 0; i < possibilities; ++i) {
            // Convert the current index to a list of color indices representing a code
            final List<Integer> colorIndices = MathUtil.digitsFromBase(i, Mastermind.TOTAL_COLORS, Mastermind.CODE_LENGTH);

            // Create a Code object from the list of color indices
            final Code secretCode = CodeFactory.fromColorIndices(colorIndices);

            // Test the solver's ability to guess the secret code
            testGuess(secretCode);
        }
    }

    void testGuess(final Code secretCode) {
        // Create an instance of the DonaldKnuthAlgorithm solver
        final DonaldKnuthAlgorithm solver = new DonaldKnuthAlgorithm();

        // Initialize the result with a status of Continue and the first guess from the solver
        Tuple2<Status, Code> result = new Tuple2<>(Status.Continue, solver.guess());

        // Loop until the solver either solves the code or fails
        while (result.first() == Status.Continue) {
            // Generate a response (hints) based on the secret code and the solver's guess
            final Response hints = new Response(secretCode, result.second());

            // Get the next guess from the solver based on the hints
            result = solver.guess(hints);
        }

        // If the solver fails to solve the code, fail the test
        if (result.first() == Status.Lose) {
            fail("Donald Knuth algorithm failed to solve secret code: " + secretCode);
        }

        // Assert that the solver solved the code within the allowed number of attempts
        // The algorithm should solve the code in at most 6 guesses, but due to the way attempts are counted, it may return 7
        assertTrue(solver.getAttempts() <= 7);
    }
}
