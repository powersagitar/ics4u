import java.util.Random;

public class DongMohanSnowPlowAlgorithm {

    static final int N_ROWS = 8;
    static final int N_COLS = N_ROWS;

    static final long CLEAR_INTERVAL_MILLIS = 1000;

    // https://stackoverflow.com/a/5762502/20143641
    static final String ANSI_RESET = "\u001B[0m";
    static final String ANSI_GREEN = "\u001B[32m";
    static final String ANSI_YELLOW = "\u001B[33m";
    static final String ANSI_RED = "\u001B[31m";

    // index 0 = number 0, index 1 = number 1, etc.
    static final String[] NUM_COLOR = { ANSI_GREEN, ANSI_YELLOW, ANSI_RED };

    public static void main(String[] args) throws InterruptedException {
        var grid = init();
        print(grid, "Generated grid", true);

        final int firstPlowable = indexOf(grid[0], 1);
        if (firstPlowable < 0) {
            System.out.println("1 is not present in the first row. PLow is not used.");
            System.exit(0);
        }

        Thread.sleep(CLEAR_INTERVAL_MILLIS);

        clear(grid, 0, firstPlowable);

        print(grid, "Plowed grid", true);
    }

    static int[][] init() {
        var grid = new int[N_ROWS][N_COLS];
        final var random = new Random();

        for (int row = 0; row < N_ROWS; ++row) {
            for (int col = 0; col < N_COLS; ++col) {
                final int randomNum = random.nextInt(2) + 1;
                grid[row][col] = randomNum;
            }
        }

        return grid;
    }

    static void print(final int[][] grid, final String caption, final boolean clearScreen) {
        if (clearScreen) {
            System.out.print("\033[H\033[2J");
        }

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

    static int indexOf(final int[] arr, final int key) {
        for (int i = 0; i < arr.length; ++i) {
            if (arr[i] == key) {
                return i;
            }
        }

        return -1;
    }

    static void clear(int[][] grid, final int row, final int col) throws InterruptedException {
        if (!isValidIdx(grid, row, col) || grid[row][col] != 1) {
            return;
        }

        grid[row][col] = 0;

        print(grid, "Turning row " + row + " col " + col, true);

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
