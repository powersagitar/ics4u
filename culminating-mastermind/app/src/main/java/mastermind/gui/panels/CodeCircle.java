package mastermind.gui.panels;

import javax.swing.*;
import java.awt.*;

public class CodeCircle extends JPanel {
    final Color color;

    public CodeCircle(final Color color) {
        super();

        this.color = color;
        this.setPreferredSize(new Dimension(20, 20));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(color);
        g2d.fillOval(0, 0, getWidth(), getHeight());
    }
}
