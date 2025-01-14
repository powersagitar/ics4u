package mastermind.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.time.Instant;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Represents a singleton logger for recording messages with different severity
 * levels.
 */
@SuppressWarnings("unused")
public class Log {
    /**
     * The set of output streams to which log messages will be written, in
     * addition to stdout, which is configured by {@link #logToStdout}.
     */
    private static final Set<OutputStream> sinks = new HashSet<>();
    /**
     * The severity level of the logger.
     * <p>
     * Only messages with a severity level greater than or equal to this level
     * will be logged.
     */
    private static Severity level = Severity.INFO;
    /**
     * A flag indicating whether log messages should be written to stdout.
     */
    private static boolean logToStdout = true;

    /**
     * Disable the constructor as {@Code Log} being a singleton class.
     */
    private Log() {
    };

    /**
     * Adds sink to which log messages will be written.
     *
     * @param sink The output stream to add.
     */
    public static void addSink(final OutputStream sink) {
        sinks.add(sink);
    }

    /**
     * Sets the severity level of the logger.
     * <p>
     * Only messages with a severity level greater than or equal to this level
     * will be logged.
     *
     * @param level The severity level to set.
     */
    public static void setLevel(final Severity level) {
        Log.level = level;
    }

    /**
     * Sets whether log messages should be written to stdout.
     *
     * @param logToStdout The flag to set.
     */
    public static void setLogToStdout(final boolean logToStdout) {
        Log.logToStdout = logToStdout;
    }

    /**
     * Logs a message with the TRACE severity level.
     *
     * @param msg The message to log.
     */
    public static void trace(final String msg) {
        log(Severity.TRACE, msg);
    }

    /**
     * Logs a message with the DEBUG severity level.
     *
     * @param msg The message to log.
     */
    public static void debug(final String msg) {
        log(Severity.DEBUG, msg);
    }

    /**
     * Logs a message with the INFO severity level.
     *
     * @param msg The message to log.
     */
    public static void info(final String msg) {
        log(Severity.INFO, msg);
    }

    /**
     * Logs a message with the WARNING severity level.
     *
     * @param msg The message to log.
     */
    public static void warning(final String msg) {
        log(Severity.WARNING, msg);
    }

    /**
     * Logs a message with the ERROR severity level.
     *
     * @param msg The message to log.
     */
    public static void error(final String msg) {
        log(Severity.ERROR, msg);
    }

    /**
     * Logs a message with the FATAL severity level and exits the program.
     *
     * @param msg The message to log.
     */
    public static void fatal(final String msg) {
        log(Severity.FATAL, msg);
        System.exit(1);
    }

    /**
     * Logs a message with the specified severity level.
     * <p>
     * Only messages with a severity level greater than or equal to
     * {@link #level} will be logged.
     *
     * @param severity The severity level of the message.
     * @param msg      The message to log.
     */
    private static void log(final Severity severity, final String msg) {
        if (severity.ordinal() < level.ordinal()) {
            return;
        }

        final Instant now = Instant.now();

        final String severityStr = severity.toString();
        final String coloredSeverityStr = ANSIColor.colorize(Severity.COLOR_MAP.get(severity), severityStr);

        final String msgFormatString = "[%s][%s] %s%n";

        final String fullMsg = String.format(msgFormatString, now.toString(), severityStr, msg);
        final String coloredFullMsg = String.format(msgFormatString, now, coloredSeverityStr, msg);

        if (logToStdout) {
            System.out.print(coloredFullMsg);
        }

        for (OutputStream sink : sinks) {
            try {
                sink.write(fullMsg.getBytes());
            } catch (final IOException e) {
                System.err.println("Failed to log to sink: " + e.getMessage());
            }
        }
    }

    /**
     * Represents the severity levels of log messages.
     */
    public enum Severity {
        /**
         * The TRACE severity level.
         */
        TRACE,

        /**
         * The DEBUG severity level.
         */
        DEBUG,

        /**
         * The INFO severity level.
         */
        INFO,

        /**
         * The WARNING severity level.
         */
        WARNING,

        /**
         * The ERROR severity level.
         */
        ERROR,

        /**
         * The FATAL severity level.
         */
        FATAL;

        /**
         * A map of severity levels to their respective ANSI colors.
         */
        private static final Map<Severity, ANSIColor> COLOR_MAP = Map.of(
                TRACE, ANSIColor.GRAY,
                DEBUG, ANSIColor.GREEN,
                INFO, ANSIColor.CYAN,
                WARNING, ANSIColor.YELLOW,
                ERROR, ANSIColor.RED,
                FATAL, ANSIColor.RED_BACKGROUND);
    }
}