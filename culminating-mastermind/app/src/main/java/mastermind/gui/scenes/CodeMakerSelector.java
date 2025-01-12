package mastermind.gui.scenes;

import mastermind.Mastermind;
import mastermind.gui.panels.Help;
import mastermind.gui.panels.HomeButton;

import javax.swing.*;
import java.awt.*;

public class CodeMakerSelector extends Scene{
    private final JPanel selectorPanel = new JPanel();
    private final JRadioButton randomButton = new JRadioButton("Random Code", true);
    private final JRadioButton preProgrammedButton = new JRadioButton("Pre-Programmed Code");
    private final JButton proceedButton = new JButton("Proceed");

    /**
     * Constructs a CodeBreakerSelector, which provides a user interface to select
     * a code-breaking algorithm and proceed to the next scene.
     *
     * <p>
     * This constructor initializes the selection panel with several algorithm options,
     * a proceed button, and registers an event handler to handle user interaction.
     * It also refreshes the parent frame to reflect the changes.
     * </p>
     *
     * @param frame The parent JFrame where this selector will be displayed.
     */
    public CodeMakerSelector(final JFrame frame) {
        super(frame);

        Mastermind.log.info("Creating CodeBreakerSelector scene");

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

    /**
     * Configures and renders the selector panel for choosing code-breaking algorithms.
     *
     * <p>
     * This method sets the layout of the panel to a vertical box layout
     * and centers its alignment. A titled border is added to the panel
     * to denote its purpose as "Code Breaker Algorithms". After configuration,
     * the panel is added to the parent frame for display.
     * </p>
     */
    private void drawSelectorPanel() {
        selectorPanel.setLayout(new BoxLayout(selectorPanel, BoxLayout.Y_AXIS));
        selectorPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectorPanel.setBorder(BorderFactory.createTitledBorder("Code Breaker Algorithms"));

        frame.add(selectorPanel);
    }

    /**
     * Configures and adds radio buttons to the selector panel for choosing a code-breaking algorithm.
     *
     * <p>
     * This method creates a group of mutually exclusive radio buttons representing the various
     * algorithm options: "Donald Knuth 5-Guess Algorithm", "Medium Algorithm", and "Basic Algorithm".
     * Each button is added to a ButtonGroup to enforce exclusivity, ensuring only one option
     * can be selected at a time. The buttons are then added to the selector panel for display.
     * </p>
     *
     * <p>
     * This configuration provides a user-friendly interface for selecting the desired algorithm.
     * </p>
     */
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

    /**
     * Registers an event handler for the "Proceed" button to handle algorithm selection.
     *
     * <p>
     * When the "Proceed" button is clicked, this method checks which algorithm
     * radio button is selected. Based on the user's selection, it determines
     * the chosen algorithm. If no algorithm is selected, an exception is thrown.
     * </p>
     *
     * <p>
     * After the selection is validated, the method initializes the `CodeBreaker`
     * instance with the selected algorithm and the current frame, transitioning
     * to the next stage of the application.
     * </p>
     *
     * @throws IllegalArgumentException if no algorithm is selected.
     */
    private void registerProceedHandler() {
        proceedButton.addActionListener(event -> {
            Mastermind.log.trace("Proceed button pressed");

            if (randomButton.isSelected()) {
                Mastermind.log.info("Random Code Maker selected");

                new CodeMaker(frame);
            } else if (preProgrammedButton.isSelected()) {
                Mastermind.log.info("Pre-Programmed Code Maker selected");

                // TODO: add pre-programmed code maker
            } else {
                Mastermind.log.fatal("No code maker algorithm selected");
            }
        });
    }
}
