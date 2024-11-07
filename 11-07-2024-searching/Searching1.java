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

        System.out.println("Please enter a name to search for in the array:");
        final String target = scanner.nextLine().trim();
        final int targetIdx = binarySearch(names, target);
        if (targetIdx > -1) {
            System.out.println("The index of " + target + " in the array is: " + targetIdx);
            System.out.println("The corresponding phone number is: " + phoneNumbers.get(targetIdx));
        } else {
            System.out.println(target + " does not exist in array");
        }

        // System.out.println(names);
        // System.out.println(phoneNumbers);

    }

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

    static void sortNamesPhoneNumbers(ArrayList<String> names, ArrayList<Integer> phoneNumbers) {
        // insertion sort
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
