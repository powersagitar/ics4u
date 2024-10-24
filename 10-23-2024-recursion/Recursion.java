import java.util.Scanner;

public class Recursion {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Please enter a double value as base:");
            final double base = scanner.nextDouble();

            System.out.println("Please enter an integer value as exponent:");
            final int exp = scanner.nextInt();

            System.out.println("The value is: " + power(base, exp));
        }
    }

    static double power(double base, int exp) {
        if (exp > 0)
            return base * power(base, exp - 1);
        else if (exp == 0)
            return 1;
        else
            return 1.0 / power(base, -exp);
    }
}
