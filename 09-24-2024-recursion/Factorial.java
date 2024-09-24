
/**
 * Factorial
 * Find the factorial of given user input using recursion
 *
 * Author: Mohan Dong
 * Date:   09/24/2024
 */

import java.util.Scanner;

public class Factorial {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Please enter a non-negative integer value");

        final int input = scanner.nextInt();

        if (input < 0) {
            System.err.println("Factorial is not defined for negative values");
            System.exit(-1);
        }

        final int factValue = Fact(input);

        System.out.println(input + "! is: " + factValue);
    }

    /**
     * Returns the factorial of passed argument in the range of 0..=12
     *
     * @param iNumber the number to calculate factorial of
     * @return factorial of iNumber
     */
    static int Fact(final int iNumber) {
        // factorial defined for only non-negative integers
        assert iNumber >= 0;

        if (iNumber <= 1) {
            return 1;
        } else {
            return iNumber * Fact(iNumber - 1);
        }
    }
}
