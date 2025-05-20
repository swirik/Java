import javax.swing.*;
import java.awt.*;

public class HypnoticCircles extends JPanel {
    private int radius = 10; // Starting radius
    private boolean growing = true; // Direction of growth

    public HypnoticCircles() {
        Timer timer = new Timer(30, e -> {
            // Adjust radius
            if (growing) {
                radius += 2;
                if (radius > 200) growing = false;
            } else {
                radius -= 2;
                if (radius < 10) growing = true;
            }
            repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        setBackground(Color.BLACK);
        g2d.setColor(Color.MAGENTA);
        g2d.setStroke(new BasicStroke(2));

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        for (int r = radius; r > 0; r -= 20) {
            g2d.drawOval(centerX - r, centerY - r, r * 2, r * 2);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Hypnotic Circles");
        HypnoticCircles circles = new HypnoticCircles();
        frame.add(circles);
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
