import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.TreeMap;

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

        final Code nextGuess = new Code(Arrays.asList(0, 0, 1, 1));
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
    public Tuple2<Status, Code> guess(final Response response) {
        if (attempts <= 0) {
            throw new IllegalCallerException("guess(Response) is meant for subsequent guesses.");
        }

        final Tuple3<ArrayList<Integer>, ArrayList<Integer>, ArrayList<Integer>> validation = response.validate();
        final ArrayList<Integer> correctIndices = validation.first;
        final ArrayList<Integer> misplacedIndices = validation.second;
        final ArrayList<Integer> incorrectIndices = validation.third;

        if (correctIndices.size() >= Mastermind.CODE_LENGTH) {
            return new Tuple2<>(Status.Win, previousGuess);
        } else if (attempts >= Mastermind.MAX_GUESSES) {
            return new Tuple2<>(Status.Lose, previousGuess);
        }

        reducePermutations(permutations, previousGuess, correctIndices, misplacedIndices, incorrectIndices);

        ++attempts;

        final Code nextGuess = findNextGuess();
        previousGuess = nextGuess;

        return new Tuple2<>(Status.Continue, nextGuess);
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
     * such that xj != yj, but xj = yk for some k and yk has not been used in
     * another
     * hit.
     *
     * @param correctIndices
     * @param misplacedIndices
     */
    private static void reducePermutations(HashSet<Code> permutations, final Code previousGuess,
            final ArrayList<Integer> correctIndices,
            final ArrayList<Integer> misplacedIndices,
            final ArrayList<Integer> incorrectIndices) {
        // todo: incorrect logic, not removing permutations correctly

        permutations.removeIf(permutation -> {
            // remove permutations that don't have the correct pegs in the correct indices
            for (final int index : correctIndices) {
                if (permutation.getColor(index) != previousGuess.getColor(index)) {
                    return true;
                }
            }

            // remove permutations that have the misplaced pegs in the misplaced indices
            for (final int index : misplacedIndices) {
                if (permutation.getColor(index) == previousGuess.getColor(index)) {
                    return true;
                }
            }

            // remove permutations that have the incorrect pegs in the incorrect indices
            for (final int index : incorrectIndices) {
                if (permutation.getColor(index) == previousGuess.getColor(index)) {
                    return true;
                }
            }

            return false;
        });
    }

    /**
     * Step 6
     * Find the best guess from the remaining permutations.
     *
     * @return The best guess.
     */
    private Code findNextGuess() {
        TreeMap<Integer, Code> scores = new TreeMap<>();

        for (final Code guess : permutations) {
            int score = Integer.MIN_VALUE;

            for (final Code assumption : permutations) {
                @SuppressWarnings("unchecked")
                HashSet<Code> permutationsClone = (HashSet<Code>) permutations.clone();

                final Response response = new Response(assumption, guess);
                final Tuple3<ArrayList<Integer>, ArrayList<Integer>, ArrayList<Integer>> validation = response
                        .validate();
                final ArrayList<Integer> correctIndices = validation.first;
                final ArrayList<Integer> misplacedIndices = validation.second;
                final ArrayList<Integer> incorrectIndices = validation.third;

                reducePermutations(permutationsClone, assumption, correctIndices, misplacedIndices, incorrectIndices);

                score = Math.max(score, permutationsClone.size());
            }

            scores.put(score, guess);
        }

        // for (final Code codeword : permutations) {
        // // for (final Code testPattern : permutations) {
        // // HashSet<Code> permutationsClone = (HashSet<Code>) permutations.clone();

        // // Response response = new Response(codeword, testPattern);
        // // Tuple<ArrayList<Integer>, ArrayList<Integer>> validation =
        // // response.validate();
        // // ArrayList<Integer> correctIndices = validation.first;
        // // ArrayList<Integer> misplacedIndices = validation.second;

        // // reducePermutations(permutationsClone, testPattern, correctIndices,
        // // misplacedIndices);
        // // }

        // // final HashMap<Code.Color, Integer> codewordOccurrences =
        // // codeword.getOccurrences();
        // // int hits = 0;
        // // int misses = 0;

        // // for (final Code testPattern : permutations) {
        // // final HashMap<Code.Color, Integer> testPatternOccurrences =
        // // testPattern.getOccurrences();

        // // for (final Code.Color color : Code.Color.values()) {
        // // hits += Math.min(codewordOccurrences.get(color),
        // // testPatternOccurrences.get(color));
        // // misses += Math.max(codewordOccurrences.get(color) -
        // // testPatternOccurrences.get(color), 0);
        // // }
        // // }
        // }

        // code with lowest score
        return scores.firstEntry().getValue();
    }
}
