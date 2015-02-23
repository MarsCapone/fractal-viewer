import javax.swing.*;
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

    // getJuliaDivergence(juliaGraphPoint, clickedMandelbrotPoint)
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

    private Color getJuliaColour(Complex z, Complex d) {
        int divergence = getJuliaDivergence(z, d);
        return getColour(divergence);
    }

    private Complex getNextJulia(Complex z, Complex c) {
        Complex zsquared = z.square();
        return zsquared.add(c);
    }



}
