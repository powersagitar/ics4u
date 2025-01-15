/*
    Authors: Mohan Dong
    Date: 01/15/2024
    Title: CodeBreakerSelector.java
 */

package mastermind.gui.scenes;

// Import necessary classes and packages
import mastermind.core.solvers.DonaldKnuthAlgorithm;
import mastermind.core.solvers.EasyAlgorithm;
import mastermind.core.solvers.MediumAlgorithm;
import mastermind.gui.panels.Help;
import mastermind.gui.panels.HomeButton;
import mastermind.utils.Log;

import javax.swing.*;
import java.awt.*;

/**
 * Prompts the user to select a code-breaking algorithm.
 * <p>
 * Options:
 * <br>
 * - Donald Knuth 5-Guess Algorithm
 * <br>
 * - Medium Algorithm
 * <br>
 * - Basic Algorithm
 */
public class CodeBreakerSelector extends Scene {
    /**
     * The panel containing the algorithm selection options.
     */
    private final JPanel selectorPanel = new JPanel();

    /**
     * The radio button representing the Donald Knuth 5-Guess Algorithm.
     */
    private final JRadioButton donaldKnuthButton = new JRadioButton("Donald " +
        "Knuth 5-Guess Algorithm - Hard", true);

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

        // Log the creation of the CodeBreakerSelector scene
        Log.info("Creating CodeBreakerSelector scene");

        // Draw the selector panel
        drawSelectorPanel();

        // Draw the selector buttons
        drawSelectorButtons();

        // Draw the proceed button
        drawProceedButton();

        // Draw the home button
        HomeButton.drawHomeButton(frame);

        // Draw the help button
        Help.drawHelpButton(frame);

        // Register event handlers for the home button
        HomeButton.registerHomeHandlers(frame);

        // Register event handlers for the help button
        Help.registerHelpHandlers();

        // Register the event handler for the proceed button
        registerProceedHandler();

        // Refresh the frame to apply changes
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
        // Set the layout of the selector panel to a vertical box layout
        selectorPanel.setLayout(new BoxLayout(selectorPanel, BoxLayout.Y_AXIS));
        // Center align the selector panel
        selectorPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        // Add a titled border to the selector panel
        selectorPanel.setBorder(BorderFactory.createTitledBorder("Code Breaker Algorithms"));

        // Add the selector panel to the parent frame
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
        // Create a button group to enforce exclusivity among the radio buttons
        final ButtonGroup selectorButtonGroup = new ButtonGroup();
        // Add the Donald Knuth button to the button group
        selectorButtonGroup.add(donaldKnuthButton);
        // Add the medium algorithm button to the button group
        selectorButtonGroup.add(mediumAlgoButton);
        // Add the basic algorithm button to the button group
        selectorButtonGroup.add(basicAlgoButton);

        // Add the Donald Knuth button to the selector panel
        selectorPanel.add(donaldKnuthButton);
        // Add the medium algorithm button to the selector panel
        selectorPanel.add(mediumAlgoButton);
        // Add the basic algorithm button to the selector panel
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
        // Center align the proceed button
        proceedButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        // Add the proceed button to the parent frame
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
        // Add an action listener to the proceed button
        proceedButton.addActionListener(event -> {
            // Log that the proceed button was pressed
            Log.trace("Proceed button pressed");

            // Check if the Donald Knuth button is selected
            if (donaldKnuthButton.isSelected()) {
                // Log the selection of the Donald Knuth algorithm
                Log.info("Donald Knuth 5-Guess Algorithm selected");

                // Initialize the CodeBreaker with the Donald Knuth algorithm
                new CodeBreaker(frame, new DonaldKnuthAlgorithm());
            } else if (mediumAlgoButton.isSelected()) {
                // Log the selection of the medium algorithm
                Log.info("Medium Algorithm selected");

                // Initialize the CodeBreaker with the medium algorithm
                new CodeBreaker(frame, new MediumAlgorithm());
            } else if (basicAlgoButton.isSelected()) {
                // Log the selection of the basic algorithm
                Log.info("Basic Algorithm selected");

                // Initialize the CodeBreaker with the basic algorithm
                new CodeBreaker(frame, new EasyAlgorithm());
            } else {
                // Log a fatal error if no algorithm is selected
                Log.fatal("No code breaker algorithm selected");
            }
        });
    }
}
