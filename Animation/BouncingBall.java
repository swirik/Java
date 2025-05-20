import javax.swing.*;
import java.awt.*;

public class BouncingBall extends JPanel {
    private int x = 100, y = 100; // Ball position
    private int dx = 5, dy = 5;   // Ball velocity
    private int ballSize = 30;    // Ball size

    public BouncingBall() {
        Timer timer = new Timer(30, e -> {
            // Update ball position
            x += dx;
            y += dy;

            // Bounce off walls
            if (x < 0 || x > getWidth() - ballSize) dx = -dx;
            if (y < 0 || y > getHeight() - ballSize) dy = -dy;

            repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        setBackground(Color.BLACK);
        g2d.setColor(Color.ORANGE);
        g2d.fillOval(x, y, ballSize, ballSize);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Bouncing Ball");
        BouncingBall ball = new BouncingBall();
        frame.add(ball);
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
