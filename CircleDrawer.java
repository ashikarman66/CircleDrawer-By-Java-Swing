import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class CircleDrawer extends JFrame {

    private DrawingPanel drawingPanel;
    private JButton drawButton, clearButton;

    public CircleDrawer() {
        setTitle("Circle Drawer");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        drawingPanel = new DrawingPanel();
        add(drawingPanel, BorderLayout.CENTER);

        drawButton = new JButton("Draw Circle");
        clearButton = new JButton("Clear");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(drawButton);
        buttonPanel.add(clearButton);
        add(buttonPanel, BorderLayout.SOUTH);

        drawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawingPanel.addRandomCircle();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawingPanel.clearCircles();
            }
        });

        setVisible(true);
    }

    class DrawingPanel extends JPanel {
        private ArrayList<Circle> circles;
        private Random random;

        public DrawingPanel() {
            circles = new ArrayList<>();
            random = new Random();
            setBackground(Color.WHITE);
        }

        public void addRandomCircle() {
            int diameter = 50;
            int x = random.nextInt(Math.max(1, getWidth() - diameter));
            int y = random.nextInt(Math.max(1, getHeight() - diameter));
            circles.add(new Circle(x, y, diameter));
            repaint();
        }

        public void clearCircles() {
            circles.clear();
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (Circle c : circles) {
                g.setColor(Color.RED);
                g.fillOval(c.x, c.y, c.diameter, c.diameter);
            }
        }
    }

    class Circle {
        int x, y, diameter;

        public Circle(int x, int y, int diameter) {
            this.x = x;
            this.y = y;
            this.diameter = diameter;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CircleDrawer());
    }
}
