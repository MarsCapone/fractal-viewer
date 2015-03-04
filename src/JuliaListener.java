import java.awt.event.MouseEvent;

public class JuliaListener extends FractalPanelListener{

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
        startDrag = mouseEvent.getPoint();
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        endDrag = mouseEvent.getPoint();
        boolean xok = Math.abs(startDrag.x - endDrag.x) >= 10;
        boolean yok = Math.abs(startDrag.y - endDrag.y) >= 10;
        if (xok && yok) {
            juliaPanel.zoom(startDrag, endDrag);
            juliaPanel.paintImage();
            juliaPanel.repaint();
        }
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        if (juliaPanel.getDrawMode()) {
            endDrag = mouseEvent.getPoint();
            System.out.println("dragging");
            juliaPanel.setRectangle(startDrag, endDrag);
            juliaPanel.repaint();
        } else {
            System.out.println("not dragging for some reason");
        }
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }
}
