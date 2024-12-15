import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class MastermindSolver {
    public enum Status {
        Win, Lose, Continue
    }

    private HashSet<Code> permutations;
    private int attempts = 0;
    private Code previousGuess;

    public MastermindSolver() {
        generatePermutations();
    }

    /**
     * Step 1
     * Generates all possible permutations of the secret code.
     */
    private void generatePermutations() {
        final int possibilities = (int) Math.pow(Mastermind.TOTAL_COLORS, Mastermind.CODE_LENGTH);
        permutations = new HashSet<>(possibilities);

        for (int i = 0; i < possibilities; ++i) {
            final ArrayList<Integer> codeInDigits = MathUtil.digitsFromBase(i, Mastermind.TOTAL_COLORS,
                    Mastermind.CODE_LENGTH);
            final Code code = new Code(codeInDigits);
            permutations.add(code);
        }
    }

    /**
     * Step 2
     * Initial guess
     */
    public Code guess() {
        if (attempts > 0) {
            throw new IllegalCallerException("guess() is meant for the first guess.");
        }

        ++attempts;

        final Code nextGuess = new Code((ArrayList<Integer>) Arrays.asList(0, 0, 1, 1));
        previousGuess = nextGuess;

        return nextGuess;
    }

    /**
     * Step 3-6
     *
     * @param response The response/validation from the previous guess.
     * @return (status, code) where status is the game status and code is the next
     *         guess.
     */
    public Tuple<Status, Code> guess(final Response response) {
        if (attempts <= 0) {
            throw new IllegalCallerException("guess(Response) is meant for subsequent guesses.");
        }

        final Tuple<ArrayList<Integer>, ArrayList<Integer>> validation = response.validate();
        final ArrayList<Integer> correctIndices = validation.first;
        final ArrayList<Integer> misplacedIndices = validation.second;

        if (correctIndices.size() >= Mastermind.CODE_LENGTH) {
            return new Tuple<>(Status.Win, previousGuess);
        } else if (attempts >= Mastermind.MAX_GUESSES) {
            return new Tuple<>(Status.Lose, previousGuess);
        }

        reducePermutations(correctIndices, misplacedIndices);

        ++attempts;

        return null;
    }

    /**
     * Step 5
     * Remove from permutations any code that would not give the same response.
     *
     * Rules:
     * Given the secret codeword: x1 x2 x3 x4,
     * and the test pattern: y1 y2 y3 y4.
     *
     * 1. The number of black hits / KeyPeg.Correct, ie, the number of positions j
     * such that xj = yj.
     *
     * 2. The number of white hits / KeyPeg.Misplaced, ie, the number of positions j
     * such that xj ≠ yj, but xj = yk for some k and yk has not been used in another
     * hit.
     *
     * @param correctIndices
     * @param misplacedIndices
     */
    private void reducePermutations(final ArrayList<Integer> correctIndices,
            final ArrayList<Integer> misplacedIndices) {
        // remove all permutations that don't have the correct pegs in the correct
        // indices
        permutations.removeIf(permutation -> {
            for (final int index : correctIndices) {
                if (permutation.getColor(index) != previousGuess.getColor(index)) {
                    return true;
                }
            }
            return false;
        });

        // remove permutations that have the misplaced pegs in the misplaced indices
        permutations.removeIf(permutation -> {
            for (final int index : misplacedIndices) {
                if (permutation.getColor(index) == previousGuess.getColor(index)) {
                    return true;
                }
            }
            return false;
        });
    }
}
