import java.util.Random;
import java.util.Scanner;

public class Mastermind {
    private final static int NUM_COLOR = 6;
    private final static int CODE_LENGTH = 4;
    private final static int MAX_GUESSES = 10;

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        solve();
    }

    private static void solve() {
        MastermindSolver solver = new MastermindSolver(NUM_COLOR, CODE_LENGTH, MAX_GUESSES);
        Tuple<MastermindSolver.Status, Code.Color[]> result = solver.attempt();

        while (result.getFirst() == MastermindSolver.Status.Continue) {
            System.out.print("Algorithm guessed: ");
            Debug.print(result.getSecond());
            System.out.println("Enter validation: ");
            Code.KeyPeg[] validation = new Code.KeyPeg[CODE_LENGTH];
            char[] input = scanner.nextLine().trim().toCharArray();

            for (int i = 0; i < CODE_LENGTH; ++i) {
                switch (input[i]) {
                    case 'c':
                        validation[i] = Code.KeyPeg.Colored;
                        break;

                    case 'w':
                        validation[i] = Code.KeyPeg.White;
                        break;

                    default:
                        validation[i] = Code.KeyPeg.None;
                        break;
                }
            }

            result = solver.attempt(validation);
        }

        if (result.getFirst() == MastermindSolver.Status.Win) {
            System.out.print("Algorithm guessed the code: ");
            Debug.print(result.getSecond());
        } else {
            System.out.println("Algorithm failed to guess the code.");
        }
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
