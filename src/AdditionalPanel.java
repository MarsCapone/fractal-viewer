
import build.tools.javazic.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdditionalPanel extends JPanel {

    /**
     * Additional Pane contains Julia set and settings.
     */
    public AdditionalPanel(MandelbrotPanel mandelbrotPanel, JuliaPanel juliaPanel) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        juliaPanel.setPreferredSize(new Dimension(400, 400));
        
        this.add(juliaPanel, BorderLayout.NORTH);
        this.add(new SettingsPane(mandelbrotPanel, juliaPanel), BorderLayout.CENTER);
    }
}
