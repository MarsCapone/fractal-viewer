import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public abstract class FractalPanel extends JPanel {

    protected static double MODULUS_SQUARED_LIMIT = 4.0; // square of the modulus to use.
    protected static int ITERATION_LIMIT = 50; // max number of iterations to do.
    // set constants for easy changing
    protected final int WIDTH = 800;
    protected final int HEIGHT = 800;
    protected Complex lastComplex;
    protected BufferedImage image;
    // create abstract axis values
    private double abstractRangeY;
    private double abstractRangeX;
    private double abstractMinY;
    private double abstractMinX;
    private boolean rectangleMode = false; // whether or not to be drawing a rectangle.
    private Rectangle zoomLocationRectangle = new Rectangle();
    private boolean firstPaint = true;

    /**
     * A new Fractal Panel has an abstract axis, which is set using parameters.
     *
     * @param abstractMinX   The minimum value on the real axis.
     * @param abstractRangeX The range of the real axis.
     * @param abstractMinY   The minimum value on the imaginary axis.
     * @param abstractRangeY The range of the imaginary axis.
     */
    public FractalPanel(double abstractMinX, double abstractRangeX, double abstractMinY, double abstractRangeY) {
        this.abstractMinX = abstractMinX;
        this.abstractMinY = abstractMinY;
        this.abstractRangeX = abstractRangeX;
        this.abstractRangeY = abstractRangeY;

        this.setDoubleBuffered(true);
        this.setLayout(new FlowLayout());
    }

    /**
     * By default a Fractal Panel extends from -2 to 2 on the real axis, and -1.6 to 1.6 on the imaginary axis.
     */
    public FractalPanel() {
        this(-2.0, 4.0, -2.0, 4.0);
    }

    /**
     * Get the current modulus limit.
     *
     * @return The current modulus limit.
     */
    public static double getModulusSquaredLimit() {
        return MODULUS_SQUARED_LIMIT;
    }

    /**
     * Set the modulus limit.
     *
     * @param limit The new modulus limit.
     */
    public static void setModulusSquaredLimit(double limit) {
        MODULUS_SQUARED_LIMIT = limit;
    }

    /**
     * The current iteration limit
     *
     * @return The current iteration limit.
     */
    public static int getIterationLimit() {
        return ITERATION_LIMIT;
    }

    /**
     * Set the iteration limit. Set from double to avoid user error.
     *
     * @param limit The new modulus limit.
     */
    public static void setIterationLimit(double limit) {
        ITERATION_LIMIT = (int) limit;
    }

    /**
     * Get the complex number corresponding to a point on a JPanel axis.
     *
     * @param x The x value on the JPanel axis.
     * @param y The y value on the JPanel axis.
     * @return A complex number with real and imaginary values translated from a JPanel point.
     */
    public Complex getComplexPoint(int x, int y) {
        return Calculations.getComplexPoint(x, y, this.getWidth(), this.getHeight(), abstractRangeX, abstractMinX, abstractRangeY, abstractMinY);
    }

    /**
     * Get the complex number corresponding to a point on the JPanel axis.
     *
     * @param p The point on a JPanel
     * @return The complex number on the imaginary plane.
     */
    public Complex getComplexPoint(Point p) {
        return getComplexPoint(p.x, p.y);
    }

    /**
     * Paint the panels.
     *
     * @param g The graphics object.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
        doCalculations();
    }

    /**
     * All the drawing that needs doing.
     *
     * @param g The graphics object.
     */
    private void doDrawing(Graphics g) {
        // on the first paint, setup the 2 initial fractals
        if (firstPaint) {
            paintImage();
            System.out.println("Painting initial paint.");
            firstPaint = false;
        }

        // draw the image and fit to panel
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);

        // if dragging, draw a rectangle
        if (rectangleMode) {
            Graphics2D g2 = (Graphics2D) g;

            // the rectangle should be half transparent
            AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 0.5);
            g2.setComposite(ac);

            g2.fill(zoomLocationRectangle);
        }
    }

    /**
     * Do any calculations.
     */
    public void doCalculations() {
        // Set positioning boxes
        double[] position = getPosition();
        ReaxingSetting reax = (ReaxingSetting) SettingsPane.getReaxingPane();

        FractalJumperSetting jumperSetting;
        if (this instanceof BigPanel) {
            jumperSetting = reax.getMandelbrotJump();
        } else {
            jumperSetting = reax.getJuliaJump();
        }

        jumperSetting.setxCenter(position[0]);
        jumperSetting.setyCenter(position[1]);
        jumperSetting.setR(position[2]);

        // Set Julia constant jumper box if this is a panels.JuliaPanel
        if (this instanceof SmallPanel) {
            SmallPanel jP = (SmallPanel) this;
            SmallJumperSetting jJ = (SmallJumperSetting) SettingsPane.getConstantJump();
            jJ.setFieldText(jP.getConstant().toString());
        }
    }

    /**
     * Reset the axes to new boundaries on the imaginary plane.
     *
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
        this.paintImage();
        this.repaint();
    }

    /**
     * Reset the axes to new boundaries on the imaginary plane.
     *
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
     *
     * @param xCenter The real value for the center of the panel.
     * @param yCenter The imaginary value for the center of the panel.
     * @param R       Half the range of the real or imaginary axis to display.
     */
    public void resetAxes(double xCenter, double yCenter, double R) {
        resetAxes(xCenter - R,
                2 * R,
                yCenter - R,
                2 * R);
    }

    /**
     * Get a map that can be fed into resetAxes(HashMap m).
     *
     * @return A map that can be used in resetAxes(HashMap m).
     */
    public HashMap<String, Double> getAxes() {
        HashMap<String, Double> axes = new HashMap<String, Double>();
        axes.put("minX", abstractMinX);
        axes.put("minY", abstractMinY);
        axes.put("maxX", abstractMinX + abstractRangeX);
        axes.put("maxY", abstractMinY + abstractRangeY);
        return axes;
    }

    /**
     * Zoom the image to an area in the Mandelbrot set.
     *
     * @param startDrag The point where the mouse starts dragging.
     * @param endDrag   The point where the mouse ends dragging.
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

        if (w >= h) {
            // get the left most square of the selection.
            resetAxes(minX, w, minY, w);
        } else {
            // get the top most square of the selection.
            resetAxes(minX, h, minY, h);
        }
        System.out.printf("Zooming: (%f, %f) to (%f, %f) for type %s \n", minX, maxY, maxX, minY, SetAlgorithms.getType());
    }

    public void zoom(double X, double Y, double R) {
        resetAxes(
                X-R,
                R*2,
                Y-R,
                R*2
        );
        System.out.printf("Zooming: (%f, %f) with R=%f for type %s \n", X, Y, R, SetAlgorithms.getType());
    }

    public void zoom(Complex centre, double R) {
        zoom(
                centre.getReal(),
                centre.getImaginary(),
                R
        );
    }

    /**
     * Paint the pixels in the buffered image.
     */
    public abstract void paintImage();

    /**
     * Get whether the mouse is pressed and dragging.
     *
     * @return True if a rectangle should be being drawn.
     */
    public boolean getDrawMode() {
        return rectangleMode;
    }

    /**
     * Set the rectangle drawing mode.
     *
     * @param m The value of the rectangle drawing mode.
     */
    public void setDrawMode(boolean m) {
        rectangleMode = m;
    }

    /**
     * Set the zoom location rectangle based on start and end points of a drag.
     *
     * @param start The starting drag point.
     * @param end   The ending drag point.
     */
    public void setRectangle(Point start, Point end) {
        int x = Math.min(start.x, end.x);
        int y = Math.min(start.y, end.y);
        int w = Math.abs(start.x - end.x);
        int h = Math.abs(start.y - end.y);
        zoomLocationRectangle = new Rectangle(x, y, w, h);
    }

    /**
     * Get the current buffered image.
     *
     * @return The image on which the fractals are being drawn.
     */
    public BufferedImage getImage() {
        return image;
    }

    /**
     * Get the current position of the view.
     *
     * @return An array of abstract axis values that fill in the boxes in the settings.
     * { xCenter, yCenter, R }
     */
    public double[] getPosition() {
        double xCenter = abstractMinX + (abstractRangeX / 2);
        double yCenter = abstractMinY + (abstractRangeY / 2);
        double R = abstractRangeX / 2;

        return new double[]{xCenter, yCenter, R};
    }

    public double getXRange() {
        return abstractRangeX;
    }

    public double getYRange() {
        return abstractRangeY;
    }


}
