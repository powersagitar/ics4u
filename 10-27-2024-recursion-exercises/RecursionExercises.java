
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
        System.out.println(factorial(10));

        gcf();

        pow();
    }

    /**
     * Calculate factorial of given integer.
     *
     * @param x the number whose factorial is to be calculated
     * @return factorial of {@code x}
     */
    static int factorial(final int x) {
        assert x >= 0;

        if (x <= 1) {
            return 1;
        }

        return x * factorial(x - 1);
    }

    /**
     * Prompts the user to enter 2 integer values, finds the greatest common factor,
     * and displays the value.
     */
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

    /**
     * Finds the greatest common factor of {@code m} and {@code n}. {@code gcf()}
     * calls this method under the hood.
     *
     * @param m a number whose greatest common factor is to be found
     * @param n a number whose greatest common factor is to be found
     * @return
     */
    static int _gcf(final int m, final int n) {
        if (m == n) {
            return m;
        } else if (m > n) {
            return _gcf(n, m - n);
        } else {
            return _gcf(n, m);
        }
    }

    /**
     * Prompts the user to enter a base and an exponent, calculates the base raised
     * to the power of exponent, and displays the result.
     */
    static void pow() {
        System.out.println("Please enter a real number as base and an integer as exponent.");

        final double base = scanner.nextDouble();
        final int exp = scanner.nextInt();

        final double dPower = _pow(base, exp);

        System.out.println(base + " raised to the power of " + exp + " is: " + dPower);
    }

    /**
     * Calculates the result of {@code base} raised to the power of {@code exp}.
     *
     * @param base the base number
     * @param exp  the exponent
     * @return the result of base raised to the power of exp
     */
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
