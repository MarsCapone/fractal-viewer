import javax.swing.*;
import java.awt.*;

public class InteractiveFractalExplorer extends JFrame {

    /**
     * Create a new Fractal Viewer. This is what is called to start the program.
     */
    public InteractiveFractalExplorer() {

        setTitle("Interactive Fractal Explorer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new MainPanel());

        pack();
        setResizable(true);
        setVisible(true);
        setMinimumSize(new Dimension(500,500));

    }

    public static void main(String[] args) {
        // Make program look better depending on OS.
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        new InteractiveFractalExplorer();
    }

}
