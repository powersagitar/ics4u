
/**
 * Two Dimensional Array Exercises
 * Fill the array by rows using keyboard for input, and then print the array in
 * original and transposed order, along with the sum of each row and column.
 *
 * Author: Mohan Dong
 * Date:   09/17/2024
 */

import java.util.Scanner;

public class Array41 {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        final int[][] array = initializeArrayKeyboard();
        printArray(array);
        printArrayTransposed(array);
    }

    /**
     * Initialize a 3x4 2D array using keyboard inputs
     * 
     * @return the initialized array
     */
    static int[][] initializeArrayKeyboard() {
        int[][] array = new int[3][4];

        for (int row = 0; row < array.length; ++row) {
            for (int col = 0; col < array[0].length; ++col) {
                System.out.println("Please enter an integer");
                array[row][col] = scanner.nextInt();
            }
        }

        return array;
    }

    /**
     * Print a 2D array in original order and the sum of each row
     * 
     * @param array to be printed
     */
    static void printArray(final int[][] array) {
        System.out.println("Original:");

        for (int row = 0; row < array.length; ++row) {
            int sum = 0;

            for (int col = 0; col < array[0].length; ++col) {
                System.out.print(array[row][col] + " ");
                sum += array[row][col];
            }

            System.out.println(" sum: " + sum);
        }
    }

    /**
     * Print a 2D array in transposed order and the sum of each column
     * 
     * @param array to be printed
     */
    static void printArrayTransposed(final int[][] array) {
        System.out.println("Transposed:");

        for (int col = 0; col < array[0].length; ++col) {
            int sum = 0;

            for (int row = 0; row < array.length; ++row) {
                System.out.print(array[row][col] + " ");
                sum += array[row][col];
            }

            System.out.println(" sum: " + sum);
        }
    }
}
