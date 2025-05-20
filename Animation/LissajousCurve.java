import javax.swing.*;
import java.awt.*;

public class LissajousCurve extends JPanel {
    private double t = 0; // Parameter for animation

    public LissajousCurve() {
        Timer timer = new Timer(30, e -> {
            t += 0.05; // Increment parameter for motion
            repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        setBackground(Color.BLACK);
        g2d.setColor(Color.CYAN);
        g2d.setStroke(new BasicStroke(2));

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        // Parameters for Lissajous curve
        int A = 150; // Amplitude for x
        int B = 150; // Amplitude for y
        int a = 3;   // Frequency for x
        int b = 2;   // Frequency for y
        double delta = Math.PI / 2; // Phase difference

        for (double i = 0; i <= t; i += 0.01) {
            int x = centerX + (int) (A * Math.sin(a * i + delta));
            int y = centerY + (int) (B * Math.sin(b * i));

            g2d.fillOval(x, y, 2, 2);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Lissajous Curve");
        LissajousCurve curve = new LissajousCurve();
        frame.add(curve);
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
