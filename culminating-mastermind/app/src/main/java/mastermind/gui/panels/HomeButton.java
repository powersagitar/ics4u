package mastermind.gui.panels;

import javax.swing.*;
import mastermind.gui.scenes.GameModeSelector;
import mastermind.gui.scenes.Scene;

public class HomeButton {
    private final static JButton homeButton;
    private final static JPanel buttonPanel;

    static {
        homeButton = new JButton("Home");
        buttonPanel = new JPanel();
        buttonPanel.add(homeButton);
    }

    public static void registerHomeHandlers() {
        homeButton.addActionListener(event -> {
            final JFrame gameModeSelector = Scene.createDefaultScene();
            new GameModeSelector(gameModeSelector);
        });
    }

    public static void drawHomeButton(final JFrame frame) {
        frame.add(buttonPanel);
    }
}
