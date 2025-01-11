package mastermind.utils;

import java.util.Map;

public enum ANSIColor {
    RESET,
    CYAN,
    GRAY,
    GREEN,
    RED,
    RED_BACKGROUND,
    YELLOW;

    private static final Map<ANSIColor, String> colorMap = Map.of(
        RESET, "\u001B[0m",
        CYAN, "\u001B[36m",
        GRAY, "\u001B[90m",
        GREEN, "\u001B[32m",
        RED, "\u001B[31m",
        RED_BACKGROUND, "\u001B[41m",
        YELLOW, "\u001B[33m"
    );

    public static String colorize(final ANSIColor color, final String msg) {
        return colorMap.get(color) + msg + colorMap.get(RESET);
    }
}
