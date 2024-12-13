
/**
 * MastermindSolver.java
 *
 * This class implements the five-guess algorithm.
 * https://en.wikipedia.org/wiki/Mastermind_(board_game)#Worst_case:_Five-guess_algorithm
 */

import java.util.HashSet;

public class MastermindSolver {
    private final int NUM_COLORS;
    private final int CODE_LENGTH;
    private HashSet<Code.Color[]> permutations;

    public MastermindSolver(final int numColors, final int codeLength) {
        NUM_COLORS = numColors;
        CODE_LENGTH = codeLength;
        // step 1 of the five-guess algorithm
        permutations = permutePossibleCodes();
    }

    public void solve() {
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
}
