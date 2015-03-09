import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public abstract class FractalPanelListener implements MouseListener, MouseMotionListener {

    protected static Point startDrag, endDrag;
    protected final int MIN_DRAG_SIZE = 3;

}
