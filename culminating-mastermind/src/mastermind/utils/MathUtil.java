package src.mastermind.utils;

import java.util.ArrayList;
import java.util.Collections;

public class MathUtil {
    public static ArrayList<Integer> digitsFromBase(int number, final int base, final int arrLength) {
        ArrayList<Integer> digits = new ArrayList<>(arrLength);

        while (number > 0) {
            final int leastSignificantDigit = number % base;
            digits.add(leastSignificantDigit);
            number /= base;
        }

        while (digits.size() < arrLength) {
            digits.add(0);
        }

        Collections.reverse(digits);

        return digits;
    }
}
