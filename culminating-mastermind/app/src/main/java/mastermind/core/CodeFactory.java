/*
    Authors: Mohan Dong, Kenneth Chen
    Date: 01/15/2024
    Title: CodeFactory.java
 */

package mastermind.core;

import mastermind.Mastermind;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Factory class for creating {@link Code} objects.
 */
public class CodeFactory {
    /**
     * Private constructor to prevent instantiation.
     */
    private CodeFactory() {
        // Prevent instantiation of the factory class, throwing an exception.
        throw new IllegalStateException("Factory class should not be instantiated.");
    }

    /**
     * Creates a {@link Code} object from a list of color indices.
     *
     * @param indices List of color indices.
     * @return A {@link Code} object.
     */
    public static Code fromColorIndices(final List<Integer> indices) {
        // Convert the list of color indices to a list of color objects.
        // Stores in List colors
        final List<Code.Color> colors = indices
            .stream()
            .map(Code.Color::fromIndex)
            .toList();

        return new Code(colors); // Return a new Code object with the list of colors.
    }

    /**
     * Creates a random {@link Code} object.
     *
     * @return A random {@link Code} object.
     */
    public static Code getRandom() {
        // Create a random object.
        final Random random = new Random();
        // Create a list of integers to store the color indices.
        final ArrayList<Integer> codeBuilder = new ArrayList<>(Mastermind.CODE_LENGTH);


        // Generate a random color index and add it to the list of color indices.
        for (int i = 0; i < Mastermind.CODE_LENGTH; ++i) {
            final int randColor = random.nextInt(Mastermind.TOTAL_COLORS); //
            // Generate a random color index, store in randColor
            codeBuilder.add(randColor);
        }

        return fromColorIndices(codeBuilder); // Return a new Code object with the list of color indices.
    }

    /**
     * Creates a random {@link Code} object from a file.
     *
     * @param file File containing the codes.
     * @return A random {@link Code} object.
     * @throws FileNotFoundException    If the file is not found.
     * @throws IllegalArgumentException If the file is empty, the code length
     *                                  is invalid, or the color index is invalid.
     */
    @SuppressWarnings("ExtractMethodRecommender")
    public static Code getRandomFromFile(final File file)
        // throws FileNotFoundException, IllegalArgumentException
        throws FileNotFoundException {
        // Create a list of strings to store the lines of the file.
        final List<String> lines = new ArrayList<>();
        // Create a scanner object to read the file.
        final Scanner scanner = new Scanner(file);

        // Read the lines of the file and store them in the list of strings.
        while (scanner.hasNextLine()) {
            lines.add(scanner.nextLine());
        }

        // Close the scanner.
        scanner.close();

        // If the list of lines is empty, throw an exception.
        if (lines.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }

        // Create a random object.
        final Random random = new Random();
        // Generate a random line number.
        final int randLineNo = random.nextInt(lines.size());
        // Get the random line from the list of lines.
        final String randLine = lines.get(randLineNo);

        // If the length of the random line is not equal to the code length, throw an exception.
        if (randLine.length() != Mastermind.CODE_LENGTH) {
            throw new IllegalArgumentException("Invalid code length");
        }

        // Convert the random line to a character array.
        final char[] chars = randLine.toCharArray();

        // Create a list of integers to store the color indices.
        final List<Integer> codeBuilder = new ArrayList<>(Mastermind.CODE_LENGTH);

        // Iterate over the characters of the random line.
        for (final char c : chars) {
            final int numericValue = Character.getNumericValue(c); // Get the numeric value of the character.

            // If the numeric value is less than 0 or greater than or equal to the total number of colors, throw an exception.
            if (numericValue < 0 || numericValue >= Mastermind.TOTAL_COLORS) {
                throw new IllegalArgumentException("Invalid color index");
            }

            // Add the numeric value to the list of color indices.
            codeBuilder.add(numericValue);
        }

        // Return a new Code object with the list of color indices.
        return fromColorIndices(codeBuilder);
    }
}
