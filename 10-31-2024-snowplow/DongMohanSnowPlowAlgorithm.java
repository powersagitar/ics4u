import java.util.Random;
import java.util.Scanner;
import java.util.function.Function;

public class DongMohanSnowPlowAlgorithm {

    static final long CLEAR_INTERVAL_MILLIS = 300;

    // https://stackoverflow.com/a/5762502/20143641
    static final String ANSI_RESET = "\u001B[0m";
    static final String ANSI_GREEN = "\u001B[32m";
    static final String ANSI_YELLOW = "\u001B[33m";
    static final String ANSI_RED = "\u001B[31m";

    // index 0 = number 0, index 1 = number 1, etc.
    static final String[] NUM_COLOR = { ANSI_GREEN, ANSI_YELLOW, ANSI_RED };

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {
        boolean exit = false;

        while (!exit) {
            final int rows = readInt("Please enter a natural number for the number of rows (input > 0):", x -> x > 0);
            final int cols = readInt("Please enter a natural number for the number of cols (input > 0):", x -> x > 0);

            var grid = init(rows, cols);

            print(grid, "Generated grid");

            final int firstPlowable = indexOf(grid[0], 1);

            if (firstPlowable < 0) {
                System.out.println("1 is not present in the first row. PLow is not used.");
            } else {
                Thread.sleep(CLEAR_INTERVAL_MILLIS);

                clear(grid, 0, firstPlowable);

                print(grid, "Plowed grid");
            }

            System.out.println("Do you want to re-run the program?");
            System.out.print("\"Y\" for yes and any other key for no.[Y/n]: ");
            if (!scanner.nextLine().trim().toLowerCase().equals("y")) {
                exit = true;
            }
        }
    }

    static int[][] init(final int rows, final int cols) {
        var grid = new int[rows][cols];
        final var random = new Random();

        for (int row = 0; row < rows; ++row) {
            for (int col = 0; col < cols; ++col) {
                final int randomNum = random.nextInt(2) + 1;
                grid[row][col] = randomNum;
            }
        }

        return grid;
    }

    static int readInt(final String prompt, Function<Integer, Boolean> validator) {
        clearScreen();

        while (true) {
            System.out.println(prompt);

            final int input = scanner.nextInt();

            if (!validator.apply(input)) {
                System.out.println("Invalid input. Please try again.");
            } else {
                // consume line feed to prevent errors
                scanner.nextLine();

                return input;
            }
        }
    }

    static int indexOf(final int[] arr, final int key) {
        for (int i = 0; i < arr.length; ++i) {
            if (arr[i] == key) {
                return i;
            }
        }

        return -1;
    }

    static void clearScreen() {
        // https://stackoverflow.com/a/32295974/20143641
        System.out.print("\033[H\033[2J");
    }

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

    static boolean isValidIdx(final int[][] arr, final int row, final int col) {
        final boolean isValidRow = row > -1 && row < arr.length;
        final boolean isValidCol = col > -1 && isValidRow && col < arr[row].length;

        return isValidRow && isValidCol;
    }

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
