public class Code {
    public enum Color {
        Green, Red, Blue, Yellow, Orange, Purple
    }

    public static Color[] senaryToColors(int[] senary) {
        Color[] colors = new Color[senary.length];

        for (int i = 0; i < senary.length; ++i) {
            colors[i] = Color.values()[senary[i]];
        }

        return colors;
    }
}
