/*
    Authors: Mohan Dong, Kenneth Chen
    Date: 01/15/2024
    Title: Tuple2.java
 */

package mastermind.utils;

/**
 * Represents a tuple of two elements.
 *
 * @param first  the first element
 * @param second the second element
 * @param <T>    the type of the first element
 * @param <U>    the type of the second element
 */
public record Tuple2<T, U>(T first, U second) {
    /**
     * Compares this Tuple2 object with the specified object for equality.
     *
     * @param obj the object to be compared for equality with this instance
     * @return true if the specified object is equal to this tuple, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        // Check if the current instance is the same as the object being compared
        if (this == obj) {
            return true;
        }

        // Check if the object being compared is null or not of the same class
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        // Cast the object to Tuple2 to compare the elements
        @SuppressWarnings("unchecked") final Tuple2<T, U> otherTuple = (Tuple2<T, U>) obj;

        // Compare the first and second elements of both tuples for equality
        return this.first.equals(otherTuple.first) && this.second.equals(otherTuple.second);
    }
}
