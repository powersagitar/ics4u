
/**
 * Two Dimensional Array Exercises
 * Fill the array by rows by reading a file, and then print the array in
 * original and transposed order, along with the sum of each row and column.
 *
 * Author: Mohan Dong
 * Date:   09/17/2024
 */

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Array411 {
    public static void main(String[] args) throws FileNotFoundException {
        final int[][] array = initializeArrayFile();
        printArray(array);
        printArrayTransposed(array);
    }

    /**
     * Initialize a 3x4 2D array by reading from a file
     *
     * @return the initialized array
     */
    static int[][] initializeArrayFile() throws FileNotFoundException {
        // program working directory upon execution is /workspaces/ICS4U1
        // file absolute path is
        // /workspaces/ICS4U1/09-16-2024-2d-array-exercises/Matrix1.txt
        Scanner matrix = new Scanner(new File("09-16-2024-2d-array-exercises/Matrix1.txt"));

        final int ROWS = matrix.nextInt();
        final int COLS = matrix.nextInt();

        int[][] array = new int[ROWS][COLS];

        for (int row = 0; row < array.length; ++row) {
            for (int col = 0; col < array[0].length; ++col) {
                array[row][col] = matrix.nextInt();
            }
        }

        matrix.close();

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
