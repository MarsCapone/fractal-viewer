import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public abstract class GeneralFractalPanel extends JPanel {

    protected boolean rectangleMode = false; // whether or not to be drawing a rectangle.

    // set constants for easy changing
    protected int WIDTH = 600;
    protected int HEIGHT = 600;

    protected static double MODULUS_LIMIT = 2;
    protected static int ITERATION_LIMIT = 100;

    protected BufferedImage image;
    protected static int COLOUR_TYPE = 0;

    // create abstract axis values
    protected double abstractRangeY;
    protected double abstractRangeX;
    protected double abstractMinY;
    protected double abstractMinX;

    /**
     * A new Fractal Panel has an abstract axis, which is set using parameters.
     * @param abstractMinX The minimum value on the real axis.
     * @param abstractRangeX The range of the real axis.
     * @param abstractMinY The minimum value on the imaginary axis.
     * @param abstractRangeY The range of the imaginary axis.
     */
    public GeneralFractalPanel(double abstractMinX, double abstractRangeX, double abstractMinY, double abstractRangeY) {
        this.abstractMinX = abstractMinX;
        this.abstractMinY = abstractMinY;
        this.abstractRangeX = abstractRangeX;
        this.abstractRangeY = abstractRangeY;

        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setDoubleBuffered(true);
        this.setLayout(new FlowLayout());
    }

    /**
     * By default a Fractal Panel extends from -2 to 2 on the real axis, and -1.6 to 1.6 on the imaginary axis.
     */
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

    /**
     * Get a colour for a pixel, based on the the number of iterations and/or the starting complex number.
     * Use the constant COLOUR_TYPE to define which colouring algorithm is used.
     * @param startingComplex The complex number that started the calculation for that pixel.
     * @param iterations The number of iterations before divergence.
     * @return The colour that should be used for that input.
     */
    public Color getColour(Complex startingComplex, int iterations) {
        return getColour(startingComplex, iterations, COLOUR_TYPE);
    }

    /**
     * Get a colour for a pixel, based on the the number of iterations and/or the starting complex number.
     * @param startingComplex The complex number that started the calculation for that pixel.
     * @param iterations The number of iterations before divergence.
     * @param colourType The colouring method that should be used.
     *                   0: Black & White;
     * @return The colour that should be used for that input.
     */
    public Color getColour(Complex startingComplex, int iterations, int colourType) {
        switch (colourType) {
            case 0:
                if (iterations == ITERATION_LIMIT) {
                    return Color.BLACK;
                } else {
                    return Color.WHITE;
                }
            default:
                int x = (int) ((startingComplex.pow(2).getReal() * abstractRangeX) / this.getWidth());
                int y = (int) ((startingComplex.pow(2).getImaginary() * abstractRangeY) / this.getHeight());
                iterations = iterations * 255 / ITERATION_LIMIT;
                return new Color(x, y, iterations);
        }
    }

    //TODO fix this
    public void paint(Graphics g) {
        paintImage();
        g.drawImage(image, 0, 0, null);

    }

    /**
     * Reset the axes to new boundaries on the imaginary plane.
     * @param aMX The minimum real value.
     * @param aRX The range on the real axis.
     * @param aMY The minimum imaginary value.
     * @param aRY The range of the imaginary axis.
     */
    public void resetAxes(double aMX, double aRX, double aMY, double aRY) {
        this.abstractMinX = aMX;
        this.abstractMinY = aMY;
        this.abstractRangeX = aRX;
        this.abstractRangeY = aRY;
        this.repaint();
    }

    /**
     * Reset the axes to new boundaries on the imaginary plane.
     * @param axisConstraints The map of the strings (minX, minY, maxX, maxY) to their corresponding values.
     */
    public void resetAxes(HashMap<String, Double> axisConstraints) {
        resetAxes(axisConstraints.get("minX"),
                axisConstraints.get("maxX") - axisConstraints.get("minX"),
                axisConstraints.get("minY"),
                axisConstraints.get("maxY") - axisConstraints.get("minY"));
        
    }

    /**
     * Reset the axes to their original values.
     */
    public void resetAxes() {
        resetAxes(-2.0, 4.0, -1.6, 3.2);
    }

    /**
     * Reset the axes to new boundaries on the imaginary plane.
     * @param xCenter The real value for the center of the panel.
     * @param yCenter The imaginary value for the center of the panel.
     * @param R Half the range of the real or imaginary axis to display.
     */
    public void resetAxes(double xCenter, double yCenter, double R) {
        resetAxes(xCenter-R,
                2*R,
                yCenter-R,
                2*R);
    }

    /**
     * Get a map that can be fed into resetAxes(HashMap m).
     * @return A map that can be used in resetAxes(HashMap m).
     */
    public HashMap<String, Double> getAxes() {
        HashMap<String, Double> axes = new HashMap<String, Double>();
        axes.put("minX", abstractMinX);
        axes.put("minY", abstractMinY);
        axes.put("maxX", abstractMinX+abstractRangeX);
        axes.put("maxY", abstractMinY+abstractRangeY);
        return axes;
    }

    /**
     * Zoom the image to an area in the Mandelbrot set.
     * @param startDrag The point where the mouse starts dragging.
     * @param endDrag The point where the mouse ends dragging.
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
            // get the left most square of the selection.
            resetAxes(minX, w, minY, w);
        } else {
            // get the top most square of the selection.
            resetAxes(minX, h, minY, h);
        }
        System.out.printf("Zooming: (%f, %f) to (%f, %f) \n", minX, maxY, maxX, minY);
    }

    /**
     * Get the current modulus limit.
     * @return The current modulus limit.
     */
    public static double getModulusLimit() {
        return MODULUS_LIMIT;
    }

    /**
     * The current iteration limit
     * @return The current iteration limit.
     */
    public static int getIterationLimit() {
        return (int) ITERATION_LIMIT;
    }

    /**
     * Set the modulus limit.
     * @param limit The new modulus limit.
     */
    public static void setModulusLimit(double limit) {
        MODULUS_LIMIT = limit;
    }

    /**
     * Set the iteration limit.
     * @param limit The new modulus limit.
     */
    public static void setIterationLimit(double limit) {
        ITERATION_LIMIT = (int) limit;
    }

    /**
     * Paint the pixels in the buffered image.
     */
    public abstract void paintImage();

    /**
     * Get the next iteration.
     * @param z The Z value.
     * @param c The C value.
     * @return The next Z value.
     */
    public abstract Complex getNext(Complex z, Complex c);

    /**
     * Set the rectangle drawing mode.
     * @param m The value of the rectangle drawing mode.
     */
    public void setDrawMode(boolean m) {
        rectangleMode = m;
    }

}
