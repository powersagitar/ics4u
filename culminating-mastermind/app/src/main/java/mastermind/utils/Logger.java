package mastermind.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.time.Instant;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Represents a logger for recording messages with different severity levels.
 */
@SuppressWarnings("unused")
public class Logger {
    /**
     * The set of output streams to which log messages will be written, in
     * addition to stdout, which is configured by {@link #logToStdout}.
     */
    private final Set<OutputStream> sinks = new HashSet<>();
    /**
     * The severity level of the logger.
     * <p>
     * Only messages with a severity level greater than or equal to this level
     * will be logged.
     */
    private Severity level = Severity.INFO;
    /**
     * A flag indicating whether log messages should be written to stdout.
     */
    private boolean logToStdout = true;

    /**
     * Adds sink to which log messages will be written.
     *
     * @param sink The output stream to add.
     */
    public void addSink(final OutputStream sink) {
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
    public void setLevel(final Severity level) {
        this.level = level;
    }

    /**
     * Sets whether log messages should be written to stdout.
     *
     * @param logToStdout The flag to set.
     */
    public void setLogToStdout(final boolean logToStdout) {
        this.logToStdout = logToStdout;
    }

    /**
     * Logs a message with the TRACE severity level.
     *
     * @param msg The message to log.
     */
    public void trace(final String msg) {
        log(Severity.TRACE, msg);
    }

    /**
     * Logs a message with the DEBUG severity level.
     *
     * @param msg The message to log.
     */
    public void debug(final String msg) {
        log(Severity.DEBUG, msg);
    }

    /**
     * Logs a message with the INFO severity level.
     *
     * @param msg The message to log.
     */
    public void info(final String msg) {
        log(Severity.INFO, msg);
    }

    /**
     * Logs a message with the WARNING severity level.
     *
     * @param msg The message to log.
     */
    public void warning(final String msg) {
        log(Severity.WARNING, msg);
    }

    /**
     * Logs a message with the ERROR severity level.
     *
     * @param msg The message to log.
     */
    public void error(final String msg) {
        log(Severity.ERROR, msg);
    }

    /**
     * Logs a message with the FATAL severity level and exits the program.
     *
     * @param msg The message to log.
     */
    public void fatal(final String msg) {
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
    private void log(final Severity severity, final String msg) {
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
        final static Map<Severity, ANSIColor> COLOR_MAP = Map.of(
            TRACE, ANSIColor.GRAY,
            DEBUG, ANSIColor.GREEN,
            INFO, ANSIColor.CYAN,
            WARNING, ANSIColor.YELLOW,
            ERROR, ANSIColor.RED,
            FATAL, ANSIColor.RED_BACKGROUND);
    }
}
