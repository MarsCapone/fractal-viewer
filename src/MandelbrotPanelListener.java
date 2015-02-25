import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.security.InvalidParameterException;

/**
 * Created by samson on 2/25/15.
 */
public class MandelbrotPanelListener extends FractalPanelListener {

    @Override
    public JPanel getNewPanel(Point p1, Point p2) throws InvalidParameterException {
        int minX = Math.min(p1.x, p2.x);
        int maxX = Math.max(p1.x, p2.x);
        int minY = Math.min(p1.y, p2.y);
        int maxY = Math.max(p1.y, p2.y);

        // don't create a new panel unless the size of the rectangle is legitimate
        if (Math.abs(maxX - minX) >= 10 && Math.abs(maxY - minY) >= 10) {
            Complex topLeft = FractalViewerGraphics.mandelbrotPanel.getComplexPoint(minX, minY);
            Complex bottomRight = FractalViewerGraphics.mandelbrotPanel.getComplexPoint(maxX, maxY);
            return new MandelbrotPanel(topLeft.getReal(), Math.abs(bottomRight.getReal() - topLeft.getReal()), topLeft.getImaginary(), Math.abs(topLeft.getImaginary() - bottomRight.getImaginary()));
        } else {
            throw new InvalidParameterException("Selected area too small");
        }
    }

    private void generateJuliaSet(Complex juliaConstant) {
        changePanel(FractalViewerGraphics.juliaPanel, new JuliaPanel());
        FractalViewerGraphics.juliaPanel.setConstant(juliaConstant);
        FractalViewerGraphics.juliaPanel.paintImage();
        FractalViewerGraphics.juliaPanel.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        startDrag = new Point(mouseEvent.getX(), mouseEvent.getY());
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        try {
            endDrag = new Point(mouseEvent.getX(), mouseEvent.getY());
            changePanel(FractalViewerGraphics.mandelbrotPanel, getNewPanel(startDrag, endDrag));
        } catch (InvalidParameterException e) {
            Complex juliaConstant = FractalViewerGraphics.mandelbrotPanel.getComplexPoint(mouseEvent.getX(), mouseEvent.getY());
            generateJuliaSet(juliaConstant);
        }
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
