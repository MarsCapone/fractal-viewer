import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

public class FractalViewerGraphics extends JPanel {

    public double MODULUS_LIMIT = 2;
    private BufferedImage image;

    // settings for the abstract axes
    private double abstractMinX, abstractMinY;
    private double abstractRangeX, abstractRangeY;

    /**
     * Create a new Graphics Panel. The Graphics panel shows the actual fractal
     * @param abstractMinX The minimum value the real axis.
     * @param abstractRangeX The range of the real axis.
     * @param abstractMinY The minimum value on the imaginary axis.
     * @param abstractRangeY The range of the imaginary axis.
     */
    public FractalViewerGraphics(double abstractMinX, double abstractRangeX, double abstractMinY, double abstractRangeY) {
        this.abstractMinX = abstractMinX;
        this.abstractMinY = abstractMinY;
        this.abstractRangeX = abstractRangeX;
        this.abstractRangeY = abstractRangeY;

        // set important settings
        setPreferredSize(new Dimension(300, 300));
        setLayout(new FlowLayout());

        //this.setDoubleBuffered(true);

        this.setBackground(Color.BLACK);
    }

    /**
     * A default Fractal Viewer Graphics Pane. By default the axes show -2 to 2 on the real, and -1.6 to 1.6
     * on the imaginary axis.
     */
    public FractalViewerGraphics() {
        this(-2.0, 4.0, -1.6, 3.2);
    }

    /**
     * Create mandelbrot set on Buffered Image. Pretty sure this is a good way o do things, but unsure how to display.
     */
    private void paintImage() {
        image = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int w=0; w<getWidth(); w++) {
            for (int h=0; h<getHeight(); h++) {
                Complex complexPoint = getComplexPoint(w, h);
                Color pointColour = getMandelbrotColour(complexPoint);
                image.setRGB(w, h, pointColour.getRGB());
            }
        }
    }

    /**
     * Paint the scene. Not sure this is the ideal way of doing things.
     * @param g
     */
    public void paintComponent(Graphics g) {
        paintImage();
        g.drawImage(image, 0, 0, null);
    }

    /**
     * Get the complex number corresponding to a point on a JPanel axis.
     * @param x The x value on the JPanel axis.
     * @param y The y value on the JPanel axis.
     * @return A complex number with real and imaginary values translated from a JPanel point.
     */
    private Complex getComplexPoint(int x, int y) {
        return GraphCalc.getComplexPoint(x, y, this.getWidth(), this.getHeight(), abstractRangeX, abstractMinX, abstractRangeY, abstractMinY);
    }

    /**
     * Get an integer value for the divergence of a complex number when passed to the Mandelbrot equation.
     * The series diverges when the modulus of the number is greater than MODULUS_LIMIT.
     * @param c The starting value to calculate the Mandelbrot set from.
     * @return The number of recursions before divergence.
     */
    private int getMandelbrotDivergence(Complex c) {
        int count = 0; // the number of recursions before divergence
        Complex previousComplex = c;
        double modulus = 0.0;
        while (modulus < MODULUS_LIMIT) {
            previousComplex = getNextMandelbrot(previousComplex, c);
            modulus = Math.sqrt(previousComplex.modulusSquared());
            if (count > 99) {
                break;
            }
            count++;
        }
        return count;
    }

    /**
     * A colour depending on the integer value of divergence for a complex number in the
     * Mandelbrot set.
     * @param c The starting complex number for the Mandelbrot set.
     * @return A colour depending on the value of divergence.
     */
    private Color getMandelbrotColour(Complex c) {
        int divergence = getMandelbrotDivergence(c);
        return new Color(0, 0, 255 - divergence);
        /*if (divergence >= 100) {
            return Color.BLACK;
        } else if (divergence > 49) {
            return Color.white;
        } else if (divergence > 39) {
            return Color.gray;
        } else if (divergence > 29) {
            return Color.darkGray;
        } else if (divergence > 19) {
            return Color.BLUE;
        } else if (divergence > 9) {
            return Color.blue;
        } else if (divergence > 5) {
            return Color.cyan;
        } else {
            return Color.RED;
        }*/
        //TODO sort out nice colours
    }

    /**
     * Get the next value from the Mandelbrot equation.
     * @param z The complex z value.
     * @param c The complex c value.
     * @return The next Mandelbrot value.
     */
    private Complex getNextMandelbrot(Complex z, Complex c) {
        Complex zsquared = z.square();
        return zsquared.add(c);
    }


}
