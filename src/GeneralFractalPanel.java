import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GeneralFractalPanel extends JPanel {

    public final double MODULUS_LIMIT = 2;
    public final double COUNT_LIMIT = 255;

    private double abstractRangeY;
    private double abstractRangeX;
    private double abstractMinY;
    private double abstractMinX;

    public GeneralFractalPanel(double abstractMinX, double abstractRangeX, double abstractMinY, double abstractRangeY) {
        this.abstractMinX = abstractMinX;
        this.abstractMinY = abstractMinY;
        this.abstractRangeX = abstractRangeX;
        this.abstractRangeY = abstractRangeY;

        this.setPreferredSize(new Dimension(300, 300));
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

    public Color getColour(int count) {
        double step = count/COUNT_LIMIT;
        int totalSteps = 255*3;
        int calculatedColour = (int) step * totalSteps;
        if (calculatedColour > 255*2) {
            return new Color(255, 255, calculatedColour%255);
        } else if (calculatedColour > 255) {
            return new Color(255, calculatedColour%255, 0);
        } else {
            return new Color(calculatedColour%255, 0, 0);
        }
    }

    
}
