import javax.swing.*;

public class FractalViewer extends JFrame {

    /**
     * Create a new fractal viewer
     */
    public FractalViewer() {

        setTitle("Fractal Viewer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new MainPanel());
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
