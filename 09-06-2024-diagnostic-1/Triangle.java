import java.util.Scanner;

class Triangle {
    public static void main(String[] args) {
        System.out.println("Please enter an integer between 1 and 9 (inclusive):");

        Scanner scanner = new Scanner(System.in);

        final int input = scanner.nextInt();

        scanner.close();

        if (!(input >= 1 && input <= 9)) {
            System.out.println("Invalid input. Please enter an integer between 1 and 9 (inclusive).");
            System.exit(1);
        }

        triangle(input);
    }

    private static void triangle(final int number) {
        for (int i = 0; i < number; ++i) {
            for (int j = 0; j <= i; ++j) {
                System.out.print(j + 1);
            }
            System.out.println();
        }
    }
}
