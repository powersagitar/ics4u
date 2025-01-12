package mastermind.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.time.Instant;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@SuppressWarnings("unused")
public class Logger {
    public enum Severity {
        TRACE,
        DEBUG,
        INFO,
        WARNING,
        ERROR,
        FATAL;

        final static Map<Severity, ANSIColor> COLOR_MAP = Map.of(
                TRACE, ANSIColor.GRAY,
                DEBUG, ANSIColor.GREEN,
                INFO, ANSIColor.CYAN,
                WARNING, ANSIColor.YELLOW,
                ERROR, ANSIColor.RED,
                FATAL, ANSIColor.RED_BACKGROUND);
    }

    private Severity level = Severity.INFO;

    private final Set<OutputStream> sinks = new HashSet<>();

    private boolean logToStdout = true;

    public void addSink(final OutputStream sink) {
        sinks.add(sink);
    }

    public void setLevel(final Severity level) {
        this.level = level;
    }

    public void setLogToStdout(final boolean logToStdout) {
        this.logToStdout = logToStdout;
    }

    public void trace(final String msg) {
        log(Severity.TRACE, msg);
    }

    public void debug(final String msg) {
        log(Severity.DEBUG, msg);
    }

    public void info(final String msg) {
        log(Severity.INFO, msg);
    }

    public void warning(final String msg) {
        log(Severity.WARNING, msg);
    }

    public void error(final String msg) {
        log(Severity.ERROR, msg);
    }

    public void fatal(final String msg) {
        log(Severity.FATAL, msg);
        System.exit(1);
    }

    private void log(final Severity severity, final String msg) {
        if (severity.ordinal() < level.ordinal()) {
            return;
        }

        final Instant now = Instant.now();

        final String severityStr = severity.toString();
        final String coloredSeverityStr = ANSIColor.colorize(Severity.COLOR_MAP.get(severity), severityStr);

        final String msgFormatString = "[%s][%s] %s%n";

        final String fullMsg = String.format(msgFormatString, now.toString(), severityStr, msg);
        final String coloredFullMsg = String.format(msgFormatString, now.toString(), coloredSeverityStr, msg);

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
}
