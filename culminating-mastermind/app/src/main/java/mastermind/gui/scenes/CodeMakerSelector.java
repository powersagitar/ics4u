package mastermind.gui.scenes;

import mastermind.core.CodeFactory;
import mastermind.gui.panels.Help;
import mastermind.gui.panels.HomeButton;
import mastermind.utils.Log;

import javax.swing.*;
import java.awt.*;

public class CodeMakerSelector extends Scene {
    private final JPanel selectorPanel = new JPanel();
    private final JRadioButton randomButton = new JRadioButton("Random Code", true);
    private final JRadioButton preProgrammedButton = new JRadioButton("Pre-Programmed Code");
    private final JButton proceedButton = new JButton("Proceed");

    public CodeMakerSelector(final JFrame frame) {
        super(frame);

        Log.info("Creating CodeBreakerSelector scene");

        drawSelectorPanel();

        drawSelectorButtons();

        drawProceedButton();

        HomeButton.drawHomeButton(frame);

        Help.drawHelpButton(frame);

        HomeButton.registerHomeHandlers(frame);

        Help.registerHelpHandlers();

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
        selectorButtonGroup.add(randomButton);
        selectorButtonGroup.add(preProgrammedButton);


        selectorPanel.add(randomButton);
        selectorPanel.add(preProgrammedButton);
    }

    /**
     * Configures and renders the "Proceed" button for the user interface.
     *
     * <p>
     * This method sets the alignment of the proceed button to ensure
     * it is centered horizontally within its container. The button is then
     * added to the parent frame, making it available for user interaction.
     * </p>
     */
    private void drawProceedButton() {
        proceedButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(proceedButton);
    }

    private void registerProceedHandler() {
        proceedButton.addActionListener(event -> {
            Log.trace("Proceed button pressed");

            if (randomButton.isSelected()) {
                Log.info("Random Code Maker selected");
                new CodeMaker(frame, CodeFactory.getRandom());

            } else if (preProgrammedButton.isSelected()) {
                Log.info("Pre-Programmed Code Maker selected");
                // TODO: add pre-programmed code maker

            } else {
                Log.fatal("No code maker algorithm selected");
            }
        });
    }
}
