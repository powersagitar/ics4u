
/**
 * Snow Plow Algorithm
 * A minesweeper-like algorithm that recursively turns all 1 and adjacent 1s to 0s.
 *
 * Author: Mohan Dong
 * Date: 10/31/2024
 * Version: v0.1.1
 */

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.function.Function;

public class DongMohanSnowPlowAlgorithm {

    /**
     * The clear interval in milliseconds.
     */
    static final long CLEAR_INTERVAL_MILLIS = 300;

    /**
     * ANSI color codes for console output.
     * https://stackoverflow.com/a/5762502/20143641
     */
    static final String ANSI_RESET = "\u001B[0m";
    static final String ANSI_GREEN = "\u001B[32m";
    static final String ANSI_YELLOW = "\u001B[33m";
    static final String ANSI_RED = "\u001B[31m";

    /**
     * ANSI color codes for numbers in the console.
     * index 0 = number 0, index 1 = number 1, etc.
     */
    static final String[] NUM_COLOR = { ANSI_GREEN, ANSI_YELLOW, ANSI_RED };

    /**
     * Scanner for reading input from the console.
     */
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {
        boolean exit = false;

        while (!exit) {
            // prompt user for number of rows and columns
            final int rows = readInt("Please enter a natural number for the number of rows (input > 0):", x -> x > 0);
            final int cols = readInt("Please enter a natural number for the number of cols (input > 0):", x -> x > 0);

            // initialize grid
            int[][] grid = init(rows, cols);

            // print generated grid
            print(grid, "Generated grid");

            // try to find first 1 in the first row
            final int firstPlowable = indexOf(grid[0], 1);

            if (firstPlowable < 0) {
                System.out.println("1 is not present in the first row. PLow is not used.");
            } else {
                Thread.sleep(CLEAR_INTERVAL_MILLIS);

                // recursively clear the grid
                clear(grid, 0, firstPlowable);

                // print the final plowed grid
                print(grid, "Plowed grid");
            }

            System.out.println("Do you want to re-run the program?");
            System.out.print("\"Y\" for yes and any other key for no.[Y/n]: ");
            if (!scanner.nextLine().trim().toLowerCase().equals("y")) {
                exit = true;
            }
        }
    }

    /**
     * Initializes a 2D grid with random numbers between 1 and 2.
     *
     * @param rows the number of rows
     * @param cols the number of columns
     * @return a 2D grid with random numbers between 1 and 2
     */
    static int[][] init(final int rows, final int cols) {
        int[][] grid = new int[rows][cols];
        final Random random = new Random();

        for (int row = 0; row < rows; ++row) {
            for (int col = 0; col < cols; ++col) {
                final int randomNum = random.nextInt(2) + 1;
                grid[row][col] = randomNum;
            }
        }

        return grid;
    }

    /**
     * Reads an integer from the console with a prompt and a validator.
     *
     * @param prompt    the prompt to display
     * @param validator the validator to check if the input is valid
     * @return the integer read from the console
     */
    static int readInt(final String prompt, Function<Integer, Boolean> validator) {
        clearScreen();

        while (true) {
            System.out.println(prompt);

            final int input;

            try {
                input = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please try again.");
                System.out.println("Please make sure the input is not too big either.");

                // consume token to prevent infinite loop
                // https://stackoverflow.com/q/3572160
                scanner.nextLine();

                continue;
            }

            if (!validator.apply(input)) {
                System.out.println("Invalid input. Please try again.");
                continue;
            }

            // consume line feed to prevent errors
            scanner.nextLine();

            return input;
        }
    }

    /**
     * Returns the index of the key in the array.
     *
     * @param arr the array to search
     * @param key the key to search for
     * @return the index of the key in the array or -1 if not found
     */
    static int indexOf(final int[] arr, final int key) {
        for (int i = 0; i < arr.length; ++i) {
            if (arr[i] == key) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Clears the console screen.
     */
    static void clearScreen() {
        // https://stackoverflow.com/a/32295974/20143641
        System.out.print("\033[H\033[2J");
    }

    /**
     * Prints the 2D grid with a caption.
     *
     * @param grid    the 2D grid to print
     * @param caption the caption to display
     */
    static void print(final int[][] grid, final String caption) {
        clearScreen();

        System.out.println(caption);

        for (int[] row : grid) {
            for (int col : row) {
                System.out.print(NUM_COLOR[col] + col + ANSI_RESET + " ");
            }
            System.out.println();
        }
    }

    /**
     * Checks if the row and column are valid indices in the 2D array.
     *
     * @param arr the 2D array
     * @param row the row index
     * @param col the column index
     * @return true if the row and column are valid indices in the 2D array
     */
    static boolean isValidIdx(final int[][] arr, final int row, final int col) {
        final boolean isValidRow = row > -1 && row < arr.length;
        final boolean isValidCol = col > -1 && isValidRow && col < arr[row].length;

        return isValidRow && isValidCol;
    }

    /**
     * Clears the grid recursively. If grid[row][col] == 1, it will turn it to 0.
     *
     * @param grid the 2D grid to clear
     * @param row  the row index
     * @param col  the column index
     * @throws InterruptedException
     */
    static void clear(int[][] grid, final int row, final int col) throws InterruptedException {
        if (!isValidIdx(grid, row, col) || grid[row][col] != 1) {
            return;
        }

        grid[row][col] = 0;

        print(grid, "Turning row " + row + " col " + col);

        Thread.sleep(CLEAR_INTERVAL_MILLIS);

        clear(grid, row - 1, col); // top
        clear(grid, row + 1, col); // bottom
        clear(grid, row, col - 1); // left
        clear(grid, row, col + 1); // right
        clear(grid, row - 1, col - 1); // top left
        clear(grid, row - 1, col + 1); // top right
        clear(grid, row + 1, col - 1); // bottom left
        clear(grid, row + 1, col + 1); // bottom right
    }
}
