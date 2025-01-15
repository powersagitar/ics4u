/*
    Authors: Mohan Dong
    Date: 01/15/2024
    Title: Response.java
 */

package mastermind.core;

import mastermind.Mastermind;
import mastermind.utils.Tuple2;

import java.util.HashMap;

/**
 * Represents the response to a guess in the Mastermind game.
 */
public class Response {
    /**
     * A Tuple2 object containing the counts of correct and misplaced elements.
     * <p>
     * First element: count of correct matches.
     * Second element: count of misplaced elements.
     */
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
        // constructor to response, constructs a response object containing
        // correct and misplaced elements (black and white pegs)
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
     *
     * @param code  the code to be matched, represented as a sequence of colors
     * @param guess the guessed code, represented as a sequence of colors
     */
    public Response(final Code code, final Code guess) {
        // constructor to response, constructs a response object containing
        // correct and misplaced elements (black and white pegs)
        int correctCount = 0;
        int misplacementCount = 0;

        // create a hashmap to store the occurrences of each color in the code
        HashMap<Code.Color, Integer> codeColorOccurrences = code.getOccurrences();

        // count correct placements
        for (int i = 0; i < Mastermind.CODE_LENGTH; ++i) {
            // get the code color at index i
            final Code.Color codeColor = code.getColor(i);
            // get the guess color at index i
            final Code.Color guessColor = guess.getColor(i);

            // if the code color at index i is equal to the guess color at index i
            // increment the correct count and decrement the occurrence of the guess color
            if (codeColor == guessColor) {
                ++correctCount;
                codeColorOccurrences.put(guessColor, codeColorOccurrences.get(guessColor) - 1);
            }
        }

        // count misplacements
        for (int i = 0; i < Mastermind.CODE_LENGTH; ++i) {
            // get the code color at index i
            final Code.Color codeColor = code.getColor(i);
            // get the guess color at index i
            final Code.Color guessColor = guess.getColor(i);

            // if the code color at index i is not equal to the guess color at index i
            // and the occurrence of the guess color is greater than 0
            if (codeColor != guessColor && codeColorOccurrences.get(guessColor) > 0) {
                ++misplacementCount;
                codeColorOccurrences.put(guessColor, codeColorOccurrences.get(guessColor) - 1);
            }
        }

        // set the response to a tuple containing the correct and misplaced counts
        response = new Tuple2<>(correctCount, misplacementCount);
    }

    /**
     * Returns the Tuple2 object containing the counts of correct and
     * misplaced elements.
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
     *
     * @param obj the object to compare with this Response instance
     * @return true if the specified object is equal to this Response instance, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        // check if the two objects are equal: returns true if yes
        if (this == obj) {
            return true;
        }

        // check if the object is null or if the class differs from this class
        // returns false if either condition is true
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        // cast the object to a Response object
        final Response otherResponse = (Response) obj;

        // compare the response field of the two Response objects and returns
        // the boolean result
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
     *
     * <p>
     * "correct count: [value] misplacement count: [value]"
     *
     * @return a string representation of this Response object
     */
    @Override
    public String toString() {
        return "Black: " + this.response.first() + ", White: " + this.response.second();
    }
}
