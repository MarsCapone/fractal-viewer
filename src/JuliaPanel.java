import java.awt.*;
import java.awt.image.BufferedImage;

public class JuliaPanel extends GeneralFractalPanel {

    private BufferedImage juliaImage;

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
    private void paintJuliaImage(Complex d) {
        juliaImage = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int w=0; w<getWidth(); w++) {
            for (int h=0; h<getHeight(); h++) {
                Complex complexPoint = getComplexPoint(w, h);
                Color pointColour = getJuliaColour(complexPoint, d);
                juliaImage.setRGB(w, h, pointColour.getRGB());
            }
        }
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
     * @param g
     */
    public void paintComponent(Graphics g) {
        paintJuliaImage(new Complex(-0.15, -0.8));
        g.drawImage(juliaImage, 0, 0, null);
    }

    /**
     * Get a colour for a point in the Julia Set. 
     * @param z The z value.
     * @param d The constant value.
     * @return A color to display.
     */
    private Color getJuliaColour(Complex z, Complex d) {
        int divergence = getJuliaDivergence(z, d);
        return getColour(divergence);
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



}
