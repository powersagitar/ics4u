package src.mastermind.core;

import java.util.HashMap;

import src.mastermind.utils.*;
import src.mastermind.Mastermind;

public class Response {
    //    (correct count, misplacement count)
    private final Tuple2<Integer, Integer> response;

    /**
     * Constructs a Response object using the given Tuple2 containing the counts
     * of correct and misplaced elements.
     *
     * @param response a Tuple2 object where:
     *                 - The first element represents the count of correct matches.
     *                 - The second element represents the count of misplaced elements.
     */
    public Response(final Tuple2<Integer, Integer> response) {
        this.response = response;
    }

    /**
     * Constructs a Response object by calculating the number of correctly matched and misplaced elements
     * between the provided code and guess.
     *
     * <p>
     * The constructor evaluates the following:
     * - Matches where both the position and color are correct.
     * - Matches where the color is correct but the position is incorrect.
     * The results are stored as a Tuple2 consisting of the count of correct matches and the count of misplaced elements.
     * </p>
     *
     * @param code  the code to be matched, represented as a sequence of colors
     * @param guess the guessed code, represented as a sequence of colors
     */
    public Response(final Code code, final Code guess) {
        int correctCount = 0;
        int misplacementCount = 0;

        HashMap<Code.Color, Integer> codeColorOccurrences = code.getOccurrences();

        //        count correct
        for (int i = 0; i < Mastermind.CODE_LENGTH; ++i) {
            final Code.Color codeColor = code.getColor(i);
            final Code.Color guessColor = guess.getColor(i);

            if (codeColor == guessColor) {
                ++correctCount;
                codeColorOccurrences.put(guessColor, codeColorOccurrences.get(guessColor) - 1);
            }
        }

        //        count misplacements
        for (int i = 0; i < Mastermind.CODE_LENGTH; ++i) {
            final Code.Color codeColor = code.getColor(i);
            final Code.Color guessColor = guess.getColor(i);

            if (codeColor != guessColor && codeColorOccurrences.get(guessColor) > 0) {
                ++misplacementCount;
                codeColorOccurrences.put(guessColor, codeColorOccurrences.get(guessColor) - 1);
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

    /**
     * Compares this Response object to the specified object for equality.
     *
     * <p>
     * The method first checks if the two objects reference the same memory location; if so, they are considered equal.
     * Next, it checks if the specified object is null or if its class differs from this Response object's class;
     * if either condition is true, they are not considered equal.
     * Finally, it compares the "response" field of the two Response objects for equality.
     * </p>
     *
     * @param obj the object to compare with this Response instance
     * @return true if the specified object is equal to this Response instance, false otherwise
     */
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

    /**
     * Returns the hash code value for this Response object.
     *
     * <p>
     * The hash code is computed based on the underlying `response` field,
     * which is a Tuple2 containing the counts of correct and misplaced matches.
     * This ensures that the hash code is consistent with the definition of
     * equality provided in the `equals` method.
     * </p>
     *
     * @return the hash code value for this Response object
     */
    @Override
    public int hashCode() {
        return this.response.hashCode();
    }

    /**
     * Returns a string representation of the Response object.
     *
     * <p>
     * The returned string includes the count of correctly placed elements (correct count)
     * and the count of misplaced elements (misplacement count), both extracted from the
     * `response` field, formatted in the following structure:
     * </p>
     *
     * <p>
     * "correct count: [value] misplacement count: [value]"
     * </p>
     *
     * @return a string representation of this Response object
     */
    @Override
    public String toString() {
        return "correct count: " + this.response.first + " misplacement count: " + this.response.second;
    }
}
