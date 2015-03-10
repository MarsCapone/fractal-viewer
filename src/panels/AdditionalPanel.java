package panels;

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

    public void changeFractalPanel(FractalPanel newPanel) {
        FractalPanel currentAdditional = getFractalPanel();
        this.add(newPanel, BorderLayout.NORTH);
        remove(currentAdditional);
        revalidate();
    }

    public FractalPanel getFractalPanel() {
        Component[] allComponents = getComponents();
        for (Component c : allComponents) {
            if (c instanceof FractalPanel) {
                return (FractalPanel) c;
            }
        }
        return null;
    }
}
