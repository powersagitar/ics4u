package mastermind.core;

import mastermind.Mastermind;

import java.util.HashMap;
import java.util.List;

/**
 * Represents a code sequence in the Mastermind game.
 */
public class Code {
    /**
     * The code sequence represented as a list of {@code Color} objects.
     */
    private final List<Color> code;

    /**
     * Initializes a new {@code Code} object with the specified code sequence.
     *
     * @param code a list of {@code Color} objects representing the code sequence.
     */
    public Code(final List<Color> code) {
        if (code.size() != Mastermind.CODE_LENGTH) {
            throw new IllegalArgumentException(
                "Code length must be equal to " + Mastermind.CODE_LENGTH);
        }

        this.code = code;
    }

    /**
     * Represents a color in the Mastermind game.
     */
    public enum Color {
        /**
         * The green code in the Mastermind game.
         */
        Green,

        /**
         * The red code in the Mastermind game.
         */
        Red,

        /**
         * The blue code in the Mastermind game.
         */
        Blue,

        /**
         * The yellow code in the Mastermind game.
         */
        Yellow,

        /**
         * The orange code in the Mastermind game.
         */
        Orange,

        /**
         * The purple code in the Mastermind game.
         */
        Purple;

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
    }

    /**
     * Retrieves the {@code Color} at the specified position in the code sequence.
     *
     * @param index the zero-based index of the {@code Color} to retrieve.
     * @return the {@code Color} at the specified index.
     * @throws IndexOutOfBoundsException if the index is out of range (index &lt; 0 || index &gt;= size of the code).
     */
    public Color getColor(final int index) {
        return code.get(index);
    }

    /**
     * Retrieves the list of {@code Color} objects contained in this code sequence.
     *
     * @return an {@link List} of {@code Color} objects representing the current
     * code sequence.
     */
    public List<Color> getColors() {
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
     * are integers representing the number of occurrences of each color in the
     * code sequence.
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

    @Override
    public String toString() {
        return code.toString();
    }
}
