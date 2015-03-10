package panels;

import listeners.JuliaListener;
import listeners.MandelbrotListener;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {

    private final AdditionalPanel additionalPanel;

    /**
     * The panel containing everything required for the program.
     */
    public MainPanel() {
        setLayout(new BorderLayout());

        MandelbrotPanel mandelbrotPanel = new MandelbrotPanel();
        JuliaPanel juliaPanel = new JuliaPanel();

        additionalPanel = new AdditionalPanel(mandelbrotPanel, juliaPanel);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, mandelbrotPanel, additionalPanel);
        splitPane.setOneTouchExpandable(true);
        splitPane.setContinuousLayout(true);
        splitPane.setResizeWeight(0.5);
        splitPane.setDividerSize(5);

        Dimension minimumSize = new Dimension(300, 400);
        mandelbrotPanel.setMinimumSize(minimumSize);
        additionalPanel.setMinimumSize(minimumSize);

        this.add(splitPane);

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

        System.out.println(currentMain);
        System.out.println(currentAdditional);
        additionalPanel.changeFractalPanel(currentMain);

        remove(currentMain);
        this.add(currentAdditional, BorderLayout.CENTER);
        revalidate();

    }

    private GeneralFractalPanel getFractalPanel() {
        Component[] allComponents = getComponents();
        for (Component c : allComponents) {
            if (c instanceof GeneralFractalPanel) {
                return (GeneralFractalPanel) c;
            }
        }
        return null;
    }
}

