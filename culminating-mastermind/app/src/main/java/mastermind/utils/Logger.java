package mastermind.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

        @Override
        public String toString() {
            return ANSIColor.colorize(colorMap.get(this), super.toString());
        }
    }

    private final ArrayList<OutputStream> sinks = new ArrayList<>(List.of(System.out));

    private Severity level = Severity.INFO;

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

        final String formattedMsg = String.format("[%s][%s] %s%n", now.toString(), severity, msg);

        for (OutputStream sink : sinks) {
            try {
                sink.write(formattedMsg.getBytes());
            } catch (final IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
