package fractalviewer.settings;

import fractalviewer.extras.Complex;
import fractalviewer.panels.JuliaPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JuliaJumperSetting extends JPanel {

    private final JTextField constantField;

    /**
     * Create a panel to enable to jumping to specific constants for the Julia Set.
     *
     * @param juliaPanel The Julia Panel that will be jumped around.
     */
    public JuliaJumperSetting(final JuliaPanel juliaPanel) {
        setLayout(new GridLayout(4, 1));
        JLabel label = new JLabel("Julia Jump: ");
        constantField = new JTextField();
        constantField.setText(juliaPanel.getConstant().toString());

        add(label);
        add(constantField);

        // when changed, change the Julia panel image for the new value.
        constantField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String text = constantField.getText();
                Complex constant = new Complex(text);

                System.out.println(constant);

                // reset the julia panel stuff
                juliaPanel.resetAxes();
                juliaPanel.setConstant(constant);
                juliaPanel.paintImage();
                juliaPanel.repaint();
            }
        });
    }

    /**
     * Set the text of the constant field.
     *
     * @param text The text that the constant field should display.
     */
    public void setFieldText(String text) {
        constantField.setText(text);
    }

}
