package mastermind.core.solvers;

import mastermind.Mastermind;
import mastermind.core.*;
import mastermind.utils.*;
import java.util.*;

public class MediumAlgorithm extends MastermindAlgorithm {
    private Code previousGuess = null;
    private HashSet<Code> permutations;

    public MediumAlgorithm() {
        generatePermutations();
    }

    private void generatePermutations() {
        final int possibilities = (int) Math.pow(Mastermind.TOTAL_COLORS, Mastermind.CODE_LENGTH);
       permutations = new HashSet<>();
       for (int i = 0; i < possibilities; i++) {
           final ArrayList<Integer> codeInDigits = MathUtil.digitsFromBase(i, Mastermind.TOTAL_COLORS, Mastermind.CODE_LENGTH);
           final Code code = new Code(codeInDigits);
           permutations.add(code);
       }
    }

    private void reducePermutations(final Response response){
        this.permutations.removeIf(permutation -> {
            final Response testResponse = new Response(permutation, this.previousGuess);
            return !response.equals(testResponse);
        });
    }

    private Code findNextGuess(int numAttempts) {
        int size = permutations.size();
        if (numAttempts < 4) {
            return new Code(Arrays.asList(numAttempts, numAttempts, numAttempts, numAttempts));
        }
        for (Code code : permutations) {
            return code;
        }

        return null;
    }

    public Code guess() {
        if (!isInitialGuess()) {
            throw new IllegalCallerException("guess() is meant for the first guess.");
        }

        isLosing();

        final Code nextGuess = new Code(Arrays.asList(0, 0, 0, 0));
        previousGuess = nextGuess;

        return nextGuess;
    }

    public Tuple2<Status, Code> guess(final Response response) {
        if (isInitialGuess()) {
            throw new IllegalCallerException("guess(Response) is meant for subsequent guesses.");
        }

        final Tuple2<Integer, Integer> validation = response.getResponse();
        final int correctCount = validation.first;

        if (correctCount >= Mastermind.CODE_LENGTH) {
            return new Tuple2<>(Status.Win, previousGuess);
        } else if (isLosing()) {
            return new Tuple2<>(Status.Lose, previousGuess);
        }

        reducePermutations(response);
        final Code nextGuess = findNextGuess(getAttempts());
        previousGuess = nextGuess;

        return new Tuple2<>(Status.Continue, nextGuess);
    }

}
