import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class Code {
    public enum Color {
        Green, Red, Blue, Yellow, Orange, Purple;

        public static Color fromIndex(final int index) {
            return Color.values()[index];
        }
    }

    private final ArrayList<Color> code;

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

    public int getOccurrence(final Color color) {
        int occurrence = 0;

        for (final Color c : code) {
            if (c == color) {
                ++occurrence;
            }
        }

        return occurrence;
    }

    public HashMap<Color, Integer> getOccurrences() {
        HashMap<Color, Integer> occurrences = new HashMap<>(Mastermind.TOTAL_COLORS);

        for (final Color color : Color.values()) {
            final int occurrence = getOccurrence(color);
            occurrences.put(color, occurrence);
        }

        return occurrences;
    }
}
