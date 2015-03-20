import java.awt.*;
import java.awt.image.BufferedImage;

public class SmallPanel extends FractalPanel {

    private Complex CONSTANT = new Complex(0, 0);

    public SmallPanel() {
        super();
    }

    /**
     * Draw the Julia Set with the given constant, onto a Buffered Image.
     *
     * @param d The constant for the Julia Set
     */
    public void paintSmallImage(Complex d) {
        image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int w = 0; w < getWidth(); w++) {
            for (int h = 0; h < getHeight(); h++) {
                Complex complexPoint = getComplexPoint(w, h);
                int divergence = getSmallPanelDivergence(complexPoint, d);
                Color pointColour = SetColours.getColour(complexPoint, lastComplex, divergence, this);
                image.setRGB(w, h, pointColour.getRGB());
            }
        }
    }

    /**
     * Paint the image using the value of the CONSTANT variable as constant.
     */
    public void paintImage() {
        paintSmallImage(CONSTANT);
    }

    /**
     * Get the number of times that the equation runs before the iterations diverge.
     * USE: getSmallPanelDivergence(juliaGraphPoint, clickedMandelbrotPoint)
     *
     * @param z The z value for calculating the Julia Set.
     * @param d The constant value for calculating the Julia Set.
     * @return The count before divergence occurs.
     */
    private int getSmallPanelDivergence(Complex z, Complex d) {
        int count = 0; // recursions before divergence
        Complex previousComplex = z;
        double modulus = 0.0;
        while (modulus < MODULUS_SQUARED_LIMIT && count < ITERATION_LIMIT) {
            previousComplex = SetAlgorithms.getNext(previousComplex, d);
            lastComplex = previousComplex;
            modulus = Math.sqrt(previousComplex.modulusSquared());
            count++;
        }
        return count;
    }

    /**
     * Get the current constant value.
     *
     * @return The current constant value.
     */
    public Complex getConstant() {
        return CONSTANT;
    }

    /**
     * Set anew value for constant.
     *
     * @param d The new constant value.
     */
    public void setConstant(Complex d) {
        CONSTANT = d;
    }

    public Image getScaledImage() {
        return image.getScaledInstance(70, 70, 10);
    }
}
