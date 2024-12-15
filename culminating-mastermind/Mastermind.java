import java.util.Random;
import java.util.Scanner;

public class Mastermind {
    public final static int TOTAL_COLORS = 6;
    public final static int CODE_LENGTH = 4;
    public final static int MAX_GUESSES = 10;

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        solve();
    }

    private static void solve() {
        MastermindSolver solver = new MastermindSolver();
    }
}
