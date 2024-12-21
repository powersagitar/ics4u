package src.mastermind.scenes;

import javax.swing.*;

public abstract class Scene {
    protected final JFrame frame;

    Scene(final JFrame frame) {
        this.frame = frame;
        this.frame.getContentPane().removeAll();
        this.frame.setLayout(new BoxLayout(this.frame.getContentPane(), BoxLayout.Y_AXIS));
    }

    void refresh() {
        frame.repaint();
        frame.revalidate();
    }
}