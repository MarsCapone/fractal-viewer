package fractalviewer.panels;

import javax.swing.*;
import java.awt.*;

public class AdditionalPanel extends JPanel {

    /**
     * Additional Pane contains Julia set and settings.
     */
    public AdditionalPanel(MandelbrotPanel mandelbrotPanel, JuliaPanel juliaPanel) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        juliaPanel.setPreferredSize(new Dimension(400, 400));
        SettingsPane settingsPane = new SettingsPane(mandelbrotPanel, juliaPanel);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, juliaPanel, settingsPane);
        splitPane.setOneTouchExpandable(true);
        splitPane.setContinuousLayout(true);
        splitPane.setResizeWeight(0.5);
        splitPane.setDividerSize(5);

        Dimension minimumSize = new Dimension(300, 300);
        juliaPanel.setMinimumSize(minimumSize);
        settingsPane.setMinimumSize(minimumSize);

        add(splitPane);

    }

    public void changeFractalPanel(GeneralFractalPanel newPanel) {
        GeneralFractalPanel currentAdditional = getFractalPanel();
        this.add(newPanel, BorderLayout.NORTH);
        remove(currentAdditional);
        revalidate();
    }

    public GeneralFractalPanel getFractalPanel() {
        Component[] allComponents = getComponents();
        for (Component c : allComponents) {
            if (c instanceof GeneralFractalPanel) {
                return (GeneralFractalPanel) c;
            }
        }
        return null;
    }
}
