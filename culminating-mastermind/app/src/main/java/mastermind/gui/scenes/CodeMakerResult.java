/*
    Authors: Kenneth Chen
    Date: 01/15/2024
    Title: CodeMakerResult.java
 */

package mastermind.gui.scenes;

import mastermind.Mastermind;
import mastermind.core.Code;
import mastermind.core.solvers.Status;
import mastermind.gui.panels.GameBoard;
import mastermind.gui.panels.Help;
import mastermind.gui.panels.HomeButton;
import mastermind.utils.Log;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Represents the scene displayed to the user after the Mastermind game has
 * concluded in the Code Maker mode.
 */
public class CodeMakerResult extends Scene {
    /**
     * The correct code that was to be guessed in the game.
     */
    private final Code correctCode;

    /**
     * Constructs a new Result scene to display the outcome of the Mastermind game.
     *
     * @param frame       The parent JFrame that serves as the container for this scene.
     * @param status      The game status, indicating whether the code breaker has won or lost.
     * @param correctCode The correct code that was to be guessed in the game.
     * @throws IllegalArgumentException If the provided status is not MastermindSolver.Status.Win or MastermindSolver.Status.Lose.
     */
    public CodeMakerResult(final JFrame frame, Status status, final Code correctCode) {
        super(frame);

        // Log the creation of the CodeMakerResult scene
        Log.info("Creating CodeMakerResult scene.");

        // Initialize the correct code
        this.correctCode = correctCode;

        // Check the game status and draw the appropriate screen
        if (status == Status.Win) {
            drawWinScreen();
        } else if (status == Status.Lose) {
            drawLoseScreen();
        } else {
            // Log a fatal error if the status is neither win nor lose
            Log.fatal("Status other than win and lose provided to CodeMakerResult.");
        }

        // Draw the correct code on the screen
        drawCorrectCode();

        // Draw the "Proceed" button
        drawProceedButton();

        // Draw the home button
        HomeButton.drawHomeButton(frame);

        // Register handlers for the home button
        HomeButton.registerHomeHandlers(frame);

        // Draw the help button
        Help.drawHelpButton(frame);

        // Register handlers for the help button
        Help.registerHelpHandlers();

        // Refresh the frame to display the updated components
        refreshFrame();
    }

    /**
     * Displays the win screen for the game.
     * This method adds a label to the frame indicating that the code breaker has won.
     * The label is centrally aligned within the frame.
     */
    private void drawWinScreen() {
        // Create a label indicating the code breaker wins
        final JLabel title = new JLabel("The code breaker wins!");
        // Center align the label
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        // Add the label to the frame
        frame.add(title);
    }

    /**
     * Displays the losing screen for the Mastermind game.
     * This method adds a label to the frame indicating that the code breaker failed to
     * guess the correct code within the maximum number of attempts allowed.
     * The label is centrally aligned within the frame.
     */
    private void drawLoseScreen() {
        // Create a label indicating the code breaker did not guess the code in the maximum attempts
        final JLabel title = new JLabel("The code breaker did not guess the code in " + Mastermind.MAX_GUESSES + " attempts.");
        // Center align the label
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        // Add the label to the frame
        frame.add(title);
    }

    /**
     * Displays the correct code from the Mastermind game in the scene.
     *
     * <p>
     * This method creates and displays a label indicating "The correct code was:"
     * and dynamically generates a panel containing the visual representation of the
     * correct code using the colors from the {@code correctCode} object. Each color
     * in the code is mapped to its corresponding AWT color and displayed using the
     * {@code drawGuess} method from the {@code GameBoard} class. The components
     * are centrally aligned to match the layout of the scene.
     */
    private void drawCorrectCode() {
        // Create a label indicating the correct code
        final JLabel correctCodeLabel = new JLabel("The correct code was:");
        // Center align the label
        correctCodeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        // Add the label to the frame
        frame.add(correctCodeLabel);

        // Create a panel to display the correct code
        final JPanel correctCodePanel = new JPanel(new FlowLayout());
        // Center align the panel
        correctCodePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        // Add the panel to the frame
        frame.add(correctCodePanel);

        // Create a list to store the AWT colors of the correct code
        final ArrayList<Color> codeAwtColors = new ArrayList<>(Mastermind.CODE_LENGTH);

        // Loop through each color in the correct code
        for (final Code.Color codeColor : correctCode.getColors()) {
            // Map the code color to its corresponding AWT color
            final Color awtColor = GameBoard.CODE_COLOR_TO_AWT_COLOR.get(codeColor);
            // Add the AWT color to the list
            codeAwtColors.add(awtColor);
        }

        // Draw the correct code using the GameBoard's drawGuess method
        GameBoard.drawGuess(correctCodePanel, codeAwtColors);
    }

    /**
     * Adds a "Proceed" button to the frame and sets up its action listener.
     *
     * <p>
     * The button is centrally aligned and appended to the frame. When clicked,
     * the button transitions the application to the game mode selection screen
     * by instantiating a new {@code GameModeSelector} with the current frame.
     */
    private void drawProceedButton() {
        // Create a "Proceed" button
        final JButton proceedButton = new JButton("Proceed");
        // Center align the button
        proceedButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        // Add the button to the frame
        frame.add(proceedButton);

        // Add an action listener to the button to handle click events
        proceedButton.addActionListener(event -> new GameModeSelector(frame));
    }
}