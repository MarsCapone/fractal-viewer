import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.security.InvalidParameterException;

/**
 * Created by samson on 2/25/15.
 */
public class JuliaPanelListener extends FractalPanelListener {

    @Override
    public BufferedImage getNewPanelImage(Point p1, Point p2) throws InvalidParameterException {
        int minX = Math.min(p1.x, p2.x);
        int maxX = Math.max(p1.x, p2.x);
        int minY = Math.min(p1.y, p2.y);
        int maxY = Math.max(p1.y, p2.y);

        // don't create a new panel unless the size of the rectangle is legitimate
        if (Math.abs(maxX - minX) >= 10 && Math.abs(maxY - minY) >= 10) {
            Complex topLeft = FractalViewerPanel.juliaPanel.getComplexPoint(minX, minY);
            Complex bottomRight = FractalViewerPanel.juliaPanel.getComplexPoint(maxX, maxY);
            JuliaPanel newJulia =  new JuliaPanel(topLeft.getReal(), bottomRight.getReal() - topLeft.getReal(), topLeft.getImaginary(), topLeft.getImaginary() - bottomRight.getImaginary());
            return newJulia.getImage();
        }
        return FractalViewerPanel.juliaPanel.getImage();
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
        endDrag = new Point(mouseEvent.getX(), mouseEvent.getY());
        changePanelImage(FractalViewerPanel.juliaPanel, getNewPanelImage(startDrag, endDrag));
        FractalViewerPanel.juliaPanel.repaint();
        FractalViewerPanel.mandelbrotPanel.repaint();
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        System.err.println("Mouse outside Fractal Panel");
    }
}
