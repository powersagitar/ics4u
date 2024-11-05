
/**
 * 2D Arrays, Arraylist, Methods, Style, Thinking & Application Test
 *
 * Second part of the test. Probleming solving around 2d arrays and array lists.
 *
 * Author: Mohan Dong
 * Date: 11/05/2024
 * Version: v0.1.0
 */

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Dong_Mohan {
    /**
     * Global scanner instance for reading stdin
     */
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        /**
         * Task 1
         */
        System.out.println("Please enter the number of values to store in the array list:");
        final int numberOfValues = scanner.nextInt();

        printDivider();

        /**
         * Task 2
         */
        final ArrayList<Integer> values = initArrayList(numberOfValues);

        System.out
                .println("The numbers from 1 to " + numberOfValues + ", stored in random order in the array list are:");
        System.out.println(values);

        printDivider();

        /**
         * Task 3, 4, 5
         */
        final int gridDimension = (int) Math.sqrt(numberOfValues);

        final int[][] grid = initGrid(values, gridDimension);

        System.out.println("The values in the array list after storing in a " + gridDimension + "x" + gridDimension
                + " 2D array is:");
        printGrid(grid);

        printDivider();

        /**
         * Task 6.a
         */
        final int[] sums = sumOfCols(grid);

        System.out.println("The sum of the values in each column is:");
        for (int i = 0; i < sums.length; ++i) {
            System.out.println("Col " + (i + 1) + ": " + sums[i]);
        }

        printDivider();

        /**
         * Task 6.b
         */
        final int sumTLBR = sumTopLeftBottomRight(grid);
        final int sumTRBF = sumTopRightBottomLeft(grid);

        System.out.println("The sum of the values in the diagonal order from top left to bottom right is: " + sumTLBR);
        System.out.println("The sum of the values in the diagonal order from top right to bottom left is: " + sumTRBF);
    }

    /**
     * Initialize an ArrayList<Integer> with values from 1 to
     * {@code numberOfValues}, inclusive, in random order.
     *
     * @param numberOfValues the number of values the array list should have
     * @return the instatiated and initialized array list
     */
    static ArrayList<Integer> initArrayList(final int numberOfValues) {
        ArrayList<Integer> values = new ArrayList<Integer>(numberOfValues);

        // generate an ordered array list
        for (int i = 1; i <= numberOfValues; ++i) {
            values.add(i);
        }

        // shuffle the array list to make the order random
        Random random = new Random();

        for (int i = 0; i < numberOfValues; ++i) {
            final int idx1 = random.nextInt(numberOfValues);
            final int idx2 = random.nextInt(numberOfValues);

            final int temp = values.get(idx1);
            values.set(idx1, values.get(idx2));
            values.set(idx2, temp);
        }

        return values;
    }

    /**
     * Initialize a 2D array with {@code values} that has a dimension of
     * {@code dimension} x {@code dimension} in columns.
     *
     * @param values    the values to initialize the 2d array
     * @param dimension the dimensions of the 2d array as in {@code dimension} x
     *                  {@code dimension}
     * @return the initialized 2d array
     */
    static int[][] initGrid(final ArrayList<Integer> values, final int dimension) {
        int[][] grid = new int[dimension][dimension];
        int valueIdx = 0;

        for (int col = 0; col < dimension; ++col) {
            for (int row = 0; row < dimension; ++row) {
                grid[row][col] = values.get(valueIdx);
                ++valueIdx;
            }
        }

        return grid;
    }

    /**
     * Print the 2d array in rows
     *
     * @param grid to be printed
     */
    static void printGrid(final int[][] grid) {
        for (final int[] row : grid) {
            for (final int col : row) {
                System.out.print(col + "\t");
            }
            System.out.println();
        }
    }

    /**
     * Print the visual divider between sections of the test
     */
    static void printDivider() {
        System.out.println();
    }

    /**
     * Calculate the sum of each column of {@code grid}
     *
     * @param grid to be calculated
     * @return an array containing the sum of each column
     */
    static int[] sumOfCols(final int[][] grid) {
        int[] sums = new int[grid.length];

        for (int col = 0; col < grid.length; ++col) {
            int colSum = 0;

            for (int row = 0; row < grid.length; ++row) {
                colSum += grid[row][col];
            }

            sums[col] = colSum;
        }

        return sums;
    }

    /**
     * Calculate the sum of {@code grid} diagonally, from the top left corner to the
     * bottom right corner
     *
     * @param grid to be calculated
     * @return the sum calculated
     */
    static int sumTopLeftBottomRight(final int[][] grid) {
        int sum = 0;

        for (int i = 0; i < grid.length; ++i) {
            sum += grid[i][i];
        }

        return sum;
    }

    /**
     * Calculate the sum of {@code grid} diagonally, from the top right corner to
     * the bottom left corner
     *
     * @param grid to be calculated
     * @return the sum calculated
     */
    static int sumTopRightBottomLeft(final int[][] grid) {
        int sum = 0;

        for (int row = 0, col = grid.length - 1; row < grid.length && col >= 0; ++row, --col) {
            sum += grid[row][col];
        }

        return sum;
    }
}
