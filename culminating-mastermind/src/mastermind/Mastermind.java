package src.mastermind;

import java.util.Scanner;
import java.util.HashMap;

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
        MastermindSolver.Status status = MastermindSolver.Status.Continue;
        Code guess = solver.guess();

        while (status == MastermindSolver.Status.Continue) {
            System.out.println("Guess: " + guess.getColors());
            System.out.print("Response: ");

            HashMap<Response.KeyPeg, Integer> responseBuilder = new HashMap<>(Response.KeyPeg.values().length);

            for (final char c : scanner.nextLine().trim().toCharArray()) {
                switch (c) {
                    case 'c':
                        responseBuilder.put(Response.KeyPeg.Correct, responseBuilder.getOrDefault(Response.KeyPeg.Correct, 0) + 1);
                        break;

                    case 'm':
                        responseBuilder.put(Response.KeyPeg.Misplaced, responseBuilder.getOrDefault(Response.KeyPeg.Misplaced, 0) + 1);
                        break;
                }
            }

            final Response response = new Response(responseBuilder);

            final Tuple2<MastermindSolver.Status, Code> result = solver.guess(response);
            status = result.first;
            guess = result.second;
        }

        if (status == MastermindSolver.Status.Win) {
            System.out.println("Algorithm wins");
        } else {
            System.out.println("Algorithm loses");
        }
    }
}
