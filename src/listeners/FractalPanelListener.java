package listeners;

import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;

public abstract class FractalPanelListener implements MouseListener, MouseMotionListener, MouseWheelListener {

    protected static Point startDrag, endDrag;
    protected final int MIN_DRAG_SIZE = 3;

}
