import javax.swing.*;

public class FractalViewer extends JFrame {

    /**
     * Create a new Fractal Viewer. This is what is called to start the program.
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
        // Make program look better depending on OS.
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        new FractalViewer();
    }

}
