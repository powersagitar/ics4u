package src.mastermind.core;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Random;
import java.util.function.Function;

import src.mastermind.Mastermind;

public class Code {
    public enum Color {
        Green, Red, Blue, Yellow, Orange, Purple;

        public static Color fromIndex(final int index) {
            return Color.values()[index];
        }
    }

    private final ArrayList<Color> code;

    public static Code generateRandomCode(List<Function<Code, Boolean>> filters) {
        final Random random = new Random();

        while (true) {
            ArrayList<Integer> codeBuilder = new ArrayList<>(Mastermind.CODE_LENGTH);

            for (int i = 0; i < Mastermind.CODE_LENGTH; ++i) {
                final int randomColor = random.nextInt(Mastermind.TOTAL_COLORS);
                codeBuilder.add(randomColor);
            }

            final Code code = new Code(codeBuilder);
            boolean satisfiesAllFilters = true;

            for (final Function<Code, Boolean> filter : filters) {
                if (!filter.apply(code)) {
                    satisfiesAllFilters = false;
                    break;
                }
            }

            if (satisfiesAllFilters) {
                return code;
            }
        }
    }

    public Code(final List<Integer> code) {
        ArrayList<Color> codeBuilder = new ArrayList<>(Mastermind.CODE_LENGTH);

        for (final int color : code) {
            codeBuilder.add(Color.fromIndex(color));
        }

        this.code = codeBuilder;
    }

    public Color getColor(final int index) {
        return code.get(index);
    }

    public ArrayList<Color> getColors() {
        return code;
    }

    public HashMap<Color, Integer> getOccurrences() {
        HashMap<Color, Integer> occurrences = new HashMap<>(Mastermind.TOTAL_COLORS);

        for (final Color color : Color.values()) {
            occurrences.put(color, 0);
        }

        for (final Color color : code) {
            occurrences.put(color, occurrences.get(color) + 1);
        }

        return occurrences;
    }
}
