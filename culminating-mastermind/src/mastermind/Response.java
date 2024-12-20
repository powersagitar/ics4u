package src.mastermind;

import java.util.HashMap;

public class Response {
    public enum KeyPeg {
        Correct, Misplaced;
    }

    //    (correct count, misplacement count)
    private final Tuple2<Integer, Integer> response;

    public Response(final Tuple2<Integer, Integer> response) {
        this.response = response;
    }

    public Response(final Code code, final Code guess) {
        int correctCount = 0;
        int misplacementCount = 0;

        HashMap<Code.Color, Integer> codeOccurrences = code.getOccurrences();

        for (int i = 0; i < Mastermind.CODE_LENGTH; ++i) {
            final Code.Color color = guess.getColor(i);

            if (code.getColor(i) == color) {
                ++correctCount;
            } else if (codeOccurrences.get(color) > 0) {
                ++misplacementCount;
                codeOccurrences.put(color, codeOccurrences.get(color) - 1);
            }
        }

        response = new Tuple2<>(correctCount, misplacementCount);
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
        return this.response;
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
