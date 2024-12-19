package src.mastermind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

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

            ArrayList<Character> chars = (ArrayList<Character>) scanner.nextLine().trim().chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toList());

            final Response response = new Response(chars);

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
