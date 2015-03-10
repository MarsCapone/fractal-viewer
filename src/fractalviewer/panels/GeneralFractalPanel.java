package fractalviewer.panels;

import fractalviewer.extras.Calculations;
import fractalviewer.extras.Complex;
import fractalviewer.settings.FractalJumperSetting;
import fractalviewer.settings.JuliaJumperSetting;
import fractalviewer.settings.ReaxingSetting;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public abstract class GeneralFractalPanel extends JPanel {

    protected static double MODULUS_SQUARED_LIMIT = 4.0; // square of the modulus to use.
    protected static int ITERATION_LIMIT = 100; // max number of iterations to do.
    protected static int ORDER = 2; // the power used in set generation
    private static int COLOUR_TYPE = 3; // start the colour type as the fire setting
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
    public GeneralFractalPanel(double abstractMinX, double abstractRangeX, double abstractMinY, double abstractRangeY) {
        this.abstractMinX = abstractMinX;
        this.abstractMinY = abstractMinY;
        this.abstractRangeX = abstractRangeX;
        this.abstractRangeY = abstractRangeY;

        //this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
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
     * Get the power of the fractal generation. f(z) = z^d + c
     *
     * @return The order of the fractal generation.
     */
    public static int getOrder() {
        return ORDER;
    }

    /**
     * Set the order of the fractal generation. f(z) = z^d + c
     *
     * @param d The power to raise Z to.
     */
    public static void setOrder(int d) {
        ORDER = d;
    }

    public static void setColourType(int colourType) {
        COLOUR_TYPE = colourType;
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
     * Get a colour for a pixel, based on the the number of iterations and/or the starting complex number.
     * Use the constant COLOUR_TYPE to define which colouring algorithm is used.
     *
     * @param startingComplex The complex number that started the calculation for that pixel.
     * @param iterations      The number of iterations before divergence.
     * @return The colour that should be used for that input.
     */
    public Color getColour(Complex startingComplex, Complex endingComplex, int iterations) {
        return getColour(startingComplex, endingComplex, iterations, COLOUR_TYPE);
    }

    /**
     * Get a colour for a pixel, based on the the number of iterations and/or the starting complex number.
     *
     * @param startingComplex The complex number that started the calculation for that pixel.
     * @param iterations      The number of iterations before divergence.
     * @param colourType      The colouring method that should be used.
     *                        0: Black & White;
     * @return The colour that should be used for that input.
     */
    public Color getColour(Complex startingComplex, Complex endingComplex, int iterations, int colourType) {
        Color colour;
        switch (colourType) {
            case 0: // black and white
                if (iterations == ITERATION_LIMIT) {
                    colour = Color.BLACK;
                } else {
                    colour = Color.WHITE;
                }
                break;
            case 1: // binary decomposition
                if (iterations == ITERATION_LIMIT) {
                    colour = Color.BLACK;
                } else {
                    if (endingComplex.getImaginary() > 0) {
                        colour = Color.BLACK;
                    } else {
                        colour = Color.WHITE;
                    }
                }
                break;
            case 2: // divergence
                if (iterations == ITERATION_LIMIT) {
                    colour = Color.BLACK;
                } else {
                    int grey = (int) ((Math.sqrt(endingComplex.modulusSquared()) * 255) / (2 * abstractRangeX)) % 255;
                    colour = new Color(grey, grey, grey);
                }
                break;
            case 3: // fire
                if (iterations == ITERATION_LIMIT) {
                    colour = Color.WHITE;
                } else {
                    int colourValue = (int) Math.floor(iterations * 255 * 3 / ITERATION_LIMIT);
                    if (colourValue <= 255) {
                        colour = new Color(colourValue, 0, 0);
                    } else if (colourValue <= 255 * 2) {
                        colour = new Color(255, colourValue % 255, 0);
                    } else {
                        colour = new Color(255, 255, colourValue % 255);
                    }
                }
                break;
            case 4: // inverse fire
                if (iterations == ITERATION_LIMIT) {
                    colour = Color.BLACK;
                } else {
                    int colourValue = (int) Math.floor(iterations * 255 * 3 / ITERATION_LIMIT);
                    if (colourValue <= 255) {
                        colour = new Color(255 - colourValue, 0, 0);
                    } else if (colourValue <= 255 * 2) {
                        colour = new Color(0, 255 - colourValue % 255, 255);
                    } else {
                        colour = new Color(0, 0, 255 - colourValue % 255);
                    }
                }
                break;
            case 5: // smooth colouring - currently not working
                double c = ITERATION_LIMIT - Math.log(Math.log(iterations) / Math.log(MODULUS_SQUARED_LIMIT));
                colour = new Color((int) Math.floor(c));
                break;
            default:
                int x = (int) ((startingComplex.pow(2).getReal() * abstractRangeX) / this.getWidth());
                int y = (int) ((startingComplex.pow(2).getImaginary() * abstractRangeY) / this.getHeight());
                iterations = iterations * 255 / ITERATION_LIMIT;
                colour = new Color(x, y, iterations);
        }
        return colour;
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
        if (this instanceof MandelbrotPanel) {
            jumperSetting = reax.getMandelbrotJump();
        } else {
            jumperSetting = reax.getJuliaJump();
        }

        jumperSetting.setxCenter(position[0]);
        jumperSetting.setyCenter(position[1]);
        jumperSetting.setR(position[2]);

        // Set Julia constant jumper box if this is a fractalviewer.panels.JuliaPanel
        if (this instanceof JuliaPanel) {
            JuliaPanel jP = (JuliaPanel) this;
            JuliaJumperSetting jJ = reax.getJuliaConstantJump();
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
        System.out.printf("Zooming: (%f, %f) to (%f, %f) on %s \n", minX, maxY, maxX, minY, this.getClass().getName());
    }

    /**
     * Paint the pixels in the buffered image.
     */
    public abstract void paintImage();

    /**
     * Get the next iteration.
     *
     * @param z The Z value.
     * @param c The C value.
     * @return The next Z value.
     */
    public abstract Complex getNext(Complex z, Complex c);

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
     * Get the zoom location rectangle of a drag.
     *
     * @return The rectangle.
     */
    public Rectangle getRectangle() {
        return zoomLocationRectangle;
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

}
