import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.security.InvalidParameterException;

/**
 * Created by samson on 2/25/15.
 */
public abstract class FractalPanelListener implements MouseListener {

    Point startDrag, endDrag;

    public void changePanelImage(GeneralFractalPanel oldPanel, BufferedImage newImage) {
        oldPanel.setImage(newImage);
    }

    public abstract BufferedImage getNewPanelImage(Point p1, Point p2) throws InvalidParameterException ;


}
