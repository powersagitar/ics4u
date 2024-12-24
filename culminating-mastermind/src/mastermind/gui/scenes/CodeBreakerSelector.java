package src.mastermind.gui.scenes;

import javax.swing.*;
import java.awt.*;

public class CodeBreakerSelector extends Scene {
    public enum Algorithm {
        DonaldKnuth, Medium, Basic
    }

    private final JPanel selectorPanel = new JPanel();
    private final JRadioButton donaldKnuthButton = new JRadioButton("Donald Knuth 5-Guess Algorithm", true);
    private final JRadioButton mediumAlgoButton = new JRadioButton("Medium Algorithm");
    private final JRadioButton basicAlgoButton = new JRadioButton("Basic Algorithm");
    private final JButton proceedButton = new JButton("Proceed");

    public CodeBreakerSelector(final JFrame frame) {
        super(frame);

        drawSelectorPanel();

        drawSelectorButtons();

        drawProceedButton();

        registerProceedHandler();

        refreshFrame();
    }

    private void drawSelectorPanel() {
        selectorPanel.setLayout(new BoxLayout(selectorPanel, BoxLayout.Y_AXIS));
        selectorPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectorPanel.setBorder(BorderFactory.createTitledBorder("Code Breaker Algorithms"));

        frame.add(selectorPanel);
    }

    private void drawSelectorButtons() {
        final ButtonGroup selectorButtonGroup = new ButtonGroup();
        selectorButtonGroup.add(donaldKnuthButton);
        selectorButtonGroup.add(mediumAlgoButton);
        selectorButtonGroup.add(basicAlgoButton);

        selectorPanel.add(donaldKnuthButton);
        selectorPanel.add(mediumAlgoButton);
        selectorPanel.add(basicAlgoButton);
    }

    private void drawProceedButton() {
        proceedButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(proceedButton);
    }

    private void registerProceedHandler() {
        proceedButton.addActionListener(_ -> {
            final Algorithm algorithm;

            if (donaldKnuthButton.isSelected()) {
                algorithm = Algorithm.DonaldKnuth;
            } else if (mediumAlgoButton.isSelected()) {
                algorithm = Algorithm.Medium;
            } else if (basicAlgoButton.isSelected()) {
                algorithm = Algorithm.Basic;
            } else {
                throw new IllegalArgumentException("A code breaker algorithm has to be selected");
            }

            new CodeBreaker(frame, algorithm);
        });
    }
}