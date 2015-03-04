import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {

    protected static JuliaPanel juliaPanel;
    protected static MandelbrotPanel mandelbrotPanel;

    /**
     * The panel containing everything required for the program.
     */
    public MainPanel() {
        setLayout(new BorderLayout());

        mandelbrotPanel = new MandelbrotPanel();
        Container additionalPanel = new AdditionalPanel();
        this.add(mandelbrotPanel, BorderLayout.CENTER);
        this.add(additionalPanel, BorderLayout.EAST);
        
        mandelbrotPanel.addMouseListener(new MandelbrotListener());
        juliaPanel.addMouseListener(new JuliaListener());
    }
}

//TODO Use Double buffered images
//TODO Draw a rectangle on mouse drag
//TODO Add more coloring methods
