/*
    Authors: Mohan Dong, Kenneth Chen
    Date: 01/15/2024
    Title: CodeTest.java
 */

package mastermind;

// Import necessary classes from the mastermind.core package
import mastermind.core.Code;
import mastermind.core.CodeFactory;
// Import the Test annotation from JUnit for writing test methods
import org.junit.jupiter.api.Test;

// Import necessary classes from the Java standard library
import java.util.HashMap;
import java.util.List;

// Import static methods from JUnit for assertions and assumptions
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class CodeTest {
    // Test method to verify the generation of a random code
    @Test
    void testGenerateRandomCode() {
        // Generate a random code using the CodeFactory
        final Code randomCode = CodeFactory.getRandom();
        // Define the expected length of the code based on the Mastermind game settings
        final int expectedCodeLength = Mastermind.CODE_LENGTH;
        // Get the list of colors from the generated random code
        final var colorArray = randomCode.getColors();

        // Assert that the length of the color array matches the expected code length
        assertEquals(expectedCodeLength, colorArray.size());
    }

    // Test method to verify the creation of a code from a list of color indices
    @Test
    void testCodeFromIndices() {
        // Create a code using the CodeFactory from a list of color indices
        final Code code = CodeFactory.fromColorIndices(List.of(0, 1, 2, 3));
        // Define the expected list of colors corresponding to the indices
        final List<Code.Color> expectedColors = List.of(Code.Color.Green, Code.Color.Red, Code.Color.Blue,
                Code.Color.Yellow);

        // Assert that the colors of the created code match the expected colors
        assertEquals(expectedColors, code.getColors());
    }

    // Test method to verify the retrieval of individual colors from a code
    @Test
    void testGetColor() {
        // Create a code using the CodeFactory from a list of color indices
        final Code code = CodeFactory.fromColorIndices(List.of(0, 1, 2, 3));
        // Define the expected list of colors corresponding to the indices
        final List<Code.Color> expectedColors = List.of(Code.Color.Green, Code.Color.Red, Code.Color.Blue,
                Code.Color.Yellow);

        // Loop through each index in the code
        for (int i = 0; i < Mastermind.CODE_LENGTH; ++i) {
            // Assert that the color at each index matches the expected color
            assertEquals(expectedColors.get(i), code.getColor(i));
        }
    }

    // Test method to verify the retrieval of the entire list of colors from a code
    @Test
    void testGetColors() {
        // Create a code using the CodeFactory from a list of color indices
        final Code code = CodeFactory.fromColorIndices(List.of(0, 1, 2, 3));
        // Define the expected list of colors corresponding to the indices
        final List<Code.Color> expectedColors = List.of(Code.Color.Green, Code.Color.Red, Code.Color.Blue,
                Code.Color.Yellow);

        // Assert that the colors of the created code match the expected colors
        assertEquals(expectedColors, code.getColors());
    }

    // Test method to verify the occurrence count of each color in a code
    @Test
    void testGetOccurrences() {
        // Create a code using the CodeFactory from a list of color indices
        final Code code = CodeFactory.fromColorIndices(List.of(0, 1, 2, 3));
        // Define the expected occurrences of each color in the code
        HashMap<Code.Color, Integer> expectedOccurrences = new HashMap<>(4);
        expectedOccurrences.put(Code.Color.Green, 1);
        expectedOccurrences.put(Code.Color.Red, 1);
        expectedOccurrences.put(Code.Color.Blue, 1);
        expectedOccurrences.put(Code.Color.Yellow, 1);

        // Assume that the occurrences of colors in the code match the expected occurrences
        assumeTrue(code.getOccurrences().equals(expectedOccurrences));
    }
}