import javax.swing.*;
import java.awt.*;

public class AdditionalPanel extends JPanel {

    /**
     * Additional Pane contains Julia set and settings.
     */
    public AdditionalPanel(BigPanel bigPanel, SmallPanel smallPanel) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        smallPanel.setPreferredSize(new Dimension(400, 400));
        SettingsPane settingsPane = new SettingsPane(bigPanel, smallPanel);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, smallPanel, settingsPane);
        splitPane.setOneTouchExpandable(true);
        splitPane.setContinuousLayout(true);
        splitPane.setResizeWeight(0.5);
        splitPane.setDividerSize(5);
        splitPane.setDividerLocation(400);

        Dimension minimumSize = new Dimension(200, 200);
        smallPanel.setMinimumSize(minimumSize);
        settingsPane.setMinimumSize(minimumSize);

        add(splitPane);

    }

    /**
     * Swap the current small panel with a new panel.
     * @param newPanel The new panel to show instead of the current small panel.
     */
    public void changeFractalPanel(FractalPanel newPanel) {
        try {
            FractalPanel currentAdditional = getFractalPanel();
            this.add(newPanel, BorderLayout.NORTH);
            remove(currentAdditional);
            revalidate();
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Get the current panel which shows a fractal.
     * @return The current fractal panel.
     */
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
        throw new NullPointerException("Small Fractal Panel cannot be found.");
    }
}
