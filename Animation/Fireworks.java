import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Fireworks extends JPanel {
    private List<Particle> particles = new ArrayList<>();
    private Random random = new Random();

    public Fireworks() {
        Timer timer = new Timer(30, e -> {
            if (random.nextInt(10) < 2) {
                // Add new firework explosion
                int x = random.nextInt(getWidth());
                int y = random.nextInt(getHeight() / 2);
                createExplosion(x, y);
            }

            particles.removeIf(p -> !p.update());
            repaint();
        });
        timer.start();
    }

    private void createExplosion(int x, int y) {
        for (int i = 0; i < 100; i++) {
            double angle = 2 * Math.PI * i / 100;
            double speed = random.nextDouble() * 4 + 2;
            particles.add(new Particle(x, y, Math.cos(angle) * speed, Math.sin(angle) * speed, Color.getHSBColor(random.nextFloat(), 1, 1)));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        setBackground(Color.BLACK);
        for (Particle p : particles) {
            p.draw(g2d);
        }
    }

    private static class Particle {
        double x, y, dx, dy;
        Color color;
        int life = 100;

        Particle(double x, double y, double dx, double dy, Color color) {
            this.x = x;
            this.y = y;
            this.dx = dx;
            this.dy = dy;
            this.color = color;
        }

        boolean update() {
            x += dx;
            y += dy;
            dy += 0.1; // Gravity
            life--;
            return life > 0;
        }

        void draw(Graphics2D g) {
            g.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), Math.max(0, life * 255 / 100)));
            g.fillOval((int) x, (int) y, 4, 4);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Fireworks");
        Fireworks fireworks = new Fireworks();
        frame.add(fireworks);
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
