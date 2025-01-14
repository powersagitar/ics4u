package mastermind.gui.panels;

import javax.swing.*;
import mastermind.gui.scenes.GameModeSelector;

public class HomeButton {
    private final static JButton HOME_BUTTON;
    private final static JPanel BUTTON_PANEL;
    private static boolean isHandlerRegistered = false;

    static {
        HOME_BUTTON = new JButton("Home");
        BUTTON_PANEL = new JPanel();
        BUTTON_PANEL.add(HOME_BUTTON);
    }

    public static void registerHomeHandlers(final JFrame frame) {
        if (!isHandlerRegistered) {
            HOME_BUTTON.addActionListener(event -> new GameModeSelector(frame));
            isHandlerRegistered = true;
        }
    }

    public static void drawHomeButton(final JFrame frame) {
        frame.add(BUTTON_PANEL);
    }
}
