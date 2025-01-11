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

    static {
        try {
            final FileOutputStream logFile = new FileOutputStream("mastermind.log", false);
            log.addSink(logFile);
        } catch (final FileNotFoundException e) {
            log.error("Failed to open log file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        log.info("Starting Mastermind");

        final JFrame frame = Scene.createDefaultScene();
        new GameModeSelector(frame);
    }
}
