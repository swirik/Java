import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.HashSet;
import java.util.Set;

public class HeartAnimation extends JPanel {
    private Set<Integer> drawnLines = new HashSet<>(); // Store the drawn line indices
    private final int totalLines = 360; // Total number of lines (degree steps)
    private final int maxLines = 100; // Reduced number of lines
    private final int scaleFactor = 20; // Increased scaling factor for bigger lines

    public HeartAnimation() {
        Timer timer = new Timer(100, e -> { // Increased delay (100ms for slower animation)
            // Randomly pick a line index to draw
            if (drawnLines.size() < maxLines) {
                int randomLine;
                do {
                    randomLine = (int) (Math.random() * totalLines); // Randomize line index
                } while (drawnLines.contains(randomLine)); // Ensure no duplicate lines are drawn
                
                drawnLines.add(randomLine); // Add the line index to the set
            }
            repaint(); // Redraw the panel
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Set anti-aliasing for smooth lines
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Set background color
        setBackground(Color.BLACK);

        // Create a radial glowing effect at the center
        RadialGradientPaint glow = new RadialGradientPaint(
                getWidth() / 2, getHeight() / 2, 100, // Center and radius
                new float[] { 0f, 0.5f, 1f }, // Gradient distribution
                new Color[] { new Color(255, 0, 0, 255), new Color(255, 0, 0, 100), new Color(255, 0, 0, 0) }
        );
        g2d.setPaint(glow);
        g2d.fillOval(getWidth() / 2 - 40, getHeight() / 2 - 40, 0, 0); // Glow effect, smaller circle

        // Set heart line color
        g2d.setColor(Color.RED);

        // Center of the panel
        int width = getWidth();
        int height = getHeight();
        int centerX = width / 2;
        int centerY = height / 2;

        // Draw the lines randomly chosen and within the limit
        for (int i : drawnLines) {
            double t = Math.toRadians(i); // Angle in radians
            double x = 16 * Math.pow(Math.sin(t), 3); // Parametric x-coordinate
            double y = 13 * Math.cos(t) - 5 * Math.cos(2 * t) - 2 * Math.cos(3 * t) - Math.cos(4 * t); // Parametric y-coordinate

            // Scale and translate the heart, make the lines bigger
            int xEnd = centerX + (int) (x * scaleFactor); // Increased scaleFactor for bigger lines
            int yEnd = centerY - (int) (y * scaleFactor); // Negative to flip y-axis

            // Draw a line from the center to the calculated point
            g2d.draw(new Line2D.Double(centerX, centerY, xEnd, yEnd));
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Heart Animation");
        HeartAnimation animation = new HeartAnimation();

        frame.add(animation);
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
