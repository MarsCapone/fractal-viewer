import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MandelbrotPanel extends GeneralFractalPanel {

    private BufferedImage mandelbrotImage;

    public MandelbrotPanel(double abstractMinX, double abstractRangeX, double abstractMinY, double abstractRangeY) {
        super(abstractMinX, abstractRangeX, abstractMinY, abstractRangeY);
    }

    public MandelbrotPanel() {
        super();
    }

    /**
     * Create mandelbrot set on Buffered Image. Pretty sure this is a good way o do things, but unsure how to display.
     */
    private void paintMandelbrotImage() {
        mandelbrotImage = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int w=0; w<getWidth(); w++) {
            for (int h=0; h<getHeight(); h++) {
                Complex complexPoint = getComplexPoint(w, h);
                Color pointColour = getColour(getMandelbrotDivergence(complexPoint));
                mandelbrotImage.setRGB(w, h, pointColour.getRGB());
            }
        }
    }

    /**
     * Paint the scene. Not sure this is the ideal way of doing things.
     * @param g
     */
    public void paintComponent(Graphics g) {
        paintMandelbrotImage();
        g.drawImage(mandelbrotImage, 0, 0, null);
    }
    

    /**
     * Get an integer value for the divergence of a complex number when passed to the paintMandelbrotImage equation.
     * The series diverges when the modulus of the number is greater than MODULUS_LIMIT.
     * @param c The starting value to calculate the paintMandelbrotImage set from.
     * @return The number of recursions before divergence.
     */
    private int getMandelbrotDivergence(Complex c) {
        int count = 0; // the number of recursions before divergence
        Complex previousComplex = c;
        double modulus = 0.0;
        while (modulus < MODULUS_LIMIT && count < COUNT_LIMIT) {
            previousComplex = getNextMandelbrot(previousComplex, c);
            modulus = Math.sqrt(previousComplex.modulusSquared());
            count++;
        }
        return count;
    }

    /**
     * Get the next value from the paintMandelbrotImage equation.
     * @param z The complex z value.
     * @param c The complex c value.
     * @return The next paintMandelbrotImage value.
     */
    private Complex getNextMandelbrot(Complex z, Complex c) {
        Complex zsquared = z.square();
        return zsquared.add(c);
    }
    
}
