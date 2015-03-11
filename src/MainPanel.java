import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {

    private final AdditionalPanel additionalPanel;

    /**
     * The panel containing everything required for the program.
     */
    public MainPanel() {
        setLayout(new BorderLayout());

        BigPanel bigPanel = new BigPanel();
        SmallPanel smallPanel = new SmallPanel();

        additionalPanel = new AdditionalPanel(bigPanel, smallPanel);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, bigPanel, additionalPanel);
        splitPane.setOneTouchExpandable(true);
        splitPane.setContinuousLayout(true);
        splitPane.setResizeWeight(0.5);
        splitPane.setDividerSize(5);

        Dimension minimumSize = new Dimension(300, 400);
        bigPanel.setMinimumSize(minimumSize);
        additionalPanel.setMinimumSize(minimumSize);

        this.add(splitPane);

        BigPanelListener mL = new BigPanelListener(bigPanel, smallPanel);
        SmallPanelListener jL = new SmallPanelListener(smallPanel);

        bigPanel.addMouseListener(mL);
        bigPanel.addMouseMotionListener(mL);
        bigPanel.addMouseWheelListener(mL);

        smallPanel.addMouseListener(jL);
        smallPanel.addMouseMotionListener(jL);
        smallPanel.addMouseWheelListener(jL);
    }

    public void swapPanels() {
        try {
            FractalPanel currentMain = getFractalPanel();
            FractalPanel currentAdditional = additionalPanel.getFractalPanel();

            System.out.println(currentMain);
            System.out.println(currentAdditional);
            additionalPanel.changeFractalPanel(currentMain);

            remove(currentMain);
            this.add(currentAdditional, BorderLayout.CENTER);
            revalidate();
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }

    }

    public FractalPanel getFractalPanel() {
        Component[] allComponents = getComponents();
        for (Component c : allComponents) {
            Container e = (Container) c;
            for (Component d: e.getComponents()) {
                if (e instanceof BigPanel) {
                    return (BigPanel) e;
                } else if (e instanceof SmallPanel) {
                    return (SmallPanel) e;
                }
            }
        }
        throw new NullPointerException("Big Fractal Panel cannot be found.");
    }
}

