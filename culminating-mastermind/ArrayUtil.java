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

    public static <T> int[] findIndices(T[] arr, final T target) {
        int[] indices = new int[arr.length];
        int index = 0;

        for (int i = 0; i < arr.length; ++i) {
            if (arr[i].equals(target)) {
                indices[index] = i;
                ++index;
            }
        }

        int[] shrunkenIndices = new int[index];
        System.arraycopy(indices, 0, shrunkenIndices, 0, index);

        return shrunkenIndices;
    }

    // public static Object[] initializeArray(final int length, Object initialValue)
    // {
    // Object[] arr = new Object[length];

    // for (int i = 0; i < length; ++i) {
    // arr[i] = initialValue;
    // }

    // return arr;
    // }
}
