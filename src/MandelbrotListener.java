import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.security.InvalidParameterException;

public class MandelbrotListener extends FractalPanelListener {

    private MandelbrotPanel mandelbrotPanel;
    private JuliaPanel juliaPanel;

    public MandelbrotListener(MandelbrotPanel mandelbrotPanel, JuliaPanel juliaPanel) {
        this.mandelbrotPanel = mandelbrotPanel;
        this.juliaPanel = juliaPanel;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        startDrag = mouseEvent.getPoint();
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        endDrag = mouseEvent.getPoint();
        boolean xok = Math.abs(startDrag.x - endDrag.x) >= 10;
        boolean yok = Math.abs(startDrag.y - endDrag.y) >= 10;
        if (xok && yok) {
            mandelbrotPanel.zoom(startDrag, endDrag);
            mandelbrotPanel.repaint();
            
        } else if (startDrag.x == endDrag.x && startDrag.y == endDrag.y) {
            Point click = mouseEvent.getPoint();
            Complex juliaConstant = mandelbrotPanel.getComplexPoint(click);
            System.out.printf("Generating Julia Set for constant: %s \n", juliaConstant); //
            juliaPanel.setConstant(juliaConstant);
            juliaPanel.repaint();
        }
        mandelbrotPanel.setDrawMode(false);

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        endDrag = mouseEvent.getPoint();
        mandelbrotPanel.setDrawMode(true);
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }
}

