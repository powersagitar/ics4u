
/**
 * MastermindSolver.java
 *
 * This class implements the five-guess algorithm.
 * https://en.wikipedia.org/wiki/Mastermind_(board_game)#Worst_case:_Five-guess_algorithm
 */

import java.util.HashSet;
import java.util.Iterator;
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
        permutations = generatePermutations();
    }

    public Tuple<Status, Code.Color[]> attempt() {
        if (attempt >= MAX_ATTEMPTS) {
            return new Tuple<>(Status.Lose, null);
        } else if (attempt > 0) {
            throw new IllegalArgumentException("Please use this method only for the first guess.");
        }

        attempt++;

        final Code.Color[] guess = Code.senaryToColors(new int[] { 0, 0, 1, 1 });
        previousGuess = guess;
        return new Tuple<>(Status.Continue, guess);
    }

    public Tuple<Status, Code.Color[]> attempt(final Code.KeyPeg[] validation) {
        if (attempt >= MAX_ATTEMPTS) {
            return new Tuple<>(Status.Lose, null);
        } else if (attempt == 0) {
            throw new IllegalArgumentException("Please use this method only after the first guess.");
        }

        if (ArrayUtil.count(validation, Code.KeyPeg.Colored) == CODE_LENGTH) {
            return new Tuple<>(Status.Win, previousGuess);
        }

        attempt++;

        filterPermutations(validation);
        previousGuess = calculateNextGuess();

        return new Tuple<>(Status.Continue, previousGuess);
    }

    private HashSet<Code.Color[]> generatePermutations() {
        final int possibilities = (int) Math.pow(NUM_COLORS, CODE_LENGTH);
        HashSet<Code.Color[]> permutations = new HashSet<>();

        for (int i = 0; i < possibilities; i++) {
            int[] digits = MathUtil.digitsFromBase(i, NUM_COLORS, CODE_LENGTH);
            permutations.add(Code.senaryToColors(digits));
        }

        return permutations;
    }

    private void filterPermutations(final Code.KeyPeg[] validation) {
        Iterator<Code.Color[]> iterator = permutations.iterator();

        while (iterator.hasNext()) {
            Code.Color[] code = iterator.next();
            if (!isValidCode(previousGuess, code, validation)) {
                iterator.remove();
            }
        }
    }

    private boolean isValidCode(Code.Color[] guess, Code.Color[] code, Code.KeyPeg[] validation) {
        Tuple<Integer, Integer> score = calculateScore(guess, code);
        int coloredPegs = score.getFirst();
        int whitePegs = score.getSecond();

        int expectedColored = ArrayUtil.count(validation, Code.KeyPeg.Colored);
        int expectedWhite = ArrayUtil.count(validation, Code.KeyPeg.White);

        return coloredPegs == expectedColored && whitePegs == expectedWhite;
    }

    private Code.Color[] calculateNextGuess() {
        Code.Color[] bestGuess = null;
        int bestScore = Integer.MAX_VALUE;

        for (Code.Color[] guess : permutations) {
            int worstScore = 0;

            for (Code.Color[] code : permutations) {
                Tuple<Integer, Integer> score = calculateScore(guess, code);
                int unfitCodes = permutations.size() - (score.getFirst() + score.getSecond());
                worstScore = Math.max(worstScore, unfitCodes);
            }

            if (worstScore < bestScore) {
                bestGuess = guess;
                bestScore = worstScore;
            }
        }

        return bestGuess;
    }

    private Tuple<Integer, Integer> calculateScore(final Code.Color[] guess, final Code.Color[] code) {
        int coloredPegs = 0;
        int whitePegs = 0;
        boolean[] usedCode = new boolean[CODE_LENGTH];
        boolean[] usedGuess = new boolean[CODE_LENGTH];

        for (int i = 0; i < CODE_LENGTH; i++) {
            if (guess[i].equals(code[i])) {
                coloredPegs++;
                usedCode[i] = true;
                usedGuess[i] = true;
            }
        }

        for (int i = 0; i < CODE_LENGTH; i++) {
            if (!usedGuess[i]) {
                for (int j = 0; j < CODE_LENGTH; j++) {
                    if (!usedCode[j] && guess[i].equals(code[j])) {
                        whitePegs++;
                        usedCode[j] = true;
                        break;
                    }
                }
            }
        }

        return new Tuple<>(coloredPegs, whitePegs);
    }
}
