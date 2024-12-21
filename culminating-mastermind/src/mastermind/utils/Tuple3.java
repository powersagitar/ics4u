package src.mastermind.utils;

import java.util.Objects;

public class Tuple3<T, U, K> extends Tuple2<T, U> {
    public final K third;

    public Tuple3(final T first, final U second, final K third) {
        super(first, second);
        this.third = third;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        @SuppressWarnings("unchecked") final Tuple3<T, U, K> otherTuple = (Tuple3<T, U, K>) obj;

        return super.equals(otherTuple) && this.third.equals(otherTuple.third);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.third);
    }
}