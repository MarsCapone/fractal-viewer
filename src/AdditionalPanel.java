import javax.swing.*;
import java.awt.*;

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
