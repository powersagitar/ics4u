package src.mastermind.scenes;

import src.mastermind.Mastermind;

import javax.swing.*;

public class SceneFactory {
    public static JFrame createDefaultScene() {
        final JFrame frame = new JFrame();

        frame.setSize(Mastermind.CANVAS_DIMENSION);
        frame.setTitle("Mastermind");
        frame.setResizable(true);
        frame.setVisible(true);

        return frame;
    }
}