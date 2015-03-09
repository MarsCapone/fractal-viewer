import java.awt.*;
import java.awt.event.MouseEvent;

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
        // set the starting drag point
        startDrag = mouseEvent.getPoint();

        // set the ending drag point
        mandelbrotPanel.setDrawMode(true);
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        // end the dragging
        endDrag = mouseEvent.getPoint();

        // check if the area is of legitimate size
        boolean xok = Math.abs(startDrag.x - endDrag.x) >= MIN_DRAG_SIZE;
        boolean yok = Math.abs(startDrag.y - endDrag.y) >= MIN_DRAG_SIZE;


        if (xok && yok) {
            // if it is zoom in
            mandelbrotPanel.zoom(startDrag, endDrag);
            mandelbrotPanel.paintImage();
            mandelbrotPanel.repaint();
            
        } else if (startDrag.x == endDrag.x && startDrag.y == endDrag.y) {
            // if it isn't generate the julia set for that point
            Point click = mouseEvent.getPoint();
            Complex juliaConstant = mandelbrotPanel.getComplexPoint(click);
            System.out.printf("Generating Julia Set for constant: %s \n", juliaConstant);

            // repaint the julia set
            juliaPanel.resetAxes();
            juliaPanel.setConstant(juliaConstant);
            juliaPanel.paintImage();
            juliaPanel.repaint();
        }
        
        // stop drawing
        mandelbrotPanel.setDrawMode(false);

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        // use accurate cursor
        mandelbrotPanel.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        // stop using accurate cursor
        mandelbrotPanel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        if (mandelbrotPanel.getDrawMode()) {
            // draw the rectangle
            endDrag = mouseEvent.getPoint();
            mandelbrotPanel.setRectangle(startDrag, endDrag);
            mandelbrotPanel.repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
    }
}

