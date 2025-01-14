package mastermind.utils;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Provides utility methods for mathematical operations.
 */
public class MathUtil {
    /**
     * This class should not be instantiated.
     */
    private MathUtil() {
        throw new IllegalStateException("This class should not be instantiated");
    }

    /**
     * Extracts the digits of a given number in a specified base and returns them in a list,
     * padded with leading zeros to ensure the resulting list has the specified length.
     *
     * @param number The input number to be converted into digits of the specified base.
     * @param base The base in which the number's digits are extracted. 
     * @param arrLength The desired length of the resulting list. If the extracted digits are
     *                  fewer than this length, the list is padded with leading zeros.
     * @return An ArrayList containing the digits of the number in the specified base,
     *         ordered from the most significant digit to the least significant digit.
     */
    public static ArrayList<Integer> digitsFromBase(int number, final int base, final int arrLength) {
        ArrayList<Integer> digits = new ArrayList<>(arrLength);

        while (number > 0) {
            final int leastSignificantDigit = number % base;
            digits.add(leastSignificantDigit);
            number /= base;
        }

        while (digits.size() < arrLength) {
            digits.add(0);
        }

        Collections.reverse(digits);

        return digits;
    }
}
