package mastermind.gui.panels;

import javax.swing.*;
import mastermind.gui.scenes.GameModeSelector;

public class HomeButton {
    private final static JButton homeButton;
    private final static JPanel buttonPanel;

    static {
        homeButton = new JButton("Home");
        buttonPanel = new JPanel();
        buttonPanel.add(homeButton);
    }

    public static void registerHomeHandlers(final JFrame frame) {
        homeButton.addActionListener(event -> {
            new GameModeSelector(frame);
        });
    }

    public static void drawHomeButton(final JFrame frame) {
        frame.add(buttonPanel);
    }
}
