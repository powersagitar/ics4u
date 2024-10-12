import java.util.Arrays;

public class InsertionSort {
    public static void main(String[] args) {
        int[] array1 = { 6, 5, 4, 3, 2, 1 };

        insertionSort(array1);

        System.out.println(Arrays.toString(array1));

        int[] array2 = { 5, 6, 3, 1, 4, 2 };

        insertionSort(array2);

        System.out.println(Arrays.toString(array2));
    }

    static void insertionSort(final int[] arr) {
        if (arr.length < 2) {
            return;
        }

        for (int i = 1; i < arr.length; ++i) {
            final int currVal = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > currVal) {
                arr[j + 1] = arr[j];
                --j;
            }

            arr[j + 1] = currVal;
        }
    }
}
