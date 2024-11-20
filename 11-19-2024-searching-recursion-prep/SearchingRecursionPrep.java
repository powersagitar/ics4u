public class SearchingRecursionPrep {
    public static void main(String[] args) {
        final String[] arr = { "david", "lucy", "hello", "world" };

        System.out.println(linearSearch(arr, "david") == 0);
        System.out.println(linearSearch(arr, "world") == 3);
        System.out.println(linearSearch(arr, "nonexistent") == -1);

        System.out.println(binarySearch(arr, "david") == 0);
        System.out.println(binarySearch(arr, "world") == 3);
        System.out.println(binarySearch(arr, "nonexistent") == -1);

        System.out.println(binarySearchRecursive(arr, "david", 0, arr.length - 1) == 0);
        System.out.println(binarySearchRecursive(arr, "world", 0, arr.length - 1) == 3);
        System.out.println(binarySearchRecursive(arr, "nonexistent", 0, arr.length - 1) == -1);

        System.out.println(pow(2.0, 2) == 4.0);
        System.out.println(pow(2.0, 3) == 8.0);
        System.out.println(pow(3.0, 0) == 1.0);
        System.out.println(pow(4.0, 4) == 256.0);
        System.out.println(pow(4.0, -4) == 0.00390625);

        System.out.println(factorial(0) == 1);
        System.out.println(factorial(1) == 1);
        System.out.println(factorial(2) == 2);
        System.out.println(factorial(3) == 6);
        System.out.println(factorial(4) == 24);

        System.out.println(gcf(120, 80) == 40);
        System.out.println(gcf(80, 120) == 40);
        System.out.println(gcf(16, 4) == 4);
    }

    static int linearSearch(final String[] arr, final String target) {
        for (int i = 0; i < arr.length; ++i) {
            if (arr[i].equals(target)) {
                return i;
            }
        }

        return -1;
    }

    static int binarySearch(final String[] arr, final String target) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            final int mid = (low + high) / 2;

            if (arr[mid].compareTo(target) > 0) {
                high = mid - 1;
            } else if (arr[mid].compareTo(target) < 0) {
                low = mid + 1;
            } else {
                return mid;
            }
        }

        return -1;
    }

    static int binarySearchRecursive(final String[] arr, final String target, final int low, final int high) {
        if (low > high) {
            return -1;
        }

        final int mid = (low + high) / 2;

        if (arr[mid].compareTo(target) > 0) {
            return binarySearchRecursive(arr, target, low, mid - 1);
        } else if (arr[mid].compareTo(target) < 0) {
            return binarySearchRecursive(arr, target, mid + 1, high);
        } else {
            return mid;
        }
    }

    static double pow(final double base, final int exp) {
        if (exp == 0) {
            return 1.0;
        } else if (exp > 0) {
            return base * pow(base, exp - 1);
        } else {
            return 1.0 / pow(base, -exp);
        }
    }

    static int factorial(final int x) {
        if (x < 2) {
            return 1;
        }

        return x * factorial(x - 1);
    }

    static int gcf(final int m, final int n) {
        if (m == n) {
            return m;
        } else if (m < n) {
            return gcf(n, m);
        } else {
            return gcf(n, m - n);
        }
    }
}
