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
        throw new IllegalStateException("Factory class should not be instantiated.");
    }

    /**
     * Creates a {@link Code} object from a list of color indices.
     *
     * @param indices List of color indices.
     * @return A {@link Code} object.
     */
    public static Code fromColorIndices(final List<Integer> indices) {
        final List<Code.Color> colors = indices
            .stream()
            .map(Code.Color::fromIndex)
            .toList();

        return new Code(colors);
    }

    /**
     * Creates a random {@link Code} object.
     *
     * @return A random {@link Code} object.
     */
    public static Code getRandom() {
        final Random random = new Random();
        final ArrayList<Integer> codeBuilder = new ArrayList<>(Mastermind.CODE_LENGTH);

        for (int i = 0; i < Mastermind.CODE_LENGTH; ++i) {
            final int randColor = random.nextInt(Mastermind.TOTAL_COLORS);
            codeBuilder.add(randColor);
        }

        return fromColorIndices(codeBuilder);
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
        throws FileNotFoundException {
        final List<String> lines = new ArrayList<>();
        final Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            lines.add(scanner.nextLine());
        }

        scanner.close();

        if (lines.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }

        final Random random = new Random();
        final int randLineNo = random.nextInt(lines.size());
        final String randLine = lines.get(randLineNo);

        if (randLine.length() != Mastermind.CODE_LENGTH) {
            throw new IllegalArgumentException("Invalid code length");
        }

        final char[] chars = randLine.toCharArray();

        final List<Integer> codeBuilder = new ArrayList<>(Mastermind.CODE_LENGTH);

        for (final char c : chars) {
            final int numericValue = Character.getNumericValue(c);

            if (numericValue < 0 || numericValue >= Mastermind.TOTAL_COLORS) {
                throw new IllegalArgumentException("Invalid color index");
            }

            codeBuilder.add(numericValue);
        }

        return fromColorIndices(codeBuilder);
    }
}
