package mastermind;

import mastermind.gui.scenes.GameModeSelector;
import mastermind.gui.scenes.Scene;
import mastermind.utils.Logger;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class Mastermind {
    public final static int TOTAL_COLORS = 6;
    public final static int CODE_LENGTH = 4;
    public final static int MAX_GUESSES = 10;
    public final static Dimension CANVAS_DIMENSION = new Dimension(640, 480);
    public final static Logger log = new Logger();

    private static void initializeLogger() {
        try {
            final FileOutputStream logFile = new FileOutputStream("mastermind.log", false);
            log.addSink(logFile);
        } catch (final FileNotFoundException e) {
            log.error("Failed to open log file output stream: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        initializeLogger();

        log.info("Starting Mastermind");
        log.debug("Total colors: " + TOTAL_COLORS);
        log.debug("Code length: " + CODE_LENGTH);
        log.debug("Max guesses: " + MAX_GUESSES);
        log.debug("Canvas dimension: " + CANVAS_DIMENSION);

        final JFrame frame = Scene.createDefaultScene();
        new GameModeSelector(frame);
    }
}
