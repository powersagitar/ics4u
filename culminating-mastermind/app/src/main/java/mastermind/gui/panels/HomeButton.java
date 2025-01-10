package mastermind.gui.panels;

import javax.swing.*;
import mastermind.gui.scenes.GameModeSelector;
import mastermind.gui.scenes.Scene;

public class HomeButton {
    private final static JPanel buttonPanel = new JPanel();

    static {
        final JButton homeButton = new JButton("Home");

        homeButton.addActionListener(event -> {
            final JFrame gameModeSelector = Scene.createDefaultScene();
            new GameModeSelector(gameModeSelector);
        });

        buttonPanel.add(homeButton);
    }

    public static void drawHomeButton(final JFrame frame) {
        frame.add(buttonPanel);
    }
}
