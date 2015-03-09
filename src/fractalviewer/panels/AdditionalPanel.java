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
        
        add(juliaPanel, BorderLayout.NORTH);
        add(new SettingsPane(mandelbrotPanel, juliaPanel), BorderLayout.SOUTH);
    }
    
    public void changeFractalPanel(GeneralFractalPanel newPanel) {
        GeneralFractalPanel currentAdditional = getFractalPanel();
        add(newPanel, BorderLayout.NORTH);
        remove(currentAdditional);
        revalidate();
    }

    public GeneralFractalPanel getFractalPanel() {
        Component[] allComponents = getComponents();
        for (Component c: allComponents) {
            if (c instanceof GeneralFractalPanel) {
                return (GeneralFractalPanel) c;
            }
        }
        return null;
    }
}
