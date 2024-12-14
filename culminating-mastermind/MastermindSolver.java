
/**
 * MastermindSolver.java
 *
 * This class implements the five-guess algorithm.
 * https://en.wikipedia.org/wiki/Mastermind_(board_game)#Worst_case:_Five-guess_algorithm
 */

import java.util.HashSet;
import java.lang.IllegalArgumentException;

public class MastermindSolver {
    public enum Status {
        Win, Lose, Continue
    }

    private final int NUM_COLORS;
    private final int CODE_LENGTH;
    private final int MAX_ATTEMPTS;

    private HashSet<Code.Color[]> permutations;
    private Code.Color[] previousGuess;
    private int attempt = 0;

    public MastermindSolver(final int numColors, final int codeLength, final int maxAttempts) {
        NUM_COLORS = numColors;
        CODE_LENGTH = codeLength;
        MAX_ATTEMPTS = maxAttempts;
        // step 1 of the five-guess algorithm
        permutations = permutePossibleCodes();
    }

    public Tuple<Status, Code.Color[]> attempt() {
        if (attempt >= MAX_ATTEMPTS) {
            return new Tuple<>(Status.Lose, null);
        } else if (attempt > 0) {
            throw new IllegalArgumentException("Please use this overload of attempt() only for the first guess.");
        }

        ++attempt;

        // step 2 & 3 of the five-guess algorithm
        final Code.Color[] guess = Code.senaryToColors(new int[] { 0, 0, 1, 1 });
        previousGuess = guess;
        return new Tuple<>(Status.Continue, guess);
    }

    public Tuple<Status, Code.Color[]> attempt(final Code.KeyPeg[] validation) {
        if (attempt >= MAX_ATTEMPTS) {
            return new Tuple<>(Status.Lose, null);
        } else if (attempt == 0) {
            throw new IllegalArgumentException("Please use this overload of attempt() only after the first guess.");
        }

        if (ArrayUtil.count(validation, Code.KeyPeg.Colored) == CODE_LENGTH) {
            // step 4 of the five-guess algorithm
            return new Tuple<>(Status.Win, null);
        }

        ++attempt;

        // step 5 of the five-guess algorithm
        // remove from S any code that would not give that response of colored and white
        // pegs
        removeInvalidPermutations(validation);

        return new Tuple<>(Status.Continue, null);
    }

    private HashSet<Code.Color[]> permutePossibleCodes() {
        final int possibilities = (int) Math.pow(NUM_COLORS, CODE_LENGTH);
        HashSet<Code.Color[]> permutations = new HashSet<>();

        for (int i = 0; i < possibilities; ++i) {
            int[] digits = MathUtil.digitsFromBase(i, NUM_COLORS, CODE_LENGTH);
            permutations.add(Code.senaryToColors(digits));
        }

        return permutations;
    }

    private void removeInvalidPermutations(final Code.KeyPeg[] validation) {
        // this handles colored pegs
        eliminateColorMismatches(validation);
        // todo: might also have to handle white pegs
    }

    private void eliminateColorMismatches(final Code.KeyPeg[] validation) {
        final int[] correctIndices = ArrayUtil.findIndices(validation,
                Code.KeyPeg.Colored);

        permutations.removeIf(code -> {
            for (int index : correctIndices) {
                if (!code[index].equals(previousGuess[index])) {
                    return true;
                }
            }

            return false;
        });
    }
}
