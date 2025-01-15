/*
    Authors: Mohan Dong
    Date: 01/15/2024
    Title: SecretCodePrompt.java
 */

package mastermind.gui.scenes;

import mastermind.Mastermind;
import mastermind.core.Code;
import mastermind.core.Response;
import mastermind.core.solvers.Status;
import mastermind.gui.panels.*;
import mastermind.utils.Log;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a scene where the user is prompted to enter the correct code.
 */
public class SecretCodePrompt extends Scene {
    // Variable to store the secret code entered by the user
    private Code secretCode = null;

    // The current game status
    private final Status status;

    // List of guesses made by the code breaker
    private final List<Code> guesses;

    // List of responses corresponding to each guess
    private final List<Response> responses;

    /**
     * Initializes a new SecretCodePrompt scene with the specified JFrame, game
     * status, guesses, and responses.
     *
     * @param frame     The JFrame to be associated with this scene.
     * @param status    The current game status.
     * @param guesses   The list of guesses made by the code breaker.
     * @param responses The list of responses corresponding to each guess.
     */
    public SecretCodePrompt(final JFrame frame,
                            final Status status,
                            final List<Code> guesses,
                            final List<Response> responses) {
        // Call the superclass constructor to initialize the frame
        super(frame);

        // Log the creation of the SecretCodePrompt scene
        Log.info("Creating CorrectCodePrompt scene");

        // Initialize the status, guesses, and responses fields
        this.status = status;
        this.guesses = guesses;
        this.responses = responses;

        // Create and configure a label to prompt the user to enter the correct code
        final JLabel promptLabel = new JLabel("Please enter the correct code:");
        promptLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(promptLabel);

        // Draw the code panel and add it to the frame
        final JPanel codePanel = drawCodePanel();
        frame.add(codePanel);

        // Draw the prompt panel and add it to the frame
        final JPanel promptPanel = drawPromptPanel(codePanel);
        frame.add(promptPanel);

        // Draw the proceed button and add it to the frame
        final JButton proceedButton = drawProceedButton();
        frame.add(proceedButton);

        // Draw and register the home button
        HomeButton.drawHomeButton(frame);
        HomeButton.registerHomeHandlers(frame);

        // Draw and register the help button
        Help.drawHelpButton(frame);
        Help.registerHelpHandlers();

        // Refresh the frame to display the updated components
        refreshFrame();
    }

    /**
     * Draws the code panel that displays the code the user is entering.
     *
     * @return The code panel.
     */
    private static JPanel drawCodePanel() {
        // Create a new JPanel with a FlowLayout
        final JPanel codePanel = new JPanel(new FlowLayout());

        // Add CodeCircle components to the panel for each position in the code
        for (int i = 0; i < Mastermind.CODE_LENGTH; ++i) {
            final CodeCircle codeCircle = new CodeCircle(Color.lightGray);
            codePanel.add(codeCircle);
        }

        // Return the configured code panel
        return codePanel;
    }

    /**
     * Draws the prompt panel that allows the user to enter the correct code.
     *
     * @param codePanel The code panel where the code is displayed.
     * @return The prompt panel.
     */
    private JPanel drawPromptPanel(final JPanel codePanel) {
        // Create a new CodeInput instance
        final CodeInput codeInput = new CodeInput();

        // Draw the buttons for the code input and wrap them in a panel
        final JPanel buttonsPanel = codeInput.drawButtons();
        final JPanel buttonsPanelWrapper = new JPanel(new BorderLayout());
        buttonsPanelWrapper.add(buttonsPanel, BorderLayout.PAGE_START);

        // Add an action listener to handle code input events
        codeInput.addActionListener(codeColorIndices -> {
            // Map the indices to Code.Color values
            final List<Code.Color> codeColors = codeColorIndices
                .stream()
                .map(Code.Color::fromIndex)
                .toList();

            // If the code has the correct length, create a new Code instance
            if (codeColors.size() == Mastermind.CODE_LENGTH) {
                this.secretCode = new Code(codeColors);
            }

            // Map the Code.Color values to AWT Color values
            final List<Color> codeAWTColors = codeColors
                .stream()
                .map(GameBoard.CODE_COLOR_TO_AWT_COLOR::get)
                .collect(Collectors.toList());

            // Fill the remaining positions with light gray color if needed
            while (codeAWTColors.size() < Mastermind.CODE_LENGTH) {
                codeAWTColors.add(Color.lightGray);
            }

            // Draw the guess on the code panel
            GameBoard.drawGuess(codePanel, codeAWTColors);
        });

        // Return the configured prompt panel
        return buttonsPanelWrapper;
    }

    /**
     * Draws the proceed button that allows the user to proceed to the next
     * scene.
     *
     * @return The proceed button.
     */
    private JButton drawProceedButton() {
        // Create a new JButton with the label "Proceed"
        final JButton proceedButton = new JButton("Proceed");
        proceedButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add an action listener to handle button click events
        proceedButton.addActionListener(e -> {
            // If the secret code is not set, show a warning message
            if (secretCode == null) {
                Log.warning("Trying to proceed with invalid secret code");

                JOptionPane.showMessageDialog(frame,
                    "Please choose all 4 colors for the correct code",
                    "Incomplete Code",
                    JOptionPane.ERROR_MESSAGE);

                return;
            }

            // Log the entered secret code and proceed to the next scene
            Log.info("Secret code entered: " + secretCode);
            new CodeBreakerResult(frame, status, secretCode, guesses, responses);
        });

        // Return the configured proceed button
        return proceedButton;
    }
}
