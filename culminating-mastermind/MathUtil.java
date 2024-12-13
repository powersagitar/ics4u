public class MathUtil {
    public static int[] digitsFromBase(int number, final int base, final int numberOfDigits) {
        int[] digits = new int[numberOfDigits];

        for (int i = numberOfDigits - 1; i >= 0; --i) {
            digits[i] = number % base;
            number /= base;
        }

        return digits;
    }
}
