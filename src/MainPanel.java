import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {

    private JuliaPanel juliaPanel;
    private MandelbrotPanel mandelbrotPanel;

    /**
     * The panel containing everything required for the program.
     */
    public MainPanel() {
        setLayout(new BorderLayout());

        mandelbrotPanel = new MandelbrotPanel();
        juliaPanel = new JuliaPanel();

        Container additionalPanel = new AdditionalPanel(mandelbrotPanel, juliaPanel);
        this.add(mandelbrotPanel, BorderLayout.CENTER);
        this.add(additionalPanel, BorderLayout.EAST);
        
        mandelbrotPanel.addMouseListener(new MandelbrotListener(mandelbrotPanel, juliaPanel));
        juliaPanel.addMouseListener(new JuliaListener(juliaPanel));
    }
}

//TODO Use Double buffered images
//TODO Draw a rectangle on mouse drag
//TODO Add more coloring methods
