package src.mastermind.utils;

import java.util.Objects;

public class Tuple2<T, U> {
    public final T first;
    public final U second;

    /**
     * Constructs a Tuple2 with the specified values for the first and second elements.
     *
     * @param first  the first element of the tuple
     * @param second the second element of the tuple
     */
    public Tuple2(final T first, final U second) {
        this.first = first;
        this.second = second;
    }

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

    /**
     * Returns a hash code value for this Tuple2. The hash code is computed
     * based on the hash codes of the `first` and `second` elements of the tuple.
     * This implementation ensures that equal tuples will have the same hash code.
     * 
     * @return the hash code value of this tuple
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.first, this.second);
    }
}
