package mastermind.gui.scenes;

import mastermind.Mastermind;

import javax.swing.*;
import java.awt.*;
import mastermind.gui.panels.Help;

public class GameModeSelector extends Scene {
    private final JPanel selectorPanel = new JPanel();
    private final JRadioButton codeBreakerButton = new JRadioButton("CODEBREAKER: Computer Guesses, Player Makes Code", true);
    private final JRadioButton codeMakerButton = new JRadioButton("CODEMAKER: Player Guesses, Computer Makes Code");
    private final JButton proceedButton = new JButton("Proceed");

    /**
     * Creates a new GameModeSelector object that represents a scene for selecting the game mode.
     *
     * <p>
     * This constructor initializes the UI components required for the game mode selection. It configures
     * the selector panel and the proceed button, adds them to the provided JFrame, and refreshes the frame
     * to ensure the UI is updated.
     * </p>
     *
     * @param frame The JFrame to which the game mode selection UI components will be added.
     */
    public GameModeSelector(final JFrame frame) {
        super(frame);

        Mastermind.log.info("Creating GameModeSelector scene");

        final int LINE_SEPARATOR = 20;
        final int CENTER_ALIGNMENT = 130;
        frame.add(Box.createRigidArea(new Dimension(10,CENTER_ALIGNMENT)));
        drawWelcomeMessage();
        frame.add(Box.createRigidArea(new Dimension(10,LINE_SEPARATOR)));
        drawSelectorPanel();
        drawProceedButton();

        frame.add(selectorPanel);
        frame.add(proceedButton);

        Help.drawHelpButton(frame);
        Help.registerHelpHandlers();

        refreshFrame();
    }

    /**
     * Displays a welcome message to the user on the game mode selection screen.
     *
     * <p>
     * This method adds a label to the frame welcoming the user to the game mode selection screen.
     * The label is centrally aligned within the frame.
     * </p>
     */
    private void drawWelcomeMessage() {
        final JLabel welcomeMessage = new JLabel("WELCOME TO MASTERMIND!");
        welcomeMessage.setFont(new Font("Default", Font.BOLD, 20));
        welcomeMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(welcomeMessage);
    }

    /**
     * Configures and sets up the selector panel for game mode selection.
     *
     * <p>
     * This method initializes the layout, alignment, and border for the selector panel.
     * It adds the game mode radio buttons (Code Breaker and Code Maker) to the panel
     * and groups these buttons using a ButtonGroup to ensure only one can be selected
     * at a time.
     * </p>
     *
     * <p>
     * The selector panel is visually structured to display the game modes vertically
     * with a centered alignment, and labeled with a titled border named "Game Modes".
     * </p>
     */
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

    /**
     * Configures and initializes the "Proceed" button for the game mode selection screen.
     *
     * <p>
     * This method sets the alignment of the "Proceed" button and attaches an event listener to it.
     * The button's action listener determines which game mode has been selected
     * by the user (Code Breaker or Code Maker) and performs the appropriate action:
     * - If the "Code Breaker" mode is selected, a new instance of `CodeBreakerSelector` is created.
     * - If the "Code Maker" mode is selected, a new instance of `CodeMaker` is created.
     * - If no mode is selected, an `IllegalArgumentException` is thrown.
     * </p>
     *
     * <p>
     * The "Proceed" button is aligned to the center for consistency with the overall UI design.
     * </p>
     */
    private void drawProceedButton() {
        proceedButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        proceedButton.addActionListener(event -> {
            Mastermind.log.trace("Proceed button pressed");

            if (codeBreakerButton.isSelected()) {
                Mastermind.log.debug("Code breaker mode selected");

                new CodeBreakerSelector(frame);
            } else if (codeMakerButton.isSelected()) {
                Mastermind.log.debug("Code maker mode selected");

                new CodeMaker(frame);
            } else {
                Mastermind.log.fatal("No game mode selected");
            }
        });
    }
}
