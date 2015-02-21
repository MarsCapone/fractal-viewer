import javax.swing.*;
import java.awt.*;

public class FractalViewerPanel extends JPanel {

    // preset some different font sizes
    private Font _bigFont = new Font(null, Font.BOLD, 20);
    private Font _medFont = new Font(null, Font.BOLD, 15);
    private Font _smallFont = new Font(null, Font.BOLD, 10);

    /**
     * Everything for the Fractal Viewer is stored in the Fractal Viewer Panel
     */
    public FractalViewerPanel() {

        setLayout(new BorderLayout(5, 5));

        // create main panels
        FractalViewerGraphics graphics = new FractalViewerGraphics();
        Container instructionPane = createInstructionPanel();
        Container infoPane = createInfoPanel();
        
        // add main panels
        this.add(instructionPane, BorderLayout.NORTH);
        this.add(infoPane, BorderLayout.EAST);
        this.add(graphics, BorderLayout.CENTER);

    }

    /**
     * Create the layout of the instruction pane
     * @return A container with the instruction pane layout
     */
    private Container createInstructionPanel() {
        Container instructionPanel = new JPanel(new BorderLayout(0, 5));
        Container topSection = new JPanel(new GridLayout(1, 3));
        
        // create labels
        JLabel title = new JLabel("Fractal Viewer");
        JLabel author = new JLabel("Samson Danziger");
        JLabel instructions = new JLabel(
                "Instructions: \n" +
                "Don't be an idiot - you'll work it out");
        
        // set attributes
        title.setFont(_medFont);
        author.setFont(_medFont);
        instructions.setFont(_smallFont);
        
        // add elements to containers
        topSection.add(title);
        topSection.add(author);
        
        instructionPanel.add(topSection, BorderLayout.NORTH);
        instructionPanel.add(instructions, BorderLayout.CENTER);
        
        return instructionPanel;
    }

    /**
     * Create the info pane. This pane contains settings and info for the fractal.
     * @return A container preset with the layout and settings required.
     */
    private Container createInfoPanel() {
        Container settingsPanel = new JPanel(new FlowLayout());
                
        JLabel tempInfo = new JLabel(" Settings will go here when they exist.");
        tempInfo.setFont(_medFont);
        
        settingsPanel.add(tempInfo);
        
        return settingsPanel;
    }
}
