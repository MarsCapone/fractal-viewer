import javax.swing.*;
import java.awt.*;

public class GeneralFractalPanel extends JPanel {

    // set limits for easy changing
    protected double MODULUS_LIMIT = 2;
    protected double COUNT_LIMIT = 255;

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

        this.setPreferredSize(new Dimension(600, 600));
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
    
    public Complex getComplexPoint(Point p) {
        return getComplexPoint(p.x, p.y);
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

    public Color getColourT1(int x, int y, int iterations) {
        x = 255 * x / getWidth();
        y = 255 * y / getHeight();
        iterations = (int) (iterations * 255 / COUNT_LIMIT);
        return new Color(x, y, iterations);
    }
}
