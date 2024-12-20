package src.mastermind;

import java.util.Objects;

public class Tuple2<T, U> {
    public final T first;
    public final U second;

    public Tuple2(final T first, final U second) {
        this.first = first;
        this.second = second;
    }

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

    @Override
    public int hashCode() {
        return Objects.hash(this.first, this.second);
    }
}
