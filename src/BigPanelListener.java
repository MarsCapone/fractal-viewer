import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class BigPanelListener extends FractalPanelListener {

    private final BigPanel bigPanel;
    private final SmallPanel smallPanel;

    public BigPanelListener(BigPanel bigPanel, SmallPanel smallPanel) {
        this.bigPanel = bigPanel;
        this.smallPanel = smallPanel;
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        // set the starting drag point
        startDrag = mouseEvent.getPoint();

        // set the ending drag point
        bigPanel.setDrawMode(true);
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
            bigPanel.zoom(startDrag, endDrag);
            bigPanel.paintImage();
            bigPanel.repaint();

        } else if (startDrag.x == endDrag.x && startDrag.y == endDrag.y) {
            // if it isn't generate the julia set for that point
            Point click = mouseEvent.getPoint();
            Complex juliaConstant = bigPanel.getComplexPoint(click);
            System.out.printf("Generating Small Panel for constant: %s \n", juliaConstant);

            // repaint the julia set
            smallPanel.resetAxes();
            smallPanel.setConstant(juliaConstant);
            smallPanel.paintImage();
            smallPanel.repaint();
        }

        // stop drawing
        bigPanel.setDrawMode(false);

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        // use accurate cursor
        bigPanel.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        // stop using accurate cursor
        bigPanel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        if (bigPanel.getDrawMode()) {
            // draw the rectangle
            endDrag = mouseEvent.getPoint();
            bigPanel.setRectangle(startDrag, endDrag);
            bigPanel.repaint();
        }
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent mouseWheelEvent) {
        double[] currentPosition = bigPanel.getPosition();
        Complex newPosition = bigPanel.getComplexPoint(mouseWheelEvent.getPoint());
        double newR;
        int direction = mouseWheelEvent.getWheelRotation();
        if (direction < 0) { // zoom in
            newR = currentPosition[2] * ZOOM_FRACTION;
        } else { // zoom out
            newR = currentPosition[2]* ( 1 + (1-ZOOM_FRACTION));
            if (newR > MAX_R) {
                newR = 2;
                newPosition = Complex.ZERO;
                System.out.println("Zoomed out too far, resetting to normal.");
            }
        }

        bigPanel.zoom(newPosition, newR);
        bigPanel.setDrawMode(false);
    }
}

