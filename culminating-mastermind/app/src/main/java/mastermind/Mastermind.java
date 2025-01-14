package mastermind;

import mastermind.gui.scenes.GameModeSelector;
import mastermind.gui.scenes.Scene;
import mastermind.utils.Log;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Main class of the Mastermind game.
 * <p>
 * This class initializes the logger and the main window of the game, and stores
 * the game's constants (configuration).
 */
public class Mastermind {
    /**
     * Length of the code to guess.
     */
    public final static int CODE_LENGTH = 4;

    /**
     * Maximum number of guesses allowed.
     * <p>
     * The code breaker loses the game if they reach this number of guesses.
     */
    public final static int MAX_GUESSES = 10;

    /**
     * Total number of colors available to choose from.
     */
    public final static int TOTAL_COLORS = 6;

    /**
     * Dimension of the canvas (screen) where the game is drawn.
     */
    public final static Dimension CANVAS_DIMENSION = new Dimension(640, 480);

    /**
     * Initializes the logger.
     * <p>
     * The default log lever is INFO, and the log messages are written to stdout
     * and "mastermind.log".
     */
    private static void initializeLogger() {
        try {
            final FileOutputStream logFile = new FileOutputStream("mastermind.log", false);
            Log.addSink(logFile);
        } catch (final FileNotFoundException e) {
            Log.error("Failed to open log file output stream: " + e.getMessage());
        }
    }

    /**
     * Main method of the game.
     *
     * @param args Command line arguments (unused).
     */
    public static void main(String[] args) {
        initializeLogger();

        Log.info("Starting Mastermind");
        Log.debug("Total colors: " + TOTAL_COLORS);
        Log.debug("Code length: " + CODE_LENGTH);
        Log.debug("Max guesses: " + MAX_GUESSES);
        Log.debug("Canvas dimension: " + CANVAS_DIMENSION);

        final JFrame frame = Scene.createDefaultScene();
        new GameModeSelector(frame);
    }
}
