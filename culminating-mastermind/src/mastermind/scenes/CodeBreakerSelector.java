package src.mastermind.scenes;

import src.mastermind.utils.Tuple3;

import javax.swing.*;
import java.awt.*;

public class CodeBreakerSelector extends Scene {
    public enum Algorithm {
        DonaldKnuth, Medium, Basic
    }

    public CodeBreakerSelector(final JFrame frame) {
        super(frame);

        final JPanel selectorPanel = makeSelectorPanel();
        frame.add(selectorPanel);

        final Tuple3<JRadioButton, JRadioButton, JRadioButton> algorithmRadioButtons = makeAlgorithmRadioButtons();
        final JRadioButton donaldKnuthButton = algorithmRadioButtons.first;
        final JRadioButton mediumAlgoButton = algorithmRadioButtons.second;
        final JRadioButton basicAlgoButton = algorithmRadioButtons.third;

        selectorPanel.add(donaldKnuthButton);
        selectorPanel.add(mediumAlgoButton);
        selectorPanel.add(basicAlgoButton);

        final JButton proceedButton = makeProceedButton(donaldKnuthButton, mediumAlgoButton, basicAlgoButton);
        frame.add(proceedButton);

        refresh();
    }

    private JPanel makeSelectorPanel() {
        final JPanel selectorPanel = new JPanel();

        selectorPanel.setLayout(new BoxLayout(selectorPanel, BoxLayout.Y_AXIS));
        selectorPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectorPanel.setBorder(BorderFactory.createTitledBorder("Code Breaker Algorithms"));

        return selectorPanel;
    }

    private Tuple3<JRadioButton, JRadioButton, JRadioButton> makeAlgorithmRadioButtons() {
        final JRadioButton donaldKnuthButton = new JRadioButton("Donald Knuth 5-Guess Algorithm", true);
        final JRadioButton mediumAlgoButton = new JRadioButton("Medium Algorithm");
        final JRadioButton basicAlgoButton = new JRadioButton("Basic Algorithm");

        final ButtonGroup selectorButtonGroup = new ButtonGroup();
        selectorButtonGroup.add(donaldKnuthButton);
        selectorButtonGroup.add(mediumAlgoButton);
        selectorButtonGroup.add(basicAlgoButton);

        return new Tuple3<>(donaldKnuthButton, mediumAlgoButton, basicAlgoButton);
    }

    private JButton makeProceedButton(final JRadioButton donaldKnuthButton, final JRadioButton mediumAlgoButton, final JRadioButton basicAlgoButton) {
        final JButton proceedButton = new JButton("Proceed");
        proceedButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        proceedButton.addActionListener(_ -> {
            if (donaldKnuthButton.isSelected()) {
                new CodeBreaker(frame, Algorithm.DonaldKnuth);
            } else if (mediumAlgoButton.isSelected()) {

            } else if (basicAlgoButton.isSelected()) {

            }
        });

        return proceedButton;
    }
}