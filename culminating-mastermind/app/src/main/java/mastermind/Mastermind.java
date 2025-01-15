/*
    Authors: Mohan Dong, Kenneth Chen
    Date: 01/15/2024
    Title: Mastermind.java
 */

package mastermind;

import mastermind.gui.scenes.GameModeSelector;
import mastermind.gui.scenes.Scene;
import mastermind.utils.Log;

import javax.swing.*;
import java.awt.*;
import java.io.File;
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
     * File containing predefined codes for the code maker.
     */
    public final static File CODEMAKER_PREDEFINED_CODES = new File(
        "src/main/resources/codemaker-predefined-codes.txt");

    /**
     * Dimension of the canvas (screen) where the game is drawn.
     */
    public final static Dimension CANVAS_DIMENSION = new Dimension(640, 480);

    /**
     * Private constructor to prevent instantiation.
     */
    private Mastermind() {
    }

    /**
     * Initializes the logger.
     * <p>
     * The default log level is INFO, and the log messages are written to stdout
     * and "mastermind.log".
     */
    private static void initializeLogger() {
        try {
            // Attempt to create a FileOutputStream for the log file in append mode
            final FileOutputStream logFile = new FileOutputStream("mastermind.log", true);
            // Add the FileOutputStream as a sink to the logger
            Log.addSink(logFile);
        } catch (final FileNotFoundException e) {
            // Log an error message if the log file cannot be opened
            Log.error("Failed to open log file output stream: " + e.getMessage());
        }
    }

    /**
     * Main method of the game.
     *
     * @param args Command line arguments (unused).
     */
    public static void main(String[] args) {
        // Initialize the logger
        initializeLogger();

        // Log the start of the game with a separator line
        Log.info("=".repeat(80));
        Log.info("Starting Mastermind");
        // Log the game configuration details
        Log.debug("Total colors: " + TOTAL_COLORS);
        Log.debug("Code length: " + CODE_LENGTH);
        Log.debug("Max guesses: " + MAX_GUESSES);
        Log.debug("Canvas dimension: " + CANVAS_DIMENSION);

        // Create the main game window (JFrame) using the default scene
        final JFrame frame = Scene.createDefaultScene();
        // Initialize the game mode selector with the main game window
        new GameModeSelector(frame);
    }
}
