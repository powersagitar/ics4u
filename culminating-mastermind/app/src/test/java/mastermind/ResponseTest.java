package mastermind;

import mastermind.core.Code;
import mastermind.core.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ResponseTest {
    @Test
    void guessAllCorrect() {
        final Code code = new Code(List.of(0, 0, 0, 0));
        final Code guess = new Code(List.of(0, 0, 0, 0));
        final Response response = new Response(code, guess);
        final int expectedCorrectCount = 4;
        final int expectedMisplacementCount = 0;

        assertEquals(expectedCorrectCount, response.getResponse().first);
        assertEquals(expectedMisplacementCount, response.getResponse().second);
    }

    @Test
    void guessPartialCorrectNoMisplacement() {
        final Code code = new Code(List.of(0, 1, 2, 3));
        final Code guess = new Code(List.of(0, 1, 4, 5));
        final Response response = new Response(code, guess);
        final int expectedCorrectCount = 2;
        final int expectedMisplacementCount = 0;

        assertEquals(expectedCorrectCount, response.getResponse().first);
        assertEquals(expectedMisplacementCount, response.getResponse().second);
    }

    @Test
    void guessNoCorrectNoMisplacement() {
        final Code code = new Code(List.of(0, 0, 0, 0));
        final Code guess = new Code(List.of(1, 1, 1, 1));
        final Response response = new Response(code, guess);
        final int expectedCorrectCount = 0;
        final int expectedMisplacementCount = 0;

        assertEquals(expectedCorrectCount, response.getResponse().first);
        assertEquals(expectedMisplacementCount, response.getResponse().second);
    }

    @Test
    void guessPartialCorrectWithMisplacement() {
        final Code code = new Code(List.of(0, 1, 2, 3));
        final Code guess = new Code(List.of(0, 1, 3, 2));
        final Response response = new Response(code, guess);
        final int expectedCorrectCount = 2;
        final int expectedMisplacementCount = 2;

        assertEquals(expectedCorrectCount, response.getResponse().first);
        assertEquals(expectedMisplacementCount, response.getResponse().second);
    }

    @Test
    void guessAllMisplacement() {
        final Code code = new Code(List.of(0, 1, 2, 3));
        final Code guess = new Code(List.of(3, 2, 1, 0));
        final Response response = new Response(code, guess);
        final int expectedCorrectCount = 0;
        final int expectedMisplacementCount = 4;

        assertEquals(expectedCorrectCount, response.getResponse().first);
        assertEquals(expectedMisplacementCount, response.getResponse().second);
    }

    @Test
    void guessAllMisplacementWithDuplicates() {
        final Code code = new Code(List.of(0, 1, 2, 3));
        final Code guess = new Code(List.of(3, 3, 3, 4));
        final Response response = new Response(code, guess);
        final int expectedCorrectCount = 0;
        final int expectedMisplacementCount = 1;

        assertEquals(expectedCorrectCount, response.getResponse().first);
        assertEquals(expectedMisplacementCount, response.getResponse().second);
    }

    @Test
    void guessMisplacementIsCorrect() {
        final Code code = new Code(List.of(0, 1, 2, 3));
        final Code guess = new Code(List.of(0, 0, 0, 0));
        final Response response = new Response(code, guess);
        final int expectedCorrectCount = 1;
        final int expectedMisplacementCount = 0;

        assertEquals(expectedCorrectCount, response.getResponse().first);
        assertEquals(expectedMisplacementCount, response.getResponse().second);
    }
}
