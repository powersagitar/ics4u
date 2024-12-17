import java.util.ArrayList;
import java.util.HashMap;

public class Response {
    public enum KeyPeg {
        Correct, Misplaced, Incorrect;

        public static KeyPeg fromChar(final char c) {
            switch (c) {
                case 'c':
                    return Correct;

                case 'm':
                    return Misplaced;

                case 'i':
                    return Incorrect;

                default:
                    throw new IllegalArgumentException("Invalid character: " + c);
            }
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
     * Validates the response.
     *
     * @return (indices of correct pegs, indices of misplaced pegs, indices of
     *         incorrect pegs)
     */
    public Tuple3<ArrayList<Integer>, ArrayList<Integer>, ArrayList<Integer>> validate() {
        ArrayList<Integer> correctIndices = new ArrayList<>(Mastermind.CODE_LENGTH);
        ArrayList<Integer> misplacedIndices = new ArrayList<>(Mastermind.CODE_LENGTH);
        ArrayList<Integer> incorrectIndices = new ArrayList<>(Mastermind.CODE_LENGTH);

        for (int i = 0; i < Mastermind.CODE_LENGTH; ++i) {
            if (response.get(i) == KeyPeg.Correct) {
                correctIndices.add(i);
            } else if (response.get(i) == KeyPeg.Misplaced) {
                misplacedIndices.add(i);
            } else {
                incorrectIndices.add(i);
            }
        }

        return new Tuple3<>(correctIndices, misplacedIndices, incorrectIndices);
    }
}
