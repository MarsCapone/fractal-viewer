package listeners;

import extras.Complex;
import panels.SmallPanel;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class SmallPanelListener extends FractalPanelListener {

    private final SmallPanel smallPanel;

    public SmallPanelListener(SmallPanel smallPanel) {
        super();
        this.smallPanel = smallPanel;
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        // set the start drag point
        startDrag = mouseEvent.getPoint();

        // set the draw mode
        smallPanel.setDrawMode(true);
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        // set the end drag point
        endDrag = mouseEvent.getPoint();

        // check if the end point is OK relative to the start point
        boolean xok = Math.abs(startDrag.x - endDrag.x) >= MIN_DRAG_SIZE;
        boolean yok = Math.abs(startDrag.y - endDrag.y) >= MIN_DRAG_SIZE;

        // if it is of legitimate size, zoom in
        if (xok && yok) {
            smallPanel.zoom(startDrag, endDrag);
            smallPanel.paintImage();
            smallPanel.repaint();
        }

        // stop drawing
        smallPanel.setDrawMode(false);
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        // use accurate mouse when on panel
        smallPanel.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        // use default mouse when not on panel
        smallPanel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        if (smallPanel.getDrawMode()) {
            // set the rectangle and draw it.
            endDrag = mouseEvent.getPoint();
            smallPanel.setRectangle(startDrag, endDrag);
            smallPanel.repaint();
        }
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent mouseWheelEvent) {

        double[] currentPosition = smallPanel.getPosition();
        Complex newPosition = smallPanel.getComplexPoint(mouseWheelEvent.getPoint());
        double newR;
        int direction = mouseWheelEvent.getWheelRotation();
        if (direction < 0) { // zoom in
            newR = currentPosition[2] * ZOOM_FRACTION;
        } else { // zoom out
            newR = currentPosition[2] * (1 + (1 - ZOOM_FRACTION));
        }
        smallPanel.setDrawMode(true);
        Point s = new Point((int) (mouseWheelEvent.getX() - ((ZOOM_FRACTION * smallPanel.getWidth()) / 2)),
                (int) (mouseWheelEvent.getY() - ((ZOOM_FRACTION * smallPanel.getHeight()) / 2)));
        Point e = new Point((int) (mouseWheelEvent.getX() + ((ZOOM_FRACTION * smallPanel.getWidth()) / 2)),
                (int) (mouseWheelEvent.getY() + ((ZOOM_FRACTION * smallPanel.getHeight()) / 2)));
        smallPanel.setRectangle(s, e);
        smallPanel.repaint();

        smallPanel.zoom(newPosition, newR);
        smallPanel.setDrawMode(false);
    }
}
