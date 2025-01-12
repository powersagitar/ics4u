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
    public final static Logger LOG = new Logger();

    private static void initializeLogger() {
        try {
            final FileOutputStream logFile = new FileOutputStream("mastermind.log", false);
            LOG.addSink(logFile);
        } catch (final FileNotFoundException e) {
            LOG.error("Failed to open log file output stream: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        initializeLogger();

        LOG.info("Starting Mastermind");
        LOG.debug("Total colors: " + TOTAL_COLORS);
        LOG.debug("Code length: " + CODE_LENGTH);
        LOG.debug("Max guesses: " + MAX_GUESSES);
        LOG.debug("Canvas dimension: " + CANVAS_DIMENSION);

        final JFrame frame = Scene.createDefaultScene();
        new GameModeSelector(frame);
    }
}
