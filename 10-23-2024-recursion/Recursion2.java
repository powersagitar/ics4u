public class Recursion2 {
    public static void main(String[] args) {
        System.out.println(gcf(24, 8));
    }

    static int gcf(final int m, final int n) {
        if (m == n)
            return m;
        else if (m > n)
            return gcf(n, m - n);
        else
            return gcf(n, m);
    }
}
