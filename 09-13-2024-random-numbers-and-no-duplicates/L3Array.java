
/**
 * Level 3 Array Processing - Random Numbers and No Duplicates
 * Storing numbers without repetition.
 *
 * Author: Mohan Dong
 * Date:   09/13/2024
 */

import java.util.Scanner;
import java.util.HashSet;
import java.util.Random;
import java.util.Arrays;

public class L3Array {
    // global Scanner instance for input handling
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        noDuplicates();
        randomNoDuplicates();
    }

    /**
     * Checks if a target exists in an array using linear search
     *
     * @param array  to check
     * @param target to look for
     * @return whether a target exists or not
     */
    static boolean existsIn(final int[] array, int target) {
        for (final int element : array) {
            if (element == target) {
                return true;
            }
        }

        return false;
    }

    /**
     * Prompt user to enter 6 unique integers and output them
     */
    static void noDuplicates() {
        int[] array = new int[6];

        int i = 0;
        while (i < array.length) {
            System.out.println("Please enter a unique integer: ");
            final int input = scanner.nextInt();

            if (existsIn(array, input)) {
                System.out.println("Integer has been previously entered.");
                continue;
            }

            array[i] = input;

            ++i;
        }

        System.out.print("User has entered: ");
        System.out.println(Arrays.toString(array));
    }

    /**
     * Generate 10 random integers in the range of 1-100 and output them
     */
    static void randomNoDuplicates() {
        final int ELEMENTS = 10;

        HashSet<Integer> uniqueRandomNumbers = new HashSet<Integer>(ELEMENTS);
        Random random = new Random();

        while (uniqueRandomNumbers.size() < ELEMENTS) {
            uniqueRandomNumbers.add(random.nextInt(100) + 1);
        }

        System.out.print("Generated numbers are: ");
        System.out.println(uniqueRandomNumbers);
    }
}
