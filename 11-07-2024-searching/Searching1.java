
/**
 * Searching1
 *
 * Prompt the user to enter 5 names and their respective phone numbers and store
 * them in two arrays (name and phone array). Sort the names in alphabetical
 * order (use any method of your choice) and save it.
 *
 * Author: Mohan Dong
 * Date: 11/07/2024
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Searching1 {
    static final int NUMBER_OF_NAMES = 5;
    static final int NUMBER_OF_PHONE_NUMBERS = NUMBER_OF_NAMES;

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ArrayList<String> names = new ArrayList<String>(NUMBER_OF_NAMES);
        ArrayList<Integer> phoneNumbers = new ArrayList<Integer>(NUMBER_OF_PHONE_NUMBERS);

        initNamesPhoneNumbers(names, phoneNumbers, NUMBER_OF_NAMES);
        sortNamesPhoneNumbers(names, phoneNumbers);

        System.out.println("Sorted phone book");
        System.out.println("names: " + names);
        System.out.println("numbers: " + phoneNumbers);

        System.out.println("Please enter a name to search for in the array:");
        final String target = scanner.nextLine().trim();
        final int targetIdx = binarySearch(names, target);
        if (targetIdx > -1) {
            System.out.println("The index of " + target + " in the array is: " + targetIdx);
            System.out.println("The corresponding phone number is: " + phoneNumbers.get(targetIdx));
        } else {
            System.out.println(target + " does not exist in array");
        }

    }

    /**
     * Initializes the provided lists with names and phone numbers.
     * Prompts the user to enter a name and a corresponding phone number
     * for a specified number of entries.
     *
     * @param names        the list to store the names
     * @param phoneNumbers the list to store the phone numbers
     * @param n            the number of entries to be added to the lists
     */
    static void initNamesPhoneNumbers(ArrayList<String> names, ArrayList<Integer> phoneNumbers, final int n) {
        for (int i = 0; i < n; ++i) {
            System.out.println("Please enter a name:");
            names.add(scanner.nextLine().trim());

            System.out.println("Please enter a phone number for the name:");
            phoneNumbers.add(scanner.nextInt());

            // consume line feed
            scanner.nextLine();
        }
    }

    /**
     * Sorts the given lists of names and phone numbers in alphabetical order of
     * names using insertion sort.
     * The phone numbers are rearranged to maintain the association with their
     * respective names.
     *
     * @param names        the list of names to be sorted
     * @param phoneNumbers the list of phone numbers associated with the names
     */
    static void sortNamesPhoneNumbers(ArrayList<String> names, ArrayList<Integer> phoneNumbers) {
        for (int i = 1; i < names.size(); ++i) {
            final String currentName = names.get(i);
            final int currentPhoneNumber = phoneNumbers.get(i);
            int j = i - 1;

            while (j >= 0 && names.get(j).compareTo(currentName) > 0) {
                names.set(j + 1, names.get(j));
                phoneNumbers.set(j + 1, phoneNumbers.get(j));

                --j;
            }

            names.set(j + 1, currentName);
            phoneNumbers.set(j + 1, currentPhoneNumber);
        }
    }

    /**
     * Performs a binary search on a sorted ArrayList of Strings to find the target
     * string.
     *
     * @param arr    the sorted ArrayList of Strings to search
     * @param target the string to search for
     * @return the index of the target string if found, otherwise -1
     */
    static int binarySearch(final ArrayList<String> arr, final String target) {
        int low = 0;
        int high = arr.size() - 1;

        while (low <= high) {
            final int mid = (low + high) / 2;

            if (arr.get(mid).compareTo(target) < 0) {
                low = mid + 1;
            } else if (arr.get(mid).compareTo(target) > 0) {
                high = mid - 1;
            } else {
                return mid;
            }
        }

        return -1;
    }
}
