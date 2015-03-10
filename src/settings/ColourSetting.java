package settings;

import panels.BigPanel;
import panels.FractalPanel;
import panels.SmallPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColourSetting extends JPanel {

    /**
     * Create a colour setting panel.
     * Currently only changes colouring types.
     *
     * @param bigPanel The mandelbrot panel being used.
     * @param smallPanel      The julia panel being used.
     */
    public ColourSetting(final BigPanel bigPanel, final SmallPanel smallPanel) {
        setLayout(new FlowLayout(FlowLayout.LEFT));

        // Give names of the different colouring types. These will be displayed in a combobox and referenced by their index.
        String[] colouringTypes = {"Black & White", "Binary Decomposition", "Divergence", "Fire", "Inverse Fire", "Continuous"};
        JLabel colourLabel = new JLabel("Colouring Type: ");
        final JComboBox<String> comboBox = new JComboBox<String>(colouringTypes);
        comboBox.setSelectedIndex(3); // the default colour scheme is the "Fire" scheme.

        add(colourLabel);
        add(comboBox);

        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                FractalPanel.setColourType(comboBox.getSelectedIndex());
                smallPanel.paintImage();
                bigPanel.paintImage();

                smallPanel.repaint();
                bigPanel.repaint();
            }
        });


    }
}

