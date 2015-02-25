import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;

public class JuliaPanel extends GeneralFractalPanel {

    private static BufferedImage juliaImage;
    
    private Complex constant;

    public JuliaPanel(double abstractMinX, double abstractRangeX, double abstractMinY, double abstractRangeY) {
        super(abstractMinX, abstractRangeX, abstractMinY, abstractRangeY);
    }
    
    public JuliaPanel() {
        super();
    }

    /**
     * Draw the Julia Set with the given constant, onto a Buffered Image. 
     * @param d The constant for the Julia Set
     */
    public void paintJuliaImage(Complex d) {
        juliaImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        constant = d;
        for (int w=0; w<getWidth(); w++) {
            for (int h=0; h<getHeight(); h++) {
                Complex complexPoint = getComplexPoint(w, h);
                int divergence = getJuliaDivergence(complexPoint, d);
                Color pointColour = getColourT1(complexPoint, divergence); //getJuliaColour(complexPoint, d);
                juliaImage.setRGB(w, h, pointColour.getRGB());
            }
        }
    }
    
    public void paintJuliaImage() {
        paintJuliaImage(constant);
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
        while (modulus < MODULUS_LIMIT && count < COUNT_LIMIT) {
            previousComplex = getNextJulia(previousComplex, d);
            modulus = Math.sqrt(previousComplex.modulusSquared());
            count++;
        }
        return count;
    }

    /**
     * Paint the scene. Not sure this is the ideal way of doing things.
     * @param g Graphics object
     */
    public void paintComponent(Graphics g) {
        //paintJuliaImage(new Complex(0, 0));
        g.drawImage(juliaImage, 0, 0, null);
    }
    
    public void repaint(Graphics g) {
        g.drawImage(juliaImage, 0, 0, null);
    }


    /**
     * Get the next complex number for a Julia Set equation.
     * @param z The z value.
     * @param c The constant value.
     * @return The next z value.
     */
    private Complex getNextJulia(Complex z, Complex c) {
        Complex zsquared = z.square();
        return zsquared.add(c);
    }
    
    public void setConstant(Complex d) {
        constant = d;
    }
}
