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

        final static Map<Severity, ANSIColor> colorMap = Map.of(
            TRACE, ANSIColor.GRAY,
            DEBUG, ANSIColor.GREEN,
            INFO, ANSIColor.CYAN,
            WARNING, ANSIColor.YELLOW,
            ERROR, ANSIColor.RED,
            FATAL, ANSIColor.RED_BACKGROUND
        );
    }

    private Severity level = Severity.INFO;

    private final Set<OutputStream> sinks = new HashSet<>(Set.of(System.out));

    public void addSink(final OutputStream sink) {
        sinks.add(sink);
    }

    public void setLevel(final Severity level) {
        this.level = level;
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

        for (OutputStream sink : sinks) {
            try {
                final String severityStr = sink == System.out ?
                    ANSIColor.colorize(Severity.colorMap.get(severity), severity.toString()) :
                    severity.toString();

                final String formattedMessage = String.format("[%s][%s] %s%n", now.toString(), severityStr, msg);

                sink.write(formattedMessage.getBytes());
            } catch (final IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
