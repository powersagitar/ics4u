package mastermind;

import javax.swing.*;
import java.awt.*;

import mastermind.gui.scenes.*;

public class Mastermind {
    public final static int TOTAL_COLORS = 6;
    public final static int CODE_LENGTH = 4;
    public final static int MAX_GUESSES = 10;
    public final static Dimension CANVAS_DIMENSION = new Dimension(640, 480);

    public static void main(String[] args) {
        final JFrame frame = Scene.createDefaultScene();
        new GameModeSelector(frame);
    }
}
