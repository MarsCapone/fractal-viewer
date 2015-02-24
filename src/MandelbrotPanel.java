import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class MandelbrotPanel extends GeneralFractalPanel implements MouseListener, MouseWheelListener, MouseMotionListener {

    private BufferedImage mandelbrotImage;
    private Point startDrag, endDrag;

    public MandelbrotPanel(double abstractMinX, double abstractRangeX, double abstractMinY, double abstractRangeY) {
        super(abstractMinX, abstractRangeX, abstractMinY, abstractRangeY);
        addMouseListener(this);
        addMouseWheelListener(this);
    }

    public MandelbrotPanel() {
        super();
        addMouseListener(this);
        addMouseWheelListener(this);
    }

    /**
     * Create mandelbrot set on Buffered Image. Pretty sure this is a good way o do things, but unsure how to display.
     */
    private void paintMandelbrotImage() {
        mandelbrotImage = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int w=0; w<getWidth(); w++) {
            for (int h=0; h<getHeight(); h++) {
                Complex complexPoint = getComplexPoint(w, h);
                Color pointColour = getColourT1(w, h, getMandelbrotDivergence(complexPoint));
                mandelbrotImage.setRGB(w, h, pointColour.getRGB());
            }
        }
    }

    /**
     * Paint the scene. Not sure this is the ideal way of doing things.
     * @param g Graphics object
     */
    public void paintComponent(Graphics g) {
        paintMandelbrotImage();
        g.drawImage(mandelbrotImage, 0, 0, null);
    }
    
    /*
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;

        if (startDrag != null && endDrag != null) {
            g2.setPaint(Color.LIGHT_GRAY);
            Shape r = new Rectangle2D.Float(Math.min(startDrag.x, endDrag.x), Math.min(startDrag.y, endDrag.y), Math.abs(endDrag.x - startDrag.x), Math.abs(endDrag.y - startDrag.y));
            g2.draw(r);
        }
    }
    */
    

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
    
    public BufferedImage getImage() {
        return mandelbrotImage;
    }


    /**
     * When the mouse is clicked on a pixel, generate the correct Julia Set with the constant at the clicked point
     * @param mouseEvent Mouse clicked.
     */
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        int x = mouseEvent.getX();
        int y = mouseEvent.getY();
        Complex point = getComplexPoint(x, y);
        //System.out.println(point);
        FractalViewerGraphics.juliaPanel.paintJuliaImage(point);
        FractalViewerGraphics.juliaPanel.repaint();
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        startDrag = new Point(mouseEvent.getX(), mouseEvent.getY());
        //System.out.println(startDrag);
        //repaint();
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        endDrag = new Point(mouseEvent.getX(), mouseEvent.getY());
        //System.out.println(endDrag);
        //repaint();
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {}

    @Override
    public void mouseExited(MouseEvent mouseEvent) {}

    @Override
    public void mouseWheelMoved(MouseWheelEvent mouseWheelEvent) {
        System.out.printf("X: %d | Y: %d | M: %d \n", mouseWheelEvent.getX(), mouseWheelEvent.getY(), mouseWheelEvent.getWheelRotation()*mouseWheelEvent.getScrollAmount());
        //zoomIn(-1, 0, 1, 1);
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        endDrag = new Point(mouseEvent.getX(), mouseEvent.getY());
        System.out.println(endDrag);
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }
}
