package src.mastermind.gui.scenes;

import javax.swing.*;
import java.awt.*;

public class GameModeSelector extends Scene {
    private final JPanel selectorPanel = new JPanel();
    private final JRadioButton codeBreakerButton = new JRadioButton("Code Breaker", true);
    private final JRadioButton codeMakerButton = new JRadioButton("Code Maker");
    private final JButton proceedButton = new JButton("Proceed");

    public GameModeSelector(final JFrame frame) {
        super(frame);

        drawSelectorPanel();
        drawProceedButton();

        frame.add(selectorPanel);
        frame.add(proceedButton);

        refreshFrame();
    }

    private void drawSelectorPanel() {
        selectorPanel.setLayout(new BoxLayout(selectorPanel, BoxLayout.Y_AXIS));
        selectorPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectorPanel.setBorder(BorderFactory.createTitledBorder("Game Modes"));

        selectorPanel.add(codeBreakerButton);
        selectorPanel.add(codeMakerButton);

        final ButtonGroup gameModeButtonGroup = new ButtonGroup();
        gameModeButtonGroup.add(codeBreakerButton);
        gameModeButtonGroup.add(codeMakerButton);
    }

    private void drawProceedButton() {
        proceedButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        proceedButton.addActionListener(_ -> {
            if (codeBreakerButton.isSelected()) {
                new CodeBreakerSelector(frame);
            } else if (codeMakerButton.isSelected()) {
                new CodeMaker(frame);
            } else {
                throw new IllegalArgumentException("A game mode has to be selected.");
            }
        });
    }
}