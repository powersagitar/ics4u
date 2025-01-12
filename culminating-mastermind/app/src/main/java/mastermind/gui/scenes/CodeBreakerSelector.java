package mastermind.gui.scenes;

import mastermind.Mastermind;
import mastermind.core.solvers.DonaldKnuthAlgorithm;
import mastermind.core.solvers.EasyAlgorithm;
import mastermind.core.solvers.MediumAlgorithm;
import mastermind.gui.panels.Help;
import mastermind.gui.panels.HomeButton;

import javax.swing.*;
import java.awt.*;

/**
 * Represents the scene where the user selects a code-breaking algorithm.
 */
public class CodeBreakerSelector extends Scene {
    /**
     * The panel containing the algorithm selection options.
     */
    private final JPanel selectorPanel = new JPanel();

    /**
     * The radio button representing the Donald Knuth 5-Guess Algorithm.
     */
    private final JRadioButton donaldKnuthButton = new JRadioButton("Donald Knuth 5-Guess Algorithm", true);

    /**
     * The radio buttons representing the medium algorithm.
     */
    private final JRadioButton mediumAlgoButton = new JRadioButton("Medium Algorithm");

    /**
     * The radio buttons representing the basic algorithm.
     */
    private final JRadioButton basicAlgoButton = new JRadioButton("Basic Algorithm");

    /**
     * The button to proceed to the next scene.
     */
    private final JButton proceedButton = new JButton("Proceed");

    /**
     * Constructs a CodeBreakerSelector, which provides a user interface to select
     * a code-breaking algorithm and proceed to the next scene.
     *
     * <p>
     * This constructor initializes the selection panel with several algorithm options,
     * a proceed button, and registers an event handler to handle user interaction.
     * It also refreshes the parent frame to reflect the changes.
     *
     * @param frame The parent JFrame where this selector will be displayed.
     */
    public CodeBreakerSelector(final JFrame frame) {
        super(frame);

        Mastermind.LOG.info("Creating CodeBreakerSelector scene");

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
     *
     * <p>
     * This configuration provides a user-friendly interface for selecting the desired algorithm.
     */
    private void drawSelectorButtons() {
        final ButtonGroup selectorButtonGroup = new ButtonGroup();
        selectorButtonGroup.add(donaldKnuthButton);
        selectorButtonGroup.add(mediumAlgoButton);
        selectorButtonGroup.add(basicAlgoButton);

        selectorPanel.add(donaldKnuthButton);
        selectorPanel.add(mediumAlgoButton);
        selectorPanel.add(basicAlgoButton);
    }

    /**
     * Configures and renders the "Proceed" button for the user interface.
     *
     * <p>
     * This method sets the alignment of the proceed button to ensure
     * it is centered horizontally within its container. The button is then
     * added to the parent frame, making it available for user interaction.
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
     *
     * <p>
     * After the selection is validated, the method initializes the `CodeBreaker`
     * instance with the selected algorithm and the current frame, transitioning
     * to the next stage of the application.
     *
     * @throws IllegalArgumentException if no algorithm is selected.
     */
    private void registerProceedHandler() {
        proceedButton.addActionListener(event -> {
            Mastermind.LOG.trace("Proceed button pressed");

            if (donaldKnuthButton.isSelected()) {
                Mastermind.LOG.info("Donald Knuth 5-Guess Algorithm selected");

                new CodeBreaker(frame, new DonaldKnuthAlgorithm());
            } else if (mediumAlgoButton.isSelected()) {
                Mastermind.LOG.info("Medium Algorithm selected");

                new CodeBreaker(frame, new MediumAlgorithm());
            } else if (basicAlgoButton.isSelected()) {
                Mastermind.LOG.info("Basic Algorithm selected");

                new CodeBreaker(frame, new EasyAlgorithm());
            } else {
                Mastermind.LOG.fatal("No code breaker algorithm selected");
            }
        });
    }
}
