package src.mastermind;

public class Tuple2<T, U> {
    public final T first;
    public final U second;

    public Tuple2(final T first, final U second) {
        this.first = first;
        this.second = second;
    }

    public boolean equals(Tuple2<T, U> tuple) {
        return first.equals(tuple.first) && second.equals(tuple.second);
    }
}
