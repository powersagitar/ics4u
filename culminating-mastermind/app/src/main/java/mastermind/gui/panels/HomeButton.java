package mastermind.gui.panels;

import mastermind.gui.scenes.GameModeSelector;

import javax.swing.*;

/**
 * A class to create a home button that will take the user back to GameModeSelector
 */
public class HomeButton {
    /**
     * A button to go back to the home screen on frames that are not the home
     * page.
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

    static {
        HOME_BUTTON = new JButton("Home");
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
     *
     * @param frame Frame of the program.
     */
    public static void registerHomeHandlers(final JFrame frame) {
        if (!isHandlerRegistered) {
            HOME_BUTTON.addActionListener(event -> new GameModeSelector(frame));
            isHandlerRegistered = true;
        }
    }

    /**
     * Draw the home button on the frame.
     * @param frame Frame of the program.
     */
    public static void drawHomeButton(final JFrame frame) {
        frame.add(BUTTON_PANEL);
    }
}
