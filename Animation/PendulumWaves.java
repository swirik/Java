import javax.swing.*;
import java.awt.*;

public class PendulumWaves extends JPanel {
    private double[] angles;
    private int[] lengths;
    private int time = 0;

    public PendulumWaves() {
        int pendulums = 10;
        angles = new double[pendulums];
        lengths = new int[pendulums];
        for (int i = 0; i < pendulums; i++) {
            lengths[i] = 100 + i * 20;
        }

        Timer timer = new Timer(30, e -> {
            time++;
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
        int centerY = getHeight() / 4;

        for (int i = 0; i < angles.length; i++) {
            double frequency = 0.1 + i * 0.01;
            angles[i] = Math.sin(time * frequency);

            int x = (int) (centerX + lengths[i] * Math.sin(angles[i]));
            int y = (int) (centerY + lengths[i] * Math.cos(angles[i]));

            g2d.drawLine(centerX, centerY, x, y);
            g2d.fillOval(x - 5, y - 5, 10, 10);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Pendulum Waves");
        PendulumWaves pendulum = new PendulumWaves();
        frame.add(pendulum);
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
