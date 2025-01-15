/*
    Authors: Kenneth Chen, Mohan Dong
    Date: 01/15/2024
    Title: Help.java
 */

package mastermind.gui.panels;

import javax.swing.*;
import java.awt.*;

/**
 * Help class that displays the help button and the help popup.
 */
public class Help {
    /**
     * Help button that displays the help popup.
     */
    private final static JButton HELP_BUTTON = new JButton("Help");

    /**
     * Help panel that contains the help button.
     */
    private final static JPanel HELP_PANEL = new JPanel();

    /**
     * Popup frame that displays the help popup.
     */
    private final static JFrame POPUP_FRAME = new JFrame("Help");

    /**
     * Title label for the help popup.
     */
    private final static JLabel HELP_TITLE = new JLabel("Mastermind Help", SwingConstants.CENTER);

    /**
     * Button to display the gameplay instructions.
     */
    private final static JButton GAMEPLAY_INSTRUCTIONS_BUTTON = new JButton("Gameplay Instructions");

    /**
     * Button to display the navigation instructions.
     */
    private final static JButton NAVIGATION_INSTRUCTIONS_BUTTON = new JButton("Navigation Instructions");

    /**
     * Panel that contains the buttons for the help popup.
     */
    private final static JPanel POPUP_PANEL = new JPanel();

    // Static block to initialize the help popup frame and panel
    static {
        // Set the layout of the popup frame to BorderLayout
        POPUP_FRAME.setLayout(new BorderLayout());

        // Set the layout of the popup panel to BoxLayout (Y_AXIS)
        POPUP_PANEL.setLayout(new BoxLayout(POPUP_PANEL, BoxLayout.Y_AXIS));

        // Set the font of the help title label
        HELP_TITLE.setFont(new Font("Default", Font.BOLD, 20));

        // Add the help title label to the popup panel
        POPUP_PANEL.add(HELP_TITLE);

        // Add a rigid area (spacer) to the popup panel
        POPUP_PANEL.add(Box.createRigidArea(new Dimension(0, 10)));

        // Add the gameplay instructions button to the popup panel
        POPUP_PANEL.add(GAMEPLAY_INSTRUCTIONS_BUTTON);

        // Add another rigid area (spacer) to the popup panel
        POPUP_PANEL.add(Box.createRigidArea(new Dimension(0, 10)));

        // Add the navigation instructions button to the popup panel
        POPUP_PANEL.add(NAVIGATION_INSTRUCTIONS_BUTTON);

        // Add the popup panel to the center of the popup frame
        POPUP_FRAME.add(POPUP_PANEL, BorderLayout.CENTER);

        // Add the help button to the help panel
        HELP_PANEL.add(HELP_BUTTON);

        // Register action listeners for the help button and instruction buttons
        registerHelpHandlers();
        registerGameplayInstructionsHandler();
        registerNavigationInstructionsHandler();
    }

    /**
     * Private constructor to prevent instantiation.
     */
    private Help() {
    }

    /**
     * Draws the help button on the frame.
     *
     * @param frame the frame to draw the help button on
     */
    public static void drawHelpButton(final JFrame frame) {
        // Add the help panel (containing the help button) to the frame
        frame.add(HELP_PANEL);
    }

    /**
     * Registers the help button action listeners.
     */
    public static void registerHelpHandlers() {
        // Add an action listener to the help button
        HELP_BUTTON.addActionListener(event -> {
            // Set the size of the popup frame
            POPUP_FRAME.setSize(400, 600);

            // Set the default close operation of the popup frame
            POPUP_FRAME.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            // Make the popup frame visible
            POPUP_FRAME.setVisible(true);
        });
    }

    /**
     * Registers the gameplay instructions button action listener.
     */
    private static void registerGameplayInstructionsHandler() {
        // Add an action listener to the gameplay instructions button
        GAMEPLAY_INSTRUCTIONS_BUTTON.addActionListener(event -> showGameplayInstructions());
    }

    /**
     * Registers the navigation instructions button action listener.
     */
    private static void registerNavigationInstructionsHandler() {
        // Add an action listener to the navigation instructions button
        NAVIGATION_INSTRUCTIONS_BUTTON.addActionListener(event -> showNavigationInstructions());
    }

    /**
     * Shows the navigation instructions in the help popup.
     */
    private static void showNavigationInstructions() {
        // Remove all components from the popup panel
        POPUP_PANEL.removeAll();

        // Create a label with the navigation instructions text
        JLabel instructionsLabel = new JLabel("<html>" + getNavigationInstructionsText() + "</html>", SwingConstants.CENTER);

        // Set the border of the instructions label
        instructionsLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create a back button to return to the help menu
        JButton backButton = new JButton("Back");

        // Add an action listener to the back button
        backButton.addActionListener(event -> showHelpMenu());

        // Set the layout of the popup panel to BorderLayout
        POPUP_PANEL.setLayout(new BorderLayout());

        // Add the instructions label to the center of the popup panel
        POPUP_PANEL.add(instructionsLabel, BorderLayout.CENTER);

        // Add the back button to the bottom of the popup panel
        POPUP_PANEL.add(backButton, BorderLayout.SOUTH);

        // Revalidate and repaint the popup panel to update the UI
        POPUP_PANEL.revalidate();
        POPUP_PANEL.repaint();
    }

    /**
     * Shows the gameplay instructions in the help popup.
     */
    private static void showGameplayInstructions() {
        // Remove all components from the popup panel
        POPUP_PANEL.removeAll();

        // Create a label with the gameplay instructions text
        JLabel instructionsLabel = new JLabel("<html>" + getGameplayInstructionsText() + "</html>", SwingConstants.CENTER);

        // Set the border of the instructions label
        instructionsLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create a back button to return to the help menu
        JButton backButton = new JButton("Back");

        // Add an action listener to the back button
        backButton.addActionListener(event -> showHelpMenu());

        // Set the layout of the popup panel to BorderLayout
        POPUP_PANEL.setLayout(new BorderLayout());

        // Add the instructions label to the center of the popup panel
        POPUP_PANEL.add(instructionsLabel, BorderLayout.CENTER);

        // Add the back button to the bottom of the popup panel
        POPUP_PANEL.add(backButton, BorderLayout.SOUTH);

        // Revalidate and repaint the popup panel to update the UI
        POPUP_PANEL.revalidate();
        POPUP_PANEL.repaint();
    }

    /**
     * Shows the help menu in the help popup.
     */
    private static void showHelpMenu() {
        // Remove all components from the popup panel
        POPUP_PANEL.removeAll();

        // Set the layout of the popup panel to BoxLayout (Y_AXIS)
        POPUP_PANEL.setLayout(new BoxLayout(POPUP_PANEL, BoxLayout.Y_AXIS));

        // Add the help title label to the popup panel
        POPUP_PANEL.add(HELP_TITLE);

        // Add a rigid area (spacer) to the popup panel
        POPUP_PANEL.add(Box.createRigidArea(new Dimension(0, 10)));

        // Add the gameplay instructions button to the popup panel
        POPUP_PANEL.add(GAMEPLAY_INSTRUCTIONS_BUTTON);

        // Add another rigid area (spacer) to the popup panel
        POPUP_PANEL.add(Box.createRigidArea(new Dimension(0, 10)));

        // Add the navigation instructions button to the popup panel
        POPUP_PANEL.add(NAVIGATION_INSTRUCTIONS_BUTTON);

        // Revalidate and repaint the popup panel to update the UI
        POPUP_PANEL.revalidate();
        POPUP_PANEL.repaint();
    }

    /**
     * Returns the gameplay instructions text.
     * @return the gameplay instructions text
     */
    private static String getGameplayInstructionsText() {
        return """
            Mastermind is a code-breaking game where a setter sets a secret code and a guesser must guess that secret code.
            The code consists of a sequence of 4 colored pegs, and the guesser must guess the correct sequence.
            After each guess, the guesser receives feedback on the correctness of their guess.
            A black peg indicates a correct color in the correct position,
            while a white peg indicates a correct color in the wrong position.
            The guesser has 10 guesses to crack the code and win the game.
            
            Good luck!
            """.replace("\n", "<br>");
    }

    /**
     * Returns the navigation instructions text.
     * @return the navigation instructions text
     */
    private static String getNavigationInstructionsText() {
        return """
            In all screens, you can press 'Proceed' to continue to the next screen.
            
            In the first screen, you can choose to play as the code maker or the code breaker.
            As the code breaker, you will set a secret code for the code breaker (the computer) to guess.
            As the code maker, you will guess the secret code that the code maker (the computer) creates.
            
            If you selected code breaker, you will choose the difficulty of the computer guesser algorithm.
            You will secretly select a code for the computer to guess.
            You will then view the code breaker's guesses and provide feedback on each guess, using the white and black pegs.
            After the game, then you will be prompted to input the correct code, and the game will double-check to see if you have inputted the correct hints.
            
            If you selected code maker, you will guess the code that the computer secretly selected.
            You will input your guesses and receive feedback on each guess, using the white and black pegs.
            
            There is a home button on each screen (except the starting screen) that will take you back to the starting screen.
            
            Please enjoy!
            """.replace("\n", "<br>");
    }
}