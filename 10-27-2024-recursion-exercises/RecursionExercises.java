
/**
 * Recursion Exercises
 * A compilation of exercises utilizing recursion to help with understanding.
 *
 * Author: Mohan Dong
 * Date: 10/27/2024
 */

import java.util.Scanner;

public class RecursionExercises {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // System.out.println(factorial(10));

        // gcf();

        pow();
    }

    static int factorial(final int x) {
        assert x >= 0;

        if (x <= 1) {
            return 1;
        }

        return x * factorial(x - 1);
    }

    static void gcf() {
        System.out.println("Please enter 2 positive integers");

        final int m = scanner.nextInt();
        final int n = scanner.nextInt();

        if (m <= 0 || n <= 0) {
            System.err.println("Invalid input.");
            System.exit(-1);
        }

        System.out.println("The greatest common factor of " + m + " and " + n + " is: " + _gcf(m, n));
    }

    static int _gcf(final int m, final int n) {
        if (m == n) {
            return m;
        } else if (m > n) {
            return _gcf(n, m - n);
        } else {
            return _gcf(n, m);
        }
    }

    static void pow() {
        System.out.println("Please enter a real number as base and an integer as exponent.");

        final double base = scanner.nextDouble();
        final int exp = scanner.nextInt();

        final double dPower = _pow(base, exp);

        System.out.println(base + " raised to the power of " + exp + " is: " + dPower);
    }

    static double _pow(final double base, final int exp) {
        if (exp == 0) {
            return 1;
        } else if (exp > 0) {
            return base * _pow(base, exp - 1);
        } else {
            return 1.0 / _pow(base, -exp);
        }
    }
}
