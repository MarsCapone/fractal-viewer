import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.security.InvalidParameterException;

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
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Mandelbrot Set", null, mandelbrotPanel, "View Mandelbrot Set");
        tabbedPane.addTab("Julia Set", null, juliaPanel, "View Julia Set");
        this.add(tabbedPane);

        // paint initial julia image
        //juliaPanel.paintJuliaImage(new Complex(0,0));

        // add panels
        //this.add(mandelbrotPanel);
        //this.add(juliaPanel);
        
        // add listeners
        mandelbrotPanel.addMouseListener(new MandelbrotPanelListener());
        juliaPanel.addMouseListener(new JuliaPanelListener());
    }

    private MandelbrotPanel getNewMandelbrotPanel(int actualX1, int actualY1, int actualX2, int actualY2) {
        int minX = Math.min(actualX1, actualX2);
        int maxX = Math.max(actualX1, actualX2);
        int minY = Math.min(actualY1, actualY2);
        int maxY = Math.max(actualY1, actualY2);

        // don't create a new panel unless the size of the rectangle is legitimate
        if (Math.abs(maxX-minX) >= 10 && Math.abs(maxY-minY) >= 10) {
            Complex topLeft = mandelbrotPanel.getComplexPoint(minX, minY);
            Complex bottomRight = mandelbrotPanel.getComplexPoint(maxX, maxY);
            return new MandelbrotPanel(topLeft.getReal(), Math.abs(bottomRight.getReal() - topLeft.getReal()), topLeft.getImaginary(), Math.abs(topLeft.getImaginary() - bottomRight.getImaginary()));
        } else {
            throw new InvalidParameterException("Selected area too small");
        }
    }

    private MandelbrotPanel getNewMandelbrotPanel(Point startDrag, Point endDrag) {
        return getNewMandelbrotPanel(startDrag.x, startDrag.y, endDrag.x, endDrag.y);
    }
    
    private JuliaPanel getNewJuliaPanel(int actualX1, int actualY1, int actualX2, int actualY2) {
        int minX = Math.min(actualX1, actualX2);
        int maxX = Math.max(actualX1, actualX2);
        int minY = Math.min(actualY1, actualY2);
        int maxY = Math.max(actualY1, actualY2);

        // don't create a new panel unless the size of the rectangle is legitimate
        if (Math.abs(maxX-minX) >= 10 && Math.abs(maxY-minY) >= 10) {
            Complex topLeft = mandelbrotPanel.getComplexPoint(minX, minY);
            Complex bottomRight = mandelbrotPanel.getComplexPoint(maxX, maxY);
            return new JuliaPanel(topLeft.getReal(), bottomRight.getReal() - topLeft.getReal(), topLeft.getImaginary(), topLeft.getImaginary() - bottomRight.getImaginary());
        }
        return new JuliaPanel();
    }

    private JuliaPanel getNewJuliaPanel(Point startDrag, Point endDrag) {
        return getNewJuliaPanel(startDrag.x, startDrag.y, endDrag.x, endDrag.y);
    }
    


    private class MandelbrotPanelListener implements MouseListener {

        Point startDrag, endDrag;

        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            try {
                int x = mouseEvent.getX();
                int y = mouseEvent.getY();
                Complex point = mandelbrotPanel.getComplexPoint(x, y);
                //System.out.println(point);
                juliaPanel.removeAll();
                juliaPanel.add(new JuliaPanel());
                juliaPanel.setConstant(point);
                juliaPanel.paintJuliaImage();
                juliaPanel.repaint();
                revalidate();
            } catch (InvalidParameterException e) {
                System.out.println("Can't zoom in, area too small.");
            }
        }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {
            startDrag = new Point(mouseEvent.getX(), mouseEvent.getY());
        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {
            try {
                endDrag = new Point(mouseEvent.getX(), mouseEvent.getY());
                mandelbrotPanel.removeAll();
                mandelbrotPanel.add(getNewMandelbrotPanel(startDrag, endDrag));
                revalidate();

                // print info
                Complex bl = mandelbrotPanel.getComplexPoint(startDrag.x, endDrag.y);
                Complex tr = mandelbrotPanel.getComplexPoint(endDrag.x, startDrag.y);
                System.out.printf("Created new Mandelbrot Panel in rectangle (%f, %f) to (%f, %f)\n", bl.getReal(), bl.getImaginary(), tr.getReal(), tr.getImaginary());
            } catch (InvalidParameterException e) {
                System.out.println("Can't zoom in, area too small.");
            }
        }

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {}

        @Override
        public void mouseExited(MouseEvent mouseEvent) {}
    }

    private class JuliaPanelListener implements MouseListener {
        Point startDrag, endDrag;
        
        @Override
        public void mouseClicked(MouseEvent mouseEvent) {}

        @Override
        public void mousePressed(MouseEvent mouseEvent) {
            startDrag = new Point(mouseEvent.getX(), mouseEvent.getY());
        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {
            endDrag = new Point(mouseEvent.getX(), mouseEvent.getY());
            juliaPanel.removeAll();
            juliaPanel.add(getNewJuliaPanel(startDrag, endDrag));
            revalidate();
            
            // print info
            Complex bl = juliaPanel.getComplexPoint(startDrag.x, endDrag.y);
            Complex tr = juliaPanel.getComplexPoint(endDrag.x, startDrag.y);
            System.out.printf("Created new Julia Panel in rectangle (%f, %f) to (%f, %f)\n", bl.getReal(), bl.getImaginary(), tr.getReal(), tr.getImaginary());
        }

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {}

        @Override
        public void mouseExited(MouseEvent mouseEvent) {}
    }
}

//TODO fix zoom, currently zooms to negative y coordinate.