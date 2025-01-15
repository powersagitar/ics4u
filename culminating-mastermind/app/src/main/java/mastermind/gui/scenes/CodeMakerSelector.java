package mastermind.gui.scenes;

import mastermind.Mastermind;
import mastermind.core.CodeFactory;
import mastermind.gui.panels.Help;
import mastermind.gui.panels.HomeButton;
import mastermind.utils.Log;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

/**
 * The Code Maker selection screen.
 */
public class CodeMakerSelector extends Scene {
    /**
     * The panel containing the code maker selection options.
     */
    private final JPanel selectorPanel = new JPanel();

    /**
     * The radio buttons for the user to select the code maker algorithm.
     */
    private final JRadioButton randomButton = new JRadioButton("Random Code", true);
    private final JRadioButton preProgrammedButton = new JRadioButton("Pre-Programmed Code");

    /**
     * The "Proceed" button that allows the user to proceed to the Code Maker gameplay.
     */
    private final JButton proceedButton = new JButton("Proceed");

    /**
     * Constructs the CodeMakerSelector scene.
     *
     * @param frame The main frame of the application.
     */
    public CodeMakerSelector(final JFrame frame) {
        super(frame);

        // Log the creation of the CodeMakerSelector scene
        Log.info("Creating CodeMakerSelector scene");

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

        // Register handlers for the home button
        HomeButton.registerHomeHandlers(frame);

        // Register handlers for the help button
        Help.registerHelpHandlers();

        // Register the proceed button handler
        registerProceedHandler();

        // Refresh the frame to display the updated components
        refreshFrame();
    }

    /**
     * Configures and renders the selector panel for the user interface.
     *
     * <p>
     * This method sets the layout of the selector panel to a vertical box layout,
     * aligns it to the center, and adds a titled border. The panel is then added
     * to the parent frame.
     * </p>
     */
    private void drawSelectorPanel() {
        selectorPanel.setLayout(new BoxLayout(selectorPanel, BoxLayout.Y_AXIS));
        selectorPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectorPanel.setBorder(BorderFactory.createTitledBorder("Code Maker Options"));
        Log.debug("Add selector panel to frame");
        frame.add(selectorPanel);
    }

    /**
     * Configures and renders the selector buttons for the user interface.
     *
     * <p>
     * This method creates a button group to ensure only one radio button can be
     * selected at a time. It then adds the radio buttons to the button group and
     * the selector panel.
     * </p>
     */
    private void drawSelectorButtons() {
        final ButtonGroup selectorButtonGroup = new ButtonGroup();
        selectorButtonGroup.add(randomButton);
        selectorButtonGroup.add(preProgrammedButton);

        Log.debug("Add selector buttons to selector panel");
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
     * Registers the action listener for the "Proceed" button.
     *
     * <p>
     * This method adds an action listener to the proceed button that handles
     * the button click event. Depending on the selected radio button, it
     * initializes the CodeMaker with either a random code or a pre-programmed
     * code from a file. If an exception occurs while loading the pre-programmed
     * code, it logs a fatal error.
     * </p>
     */
    private void registerProceedHandler() {
        proceedButton.addActionListener(event -> {
            Log.trace("Proceed button pressed");

            // Check if the random code maker is selected
            if (randomButton.isSelected()) {
                Log.info("Random Code Maker selected");
                new CodeMaker(frame, CodeFactory.getRandom());

            // Check if the pre-programmed code maker is selected
            } else if (preProgrammedButton.isSelected()) {
                Log.info("Pre-Programmed Code Maker selected");

                try {
                    new CodeMaker(frame, CodeFactory.getRandomFromFile(
                            Mastermind.CODEMAKER_PREDEFINED_CODES));
                } catch (final FileNotFoundException | IllegalArgumentException e) {
                    Log.fatal("CodeMaker predefined code: " + e.getMessage());
                }
            } else {
                Log.fatal("No code maker algorithm selected");
            }
        });
    }
}