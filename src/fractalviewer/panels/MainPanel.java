package fractalviewer.panels;

import fractalviewer.listeners.JuliaListener;
import fractalviewer.listeners.MandelbrotListener;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {

    private JuliaPanel juliaPanel;
    private MandelbrotPanel mandelbrotPanel;
    
    private AdditionalPanel additionalPanel;

    /**
     * The panel containing everything required for the program.
     */
    public MainPanel() {
        setLayout(new BorderLayout());

        mandelbrotPanel = new MandelbrotPanel();
        juliaPanel = new JuliaPanel();

        additionalPanel = new AdditionalPanel(mandelbrotPanel, juliaPanel);
        this.add(mandelbrotPanel, BorderLayout.CENTER);
        this.add(additionalPanel, BorderLayout.EAST);

        MandelbrotListener mL = new MandelbrotListener(mandelbrotPanel, juliaPanel);
        JuliaListener jL = new JuliaListener(juliaPanel);

        mandelbrotPanel.addMouseListener(mL);
        mandelbrotPanel.addMouseMotionListener(mL);

        juliaPanel.addMouseListener(jL);
        juliaPanel.addMouseMotionListener(jL);
    }
    
    public void swapPanels() {
        GeneralFractalPanel currentMain = getFractalPanel();
        GeneralFractalPanel currentAdditional = additionalPanel.getFractalPanel();
        
        additionalPanel.changeFractalPanel(currentMain);
        
        remove(currentMain);
        add(currentAdditional, BorderLayout.CENTER);
        
    }
    
    private GeneralFractalPanel getFractalPanel() {
        Component[] allComponents = getComponents();
        for (Component c: allComponents) {
            if (c instanceof GeneralFractalPanel) {
                return (GeneralFractalPanel) c;
            }
        }
        return null;
    }
}

