import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.security.InvalidParameterException;

public class MandelbrotListener extends FractalPanelListener {
    
    
    
    @Override
    public BufferedImage getNewPanelImage(Point p1, Point p2) throws InvalidParameterException {
        return null;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point click = mouseEvent.getPoint();
        Complex juliaConstant = MainPanel.juliaPanel.getComplexPoint(click);
        MainPanel.juliaPanel.paintJuliaImage(juliaConstant);
        MainPanel.juliaPanel.repaint();
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
