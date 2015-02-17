import javax.swing.*;
import java.awt.*;

public class FractalViewer extends JFrame {

    public FractalViewer() {

        setTitle("Fractal Viewer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new FractalViewerPanel());

        setMinimumSize(new Dimension(400, 400));
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
