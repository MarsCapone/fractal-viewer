import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

public class MaximizedFractalView extends JFrame {

    private FractalPanel panel;
    private double MOVEMENT_FRACTION = 0.1;

    public MaximizedFractalView(FractalPanel p) {
        setTitle(SetAlgorithms.getType());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(true);
        setVisible(true);
        setExtendedState(MAXIMIZED_BOTH);

        panel = p;

        setContentPane(panel);

        System.out.printf("Creating a maximized view of %s.\n", SetAlgorithms.getType());

        panel.paintImage();
        panel.repaint();

        panel.addKeyListener(new MaximizedListener());
    }

    public MaximizedFractalView() {
        setTitle("null");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        setVisible(true);

        JPanel error = new JPanel();
        error.setBackground(Color.BLACK);

        JLabel message = new JLabel("This frame cannot be created without a specified panel.");
        message.setBackground(Color.WHITE);

        error.add(message, CENTER_ALIGNMENT);

        setContentPane(error);
    }

    class MaximizedListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent keyEvent) {
            move(keyEvent.getKeyChar());
        }

        @Override
        public void keyPressed(KeyEvent keyEvent) {

        }

        @Override
        public void keyReleased(KeyEvent keyEvent) {

        }

        private void move(char direction) {
            HashMap<String, Double> axes = panel.getAxes();
            double movement;
            if (direction == 'a' || direction == 'd') {
                movement = panel.getXRange() * MOVEMENT_FRACTION;
                if (direction == 'a') {
                    axes.put("minX", axes.get("minX") - movement);
                    axes.put("maxX", axes.get("maxX") - movement);
                } else {
                    axes.put("minX", axes.get("minX") + movement);
                    axes.put("maxX", axes.get("maxX") + movement);
                }
            } else {
                movement = panel.getYRange() * MOVEMENT_FRACTION;
                if (direction == 'd') {
                    axes.put("minY", axes.get("minY") - movement);
                    axes.put("maxY", axes.get("maxY") - movement);
                } else {
                    axes.put("minY", axes.get("minY") + movement);
                    axes.put("maxY", axes.get("maxY") + movement);
                }
            }
            panel.resetAxes(axes);
        }
    }
}
