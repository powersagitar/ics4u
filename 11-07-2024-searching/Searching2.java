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

        // todo: MARK: whatever comes first is slower
        final long startBinarySearch = System.nanoTime();
        binarySearch(words, TARGET_WORD);
        final long timeBinarySearch = System.nanoTime() - startBinarySearch;

        final long startRecursiveBinarySearch = System.nanoTime();
        recursiveBinarySearch(words, TARGET_WORD, 0, words.size() - 1);
        final long timeRecursiveBinarySearch = System.nanoTime() - startRecursiveBinarySearch;

        System.out.println("Time taken by iterative approach: " + timeBinarySearch);
        System.out.println(
                "Time taken by recursive approach: " + timeRecursiveBinarySearch);
    }

    static ArrayList<String> readToArray(final File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        ArrayList<String> content = new ArrayList<String>();

        while (scanner.hasNextLine()) {
            content.add(scanner.nextLine().trim());
        }

        scanner.close();

        return content;
    }

    static void printNumberedList(final ArrayList<String> arr) throws FileNotFoundException {
        // disable auto flush
        PrintWriter printWriter = new PrintWriter(System.out, false);

        for (int i = 0; i < arr.size(); ++i) {
            printWriter.println((i + 1) + "\t" + arr.get(i));
        }

        printWriter.flush();
    }

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
