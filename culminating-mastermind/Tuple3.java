public class Tuple3<T, U, K> extends Tuple2<T, U> {
    public final K third;

    public Tuple3(final T first, final U second, final K third) {
        super(first, second);
        this.third = third;
    }
}
