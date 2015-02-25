import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.security.InvalidParameterException;

/**
 * Created by samson on 2/25/15.
 */
public abstract class FractalPanelListener implements MouseListener {

    Point startDrag, endDrag;

    public void changePanel(JPanel oldPanel, JPanel newPanel) {
        oldPanel.removeAll();
        oldPanel.add(newPanel);
        oldPanel.revalidate();
    }

    public abstract JPanel getNewPanel(Point p1, Point p2) throws InvalidParameterException ;


}
