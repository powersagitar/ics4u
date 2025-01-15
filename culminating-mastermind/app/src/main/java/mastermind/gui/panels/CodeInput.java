/*
    Authors: Mohan Dong, Kenneth Chen
    Date: 01/15/2024
    Title: CodeInput.java
 */

package mastermind.gui.panels;

import mastermind.Mastermind;
import mastermind.core.Code;
import mastermind.utils.Log;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Panel for the user to input the code.
 */
public class CodeInput {
    /**
     * List of buttons for the user to submit a color in the code.
     * Each button corresponds to a specific color that the user can select.
     */
    private final List<JButton> submitButtons = new ArrayList<>(Mastermind.CODE_LENGTH);

    /**
     * Button to clear all colors from the entered code.
     * This allows the user to reset their input and start over.
     */
    private final JButton clearButton = new JButton("Clear");

    /**
     * List of integers representing the code.
     * Each integer corresponds to a color selected by the user.
     * <p>
     * See {@link mastermind.core.Code.Color} for the mapping of integers to colors.
     */
    private final List<Integer> code = new ArrayList<>(Mastermind.CODE_LENGTH);

    /**
     * Constructs the CodeInput panel.
     * Initializes the panel but does not draw the buttons yet.
     */
    public CodeInput() {
    }

    /**
     * Draws the buttons for the user to input the code.
     * This method creates and arranges the buttons on a JPanel.
     *
     * @return JPanel containing the buttons.
     */
    public JPanel drawButtons() {
        // Create a new JPanel with a vertical box layout
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Add a title label to the panel
        final JLabel title = new JLabel("Controls");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(title);

        // Draw the submit buttons and add them to the panel
        drawSubmitButtons(panel);
        // Draw the clear button and add it to the panel
        drawClearButton(panel);

        // Return the panel containing all the buttons
        return panel;
    }

    /**
     * Draws the buttons for the user to submit a color in the code.
     * Each button corresponds to a specific color.
     *
     * @param panel Panel to draw the buttons on.
     */
    private void drawSubmitButtons(final JPanel panel) {
        // Check if the submit buttons have already been drawn
        if (!submitButtons.isEmpty()) {
            Log.fatal("Trying to draw submit buttons more than once");
        }

        // Create a new panel with a grid bag layout to arrange the buttons
        final JPanel buttonPanel = new JPanel(new GridBagLayout());
        panel.add(buttonPanel);

        // Get the array of possible code colors
        final Code.Color[] codeColors = Code.Color.values();

        // Calculate the number of columns and rows for the button grid
        final int cols = Mastermind.TOTAL_COLORS / 2;
        final int rows = Mastermind.TOTAL_COLORS / cols;

        // Initialize the index for the code colors
        int codeColorIndex = 0;

        // Create grid bag constraints for button placement
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3, 3, 3, 3);

        // Loop through the rows and columns to create and place the buttons
        for (int row = 0; row < rows; ++row) {
            for (int col = 0; col < cols; ++col) {
                gbc.gridx = col;
                gbc.gridy = row;

                // Create a new button for the current color
                final JButton button = new JButton(" ");
                button.setBackground(GameBoard.CODE_COLOR_TO_AWT_COLOR.get(codeColors[codeColorIndex]));
                button.setOpaque(true);
                button.setBorderPainted(false);
                button.setFocusPainted(false);
                buttonPanel.add(button, gbc);
                submitButtons.add(button);

                // Move to the next color
                codeColorIndex++;
            }
        }
    }

    /**
     * Draws the button for the user to clear the code.
     * This button allows the user to reset their input.
     *
     * @param panel Panel to draw the button on.
     */
    private void drawClearButton(final JPanel panel) {
        // Set the alignment of the clear button to the center
        clearButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        // Add the clear button to the panel
        panel.add(clearButton);
    }

    /**
     * Listener is invoked after every modification to the code.
     * This allows the application to react to changes in the user's input.
     *
     * @param onCodeModified Consumer to be invoked with the code after modification.
     *                       The code is represented as a list of integers, where each integer represents a color.
     *                       See {@link mastermind.core.Code.Color} for mapping of integers to colors.
     */
    public void addActionListener(final Consumer<List<Integer>> onCodeModified) {
        // Add listeners to the submit buttons
        addSubmitListener(onCodeModified);
        // Add a listener to the clear button
        addClearListener(onCodeModified);
    }

    /**
     * Adds a listener to the submit buttons.
     * This listener is invoked when the user selects a color.
     *
     * @param onCodeEntered Consumer to be invoked with the code after a color is entered.
     *                      The code is represented as a list of integers, where each integer represents a color.
     *                      See {@link mastermind.core.Code.Color} for mapping of integers to colors.
     */
    private void addSubmitListener(final Consumer<List<Integer>> onCodeEntered) {
        // Check if the submit buttons have been drawn
        if (submitButtons.isEmpty()) {
            Log.fatal("Trying to listen on submit buttons without drawing them first");
        }

        // Loop through the submit buttons and add action listeners
        for (int i = 0; i < submitButtons.size(); ++i) {
            final JButton button = submitButtons.get(i);
            final int colorIndex = i;
            button.addActionListener(event -> {
                // Check if the code length is less than the maximum allowed length
                if (code.size() < Mastermind.CODE_LENGTH) {
                    // Add the selected color to the code
                    code.add(colorIndex);
                    // Invoke the consumer with the updated code
                    onCodeEntered.accept(code);
                }
            });
        }
    }

    /**
     * Adds a listener to the clear button.
     * This listener is invoked when the user clears the code.
     *
     * @param onCodeCleared Consumer to be invoked with the code after it is cleared.
     *                      The code is represented as a list of integers, where each integer represents a color.
     *                      See {@link mastermind.core.Code.Color} for mapping of integers to colors.
     */
    private void addClearListener(final Consumer<List<Integer>> onCodeCleared) {
        // Add an action listener to the clear button
        clearButton.addActionListener(event -> {
            // Log the action of clearing the code
            Log.debug("Current Code Cleared");
            // Clear the code list
            code.clear();
            // Invoke the consumer with the cleared code
            onCodeCleared.accept(code);
        });
    }
}