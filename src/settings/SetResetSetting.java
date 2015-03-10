package settings;

import extras.Complex;
import panels.JuliaPanel;
import panels.MainPanel;
import panels.MandelbrotPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SetResetSetting extends JPanel {

    /**
     * Create a panel to reset the mandelbrot and julia set panels.
     *
     * @param mandelbrotPanel The Mandelbrot panel.
     * @param juliaPanel      The Julia panel.
     */
    public SetResetSetting(final MandelbrotPanel mandelbrotPanel, final JuliaPanel juliaPanel) {
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

        // create the reset buttons.
        JButton resetMandelbrot = new JButton("Reset Mandelbrot");
        JButton resetJulia = new JButton("Reset Julia");
        JButton switchPanels = new JButton("Switch Panels (Experimental)");

        add(resetMandelbrot);
        //add(Box.createHorizontalGlue()); // add a spacer
        //add(switchPanels);
        //add(Box.createHorizontalGlue());
        add(resetJulia);

        // reset for the julia panel
        resetJulia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                juliaPanel.setConstant(new Complex(0, 0));
                juliaPanel.resetAxes();
            }
        });

        // reset for the mandelbrot panel
        resetMandelbrot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                mandelbrotPanel.resetAxes();
            }
        });

        switchPanels.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Component parent = getParent();
                while (!(parent instanceof MainPanel) && parent != null) {
                    parent = parent.getParent();
                }
                if (parent != null) {
                    MainPanel main = (MainPanel) parent;
                    main.swapPanels();
                    main.revalidate();
                } else {
                    System.out.println("Failed to find main panel.");
                }
            }
        });
    }
}
