package src.mastermind;

import java.util.Scanner;

import src.mastermind.core.*;
import src.mastermind.utils.*;

public class Mastermind {
    public final static int TOTAL_COLORS = 6;
    public final static int CODE_LENGTH = 4;
    public final static int MAX_GUESSES = 10;

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        solve();
    }

    private static void solve() {
        MastermindSolver solver = new DonaldKnuthAlgorithm();
        MastermindSolver.Status status = MastermindSolver.Status.Continue;
        Code guess = solver.guess();

        while (status == MastermindSolver.Status.Continue) {
            System.out.println("Guess: " + guess.getColors());
            System.out.print("Response: ");

            int correctCount = 0;
            int misplacementCount = 0;

            for (final char c : scanner.nextLine().trim().toCharArray()) {
                switch (c) {
                    case 'c':
                        ++correctCount;
                        break;

                    case 'm':
                        ++misplacementCount;
                        break;
                }
            }

            final Response response = new Response(new Tuple2<>(correctCount, misplacementCount));

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
