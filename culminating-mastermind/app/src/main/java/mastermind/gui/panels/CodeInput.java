package mastermind.gui.panels;

import mastermind.Mastermind;
import mastermind.core.Code;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class CodeInput {
    private final List<JButton> submitButtons = new ArrayList<>(Mastermind.CODE_LENGTH);
    private final JButton clearButton = new JButton("Clear");
    private final List<Integer> code = new ArrayList<>(Mastermind.CODE_LENGTH);

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

    private void drawSubmitButtons(final JPanel panel) {
        if (!submitButtons.isEmpty()) {
            throw new IllegalStateException("Buttons already drawn");
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
                button.setBackground(GameBoard.codeColorToAwtColor.get(codeColors[codeColorIndex]));
                button.setOpaque(true);
                button.setBorderPainted(false);
                button.setFocusPainted(false);
                buttonPanel.add(button, gbc);
                submitButtons.add(button);

                codeColorIndex++;
            }
        }
    }

    private void drawClearButton(final JPanel panel) {
        clearButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(clearButton);
    }

    public void addActionListener(final Consumer<List<Integer>> onCodeModified) {
        addSubmitListener(onCodeModified);
        addClearListener(onCodeModified);
    }

    private void addSubmitListener(final Consumer<List<Integer>> onCodeEntered) {
        if (submitButtons.isEmpty()) {
            throw new IllegalStateException("No buttons to add action listeners to");
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

    private void addClearListener(final Consumer<List<Integer>> onCodeCleared) {
        clearButton.addActionListener(event -> {
            code.clear();
            onCodeCleared.accept(code);
        });
    }
}


