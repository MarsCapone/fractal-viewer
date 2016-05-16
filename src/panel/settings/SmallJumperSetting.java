package panel.settings;

import panel.SmallPanel;
import calculation.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SmallJumperSetting extends JPanel {

    private final JTextField constantField;

    /**
     * Create a panel to enable to jumping to specific constants for the Julia Set.
     *
     * @param smallPanel The Julia Panel that will be jumped around.
     */
    public SmallJumperSetting(final SmallPanel smallPanel) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JLabel label = new JLabel("Constant Jump: ");
        constantField = new JTextField();
        constantField.setText(smallPanel.getConstant().toString());

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
                smallPanel.resetAxes();
                smallPanel.setConstant(constant);
                smallPanel.paintImage();
                smallPanel.repaint();
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
