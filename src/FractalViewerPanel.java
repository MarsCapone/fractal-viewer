import javax.swing.*;
import java.awt.*;

public class FractalViewerPanel extends JPanel {

    public FractalViewerPanel() {

        setLayout(new BorderLayout(5, 5));

        // create and add main panels
        FractalViewerGraphics graphics = new FractalViewerGraphics("mandelbrot");
        Container instructionPane = new JPanel();
        Container settingsPane = new JPanel();
        this.add(instructionPane, BorderLayout.NORTH);
        this.add(settingsPane, BorderLayout.EAST);
        this.add(graphics, BorderLayout.CENTER);

        // create and add to instruction and settings panels

    }
}
