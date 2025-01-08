package mastermind;

import mastermind.core.Code;
import mastermind.core.Response;
import mastermind.utils.Tuple2;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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

    @Test
    void responseFromInt() {
        final Tuple2<Integer, Integer> counts = new Tuple2<>(1, 2);
        final Response response = new Response(counts);

        assertEquals(counts, response.getResponse());
    }

    @Test
    void equals() {
        final Response response1 = new Response(new Tuple2<>(1, 2));
        final Response response2 = new Response(new Tuple2<>(1, 2));
        final Response response3 = new Response(new Tuple2<>(2, 1));

        assertEquals(response1, response2);
        assertEquals(response2, response1);
        assertEquals(response1, response1);
        assertEquals(response2, response2);
        assertEquals(response1.hashCode(), response2.hashCode());

        assertNotEquals(response1, response3);
        assertNotEquals(response2, response3);
        assertNotEquals(response3, response1);
        assertNotEquals(response3, response2);
        assertNotEquals(response1.hashCode(), response3.hashCode());
        assertNotEquals(response2.hashCode(), response3.hashCode());
    }
}
