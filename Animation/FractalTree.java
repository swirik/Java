import javax.swing.*;
import java.awt.*;

public class FractalTree extends JPanel {
    public FractalTree() {
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        setBackground(Color.BLACK);
        g2d.setColor(Color.GREEN);
        drawTree(g2d, getWidth() / 2, getHeight() - 50, -90, 100);
    }

    private void drawTree(Graphics2D g, int x, int y, double angle, int length) {
        if (length < 10) return;

        int xEnd = x + (int) (length * Math.cos(Math.toRadians(angle)));
        int yEnd = y + (int) (length * Math.sin(Math.toRadians(angle)));

        g.drawLine(x, y, xEnd, yEnd);
        drawTree(g, xEnd, yEnd, angle - 30, length - 10);
        drawTree(g, xEnd, yEnd, angle + 30, length - 10);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Fractal Tree");
        FractalTree tree = new FractalTree();
        frame.add(tree);
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
