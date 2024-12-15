import java.util.ArrayList;

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
     * @return (indices of correct pegs, number of misplaced pegs)
     */
    public Tuple<ArrayList<Integer>, ArrayList<Integer>> validate() {
        ArrayList<Integer> correctIndices = new ArrayList<>(Mastermind.CODE_LENGTH);
        ArrayList<Integer> misplacedIndices = new ArrayList<>(Mastermind.CODE_LENGTH);

        for (int i = 0; i < Mastermind.CODE_LENGTH; ++i) {
            if (response.get(i) == KeyPeg.Correct) {
                correctIndices.add(i);
            } else if (response.get(i) == KeyPeg.Misplaced) {
                misplacedIndices.add(i);
            }
        }

        return new Tuple<>(correctIndices, misplacedIndices);
    }
}
