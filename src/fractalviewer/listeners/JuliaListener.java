package fractalviewer.listeners;

import fractalviewer.panels.JuliaPanel;

import java.awt.*;
import java.awt.event.MouseEvent;

public class JuliaListener extends FractalPanelListener {

    private JuliaPanel juliaPanel;

    public JuliaListener(JuliaPanel juliaPanel) {
        super();
        this.juliaPanel = juliaPanel;
    }
    
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        // set the start drag point
        startDrag = mouseEvent.getPoint();

        // set the draw mode
        juliaPanel.setDrawMode(true);
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
            juliaPanel.zoom(startDrag, endDrag);
            juliaPanel.paintImage();
            juliaPanel.repaint();
        }

        // stop drawing
        juliaPanel.setDrawMode(false);
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        // use accurate mouse when on panel
        juliaPanel.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        // use default mouse when not on panel
        juliaPanel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        if (juliaPanel.getDrawMode()) {
            // set the rectangle and draw it.
            endDrag = mouseEvent.getPoint();
            juliaPanel.setRectangle(startDrag, endDrag);
            juliaPanel.repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }
}
