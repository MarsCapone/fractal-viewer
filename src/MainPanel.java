import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MainPanel extends JPanel {

    // preset some different font sizes
    private final Font _bigFont = new Font(null, Font.BOLD, 20);
    private final Font _medFont = new Font(null, Font.BOLD, 15);
    private final Font _smallFont = new Font(null, Font.BOLD, 10);

    protected static JuliaPanel juliaPanel;
    protected static MandelbrotPanel mandelbrotPanel;
    
    
    public MainPanel() {
        setLayout(new BorderLayout());
        
        mandelbrotPanel = new MandelbrotPanel();
        Container additionalPanel = new AdditionalRightPanel();
        this.add(mandelbrotPanel, BorderLayout.CENTER);
        this.add(additionalPanel, BorderLayout.EAST);
        
        mandelbrotPanel.addMouseListener(new MandelbrotListener());
    }
}
