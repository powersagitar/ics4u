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

    public JPanel drawButtonsToPanel() {
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

        final int rowWidth = Mastermind.TOTAL_COLORS / 2;

        for (int rowIndex = 0; rowIndex < Mastermind.TOTAL_COLORS / rowWidth; ++rowIndex) {
            final JPanel colorPanel = new JPanel(new FlowLayout());
            panel.add(colorPanel);

            for (int colorIndex = rowWidth * rowIndex; colorIndex < rowWidth * (rowIndex + 1); ++colorIndex) {
                JButton colorButton = new JButton(" ");
                colorButton.setBackground(GameBoard.codeColorToAwtColor.get(Code.Color.values()[colorIndex]));
                colorButton.setForeground(Color.BLACK);
                colorButton.setOpaque(true);
                colorButton.setContentAreaFilled(true);
                colorPanel.add(colorButton);
                submitButtons.add(colorButton);
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


