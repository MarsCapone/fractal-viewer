
import javax.swing.*;
import java.awt.*;

public class AdditionalRightPanel extends JPanel {
    
    public AdditionalRightPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        MainPanel.juliaPanel = new JuliaPanel();
        MainPanel.juliaPanel.setPreferredSize(new Dimension(300, 300));
        
        this.add(MainPanel.juliaPanel, BorderLayout.NORTH);
        this.add(createSettingsPane(), BorderLayout.CENTER);
    }

    private Container createSettingsPane() {
        Container settings = new JPanel();
        settings.setLayout(new BoxLayout(settings, BoxLayout.PAGE_AXIS));
        
        // add all settings
        settings.add(new JLabel("Settings"));
        
        return settings;
    }
}
