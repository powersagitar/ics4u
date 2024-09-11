
/**
 * L1 Array Exercises
 *
 * Author: Mohan Dong
 * Date:   09/11/2024
 */

import java.util.Scanner;

class Array1 {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int[] array = new int[10];

        InitializeArray(array);
        enterFromKeyboard(array);

        while (true) {
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

            System.out.println("Please select a task above:");

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

                default:
                    System.exit(0);
                    break;
            }
        }
    }

    private static void InitializeArray(int[] array) {
        for (int i = 0; i < array.length; ++i) {
            array[i] = -1;
        }
    }

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

    private static void countWhole(int[] array) {
        int wholeNumbersCount = 0;

        for (final int num : array) {
            if (num >= 0) {
                ++wholeNumbersCount;
            }
        }

        System.out.println("Whole numbers count: " + wholeNumbersCount);
    }

    private static void display(int[] array) {
        System.out.println("Inputted values are:");

        for (final int num : array) {
            System.out.println(num);
        }
    }

    private static void displayReverse(int[] array) {
        System.out.println("Inputted values in reverse order are:");

        for (int pos = array.length - 1; pos >= 0; --pos) {
            System.out.println(array[pos]);
        }
    }

    private static int sum(int[] arrayNum) {
        int sum = 0;

        for (final int num : arrayNum) {
            sum += num;
        }

        return sum;
    }

    private static void Average(int[] array) {
        final double average = sum(array) / array.length;
        final double averageRounded = ((double) (int) (average * 10.0)) / 10.0;

        System.out.println("The average of all entered numbers is: " + averageRounded);
    }

    private static void FindMax(int[] array) {
        int currentMax = array[0];

        for (int pos = 1; pos < array.length; ++pos) {
            if (array[pos] > currentMax) {
                currentMax = array[pos];
            }
        }

        System.out.println("The maximum number stored in array is: " + currentMax);
    }

    private static void FindMin(int[] array) {
        int currentMin = array[0];

        for (int pos = 1; pos < array.length; ++pos) {
            if (array[pos] < currentMin) {
                currentMin = array[pos];
            }
        }

        System.out.println("The minimum number stored in array is: " + currentMin);
    }

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

    private static void Sort(int[] list) {
        for (int i = 0; i < list.length; ++i) {
            for (int j = 0; j < list.length - 1 - i; ++j) {
                if (list[j] > list[j + 1]) {
                    final int temp = list[j];
                    list[j] = list[j + 1];
                    list[j + 1] = temp;
                }
            }
        }
    }

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
        }
    }
}
