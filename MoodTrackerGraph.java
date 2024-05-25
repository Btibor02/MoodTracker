import javax.swing.*;
import java.awt.*;

public class MoodTrackerGraph extends JPanel {

    private static final long serialVersionUID = 1L;

    private final int[] perfect = {5, 8, 10, 8};
    private final int[] good = {10, 8, 9, 10};
    private final int[] meh = {8, 4, 5, 8};
    private final int[] bad = {5, 6, 4, 3};
    private final int[] awful = {3, 4, 3, 1};

    private final String[] months = {"March", "April", "May", "June"};

    public MoodTrackerGraph() {
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw grid lines
        g2d.setColor(Color.LIGHT_GRAY);
        for (int i = 1; i < 10; i++) {
            g2d.drawLine(50, 50 + (i * 20), getWidth() - 50, 50 + (i * 20));
        }

        // Draw y-axis labels
        g2d.setFont(new Font("Arial", Font.PLAIN, 12));
        for (int i = 0; i <= 10; i++) {
            g2d.drawString(String.valueOf(i), 10, 50 + (i * 20));
        }

        // Draw bars for each mood category
        drawBars(g2d, perfect, Color.GREEN, "Perfect");
        drawBars(g2d, good, Color.GREEN, "Good");
        drawBars(g2d, meh, Color.BLUE, "Meh");
        drawBars(g2d, bad, Color.RED, "Bad");
        drawBars(g2d, awful, Color.RED, "Awful");

        // Draw x-axis labels
        for (int i = 0; i < months.length; i++) {
            g2d.drawString(months[i], 50 + (i * 80), getHeight() - 20);
        }

        // Draw title
        g2d.setFont(new Font("Arial", Font.BOLD, 16));
        g2d.drawString("Mood Tracker Status", getWidth() / 2 - 80, 30);

        // Draw legend
        g2d.setFont(new Font("Arial", Font.PLAIN, 12));
        g2d.drawString("Info organized and displayed based on user input.", getWidth() / 2 - 100, 45);
        g2d.drawString("Perfect", getWidth() - 150, 60);
        g2d.setColor(Color.GREEN);
        g2d.fillRect(getWidth() - 140, 55, 10, 10);
        g2d.setColor(Color.GREEN);
        g2d.fillRect(getWidth() - 120, 55, 10, 10);
        g2d.setColor(Color.BLUE);
        g2d.fillRect(getWidth() - 100, 55, 10, 10);
        g2d.setColor(Color.RED);
        g2d.fillRect(getWidth() - 80, 55, 10, 10);
        g2d.setColor(Color.RED);
        g2d.fillRect(getWidth() - 60, 55, 10, 10);
        g2d.setColor(Color.BLACK);
        g2d.drawString("Good", getWidth() - 110, 60);
        g2d.drawString("Meh", getWidth() - 90, 60);
        g2d.drawString("Bad", getWidth() - 70, 60);
        g2d.drawString("Awful", getWidth() - 50, 60);
    }

    private void drawBars(Graphics2D g2d, int[] values, Color color, String label) {
        for (int i = 0; i < values.length; i++) {
            int barWidth = 50;
            int barHeight = values[i] * 20;
            int x = 50 + (i * 80);
            int y = getHeight() - 50 - barHeight;
            g2d.setColor(color);
            g2d.fillRect(x, y, barWidth, barHeight);
            g2d.setColor(Color.BLACK);
            g2d.drawRect(x, y, barWidth, barHeight);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Mood Tracker Graph");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.add(new MoodTrackerGraph());
        frame.setVisible(true);
    }
}