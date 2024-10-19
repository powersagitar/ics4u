/**
 * ArrayList1
 * This exercise is a modified version of Array Exercise 1,
 * where the array is replaced with an array list
 *
 * Date: 10/21/2024
 * Author: Mohan Dong
 */

import java.util.ArrayList;
import java.util.Scanner;

public class ArrayList1 {

    static Scanner scanner = new Scanner(System.in);
    static final int ARRAY_LIST_CAPACITY = 10;

    public static void main(String[] args) {
        ArrayList<Integer> ints = initializeArrayList(ARRAY_LIST_CAPACITY);
        enterFromKeyboard(ints, ARRAY_LIST_CAPACITY);

        while (true) {
            System.out.println("Available Operations");
            System.out.println("0.\tExit");
            System.out.println(
                "1.\tDisplay the number of whole numbers in array list"
            );
            System.out.println("2.\tDisplay all numbers in array list");
            System.out.println(
                "3.\tDisplay all numbers in array list in reverse order"
            );
            System.out.println(
                "4.\tCalculate and display the sum of all elements in array list"
            );
            System.out.println(
                "5.\tCalculate and display the average of all elements in array list (rounded to 1 decimal place)"
            );
            System.out.println("6.\tFind the maximum number in array list");
            System.out.println("7.\tFind the minimum number in array list");
            System.out.println(
                "8.\tFind the indices of a specific number in array list"
            );
            System.out.println(
                "9.\tSort array list in ascending order using improved bubble sort"
            );
            System.out.println(
                "10.\tSort array list in ascending order using insertion sort"
            );

            switch (scanner.nextInt()) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    countWhole(ints);
                    break;
                case 2:
                    display(ints);
                    break;
                case 3:
                    displayReverse(ints);
                    break;
                case 4:
                    sum(ints);
                    break;
                case 5:
                    average(ints);
                    break;
                case 6:
                    findMax(ints);
                    break;
                case 7:
                    findMin(ints);
                    break;
                case 8:
                    System.out.println(
                        "Indices of which number do you want to look for?"
                    );
                    search(ints, scanner.nextInt());
                    break;
                case 9:
                    improvedBubbleSort(ints);
                    break;
                case 10:
                    insertionSort(ints);
                    break;
            }
        }
    }

    /**
     * Instantiate an {@code ArrayList<Integer>} with {@code initialCapacity}.
     * @param initialCapacity of the {@code ArrayList<Integer>}
     * @return {@code ArrayList<Integer>} instance
     */
    static ArrayList<Integer> initializeArrayList(final int initialCapacity) {
        return new ArrayList<Integer>(initialCapacity);
    }

    /**
     * Read {@code targetSize} number of integers from stdin and store in {@code arrayList}.
     * @param arrayList to store the integers
     * @param targetSize of elements to read
     */
    static void enterFromKeyboard(
        ArrayList<Integer> arrayList,
        final int targetSize
    ) {
        while (arrayList.size() < targetSize) {
            System.out.println("Please enter an integer:");
            arrayList.add(scanner.nextInt());
        }
    }

    /**
     * Count and display the number of whole numbers stored in {@code arrayList}.
     * @param arrayList to look up
     */
    static void countWhole(ArrayList<Integer> arrayList) {
        int wholeNumberCnt = 0;

        for (int element : arrayList) {
            if (element >= 0) {
                ++wholeNumberCnt;
            }
        }

        System.out.println("The number of whole numbers is: " + wholeNumberCnt);
    }

    /**
     * Display all elements in {@code arrayList} in the order they are stored.
     * @param arrayList to be displayed
     */
    static void display(ArrayList<Integer> arrayList) {
        System.out.println("Integers in array list: " + arrayList);
    }

    /**
     * Display all elements in {@code arrayList} in the reverse order they are stored.
     * @param arrayList to be displayed
     */
    static void displayReverse(ArrayList<Integer> arrayList) {
        System.out.print("Integers in array list in reverse order: ");

        for (int i = arrayList.size() - 1; i >= 0; --i) {
            System.out.print(arrayList.get(i) + " ");
        }

        System.out.println();
    }

    /**
     * Calculate and display the sum of all elements in {@code arrayList}.
     * @param arrayList to be calculated
     */
    static void sum(ArrayList<Integer> arrayList) {
        int sum = 0;

        for (int i : arrayList) {
            sum += i;
        }

        System.out.println("Sum of all elements in array list is: " + sum);
    }

    /**
     * Calculate and display the average of all elements in {@code arrayList}, rounded to 1 decimal place.
     * @param arrayList to be calculated
     */
    static void average(ArrayList<Integer> arrayList) {
        int sum = 0;

        for (int i : arrayList) {
            sum += i;
        }

        final float average = (float) sum / (float) arrayList.size();
        final float averageRounded = (float) ((int) (average * 10.0f)) / 10.0f;

        System.out.println(
            "Average of all elements in array list (rounded to 1 decimal place) is: " +
            averageRounded
        );
    }

    /**
     * Find the greatest element stored in {@code arrayList}.
     * @param arrayList to look up
     * @return the greatest element in {@code arrayList}
     */
    static int findMax(ArrayList<Integer> arrayList) {
        assert arrayList.size() > 0;
        int max = arrayList.get(0);

        for (int i = 1; i < arrayList.size(); ++i) {
            if (arrayList.get(i) > max) {
                max = arrayList.get(i);
            }
        }

        System.out.println("Maximum number in array list is: " + max);

        return max;
    }

    /**
     * Find the least element stored in {@code arrayList}.
     * @param arrayList to look up
     * @return the least element in {@code arrayList}
     */
    static int findMin(ArrayList<Integer> arrayList) {
        assert arrayList.size() > 0;
        int min = arrayList.get(0);

        for (int i = 1; i < arrayList.size(); ++i) {
            if (arrayList.get(i) < min) {
                min = arrayList.get(i);
            }
        }

        System.out.println("Miminum number in array list is: " + min);

        return min;
    }

    /**
     * Search for and display all positions of {@code key} in {@code arrayList}.
     * @param arrayList to look up
     * @param key to look for
     */
    static void search(final ArrayList<Integer> arrayList, final int key) {
        ArrayList<Integer> keyPosList = new ArrayList<Integer>(
            arrayList.size()
        );

        for (int i = 0; i < arrayList.size(); ++i) {
            if (arrayList.get(i) == key) {
                keyPosList.add(i);
            }
        }

        System.out.println("Key (" + key + ") is present at: " + keyPosList);
    }

    /**
     * Sort {@code list} using improved bubble sort.
     * @param list to sort
     */
    static void improvedBubbleSort(ArrayList<Integer> list) {
        boolean swapped = true;
        int i = 0;

        while (swapped) {
            swapped = false;

            for (int j = 0; j < list.size() - 1 - i; ++j) {
                if (list.get(j) > list.get(j + 1)) {
                    swapped = true;

                    int temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }

            ++i;
        }
    }

    /**
     * Sort {@code list} using insertion sort.
     * @param list to sort
     */
    static void insertionSort(ArrayList<Integer> list) {
        if (list.size() < 2) {
            return;
        }

        for (int i = 1; i < list.size(); ++i) {
            final int currVal = list.get(i);
            int j = i - 1;

            while (j >= 0 && list.get(j) > currVal) {
                list.set(j + 1, list.get(j));
                --j;
            }

            list.set(j + 1, currVal);
        }
    }
}
