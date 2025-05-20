import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class SolarSystem extends JPanel {
    private double t = 0; // Time for animation

    public SolarSystem() {
        Timer timer = new Timer(30, e -> {
            t += 0.02; // Increment time
            repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        setBackground(Color.BLACK);

        // Draw the sun
        g2d.setColor(Color.YELLOW);
        g2d.fillOval(getWidth() / 2 - 50, getHeight() / 2 - 50, 100, 100);

        // Draw planets
        drawPlanet(g2d, Color.RED, 100, 2);  // Mercury
        drawPlanet(g2d, Color.ORANGE, 150, 1.5); // Venus
        drawPlanet(g2d, Color.BLUE, 200, 1); // Earth
        drawPlanet(g2d, Color.RED.darker(), 300, 0.5); // Mars
    }

    private void drawPlanet(Graphics2D g2d, Color color, int orbitRadius, double speed) {
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        double angle = t * speed; // Compute planet position
        int x = centerX + (int) (orbitRadius * Math.cos(angle)) - 10;
        int y = centerY + (int) (orbitRadius * Math.sin(angle)) - 10;

        // Draw orbit
        g2d.setColor(Color.GRAY);
        g2d.draw(new Ellipse2D.Double(centerX - orbitRadius, centerY - orbitRadius, orbitRadius * 2, orbitRadius * 2));

        // Draw planet
        g2d.setColor(color);
        g2d.fillOval(x, y, 20, 20);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Solar System");
        SolarSystem solarSystem = new SolarSystem();
        frame.add(solarSystem);
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
