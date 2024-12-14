public class ArrayUtil {
    public static <T> int count(T[] arr, final T target) {
        int count = 0;

        for (T element : arr) {
            if (element.equals(target)) {
                ++count;
            }
        }

        return count;
    }
}
