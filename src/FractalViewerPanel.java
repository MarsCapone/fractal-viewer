import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class FractalViewerPanel extends JPanel {

    // preset some different font sizes
    private final Font _bigFont = new Font(null, Font.BOLD, 20);
    private final Font _medFont = new Font(null, Font.BOLD, 15);
    private final Font _smallFont = new Font(null, Font.BOLD, 10);

    protected static JuliaPanel juliaPanel;
    protected static MandelbrotPanel mandelbrotPanel;

    /**
     * Everything for the Fractal Viewer is stored in the Fractal Viewer Panel
     */
    public FractalViewerPanel() {

        setLayout(new BorderLayout());

        // create main panels
        mandelbrotPanel = new MandelbrotPanel();
        Container secondaryPane = createSecondaryPane();

        // add main panels
        this.add(mandelbrotPanel, BorderLayout.CENTER);
        this.add(secondaryPane, BorderLayout.WEST);

    }

    /**
     * Create the layout of the instruction pane
     * @return A container with the instruction pane layout
     */
    private Container createSettingsPane() {
        Container settingsPane = new JPanel(new GridLayout(3, 1));
        return settingsPane;
    }

    /**
     * Create the info pane. This pane contains settings and info for the fractal.
     * @return A container preset with the layout and settings required.
     */
    private Container createSecondaryPane() {
        Container secondaryPane = new JPanel(new GridLayout(2, 1, 5, 5));
        secondaryPane.setSize(this.getWidth()/3, this.getHeight());

        juliaPanel = new JuliaPanel();
        juliaPanel.setPreferredSize(new Dimension(300, 300));
        Container settingsPane = createSettingsPane();

        secondaryPane.add(juliaPanel);
        secondaryPane.add(settingsPane);

        return secondaryPane;
    }
}
