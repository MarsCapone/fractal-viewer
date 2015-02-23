import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

public class FractalViewerGraphics extends JPanel {

    public FractalViewerGraphics() {

        // set important settings
        setLayout(new FlowLayout());

        // create panels to show different fractals
        MandelbrotPanel mandelbrotPanel = new MandelbrotPanel();
        JuliaPanel juliaPanel = new JuliaPanel();
        
        // create the tabbed panel
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Mandelbrot Set", null, mandelbrotPanel, "View Mandelbrot Set");
        tabbedPane.addTab("Julia Set", null, juliaPanel, "View Julia Set");
        
        // add panels
        this.add(tabbedPane);
    }

}
