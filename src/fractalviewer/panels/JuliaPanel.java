package fractalviewer.panels;

import fractalviewer.extras.Complex;

import java.awt.*;
import java.awt.image.BufferedImage;

public class JuliaPanel extends GeneralFractalPanel {

    private Complex CONSTANT = new Complex(0, 0);
    
    public JuliaPanel() {
        super();
    }

    /**
     * Draw the Julia Set with the given constant, onto a Buffered Image. 
     * @param d The constant for the Julia Set
     */
    public void paintJuliaImage(Complex d) {
        image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int w=0; w<getWidth(); w++) {
            for (int h=0; h<getHeight(); h++) {
                Complex complexPoint = getComplexPoint(w, h);
                int divergence = getJuliaDivergence(complexPoint, d);
                Color pointColour = getColour(complexPoint, lastComplex, divergence);
                image.setRGB(w, h, pointColour.getRGB());
            }
        }
    }

    /**
     * Paint the image using the value of the CONSTANT variable as constant.
     */
    public void paintImage() {
        paintJuliaImage(CONSTANT);
    }
    
    /**
     * Get the number of times that the equation runs before the iterations diverge.
     * USE: getJuliaDivergence(juliaGraphPoint, clickedMandelbrotPoint) 
     * @param z The z value for calculating the Julia Set.
     * @param d The constant value for calculating the Julia Set.
     * @return The count before divergence occurs.
     */
    private int getJuliaDivergence(Complex z, Complex d) {
        int count = 0; // recursions before divergence
        Complex previousComplex = z;
        double modulus = 0.0;
        while (modulus < MODULUS_SQUARED_LIMIT && count < ITERATION_LIMIT) {
            previousComplex = getNext(previousComplex, d);
            lastComplex = previousComplex;
            modulus = Math.sqrt(previousComplex.modulusSquared());
            count++;
        }
        return count;
    }


    /**
     * Get the next complex number for a Julia Set equation.
     * @param z The z value.
     * @param c The constant value.
     * @return The next z value.
     */
    public Complex getNext(Complex z, Complex c) {
        Complex zsquared = z.pow(ORDER);
        return zsquared.add(c);
    }

    /**
     * Set anew value for constant.
     * @param d The new constant value.
     */
    public void setConstant(Complex d) {
        CONSTANT = d;
    }

    /**
     * Get the current constant value.
     * @return The current constant value.
     */
    public Complex getConstant() {
        return CONSTANT;
    }
}
