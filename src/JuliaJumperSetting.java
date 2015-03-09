import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JuliaJumperSetting extends JPanel {

    static JTextField constantField;

    public JuliaJumperSetting(final JuliaPanel juliaPanel) {
        setLayout(new GridLayout(4, 1));
        JLabel label = new JLabel("Julia Jump: ");
        constantField = new JTextField();
        constantField.setText(juliaPanel.getConstant().toString());

        add(label);
        add(constantField);

        constantField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String text = constantField.getText();
                Complex constant = new Complex(text);

                System.out.println(constant);

                juliaPanel.resetAxes();
                juliaPanel.setConstant(constant);
                juliaPanel.paintImage();
                juliaPanel.repaint();
            }
        });
    }

    public String getFieldText() {
        return constantField.getText();
    }

    public static void setFieldText(String text) {
        constantField.setText(text);
    }

}
