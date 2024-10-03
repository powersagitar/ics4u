import java.util.Arrays;

public class SelectionSort {
    public static void main(String[] args) {
        int[] arr = { 45, 0, -34, 1, -12 };

        selectionSortAscending(arr);
        System.out.println(Arrays.toString(arr));

        selectionSortDescending(arr);
        System.out.println(Arrays.toString(arr));
    }

    static void selectionSortAscending(int[] arr) {
        for (int firstUnsortedElementIdx = 0; firstUnsortedElementIdx < arr.length - 1; ++firstUnsortedElementIdx) {
            int smallestValueIdx = firstUnsortedElementIdx;

            for (int i = firstUnsortedElementIdx + 1; i < arr.length; ++i) {
                if (arr[i] < arr[smallestValueIdx]) {
                    smallestValueIdx = i;
                }
            }

            // swap
            final int temp = arr[smallestValueIdx];
            arr[smallestValueIdx] = arr[firstUnsortedElementIdx];
            arr[firstUnsortedElementIdx] = temp;
        }
    }

    static void selectionSortDescending(int[] arr) {
        for (int firstUnsortedElementIdx = 0; firstUnsortedElementIdx < arr.length - 1; ++firstUnsortedElementIdx) {
            int largestValueIdx = firstUnsortedElementIdx;

            for (int i = firstUnsortedElementIdx + 1; i < arr.length; ++i) {
                if (arr[i] > arr[largestValueIdx]) {
                    largestValueIdx = i;
                }
            }

            // swap
            final int temp = arr[largestValueIdx];
            arr[largestValueIdx] = arr[firstUnsortedElementIdx];
            arr[firstUnsortedElementIdx] = temp;
        }
    }
}
