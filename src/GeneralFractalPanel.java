import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;

public abstract class GeneralFractalPanel extends JPanel {

    protected BufferedImage image;

    // set limits for easy changing
    protected double MODULUS_LIMIT = 2;
    protected double COUNT_LIMIT = 100;
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

    public Color getColourT1(Complex startingComplex, int iterations) {
        int x = (int) ((startingComplex.pow(4).getReal() * abstractRangeX) / getWidth());
        int y = (int) ((startingComplex.pow(4).getImaginary() * abstractRangeY) / getHeight());
        iterations = (int) (iterations * 255 / COUNT_LIMIT);
        return new Color(x, y, iterations);
    }

    /**
     * Convert a divergence count to a colour.
     * @param divergenceCount The count before divergence.
     * @return The colour that this divergence corresponds to.
     */
    public Color getColour(int divergenceCount) {
        if (divergenceCount > 0 ) {
            double d = Math.pow(divergenceCount / COUNT_LIMIT, 3);
            int greyness = (int) (d * 255);
            return new Color(greyness, greyness, greyness);
        } else {
            return Color.white;
        }

    }

    public double getModulusLimit() {
        return MODULUS_LIMIT;
    }

    public double getCountLimit() {
        return COUNT_LIMIT;
    }
    
    public void setModulusLimit(double limit) {
        MODULUS_LIMIT = limit;
    }
    
    public void setCountLimit(int limit) {
        COUNT_LIMIT = limit;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage newImage) {
        this.image = image;
    }

    public abstract void paintImage();

    public abstract Complex getNext(Complex z, Complex c);


}
