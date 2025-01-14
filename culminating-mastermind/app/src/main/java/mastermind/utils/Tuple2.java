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
        if (this == obj) {
            return true;
        }

        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        @SuppressWarnings("unchecked") final Tuple2<T, U> otherTuple = (Tuple2<T, U>) obj;

        return this.first.equals(otherTuple.first) && this.second.equals(otherTuple.second);

    }
}
