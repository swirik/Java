import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SpiralGalaxy extends JPanel {
    private List<Point> stars = new ArrayList<>();
    private double angle = 0;

    public SpiralGalaxy() {
        // Generate initial star positions
        for (int i = 0; i < 500; i++) {
            double t = i * 0.1; // Spiral parameter
            double r = 5 * t;   // Radial distance
            double x = r * Math.cos(t);
            double y = r * Math.sin(t);
            stars.add(new Point((int) x, (int) y));
        }

        Timer timer = new Timer(50, e -> {
            angle += 0.05; // Increase rotation angle
            repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        setBackground(Color.BLACK);
        g2d.setColor(Color.WHITE);

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        for (Point star : stars) {
            double xRot = star.x * Math.cos(angle) - star.y * Math.sin(angle);
            double yRot = star.x * Math.sin(angle) + star.y * Math.cos(angle);

            int x = (int) (centerX + xRot);
            int y = (int) (centerY + yRot);

            g2d.fillOval(x, y, 2, 2);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Spiral Galaxy");
        SpiralGalaxy galaxy = new SpiralGalaxy();
        frame.add(galaxy);
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
