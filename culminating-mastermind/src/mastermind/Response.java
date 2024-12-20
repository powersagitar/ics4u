package src.mastermind;

import java.util.HashMap;

public class Response {
    public enum KeyPeg {
        Correct, Misplaced;
    }

    private final HashMap<KeyPeg, Integer> response;

    public Response(final HashMap<KeyPeg, Integer> response) {
        this.response = response;
    }

    public Response(final Code code, final Code guess) {
        HashMap<KeyPeg, Integer> responseBuilder = new HashMap<>(KeyPeg.values().length);
        HashMap<Code.Color, Integer> codeOccurrences = code.getOccurrences();

        for (int i = 0; i < Mastermind.CODE_LENGTH; ++i) {
            final Code.Color color = guess.getColor(i);

            if (code.getColor(i) == color) {
                responseBuilder.put(KeyPeg.Correct, responseBuilder.getOrDefault(KeyPeg.Correct, 0) + 1);
            } else if (codeOccurrences.get(color) > 0) {
                responseBuilder.put(KeyPeg.Misplaced, responseBuilder.getOrDefault(KeyPeg.Misplaced, 0) + 1);
                codeOccurrences.put(color, codeOccurrences.get(color) - 1);
            }
        }

        response = responseBuilder;
    }

    /**
     * Validates the response by counting the number of correct and misplaced key
     * pegs.
     *
     * @return a Tuple2 containing two integers:
     * - The first integer represents the count of correct key pegs.
     * - The second integer represents the count of misplaced key pegs.
     */
    public Tuple2<Integer, Integer> getResponse() {
        final int correctCount = this.response.getOrDefault(KeyPeg.Correct, 0);
        final int misplacedCount = this.response.getOrDefault(KeyPeg.Misplaced, 0);

        return new Tuple2<>(correctCount, misplacedCount);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        final Response otherResponse = (Response) obj;

        return this.response.equals(otherResponse.response);
    }

    @Override
    public int hashCode() {
        return this.response.hashCode();
    }
}
