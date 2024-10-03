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
        for (int firstUnsortedElementIdx = 1; firstUnsortedElementIdx < arr.length; ++firstUnsortedElementIdx) {
            final int toBeSwapped = arr[firstUnsortedElementIdx];

            int comparisonIdx = firstUnsortedElementIdx - 1;

            for (; comparisonIdx >= 0 && arr[comparisonIdx] > toBeSwapped; --comparisonIdx) {
                int temp = arr[comparisonIdx];
                arr[comparisonIdx] = arr[comparisonIdx + 1];
                arr[comparisonIdx + 1] = temp;
            }

            arr[comparisonIdx + 1] = toBeSwapped;
        }
    }
}
