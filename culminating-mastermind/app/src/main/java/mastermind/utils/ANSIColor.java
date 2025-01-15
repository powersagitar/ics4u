package mastermind.utils;

import java.util.Map;

/**
 * Represents an ANSI color code for console output.
 */
public enum ANSIColor {
    /**
     * The ANSI reset color code.
     */
    RESET,

    /**
     * The ANSI cyan color code.
     */
    CYAN,

    /**
     * The ANSI gray color code.
     */
    GRAY,

    /**
     * The ANSI green color code.
     */
    GREEN,

    /**
     * The ANSI red color code.
     */
    RED,

    /**
     * The ANSI red background color code.
     */
    RED_BACKGROUND,

    /**
     * The ANSI yellow color code.
     */
    YELLOW;

    /**
     * A map of ANSI color codes to their respective escape sequences.
     */
    private static final Map<ANSIColor, String> colorMap = Map.of(
        RESET, "\u001B[0m",
        CYAN, "\u001B[36m",
        GRAY, "\u001B[90m",
        GREEN, "\u001B[32m",
        RED, "\u001B[31m",
        RED_BACKGROUND, "\u001B[41m",
        YELLOW, "\u001B[33m"
    );

    /**
     * Returns the provided string wrapped with the specified ANSI color code.
     *
     * @param color The ANSI color code to apply.
     * @param msg   The string to colorize.
     * @return The colorized string.
     */
    public static String colorize(final ANSIColor color, final String msg) {
        return colorMap.get(color) + msg + colorMap.get(RESET); // Reset color after coloring the string
    }
}
