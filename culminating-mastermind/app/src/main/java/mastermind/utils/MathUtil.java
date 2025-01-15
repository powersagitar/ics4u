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
        // Throw an exception if this class is instantiated, as it is meant to be a utility class
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
        // Initialize an ArrayList to store the digits, with an initial capacity of arrLength
        ArrayList<Integer> digits = new ArrayList<>(arrLength);

        // Extract digits from the number in the specified base
        while (number > 0) {
            // Get the least significant digit by taking the remainder of the number divided by the base
            final int leastSignificantDigit = number % base;
            // Add the least significant digit to the list
            digits.add(leastSignificantDigit);
            // Divide the number by the base to remove the least significant digit
            number /= base;
        }

        // Pad the list with leading zeros if the number of extracted digits is less than arrLength
        while (digits.size() < arrLength) {
            // Add a zero to the list
            digits.add(0);
        }

        // Reverse the list to have the most significant digit at the beginning
        Collections.reverse(digits);

        // Return the list of digits
        return digits;
    }
}
