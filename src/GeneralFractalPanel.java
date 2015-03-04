import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public abstract class GeneralFractalPanel extends JPanel {

    protected BufferedImage image;
    protected static int COLOUR_TYPE = 0;
    
    private boolean rectangleMode = false;

    // set limits for easy changing

    protected int WIDTH = 600;
    protected int HEIGHT = 600;

    // create abstract axis values
    protected double abstractRangeY;
    protected double abstractRangeX;
    protected double abstractMinY;
    protected double abstractMinX;

    public GeneralFractalPanel(double abstractMinX, double abstractRangeX, double abstractMinY, double abstractRangeY) {
        this.abstractMinX = abstractMinX;
        this.abstractMinY = abstractMinY;
        this.abstractRangeX = abstractRangeX;
        this.abstractRangeY = abstractRangeY;

        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setDoubleBuffered(true);
    }
    
    public GeneralFractalPanel() {
        this(-2.0, 4.0, -1.6, 3.2);
    }
    
    
    /**
     * Get the complex number corresponding to a point on a JPanel axis.
     * @param x The x value on the JPanel axis.
     * @param y The y value on the JPanel axis.
     * @return A complex number with real and imaginary values translated from a JPanel point.
     */
    public Complex getComplexPoint(int x, int y) {
        return GraphCalc.getComplexPoint(x, y, this.getWidth(), this.getHeight(), abstractRangeX, abstractMinX, abstractRangeY, abstractMinY);
    }

    /**
     * Get the complex number corresponding to a point on the JPanel axis.
     * @param p The point on a JPanel
     * @return The complex number on the imaginary plane.
     */
    public Complex getComplexPoint(Point p) {
        return getComplexPoint(p.x, p.y);
    }

    public Color getColour(Complex startingComplex, int iterations) {
        switch (COLOUR_TYPE) {
            case 0:
                if (iterations == MainPanel.COUNT_LIMIT) {
                    return Color.BLACK;
                } else {
                    return Color.WHITE;
                }
            default:
                int x = (int) ((startingComplex.pow(2).getReal() * abstractRangeX) / this.getWidth());
                int y = (int) ((startingComplex.pow(2).getImaginary() * abstractRangeY) / this.getHeight());
                iterations = (int) (iterations * 255 / MainPanel.COUNT_LIMIT);
                return new Color(x, y, iterations);
        }

    }

    public void paint(Graphics g) {
        paintImage();
        g.drawImage(image, 0, 0, null);
        
        if (rectangleMode) {
            Point start = FractalPanelListener.startDrag;
            Point end = FractalPanelListener.endDrag;
            
            int minX = Math.min(start.x, end.x);
            int minY = Math.min(start.y, end.y);
            int w = Math.abs(start.x - end.x);
            int h = Math.abs(start.y - end.y);
            
            g.drawRect(minX, minY, w, h);
        }
    }
    
    public void resetAxes(double aMX, double aRX, double aMY, double aRY) {
        this.abstractMinX = aMX;
        this.abstractMinY = aMY;
        this.abstractRangeX = aRX;
        this.abstractRangeY = aRY;
        repaint();
    }
    
    public void resetAxes(HashMap<String, Double> axisConstraints) {
        resetAxes(axisConstraints.get("minX"),
                axisConstraints.get("maxX") - axisConstraints.get("minX"),
                axisConstraints.get("minY"),
                axisConstraints.get("maxY") - axisConstraints.get("minY"));
        
    }
    
    public void resetAxes() {
        resetAxes(-2.0, 4.0, -1.6, 3.2);
    }

    /**
     * Zoom to these new axes
     */
    public void zoom(Point startDrag, Point endDrag) {
        
        Complex startClick = getComplexPoint(startDrag);
        Complex endClick = getComplexPoint(endDrag);
        
        double minX = Math.min(startClick.getReal(), endClick.getReal());
        double maxX = Math.max(startClick.getReal(), endClick.getReal());
        double minY = Math.min(startClick.getImaginary(), endClick.getImaginary());
        double maxY = Math.max(startClick.getImaginary(), endClick.getImaginary());
        
        double w = maxX - minX;
        double h = maxY - minY;
        
        this.abstractMinX = minX;
        this.abstractMinY = minY;
        
        if (w >= h ) {
            resetAxes(minX, w, minY, w);
        } else {
            resetAxes(minX, h, minY, h);
        }
        System.out.printf("Zooming to: (%f, %f) to (%f, %f) \n", minX, maxY, maxX, minY);
    }

    public double getModulusLimit() {
        return MainPanel.MODULUS_LIMIT;
    }

    public double getCountLimit() {
        return MainPanel.COUNT_LIMIT;
    }
    
    public void setModulusLimit(double limit) {
        MainPanel.MODULUS_LIMIT = limit;
    }
    
    public void setCountLimit(int limit) {
        MainPanel.COUNT_LIMIT = limit;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage newImage) {
        this.image = newImage;
    }

    public abstract void paintImage();

    public abstract Complex getNext(Complex z, Complex c);

    public HashMap<String, Double> getAxes() {
        HashMap<String, Double> axes = new HashMap<String, Double>();
        axes.put("minX", abstractMinX);
        axes.put("minY", abstractMinY);
        axes.put("maxX", abstractMinX+abstractRangeX);
        axes.put("maxY", abstractMinY+abstractRangeY);
        return axes;
    }
    
    public void setDrawMode(boolean m) {
        rectangleMode = m;
    }
    
}
