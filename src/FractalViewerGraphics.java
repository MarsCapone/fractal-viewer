import javax.swing.*;
import java.awt.*;

public class FractalViewerGraphics extends JPanel {

    public FractalViewerGraphics(String fractalType) {
        setPreferredSize(new Dimension(300, 300));
        setLayout(new FlowLayout());
        this.add(new JLabel("Actual fractal pattern will go here"));

    }
}
