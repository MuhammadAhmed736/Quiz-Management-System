package util;
 import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;
public class RoundedPanel extends JPanel {
    private int arcWidth;
    private int arcHeight;

    public RoundedPanel(int arcWidth, int arcHeight) {
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;
        setOpaque(false); // Make the panel transparent
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        // Draw a rounded rectangle with the specified arc width and arc height
        g2d.setColor(getBackground());
        g2d.fillRoundRect(0, 0, width, height, arcWidth, arcHeight);

        g2d.dispose();
    }
}

