import javax.swing.*;
import java.awt.*;

public class FractalViewer extends JFrame {

    /**
     * Create a new fractal viewer
     */
    public FractalViewer() {

        setTitle("Fractal Viewer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new MainPanel());
        //setMinimumSize(new Dimension(800, 600));

        pack();
        setResizable(true);
        setVisible(true);

    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        new FractalViewer();
    }

}
