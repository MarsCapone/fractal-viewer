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

//        mandelbrotPanel.paintImage();
//        juliaPanel.paintImage();

        Container additionalPanel = new AdditionalPanel(mandelbrotPanel, juliaPanel);
        this.add(mandelbrotPanel, BorderLayout.CENTER);
        this.add(additionalPanel, BorderLayout.EAST);

        MandelbrotListener mL = new MandelbrotListener(mandelbrotPanel, juliaPanel);
        JuliaListener jL = new JuliaListener(juliaPanel);

        mandelbrotPanel.addMouseListener(mL);
        mandelbrotPanel.addMouseMotionListener(mL);

        juliaPanel.addMouseListener(jL);
        juliaPanel.addMouseMotionListener(jL);
    }
}

//TODO Use Double buffered images
//TODO Draw a rectangle on mouse drag
//TODO Add more coloring methods
