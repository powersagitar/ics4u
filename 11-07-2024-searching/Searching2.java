
/**
 * Searching 2
 *
 * Use the file, ‘wordlist.txt” and write a program that does the following:
 * (you need to use file-handling to do this question - read from a file, and
 * store the read names into a suitable data structure).
 *
 * Author: Mohan Dong
 * Date: 11/07/2024
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Searching2 {
    static final File WORD_LIST = new File("11-07-2024-searching/wordlist.txt");
    static final String TARGET_WORD = "zyzzyvas";

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<String> words = readToArray(WORD_LIST);
        printNumberedList(words);

        // this is to solve whatever gets measured first is slower
        // execute the methods to let JVM "preheat"/optimize the code before actual
        // measurement
        // https://stackoverflow.com/questions/31314269/java-why-is-calling-a-method-for-the-first-time-slower
        System.nanoTime();
        binarySearch(words, TARGET_WORD);
        recursiveBinarySearch(words, TARGET_WORD, 0, words.size() - 1);

        final long startBinarySearch = System.nanoTime();
        binarySearch(words, TARGET_WORD);
        final long timeBinarySearch = System.nanoTime() - startBinarySearch;

        final long startRecursiveBinarySearch = System.nanoTime();
        recursiveBinarySearch(words, TARGET_WORD, 0, words.size() - 1);
        final long timeRecursiveBinarySearch = System.nanoTime() - startRecursiveBinarySearch;

        System.out.println("Time taken by iterative approach: " + timeBinarySearch);
        System.out.println(
                "Time taken by recursive approach: " + timeRecursiveBinarySearch);
        System.out.println(
                "Recursion is slightly slower than iteration. If recursion doesn't exhibit significant readability and maintainability improvements, always prefer iteration.");
    }

    /**
     * Reads the contents of a file and stores each line as a trimmed string in an
     * ArrayList.
     *
     * @param file the file to be read
     * @return an ArrayList containing the trimmed lines of the file
     * @throws FileNotFoundException if the specified file does not exist
     */
    static ArrayList<String> readToArray(final File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        ArrayList<String> content = new ArrayList<String>();

        while (scanner.hasNextLine()) {
            content.add(scanner.nextLine().trim());
        }

        scanner.close();

        return content;
    }

    /**
     * Prints the elements of the given ArrayList with their corresponding index
     * numbers.
     * Each element is printed on a new line with its index number followed by a tab
     * character.
     *
     * @param arr the ArrayList of strings to be printed
     * @throws FileNotFoundException if the PrintWriter cannot be created
     */
    static void printNumberedList(final ArrayList<String> arr) throws FileNotFoundException {
        // disable auto flush
        PrintWriter printWriter = new PrintWriter(System.out, false);

        for (int i = 0; i < arr.size(); ++i) {
            printWriter.println((i + 1) + "\t" + arr.get(i));
        }

        printWriter.flush();
    }

    /**
     * Performs a binary search on a sorted ArrayList of Strings to determine if the
     * target String is present.
     *
     * @param sortedArr the sorted ArrayList of Strings to search through
     * @param target    the String to search for
     * @return true if the target String is found in the sorted ArrayList, false
     *         otherwise
     */
    static boolean binarySearch(final ArrayList<String> sortedArr, final String target) {
        int low = 0;
        int high = sortedArr.size() - 1;

        while (low <= high) {
            final int mid = (low + high) / 2;

            if (sortedArr.get(mid).compareTo(target) < 0) {
                low = mid + 1;
            } else if (sortedArr.get(mid).compareTo(target) > 0) {
                high = mid - 1;
            } else {
                return true;
            }
        }

        return false;
    }

    /**
     * Performs a recursive binary search on a sorted ArrayList of Strings.
     *
     * @param sortedArr the sorted ArrayList of Strings to search within
     * @param target    the String value to search for
     * @param low       the lower index of the current search range
     * @param high      the upper index of the current search range
     * @return true if the target is found in the sortedArr, false otherwise
     */
    static boolean recursiveBinarySearch(final ArrayList<String> sortedArr, final String target, final int low,
            final int high) {
        if (low > high) {
            return false;
        }

        final int mid = (low + high) / 2;

        if (sortedArr.get(mid).compareTo(target) < 0) {
            return recursiveBinarySearch(sortedArr, target, mid + 1, high);
        } else if (sortedArr.get(mid).compareTo(target) > 0) {
            return recursiveBinarySearch(sortedArr, target, low, mid - 1);
        } else {
            return true;
        }
    }
}
