package mastermind.gui.panels;

import mastermind.gui.scenes.GameModeSelector;

import javax.swing.*;

/**
 * A class to create a home button that will take the user back to GameModeSelector.
 */
public class HomeButton {
    /**
     * A button to go back to the home screen on frames that are not the home page.
     */
    private final static JButton HOME_BUTTON;

    /**
     * A panel to hold the home button.
     * This is to make it easier to add the button to the frame.
     */
    private final static JPanel BUTTON_PANEL;

    /**
     * A flag to check if the handler is already registered or not.
     * This is to prevent multiple registration of the same handler, and
     * therefore creating the GameModeSelector frame multiple times.
     */
    private static boolean isHandlerRegistered = false;

    // Static block to initialize the static variables
    static {
        // Initialize the home button with the label "Home"
        HOME_BUTTON = new JButton("Home");

        // Initialize the button panel and add the home button to it
        BUTTON_PANEL = new JPanel();
        BUTTON_PANEL.add(HOME_BUTTON);
    }

    /**
     * Private constructor to prevent instantiation.
     */
    private HomeButton() {
    }

    /**
     * Register the home button handler.
     * This method adds an action listener to the home button that creates a new GameModeSelector frame.
     * It ensures that the handler is only registered once by checking the isHandlerRegistered flag.
     *
     * @param frame Frame of the program.
     */
    public static void registerHomeHandlers(final JFrame frame) {
        // Check if the handler is already registered
        if (!isHandlerRegistered) {
            // Add an action listener to the home button
            HOME_BUTTON.addActionListener(event -> new GameModeSelector(frame));

            // Set the flag to true to indicate that the handler is registered
            isHandlerRegistered = true;
        }
    }

    /**
     * Draw the home button on the frame.
     * This method adds the button panel (containing the home button) to the specified frame.
     *
     * @param frame Frame of the program.
     */
    public static void drawHomeButton(final JFrame frame) {
        // Add the button panel to the frame
        frame.add(BUTTON_PANEL);
    }
}