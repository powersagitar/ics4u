
/**
 * L1 Array Exercises
 * A program that has a menu of operations to be performed on an array.
 *
 * Author: Mohan Dong
 * Date:   09/11/2024
 */

import java.util.Scanner;

class Array1 {
    // global Scanner instance for input handling
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // the array to be operated
        int[] array = new int[10];

        // initialize array to user inputs
        InitializeArray(array);
        enterFromKeyboard(array);

        while (true) {
            // the menu presented to user
            System.out.println("0:\tExit");
            System.out.println("1:\tCalculate and display the number of whole integers");
            System.out.println("2.\tDisplay the list of inputted integers in the order entered");
            System.out.println("3.\tDisplay the list of inputted integer in reverse order entered");
            System.out.println("4.\tDisplay the sum of all inputted integers");
            System.out.println("5.\tDisplay the average of all inputted integers, rounded to 1 decimal place");
            System.out.println("6.\tDisplay the maximum integer");
            System.out.println("7.\tDisplay the minimum integer");
            System.out.println("8.\tDisplay positions (indices) of a target integer");
            System.out.println("9.\tSort array with bubble sort");
            System.out.println("10.\tSort array with improved bubble sort");
            System.out.println("11.\tSort array with insertion sort");
            System.out.println("12.\tSort array with selection sort");

            System.out.println("Please select a task above:");

            // map user inputs to operations
            switch (scanner.nextInt()) {
                case 1:
                    countWhole(array);
                    break;

                case 2:
                    display(array);
                    break;

                case 3:
                    displayReverse(array);
                    break;

                case 4:
                    sum(array);
                    break;

                case 5:
                    Average(array);
                    break;

                case 6:
                    FindMax(array);
                    break;

                case 7:
                    FindMin(array);
                    break;

                case 8:
                    System.out.println("Please enter the target integer:");
                    Search(array, scanner.nextInt());
                    break;

                case 9:
                    Sort(array);
                    break;

                case 10:
                    ImprovedBubbleSort(array);
                    break;

                case 11:
                    insertionSort_Ascending(array);
                    break;

                case 12:
                    selectionSort_Ascending(array);
                    break;

                default:
                    System.exit(0);
                    break;
            }
        }
    }

    /**
     * Initialize every element in array to (int) -1
     *
     * @param array to be initialized
     */
    private static void InitializeArray(int[] array) {
        for (int i = 0; i < array.length; ++i) {
            array[i] = -1;
        }
    }

    /**
     * Prompt user to enter 10 integers, which are assigned to array
     *
     * @param array to be manipulated
     */
    private static void enterFromKeyboard(int[] array) {
        if (array.length < 10) {
            System.out.println("Array has to be at least 10 elements long");
            System.exit(1);
        }

        for (int pos = 0; pos < 10; ++pos) {
            System.out.println("Please enter an integer");
            array[pos] = scanner.nextInt();
        }
    }

    /**
     * Find and print the number of whole numbers (>=0) in array
     *
     * @param array to be searched
     */
    private static void countWhole(int[] array) {
        int wholeNumbersCount = 0;

        for (final int num : array) {
            if (num >= 0) {
                ++wholeNumbersCount;
            }
        }

        System.out.println("Whole numbers count: " + wholeNumbersCount);
    }

    /**
     * Print every element in array
     *
     * @param array to be printed
     */
    private static void display(int[] array) {
        System.out.println("Inputted values are:");

        for (final int num : array) {
            System.out.println(num);
        }
    }

    /**
     * Print every element in array reversely
     *
     * @param array to be printed
     */
    private static void displayReverse(int[] array) {
        System.out.println("Inputted values in reverse order are:");

        for (int pos = array.length - 1; pos >= 0; --pos) {
            System.out.println(array[pos]);
        }
    }

    /**
     * Calculate the sum of all elements in array
     *
     * @param arrayNum array to be summed
     * @return sum of all elements in array
     */
    private static int sum(int[] arrayNum) {
        int sum = 0;

        for (final int num : arrayNum) {
            sum += num;
        }

        return sum;
    }

    /**
     * Calculate and print the average of all elements in array
     *
     * @param array to be averaged
     */
    private static void Average(int[] array) {
        final double average = sum(array) / array.length;
        final double averageRounded = ((double) (int) (average * 10.0)) / 10.0;

        System.out.println("The average of all entered numbers is: " + averageRounded);
    }

    /**
     * Find and print the maximum element in array
     *
     * @param array to be searched
     */
    private static void FindMax(int[] array) {
        int currentMax = array[0];

        for (int pos = 1; pos < array.length; ++pos) {
            if (array[pos] > currentMax) {
                currentMax = array[pos];
            }
        }

        System.out.println("The maximum number stored in array is: " + currentMax);
    }

    /**
     * Find and print the minimum element in array
     *
     * @param array to be searched
     */
    private static void FindMin(int[] array) {
        int currentMin = array[0];

        for (int pos = 1; pos < array.length; ++pos) {
            if (array[pos] < currentMin) {
                currentMin = array[pos];
            }
        }

        System.out.println("The minimum number stored in array is: " + currentMin);
    }

    /**
     * Find and print a target element's positions in array
     *
     * @param array  to be searched
     * @param target to be searched for
     */
    private static void Search(int[] array, int target) {
        int[] pos = new int[array.length];
        int posIdx = 0;

        for (int i = 0; i < array.length; ++i) {
            if (array[i] == target) {
                pos[posIdx] = i;
                ++posIdx;
            }
        }

        System.out.print("Target " + target + " is found in the following positions: ");

        for (int i = 0; i < posIdx; ++i) {
            System.out.print(pos[i] + " ");
        }

        System.out.println();
    }

    /**
     * Bubble sort to sort array in ascending order
     *
     * @param list to be sorted
     */
    private static void Sort(int[] list) {
        for (int i = 0; i < list.length - 1; ++i) {
            for (int j = 0; j < list.length - 1 - i; ++j) {
                if (list[j] > list[j + 1]) {
                    final int temp = list[j];
                    list[j] = list[j + 1];
                    list[j + 1] = temp;
                }
            }
        }
    }

    /**
     * Bubble sort to sort array in ascending order, with improved time complexity
     *
     * @param list to be sorted
     */
    private static void ImprovedBubbleSort(int[] list) {
        int i = 0;
        boolean swapped = true;

        while (swapped) {
            swapped = false;

            for (int j = 0; j < list.length - 1 - i; ++j) {
                if (list[j] > list[j + 1]) {
                    swapped = true;

                    final int temp = list[j];
                    list[j] = list[j + 1];
                    list[j + 1] = temp;
                }
            }

            ++i;
        }
    }

    private static void insertionSort_Ascending(final int[] list) {
        for (int firstUnsortedElementIdx = 1; firstUnsortedElementIdx < list.length; ++firstUnsortedElementIdx) {
            final int toBeSwapped = list[firstUnsortedElementIdx];

            int comparisonIdx = firstUnsortedElementIdx - 1;

            for (; comparisonIdx >= 0 && list[comparisonIdx] > toBeSwapped; --comparisonIdx) {
                int temp = list[comparisonIdx];
                list[comparisonIdx] = list[comparisonIdx + 1];
                list[comparisonIdx + 1] = temp;
            }

            list[comparisonIdx + 1] = toBeSwapped;
        }
    }

    private static void selectionSort_Ascending(final int[] list) {
        for (int firstUnsortedElementIdx = 0; firstUnsortedElementIdx < list.length - 1; ++firstUnsortedElementIdx) {
            int smallestValueIdx = firstUnsortedElementIdx;

            for (int i = firstUnsortedElementIdx + 1; i < list.length; ++i) {
                if (list[i] < list[smallestValueIdx]) {
                    smallestValueIdx = i;
                }
            }

            // swap
            final int temp = list[smallestValueIdx];
            list[smallestValueIdx] = list[firstUnsortedElementIdx];
            list[firstUnsortedElementIdx] = temp;
        }
    }
}
