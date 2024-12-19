package src.mastermind;

import java.util.ArrayList;
import java.util.HashMap;

public class Response {
    public enum KeyPeg {
        Correct, Misplaced, Incorrect;

        public static KeyPeg fromChar(final char c) {
            return switch (c) {
                case 'c' -> Correct;
                case 'm' -> Misplaced;
                case 'i' -> Incorrect;
                default -> throw new IllegalArgumentException("Invalid character: " + c);
            };
        }
    }

    private final ArrayList<KeyPeg> response;

    public Response(final Code code, final Code guess) {
        ArrayList<KeyPeg> responseBuilder = new ArrayList<>(Mastermind.CODE_LENGTH);
        HashMap<Code.Color, Integer> codeOccurrences = code.getOccurrences();

        for (int i = 0; i < Mastermind.CODE_LENGTH; ++i) {
            final Code.Color color = guess.getColor(i);

            if (code.getColor(i) == color) {
                responseBuilder.add(KeyPeg.Correct);
            } else if (codeOccurrences.get(color) > 0) {
                responseBuilder.add(KeyPeg.Misplaced);
                codeOccurrences.put(color, codeOccurrences.get(color) - 1);
            } else {
                responseBuilder.add(KeyPeg.Incorrect);
            }
        }

        response = responseBuilder;
    }

    public Response(final ArrayList<Character> response) {
        ArrayList<KeyPeg> responseBuilder = new ArrayList<>(Mastermind.CODE_LENGTH);

        for (final char peg : response) {
            responseBuilder.add(KeyPeg.fromChar(peg));
        }

        this.response = responseBuilder;
    }

    /**
     * Validates the response by counting the number of correct and misplaced key
     * pegs.
     *
     * @return a Tuple2 containing two integers:
     * - The first integer represents the count of correct key pegs.
     * - The second integer represents the count of misplaced key pegs.
     */
    public Tuple2<Integer, Integer> validate() {
        int correctCount = 0;
        int misplacedCount = 0;

        for (int i = 0; i < Mastermind.CODE_LENGTH; ++i) {
            if (response.get(i) == KeyPeg.Correct) {
                ++correctCount;
            } else if (response.get(i) == KeyPeg.Misplaced) {
                ++misplacedCount;
            }
        }

        return new Tuple2<>(correctCount, misplacedCount);
    }
}
