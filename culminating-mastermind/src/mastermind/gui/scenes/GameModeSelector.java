package src.mastermind.gui.scenes;

import src.mastermind.utils.Tuple2;

import javax.swing.*;
import java.awt.*;

public class GameModeSelector extends Scene {
    public GameModeSelector(final JFrame frame) {
        super(frame);

        final JPanel selectorPanel = makeSelectorPanel();
        frame.add(selectorPanel);

        final Tuple2<JRadioButton, JRadioButton> gameModeRadioButtons = makeGameModeRadioButtons();
        final JRadioButton codeBreakerButton = gameModeRadioButtons.first;
        final JRadioButton codeMakerButton = gameModeRadioButtons.second;

        selectorPanel.add(codeBreakerButton);
        selectorPanel.add(codeMakerButton);

        final JButton proceedButton = makeProceedButton(codeBreakerButton, codeMakerButton);
        frame.add(proceedButton);

        refreshFrame();
    }

    private JPanel makeSelectorPanel() {
        final JPanel selectorPanel = new JPanel();

        selectorPanel.setLayout(new BoxLayout(selectorPanel, BoxLayout.Y_AXIS));
        selectorPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectorPanel.setBorder(BorderFactory.createTitledBorder("Game Modes"));

        return selectorPanel;
    }

    private Tuple2<JRadioButton, JRadioButton> makeGameModeRadioButtons() {
        final JRadioButton codeBreakerButton = new JRadioButton("Code Breaker", true);
        final JRadioButton codeMakerButton = new JRadioButton("Code Maker");

        final ButtonGroup selectorButtonGroup = new ButtonGroup();
        selectorButtonGroup.add(codeBreakerButton);
        selectorButtonGroup.add(codeMakerButton);

        return new Tuple2<>(codeBreakerButton, codeMakerButton);
    }

    private JButton makeProceedButton(final JRadioButton codeBreakerButton, final JRadioButton codeMakerButton) {
        final JButton proceedButton = new JButton("Proceed");
        proceedButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        proceedButton.addActionListener(_ -> {
            if (codeBreakerButton.isSelected()) {
                new CodeBreakerSelector(frame);
            } else if (codeMakerButton.isSelected()) {
                new CodeMaker(frame);
            }
        });

        return proceedButton;
    }
}