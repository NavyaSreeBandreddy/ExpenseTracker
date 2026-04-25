import java.awt.*;
import java.util.Map;
import javax.swing.*;

public class ChartWindow extends JFrame {

    Map<String, Double> data;

    public ChartWindow(Map<String, Double> data) {
        this.data = data;

        setTitle("Expense Chart");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        add(new ChartPanel());
    }

    class ChartPanel extends JPanel {

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (data.isEmpty()) return;

            int width = getWidth();
            int height = getHeight();

            int barWidth = width / data.size();

            double max = 0;
            for (double value : data.values()) {
                if (value > max) max = value;
            }

            int i = 0;

            for (String category : data.keySet()) {
                double value = data.get(category);

                int barHeight = (int) ((value / max) * (height - 50));

                int x = i * barWidth + 20;
                int y = height - barHeight - 30;

                // Draw bar
                g.setColor(Color.BLUE);
                g.fillRect(x, y, barWidth - 40, barHeight);

                // Draw category name
                g.setColor(Color.BLACK);
                g.drawString(category, x, height - 10);

                // Draw value
                g.drawString(String.valueOf(value), x, y - 5);

                i++;
            }
        }
    }
}