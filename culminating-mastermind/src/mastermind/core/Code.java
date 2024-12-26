package src.mastermind.core;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Random;
import java.util.function.Function;

import src.mastermind.Mastermind;

public class Code {
    public enum Color {
        Green, Red, Blue, Yellow, Orange, Purple;

        /**
         * Retrieves the {@code Color} constant at the specified index.
         *
         * @param index the zero-based index of the {@code Color} constant to retrieve.
         * @return the {@code Color} constant at the given index.
         * @throws ArrayIndexOutOfBoundsException if the specified index is out of range.
         */
        public static Color fromIndex(final int index) {
            return Color.values()[index];
        }

        /**
         * Converts the zero-based index of a {@code Color} constant to its string representation.
         *
         * @param index the zero-based index of the {@code Color} constant.
         * @return the string representation of the {@code Color} constant at the specified index.
         * @throws ArrayIndexOutOfBoundsException if the index is out of range for the {@code Color} values.
         */
        public static String toString(final int index) {
            return fromIndex(index).toString();
        }
    }

    private final ArrayList<Color> code;

    /**
     * Generates a random {@code Code} instance that satisfies the provided filters.
     *
     * <p>
     * This method generates random sequences of colors, represented as a {@code Code}, and
     * evaluates each sequence against the provided filters. A filter is a {@link Function}
     * that takes a {@code Code} as input and returns {@code true} if the code satisfies a
     * specific rule and {@code false} otherwise. The method continues generating random codes
     * until one satisfies all the filters.
     * </p>
     *
     * @param filters a {@link List} of {@link Function} instances, where each function represents
     *                a condition the generated {@code Code} must satisfy.
     * @return a {@code Code} instance that passes all the provided filters.
     */
    public static Code generateRandomCode(List<Function<Code, Boolean>> filters) {
        final Random random = new Random();

        while (true) {
            ArrayList<Integer> codeBuilder = new ArrayList<>(Mastermind.CODE_LENGTH);

            for (int i = 0; i < Mastermind.CODE_LENGTH; ++i) {
                final int randomColor = random.nextInt(Mastermind.TOTAL_COLORS);
                codeBuilder.add(randomColor);
            }

            final Code code = new Code(codeBuilder);
            boolean satisfiesAllFilters = true;

            for (final Function<Code, Boolean> filter : filters) {
                if (!filter.apply(code)) {
                    satisfiesAllFilters = false;
                    break;
                }
            }

            if (satisfiesAllFilters) {
                return code;
            }
        }
    }

    /**
     * Constructs a {@code Code} object from a list of integer values representing colors.
     * Each integer in the provided list corresponds to a color, which is mapped to the
     * appropriate {@link Color} enum using the {@link Color#fromIndex(int)} method.
     *
     * @param code a {@link List} of integers, where each integer is the index of a {@code Color}.
     *             The indices must be within the range of valid {@code Color} values.
     * @throws ArrayIndexOutOfBoundsException if any index in the provided list is out of range
     *         for the {@code Color} enum.
     */
    public Code(final List<Integer> code) {
        ArrayList<Color> codeBuilder = new ArrayList<>(Mastermind.CODE_LENGTH);

        for (final int color : code) {
            codeBuilder.add(Color.fromIndex(color));
        }

        this.code = codeBuilder;
    }

    /**
     * Retrieves the {@code Color} at the specified position in the code sequence.
     *
     * @param index the zero-based index of the {@code Color} to retrieve.
     * @return the {@code Color} at the specified index.
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size of the code).
     */
    public Color getColor(final int index) {
        return code.get(index);
    }

    /**
     * Retrieves the list of {@code Color} objects contained in this code sequence.
     *
     * @return an {@link ArrayList} of {@code Color} objects representing the current code sequence.
     */
    public ArrayList<Color> getColors() {
        return code;
    }

    /**
     * Computes the occurrence count of each {@link Color} in the code sequence.
     *
     * <p>
     * This method iterates through the {@link Color} enumeration and initializes a
     * {@link HashMap} with each color as a key and its initial count set to zero. It
     * then iterates through the code sequence, updating the count for each color
     * encountered in the {@link HashMap}.
     * </p>
     *
     * @return a {@link HashMap} where the keys are {@link Color} values and the values
     *         are integers representing the number of occurrences of each color in the
     *         code sequence.
     */
    public HashMap<Color, Integer> getOccurrences() {
        HashMap<Color, Integer> occurrences = new HashMap<>(Mastermind.TOTAL_COLORS);

        for (final Color color : Color.values()) {
            occurrences.put(color, 0);
        }

        for (final Color color : code) {
            occurrences.put(color, occurrences.get(color) + 1);
        }

        return occurrences;
    }
}
