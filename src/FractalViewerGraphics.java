import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

public class FractalViewerGraphics extends JPanel {

    public static MandelbrotPanel mandelbrotPanel;
    public static JuliaPanel juliaPanel;
    
    public FractalViewerGraphics() {

        // set important settings
        setLayout(new FlowLayout());

        // create panels to show different fractals
        mandelbrotPanel = new MandelbrotPanel();
        juliaPanel = new JuliaPanel();
        
        // create the tabbed panel
        //JTabbedPane tabbedPane = new JTabbedPane();
        //tabbedPane.addTab("Mandelbrot Set", null, mandelbrotPanel, "View Mandelbrot Set");
        //tabbedPane.addTab("Julia Set", null, juliaPanel, "View Julia Set");
        
        // add panels
        this.add(mandelbrotPanel);
        this.add(juliaPanel);
    }

}
