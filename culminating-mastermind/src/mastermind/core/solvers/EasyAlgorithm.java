package mastermind.core.solvers;

import mastermind.Mastermind;
import mastermind.core.*;
import mastermind.utils.*;
import java.util.*;

public class EasyAlgorithm {
    private Code previousGuess = null;
    private ArrayList<Code> permutations;
    private HashSet<Code> previousGuesses = new HashSet<>();

    public EasyAlgorithm() {
        generatePermutations();
    }
    private void generatePermutations() {
        final int possibilities = (int) Math.pow(Mastermind.TOTAL_COLORS, Mastermind.CODE_LENGTH);
        permutations = new ArrayList<>();
        for (int i = 0; i < possibilities; i++) {
            final ArrayList<Integer> codeInDigits = MathUtil.digitsFromBase(i, Mastermind.TOTAL_COLORS, Mastermind.CODE_LENGTH);
            final Code code = new Code(codeInDigits);
            permutations.add(code);
        }
    }

    public Code guess() {
        if (isInitialGuess()) { // not visible to class
            throw new IllegalCallerException("guess() is meant for the first guess.");
        }

        isLosing(); // not visible to class

        final Code nextGuess = new Code(Arrays.asList(0, 0, 1, 1));
        previousGuess = nextGuess;
        previousGuesses.add(previousGuess);

        return nextGuess;
    }

    public Tuple2<MastermindSolver.Status, Code> guess(final Response response) {
        if (isInitialGuess()) { // not visible to class
            throw new IllegalCallerException("guess(Response) is meant for subsequent guesses.");
        }

        final Tuple2<Integer, Integer> validation = response.getResponse();
        final int correctCount = validation.first;

        if (correctCount >= Mastermind.CODE_LENGTH) {
            return new Tuple2<>(MastermindSolver.Status.Win, previousGuess);
        } else if (isLosing()) { // not visible to class
            return new Tuple2<>(MastermindSolver.Status.Lose, previousGuess);
        }

        final Code nextGuess = findNextGuess();
        previousGuess = nextGuess;

        return new Tuple2<>(MastermindSolver.Status.Continue, nextGuess);
    }

    private Code findNextGuess() {
        int size = permutations.size();

        int random = (int) (Math.random() * size);
        while (previousGuesses.contains(permutations.get(random))) {
            random = (int) (Math.random() * size);
        }
        return permutations.get(random);
    }


}
