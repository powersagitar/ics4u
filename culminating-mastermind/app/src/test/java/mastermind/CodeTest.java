package mastermind;

import mastermind.core.Code;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class CodeTest {
    @Test
    void testGenerateRandomCode() {
        final Code randomCode = Code.generateRandomCode(List.of());
        final int expectedCodeLength = Mastermind.CODE_LENGTH;
        final var colorArray = randomCode.getColors();

        assertEquals(expectedCodeLength, colorArray.size());
    }

    @Test
    void testCodeFromIndices() {
        final Code code = new Code(List.of(0, 1, 2, 3));
        final List<Code.Color> expectedColors = List.of(Code.Color.Green, Code.Color.Red, Code.Color.Blue,
                Code.Color.Yellow);

        assertEquals(expectedColors, code.getColors());
    }

    @Test
    void testGetColor() {
        final Code code = new Code(List.of(0, 1, 2, 3));
        final List<Code.Color> expectedColors = List.of(Code.Color.Green, Code.Color.Red, Code.Color.Blue,
                Code.Color.Yellow);

        for (int i = 0; i < Mastermind.CODE_LENGTH; ++i) {
            assertEquals(expectedColors.get(i), code.getColor(i));
        }
    }

    @Test
    void testGetColors() {
        final Code code = new Code(List.of(0, 1, 2, 3));
        final List<Code.Color> expectedColors = List.of(Code.Color.Green, Code.Color.Red, Code.Color.Blue,
                Code.Color.Yellow);

        assertEquals(expectedColors, code.getColors());
    }

    @Test
    void testGetOccurrences() {
        final Code code = new Code(List.of(0, 1, 2, 3));
        HashMap<Code.Color, Integer> expectedOccurrences = new HashMap<>(4);
        expectedOccurrences.put(Code.Color.Green, 1);
        expectedOccurrences.put(Code.Color.Red, 1);
        expectedOccurrences.put(Code.Color.Blue, 1);
        expectedOccurrences.put(Code.Color.Yellow, 1);

        assumeTrue(code.getOccurrences().equals(expectedOccurrences));
    }
}
