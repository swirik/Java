import javax.swing.*;
import java.awt.*;

public class SineWave extends JPanel {
    private double t = 0; // Phase for animation

    public SineWave() {
        Timer timer = new Timer(30, e -> {
            t += 0.1; // Increment phase
            repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        setBackground(Color.BLACK);
        g2d.setColor(Color.GREEN);
        g2d.setStroke(new BasicStroke(2));

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        for (int x = 0; x < getWidth(); x++) {
            int y = (int) (centerY + 50 * Math.sin((x + t) * 0.05));
            g2d.fillOval(x, y, 2, 2);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Sine Wave");
        SineWave wave = new SineWave();
        frame.add(wave);
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
