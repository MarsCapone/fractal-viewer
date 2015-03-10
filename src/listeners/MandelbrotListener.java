package listeners;

import extras.Complex;
import panels.JuliaPanel;
import panels.MandelbrotPanel;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class MandelbrotListener extends FractalPanelListener {

    private final MandelbrotPanel mandelbrotPanel;
    private final JuliaPanel juliaPanel;

    private double ZOOM_FRACTION = 0.3;

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
            mandelbrotPanel.startThread();
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

    @Override
    public void mouseWheelMoved(MouseWheelEvent mouseWheelEvent) {
        double[] currentPosition = mandelbrotPanel.getPosition();
        Complex newPosition = mandelbrotPanel.getComplexPoint(mouseWheelEvent.getPoint());
        double newR;
        int direction = mouseWheelEvent.getWheelRotation();
        if (direction < 0) { // zoom in
            newR = currentPosition[2] * ZOOM_FRACTION;
        } else { // zoom out
            newR = currentPosition[2]* ( 1 + (1-ZOOM_FRACTION));
        }
        mandelbrotPanel.setDrawMode(true);
        Point s = new Point((int) (mouseWheelEvent.getX() - ((ZOOM_FRACTION * mandelbrotPanel.getWidth()) / 2)),
                (int) (mouseWheelEvent.getY() - ((ZOOM_FRACTION * mandelbrotPanel.getHeight()) / 2)));
        Point e =  new Point((int) (mouseWheelEvent.getX() + ((ZOOM_FRACTION * mandelbrotPanel.getWidth()) / 2)),
                (int) (mouseWheelEvent.getY() + ((ZOOM_FRACTION * mandelbrotPanel.getHeight()) / 2)));
        mandelbrotPanel.setRectangle(s, e);
        mandelbrotPanel.repaint();

        mandelbrotPanel.zoom(newPosition, newR);
        mandelbrotPanel.setDrawMode(false);
    }
}

