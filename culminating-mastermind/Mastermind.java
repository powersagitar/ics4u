import java.util.Random;

public class Mastermind {
    private final static int NUM_COLOR = 6;
    private final static int CODE_LENGTH = 4;
    private final static int MAX_GUESSES = 10;

    public static void main(String[] args) {
    }

    private static void solve() {
        MastermindSolver solver = new MastermindSolver(NUM_COLOR, CODE_LENGTH);
        solver.solve();
    }

    private static Code.Color[] generateSecretCode() {
        Code.Color[] secretCode = new Code.Color[CODE_LENGTH];
        Random random = new Random();

        for (int i = 0; i < CODE_LENGTH; ++i) {
            secretCode[i] = Code.Color.values()[random.nextInt(NUM_COLOR)];
        }

        return secretCode;
    }
}
