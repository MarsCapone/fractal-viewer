import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SetResetSetting extends JPanel {

    /**
     * Create a panel to reset the mandelbrot and julia set panels.
     *
     * @param bigPanel The Mandelbrot panel.
     * @param smallPanel      The Julia panel.
     */
    public SetResetSetting(final BigPanel bigPanel, final SmallPanel smallPanel) {
        setLayout(new FlowLayout(FlowLayout.LEFT));

        // create the reset buttons.
        JButton resetMandelbrot = new JButton("Reset Big");
        JButton resetJulia = new JButton("Reset Small");
        final JComboBox<String> setType = new JComboBox<String>(SetAlgorithms.getSetTypes());
        
        JButton switchPanels = new JButton("Switch Panels (Experimental)");

        add(resetMandelbrot);
        //add(Box.createHorizontalGlue()); // add a spacer
        //add(switchPanels);
        //add(Box.createHorizontalGlue());
        add(resetJulia);
        add(Box.createHorizontalGlue());
        add(setType);

        // reset for the julia panel
        resetJulia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                smallPanel.setConstant(Complex.ZERO);
                smallPanel.resetAxes();
            }
        });

        // reset for the mandelbrot panel
        resetMandelbrot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                bigPanel.resetAxes();
            }
        });

        // change the set type and change button labels
        setType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int fractalOption = setType.getSelectedIndex();
                SetAlgorithms.setFractalOption(fractalOption);

                smallPanel.setConstant(Complex.ZERO);
                smallPanel.resetAxes();
                bigPanel.resetAxes();

                smallPanel.paintImage();
                bigPanel.paintImage();

                smallPanel.repaint();
                bigPanel.repaint();
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
