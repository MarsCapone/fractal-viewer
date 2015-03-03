import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.security.InvalidParameterException;

/**
 * Created by samson on 2/25/15.
 */
public class MandelbrotPanelListener extends FractalPanelListener {

    @Override
    public BufferedImage getNewPanelImage(Point p1, Point p2) throws InvalidParameterException {
        int minX = Math.min(p1.x, p2.x);
        int maxX = Math.max(p1.x, p2.x);
        int minY = Math.min(p1.y, p2.y);
        int maxY = Math.max(p1.y, p2.y);

        // don't create a new panel unless the size of the rectangle is legitimate
        if (Math.abs(maxX - minX) >= 10 && Math.abs(maxY - minY) >= 10) {
            Complex topLeft = FractalViewerPanel.mandelbrotPanel.getComplexPoint(minX, minY);
            Complex bottomRight = FractalViewerPanel.mandelbrotPanel.getComplexPoint(maxX, maxY);
            GeneralFractalPanel newPanel = new MandelbrotPanel(topLeft.getReal(), Math.abs(bottomRight.getReal() - topLeft.getReal()), topLeft.getImaginary(), Math.abs(topLeft.getImaginary() - bottomRight.getImaginary()));
            return newPanel.getImage();
        } else {
            throw new InvalidParameterException("Selected area too small");
        }
    }

    private void generateJuliaSet(Complex juliaConstant) {
        JuliaPanel newJuliaPanel = new JuliaPanel();
        newJuliaPanel.paintJuliaImage(juliaConstant);
        BufferedImage newJuliaImage = newJuliaPanel.getImage();
        changePanelImage(FractalViewerPanel.juliaPanel, newJuliaImage);
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
            BufferedImage zoomedImage = getNewPanelImage(startDrag, endDrag);
            changePanelImage(FractalViewerPanel.mandelbrotPanel, zoomedImage);
        } catch (InvalidParameterException e) {
            Complex juliaConstant = FractalViewerPanel.mandelbrotPanel.getComplexPoint(mouseEvent.getX(), mouseEvent.getY());
            generateJuliaSet(juliaConstant);
        }
        FractalViewerPanel.mandelbrotPanel.repaint();
        FractalViewerPanel.juliaPanel.repaint();
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
