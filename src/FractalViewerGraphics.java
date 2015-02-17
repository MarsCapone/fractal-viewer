import javax.swing.*;
import java.awt.*;

public class FractalViewerGraphics extends JPanel {

    public FractalViewerGraphics(String fractalType) {
        setSize(200, 200);
        setLayout(new FlowLayout());
        this.add(new JLabel("Actual fractal pattern will go here"));

    }
}
