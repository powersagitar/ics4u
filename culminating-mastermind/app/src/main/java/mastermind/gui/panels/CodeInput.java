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
     */
    private final List<JButton> submitButtons = new ArrayList<>(Mastermind.CODE_LENGTH);

    /**
     * Button to clear all colors from the entered code.
     */
    private final JButton clearButton = new JButton("Clear");

    /**
     * List of integers representing the code.
     * <p>
     * See {@link mastermind.core.Code.Color} for the mapping of integers to
     * colors.
     */
    private final List<Integer> code = new ArrayList<>(Mastermind.CODE_LENGTH);

    /**
     * Draws the buttons for the user to input the code.
     *
     * @return JPanel containing the buttons.
     */
    public JPanel drawButtons() {
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        final JLabel title = new JLabel("Controls");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(title);

        drawSubmitButtons(panel);
        drawClearButton(panel);

        return panel;
    }

    /**
     * Draws the buttons for the user to submit a color in the code.
     *
     * @param panel Panel to draw the buttons on.
     */
    private void drawSubmitButtons(final JPanel panel) {
        if (!submitButtons.isEmpty()) {
            Log.fatal("Trying to draw submit buttons more than once");
        }

        final JPanel buttonPanel = new JPanel(new GridBagLayout());
        panel.add(buttonPanel);

        final Code.Color[] codeColors = Code.Color.values();

        final int cols = Mastermind.TOTAL_COLORS / 2;
        final int rows = Mastermind.TOTAL_COLORS / cols;

        int codeColorIndex = 0;

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3, 3, 3, 3);

        for (int row = 0; row < rows; ++row) {
            for (int col = 0; col < cols; ++col) {
                gbc.gridx = col;
                gbc.gridy = row;

                final JButton button = new JButton(" ");
                button.setBackground(GameBoard.CODE_COLOR_TO_AWT_COLOR.get(codeColors[codeColorIndex]));
                button.setOpaque(true);
                button.setBorderPainted(false);
                button.setFocusPainted(false);
                buttonPanel.add(button, gbc);
                submitButtons.add(button);

                codeColorIndex++;
            }
        }
    }

    /**
     * Draws the button for the user to clear the code.
     *
     * @param panel Panel to draw the button on.
     */
    private void drawClearButton(final JPanel panel) {
        clearButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(clearButton);
    }

    /**
     * Listener is invoked after every modification to the code.
     *
     * @param onCodeModified Consumer to be invoked with the code after
     *                       modification. The code is represented as a list
     *                       of integers, where each integer represents a
     *                       color. See {@link mastermind.core.Code.Color}
     *                       for mapping of integers to colors.
     */
    public void addActionListener(final Consumer<List<Integer>> onCodeModified) {
        addSubmitListener(onCodeModified);
        addClearListener(onCodeModified);
    }

    /**
     * Adds a listener to the submit buttons.
     *
     * @param onCodeEntered Consumer to be invoked with the code after a
     *                      color is entered. The code is represented as a
     *                      list of integers, where each integer represents a
     *                      color. See {@link mastermind.core.Code.Color} for
     *                      mapping of integers to colors.
     */
    private void addSubmitListener(final Consumer<List<Integer>> onCodeEntered) {
        if (submitButtons.isEmpty()) {
            Log.fatal("Trying to listen on submit buttons without drawing them first");
        }

        for (int i = 0; i < submitButtons.size(); ++i) {
            final JButton button = submitButtons.get(i);
            final int colorIndex = i;
            button.addActionListener(event -> {
                if (code.size() < Mastermind.CODE_LENGTH) {
                    code.add(colorIndex);
                    onCodeEntered.accept(code);
                }
            });
        }
    }

    /**
     * Adds a listener to the clear button.
     *
     * @param onCodeCleared Consumer to be invoked with the code after it is
     *                      cleared. The code is represented as a list of
     *                      integers, where each integer represents a color.
     *                      See {@link mastermind.core.Code.Color} for
     *                      mapping of integers to colors.
     */
    private void addClearListener(final Consumer<List<Integer>> onCodeCleared) {
        clearButton.addActionListener(event -> {
            Log.debug("Current Code Cleared");
            code.clear();
            onCodeCleared.accept(code);
        });
    }
}


