package src.mastermind.scenes;

import src.mastermind.Mastermind;

import javax.swing.*;

public abstract class Scene {
    protected final JFrame frame;

    public static JFrame createDefaultScene() {
        final JFrame frame = new JFrame();
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        frame.setSize(Mastermind.CANVAS_DIMENSION);
        frame.setTitle("Mastermind");
        frame.setResizable(true);
        frame.setVisible(true);

        return frame;
    }

    Scene(final JFrame frame) {
        this.frame = frame;
        this.frame.getContentPane().removeAll();
    }

    void refreshFrame() {
        frame.repaint();
        frame.revalidate();
    }
}