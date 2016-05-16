package panel.listeners;

import panel.*;
import panel.settings.*;
import calculation.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class BigPanelListener extends FractalPanelListener {

    private static final boolean AUTO_GENERATE_SMALL = false;

    private final BigPanel bigPanel;
    private final SmallPanel smallPanel;

    public BigPanelListener(BigPanel bigPanel, SmallPanel smallPanel) {
        this.bigPanel = bigPanel;
        this.smallPanel = smallPanel;
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        // set the starting drag point
        FractalPanelListener.startDrag = mouseEvent.getPoint();

        // set the ending drag point
        bigPanel.setDrawMode(true);
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        // end the dragging
        FractalPanelListener.endDrag = mouseEvent.getPoint();

        // check if the area is of legitimate size
        boolean xok = Math.abs(FractalPanelListener.startDrag.x - FractalPanelListener.endDrag.x) >= MIN_DRAG_SIZE;
        boolean yok = Math.abs(FractalPanelListener.startDrag.y - FractalPanelListener.endDrag.y) >= MIN_DRAG_SIZE;


        if (xok && yok) {
            // if it is zoom in
            bigPanel.zoom(FractalPanelListener.startDrag, FractalPanelListener.endDrag);
            bigPanel.paintImage();
            bigPanel.repaint();

        } else if (FractalPanelListener.startDrag.x == FractalPanelListener.endDrag.x && FractalPanelListener.startDrag.y == FractalPanelListener.endDrag.y) {
            if (AUTO_GENERATE_SMALL) {
                SmallFavouriteSetting.addNewFavourite(smallPanel.getScaledImage());
            } else {
                // if it isn't generate the julia set for that point
                Point click = mouseEvent.getPoint();
                Complex juliaConstant = bigPanel.getComplexPoint(click);
                //System.out.printf("Generating Small Panel for constant: %s \n", juliaConstant);

                // repaint the julia set
                smallPanel.resetAxes();
                smallPanel.setConstant(juliaConstant);
                smallPanel.paintImage();
                smallPanel.repaint();
            }
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
            FractalPanelListener.endDrag = mouseEvent.getPoint();
            bigPanel.setRectangle(FractalPanelListener.startDrag, FractalPanelListener.endDrag);
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

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() >= 2 && FractalPanelListener.MAXIMISE) {
            new MaximizedFractalView(bigPanel);
        }
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        if (AUTO_GENERATE_SMALL) {
            // if it isn't generate the julia set for that point
            Point click = mouseEvent.getPoint();
            Complex juliaConstant = bigPanel.getComplexPoint(click);
            //System.out.printf("Generating Small Panel for constant: %s \n", juliaConstant);

            // repaint the julia set
            smallPanel.resetAxes();
            smallPanel.setConstant(juliaConstant);
            smallPanel.paintImage();
            smallPanel.repaint();
        }
    }
}

