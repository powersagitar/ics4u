package mastermind.gui.panels;

import javax.swing.*;
import mastermind.gui.scenes.GameModeSelector;
import mastermind.gui.scenes.Scene;
public class HomeButton {
    final private JButton homeButton;
    final private JPanel buttonPanel = new JPanel();

    public HomeButton() {
        homeButton = new JButton("Home");
        buttonPanel.add(homeButton);
    }

    public void registerHomeHandlers() {
        homeButton.addActionListener( event -> {
            final JFrame gameModeSelector = Scene.createDefaultScene();
            new GameModeSelector(gameModeSelector);
        });
    }

    public void drawHomeButton(final JFrame frame) {
        frame.add(buttonPanel);
    }


}
