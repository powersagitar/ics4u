public class BinarySearch {
    public static void main(String[] args) {
        String[] names = { "a", "b", "c", "d" };

        System.out.println(binarySearch(names, "a", 0, names.length - 1));
        System.out.println(binarySearch(names, "b", 0, names.length - 1));
        System.out.println(binarySearch(names, "d", 0, names.length - 1));
        System.out.println(binarySearch(names, "e", 0, names.length - 1));

        System.out.println(binarySearchRecursive(names, "a", 0, names.length - 1));
        System.out.println(binarySearchRecursive(names, "b", 0, names.length - 1));
        System.out.println(binarySearchRecursive(names, "d", 0, names.length - 1));
        System.out.println(binarySearchRecursive(names, "e", 0, names.length - 1));
    }

    static int binarySearch(String[] names, String target, int low, int high) {
        while (low <= high) {
            int mid = (low + high) / 2;

            if (names[mid].equals(target)) {
                return mid;
            } else if (names[mid].compareTo(target) > 0) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return -1;
    }

    static int binarySearchRecursive(String[] names, String target, int low, int high) {
        if (low > high) {
            return -1;
        }

        int mid = (low + high) / 2;

        if (names[mid].compareTo(target) < 0) {
            return binarySearchRecursive(names, target, mid + 1, high);
        } else if (names[mid].compareTo(target) > 0) {
            return binarySearchRecursive(names, target, low, mid - 1);
        } else {
            return mid;
        }
    }
}
