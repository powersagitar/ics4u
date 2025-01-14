/*
    Authors: Mohan Dong, Kenneth Chen
    Date: 01/15/2024
    Title: CodeCircle.java
 */

package mastermind.gui.panels;

import javax.swing.*;
import java.awt.*;

/**
 * A class that represents a colored circle that is one of the four colors in
 * a code sequence.
 */
public class CodeCircle extends JPanel {
    /**
     * The color of the circle.
     */
    final Color color;

    /**
     * Draws a new CodeCircle with the given color.
     *
     * @param color The color of the circle.
     */
    public CodeCircle(final Color color) {
        // constructor to create a new code circle with the given color
        super();

        // set the color of the circle
        this.color = color;
        // set the preferred size of the circle
        this.setPreferredSize(new Dimension(20, 20));
    }

    /**
     * Paints the circle with the given color.
     *
     * @param g The graphics object.
     */
    @Override
    protected void paintComponent(Graphics g) {
        // method to paint the circle with the given color
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(color);
        g2d.fillOval(0, 0, getWidth(), getHeight());
    }
}
