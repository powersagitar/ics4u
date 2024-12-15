import java.util.ArrayList;

public class Code {
    public enum Color {
        Green, Red, Blue, Yellow, Orange, Purple;

        public static Color fromIndex(final int index) {
            return Color.values()[index];
        }
    }

    private final ArrayList<Color> code;

    public Code(final ArrayList<Integer> code) {
        ArrayList<Color> codeBuilder = new ArrayList<>(Mastermind.CODE_LENGTH);

        for (final int color : code) {
            codeBuilder.add(Color.fromIndex(color));
        }

        this.code = codeBuilder;
    }

    public Color getColor(final int index) {
        return code.get(index);
    }
}
