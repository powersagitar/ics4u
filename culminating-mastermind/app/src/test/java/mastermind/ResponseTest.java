package mastermind;

// Import necessary classes from the mastermind.core package
import mastermind.core.Code;
import mastermind.core.CodeFactory;
import mastermind.core.Response;
import mastermind.utils.Tuple2;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ResponseTest {
    // Test method to verify the response when the guess is completely correct
    @Test
    void guessAllCorrect() {
        // Create a code with all colors being the same
        final Code code = CodeFactory.fromColorIndices(List.of(0, 0, 0, 0));
        // Create a guess that matches the code exactly
        final Code guess = CodeFactory.fromColorIndices(List.of(0, 0, 0, 0));
        // Generate a response based on the code and the guess
        final Response response = new Response(code, guess);
        // Define the expected number of correct positions
        final int expectedCorrectCount = 4;
        // Define the expected number of misplaced colors
        final int expectedMisplacementCount = 0;

        // Assert that the number of correct positions matches the expected count
        assertEquals(expectedCorrectCount, response.getResponse().first());
        // Assert that the number of misplaced colors matches the expected count
        assertEquals(expectedMisplacementCount, response.getResponse().second());
    }

    // Test method to verify the response when the guess is partially correct with no misplacements
    @Test
    void guessPartialCorrectNoMisplacement() {
        // Create a code with distinct colors
        final Code code = CodeFactory.fromColorIndices(List.of(0, 1, 2, 3));
        // Create a guess that partially matches the code
        final Code guess = CodeFactory.fromColorIndices(List.of(0, 1, 4, 5));
        // Generate a response based on the code and the guess
        final Response response = new Response(code, guess);
        // Define the expected number of correct positions
        final int expectedCorrectCount = 2;
        // Define the expected number of misplaced colors
        final int expectedMisplacementCount = 0;

        // Assert that the number of correct positions matches the expected count
        assertEquals(expectedCorrectCount, response.getResponse().first());
        // Assert that the number of misplaced colors matches the expected count
        assertEquals(expectedMisplacementCount, response.getResponse().second());
    }

    // Test method to verify the response when the guess has no correct or misplaced colors
    @Test
    void guessNoCorrectNoMisplacement() {
        // Create a code with all colors being the same
        final Code code = CodeFactory.fromColorIndices(List.of(0, 0, 0, 0));
        // Create a guess with completely different colors
        final Code guess = CodeFactory.fromColorIndices(List.of(1, 1, 1, 1));
        // Generate a response based on the code and the guess
        final Response response = new Response(code, guess);
        // Define the expected number of correct positions
        final int expectedCorrectCount = 0;
        // Define the expected number of misplaced colors
        final int expectedMisplacementCount = 0;

        // Assert that the number of correct positions matches the expected count
        assertEquals(expectedCorrectCount, response.getResponse().first());
        // Assert that the number of misplaced colors matches the expected count
        assertEquals(expectedMisplacementCount, response.getResponse().second());
    }

    // Test method to verify the response when the guess is partially correct with some misplacements
    @Test
    void guessPartialCorrectWithMisplacement() {
        // Create a code with distinct colors
        final Code code = CodeFactory.fromColorIndices(List.of(0, 1, 2, 3));
        // Create a guess that partially matches the code with some misplacements
        final Code guess = CodeFactory.fromColorIndices(List.of(0, 1, 3, 2));
        // Generate a response based on the code and the guess
        final Response response = new Response(code, guess);
        // Define the expected number of correct positions
        final int expectedCorrectCount = 2;
        // Define the expected number of misplaced colors
        final int expectedMisplacementCount = 2;

        // Assert that the number of correct positions matches the expected count
        assertEquals(expectedCorrectCount, response.getResponse().first());
        // Assert that the number of misplaced colors matches the expected count
        assertEquals(expectedMisplacementCount, response.getResponse().second());
    }

    // Test method to verify the response when all guessed colors are misplaced
    @Test
    void guessAllMisplacement() {
        // Create a code with distinct colors
        final Code code = CodeFactory.fromColorIndices(List.of(0, 1, 2, 3));
        // Create a guess with all colors misplaced
        final Code guess = CodeFactory.fromColorIndices(List.of(3, 2, 1, 0));
        // Generate a response based on the code and the guess
        final Response response = new Response(code, guess);
        // Define the expected number of correct positions
        final int expectedCorrectCount = 0;
        // Define the expected number of misplaced colors
        final int expectedMisplacementCount = 4;

        // Assert that the number of correct positions matches the expected count
        assertEquals(expectedCorrectCount, response.getResponse().first());
        // Assert that the number of misplaced colors matches the expected count
        assertEquals(expectedMisplacementCount, response.getResponse().second());
    }

    // Test method to verify the response when there are misplacements with duplicate colors
    @Test
    void guessAllMisplacementWithDuplicates() {
        // Create a code with distinct colors
        final Code code = CodeFactory.fromColorIndices(List.of(0, 1, 2, 3));
        // Create a guess with duplicate colors and some misplacements
        final Code guess = CodeFactory.fromColorIndices(List.of(3, 3, 3, 4));
        // Generate a response based on the code and the guess
        final Response response = new Response(code, guess);
        // Define the expected number of correct positions
        final int expectedCorrectCount = 0;
        // Define the expected number of misplaced colors
        final int expectedMisplacementCount = 1;

        // Assert that the number of correct positions matches the expected count
        assertEquals(expectedCorrectCount, response.getResponse().first());
        // Assert that the number of misplaced colors matches the expected count
        assertEquals(expectedMisplacementCount, response.getResponse().second());
    }

    // Test method to verify the response when the guess has one correct color and no displacements
    @Test
    void guessMisplacementIsCorrect() {
        // Create a code with distinct colors
        final Code code = CodeFactory.fromColorIndices(List.of(0, 1, 2, 3));
        // Create a guess with one correct color and the rest incorrect
        final Code guess = CodeFactory.fromColorIndices(List.of(0, 0, 0, 0));
        // Generate a response based on the code and the guess
        final Response response = new Response(code, guess);
        // Define the expected number of correct positions
        final int expectedCorrectCount = 1;
        // Define the expected number of misplaced colors
        final int expectedMisplacementCount = 0;

        // Assert that the number of correct positions matches the expected count
        assertEquals(expectedCorrectCount, response.getResponse().first());
        // Assert that the number of misplaced colors matches the expected count
        assertEquals(expectedMisplacementCount, response.getResponse().second());
    }

    // Test method to verify the creation of a response from a tuple
    @Test
    void responseFromTuple() {
        // Create a tuple with counts of correct and misplaced colors
        final Tuple2<Integer, Integer> counts = new Tuple2<>(1, 2);
        // Generate a response based on the tuple
        final Response response = new Response(counts);

        // Assert that the response matches the tuple
        assertEquals(counts, response.getResponse());
    }

    // Test method to verify the equality of response objects
    @Test
    void equals() {
        // Create response objects with different tuples
        final Response response1 = new Response(new Tuple2<>(1, 2));
        final Response response2 = new Response(new Tuple2<>(1, 2));
        final Response response3 = new Response(new Tuple2<>(2, 1));

        // Assert that response1 equals response2 and vice versa
        assertEquals(response1, response2);
        assertEquals(response2, response1);
        // Assert that response1 equals itself
        assertEquals(response1, response1);
        // Assert that response2 equals itself
        assertEquals(response2, response2);
        // Assert that the hash codes of response1 and response2 are equal
        assertEquals(response1.hashCode(), response2.hashCode());

        // Assert that response1 does not equal response3
        assertNotEquals(response1, response3);
        // Assert that response2 does not equal response3
        assertNotEquals(response2, response3);
        // Assert that response3 does not equal response1
        assertNotEquals(response3, response1);
        // Assert that response3 does not equal response2
        assertNotEquals(response3, response2);
        // Assert that the hash codes of response1 and response3 are not equal
        assertNotEquals(response1.hashCode(), response3.hashCode());
        // Assert that the hash codes of response2 and response3 are not equal
        assertNotEquals(response2.hashCode(), response3.hashCode());
    }
}