import java.awt.*;

import java.awt.image.BufferedImage;

public class MandelbrotPanel extends GeneralFractalPanel {

    public MandelbrotPanel() {
        super();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    /**
     * Create mandelbrot set on Buffered Image. Pretty sure this is a good way o do things, but unsure how to display.
     */
    public void paintImage() {
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        for (int w=0; w<WIDTH; w++) {
            for (int h=0; h<HEIGHT; h++) {
                Complex complexPoint = getComplexPoint(w, h);
                Color pointColour = getColour(complexPoint, lastComplex, getMandelbrotDivergence(complexPoint));
                image.setRGB(w, h, pointColour.getRGB());
            }
        }
    }

    /**
     * Get an integer value for the divergence of a complex number when passed to the paintMandelbrotImage equation.
     * The series diverges when the modulus of the number is greater than MODULUS_SQUARED_LIMIT.
     * @param c The starting value to calculate the paintMandelbrotImage set from.
     * @return The number of recursions before divergence.
     */
    private int getMandelbrotDivergence(Complex c) {
        int count = 0; // the number of recursions before divergence
        Complex previousComplex = c;
        double modulus_squared = 0.0;
        while (modulus_squared < MODULUS_SQUARED_LIMIT && count < ITERATION_LIMIT) {
            previousComplex = getNext(previousComplex, c);
            lastComplex = previousComplex;
            modulus_squared = previousComplex.modulusSquared();
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
    public Complex getNext(Complex z, Complex c) {
        Complex zsquared = z.pow(ORDER);
        return zsquared.add(c);
    }

}
