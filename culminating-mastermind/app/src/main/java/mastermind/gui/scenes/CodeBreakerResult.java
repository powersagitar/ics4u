/*
    Authors: Mohan Dong, Kenneth Chen
    Date: 01/15/2024
    Title: CodeBreakerResult.java
 */

package mastermind.gui.scenes;

// Import necessary classes and packages
import mastermind.Mastermind;
import mastermind.core.Code;
import mastermind.core.Response;
import mastermind.core.solvers.Status;
import mastermind.gui.panels.GameBoard;
import mastermind.utils.Log;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Scene that displays the result of the code breaker game mode.
 */
public class CodeBreakerResult extends Scene {
    /**
     * The secret code that the program was trying to guess.
     */
    private final Code secretCode;

    /**
     * The guesses made by the program.
     */
    private final List<Code> guesses;

    /**
     * The responses to the guesses made by the program.
     */
    private final List<Response> responses;

    /**
     * The indices of the guesses that have invalid responses.
     */
    private final List<Integer> invalidResponses;

    /**
     * Creates a new CodeBreakerResult scene.
     *
     * @param frame      The frame to draw the scene on.
     * @param status     The status of the game.
     * @param secretCode The secret code that the program was trying to guess.
     * @param guesses    The guesses made by the program.
     * @param responses  The responses to the guesses made by the program.
     */
    public CodeBreakerResult(final JFrame frame,
                             final Status status,
                             final Code secretCode,
                             final List<Code> guesses,
                             final List<Response> responses) {
        super(frame);

        // Initialize the secret code
        this.secretCode = secretCode;

        // Initialize the list of guesses
        this.guesses = guesses;

        // Initialize the list of responses
        this.responses = responses;

        // Validate the responses to the guesses
        this.invalidResponses = validateResponses();

        // If there are invalid responses, draw them
        if (!invalidResponses.isEmpty()) {
            drawInvalidResponses();
        } else {
            final JLabel descriptionLabel;

            // Display a message based on the game status
            if (status == Status.Win) {
                Log.info("Program successfully guessed the code.");
                descriptionLabel = new JLabel("Program successfully guessed the code.");
            } else {
                Log.info("Program failed to guess the code.");
                descriptionLabel = new JLabel("Program failed to guess the code.");
            }

            // Center align the description label
            descriptionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            frame.add(descriptionLabel);

            Log.info("Secret code: " + secretCode);
            drawCorrectCode();
        }

        // Draw the proceed button
        drawProceedButton();

        // Refresh the frame to apply changes
        refreshFrame();
    }

    /**
     * Validates the responses to the guesses.
     *
     * @return The indices of the guesses that have invalid responses.
     */
    private List<Integer> validateResponses() {
        // Ensure the number of guesses matches the number of responses
        if (guesses.size() != responses.size()) {
            throw new IllegalArgumentException("Guesses and responses must have the same size");
        }

        // Get the indices of invalid responses
        final List<Integer> invalidResponses = getInvalidResponses();

        Log.info("Indices of invalid responses: " + invalidResponses);
        return invalidResponses;
    }

    /**
     * Retrieves the indices of invalid responses.
     *
     * @return The list of indices of invalid responses.
     */
    private List<Integer> getInvalidResponses() {
        // Initialize the list to store indices of invalid responses
        final List<Integer> invalidResponses = new ArrayList<>(Mastermind.CODE_LENGTH);

        // Compare each guess's response with the expected response
        for (int i = 0; i < guesses.size(); ++i) {
            final Response expectedResponse = new Response(secretCode, guesses.get(i));

            // If the response does not match the expected response, mark it as invalid
            if (!expectedResponse.equals(responses.get(i))) {
                invalidResponses.add(i);
            }
        }
        return invalidResponses;
    }

    /**
     * Draws the proceed button.
     */
    private void drawProceedButton() {
        // Create the proceed button
        final JButton button = new JButton("Proceed");
        frame.add(button);

        // Center align the button
        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add an action listener to the proceed button to transition to the game mode selector
        button.addActionListener(e -> new GameModeSelector(frame));
    }

    /**
     * Draws the invalid responses on a {@link GameBoard}.
     */
    private void drawInvalidResponses() {
        // Description of invalid responses
        final String description = """
            You did not provide the correct hints for the following guesses,
            thus it is not possible to derive a win/lose endgame status:
            """.replaceAll("\n", "<br>");

        // Create a label for the description
        final JLabel descriptionLabel = new JLabel(
            "<html><div align=\"center\">" + description + "</div></html>",
            SwingConstants.CENTER);

        // Center align the description label
        descriptionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(descriptionLabel);

        // Create a new game board for invalid responses
        final GameBoard invalidGameBoard = new GameBoard();

        // Update the game board with the invalid guesses and their responses
        for (final int i : invalidResponses) {
            invalidGameBoard.updateGuess(i, guesses.get(i).getColors());
            invalidGameBoard.updateHints(i, responses.get(i));
        }

        // Get the panel of the game board
        final JPanel gameBoardPanel = invalidGameBoard.getBoardPanel();
        gameBoardPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add the game board panel to the frame
        frame.add(gameBoardPanel);
    }

    /**
     * Draws the correct code.
     */
    private void drawCorrectCode() {
        // Convert the secret code colors to AWT colors
        final List<Color> codeAWTColors = secretCode
            .getColors()
            .stream()
            .map(GameBoard.CODE_COLOR_TO_AWT_COLOR::get)
            .toList();

        // Create a panel for the code
        final JPanel codePanel = new JPanel(new FlowLayout());
        frame.add(codePanel);

        // Draw the secret code on the panel
        GameBoard.drawGuess(codePanel, codeAWTColors);
    }
}
