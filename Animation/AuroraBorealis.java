import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;
import java.util.Random;

public class AuroraBorealis extends JPanel {
    private double t = 0; // Time for animation

    public AuroraBorealis() {
        Timer timer = new Timer(30, e -> {
            t += 0.05; // Increment time
            repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        setBackground(Color.BLACK);

        // Draw multiple bands of aurora
        for (int i = 0; i < 5; i++) {
            drawAuroraBand(g2d, i * 30, new Color(50 + i * 40, 200 - i * 20, 255 - i * 30, 100));
        }
    }

    private void drawAuroraBand(Graphics2D g2d, int offset, Color color) {
        g2d.setColor(color);

        int width = getWidth();
        int height = getHeight();

        Path2D.Double path = new Path2D.Double();
        path.moveTo(0, height / 2 + offset);

        Random random = new Random();
        for (int x = 0; x < width; x += 10) {
            double y = height / 2 + offset + 50 * Math.sin((x + t * 100) * 0.01) + random.nextInt(10) - 5;
            path.lineTo(x, y);
        }

        path.lineTo(width, height);
        path.lineTo(0, height);
        path.closePath();

        g2d.fill(path);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Aurora Borealis");
        AuroraBorealis aurora = new AuroraBorealis();
        frame.add(aurora);
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
