import java.awt.*;
import java.awt.event.*;

public abstract class FractalPanelListener implements MouseListener, MouseMotionListener, MouseWheelListener {

    protected static Point startDrag, endDrag;
    protected final int MIN_DRAG_SIZE = 3;
    protected double ZOOM_FRACTION = 0.3;
    protected double MAX_R = 20;

    protected static boolean MAXIMISE = false;

    public void setMaximising(boolean m) {
        MAXIMISE = m;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

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

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent mouseWheelEvent) {

    }
}
