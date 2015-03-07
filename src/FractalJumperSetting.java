import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FractalJumperSetting extends JPanel {

    /**
     * Create a panel to jump to a specific location on a panel.
     * @param jumpingPanel The panel to create the jumper for.
     * @param title The title of the panel.
     */
    public FractalJumperSetting(final GeneralFractalPanel jumpingPanel, String title) {
        setLayout(new GridLayout(2, 1));
        JPanel inputBoxes = new JPanel(new FlowLayout());
        add(new JLabel(title));
        add(inputBoxes);

        final JTextField xCenter = new JTextField(5);
        final JTextField yCenter = new JTextField(5);
        final JTextField R = new JTextField(5);

        ActionListener FractalAxisChange = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                double xC = Double.parseDouble(xCenter.getText());
                double yC = Double.parseDouble(yCenter.getText());
                double r = Double.parseDouble(R.getText());

                jumpingPanel.resetAxes(xC, yC, r);
            }
        };

        inputBoxes.add(new JLabel("X: "));
        inputBoxes.add(xCenter);

        inputBoxes.add(new JLabel("Y: "));
        inputBoxes.add(yCenter);

        inputBoxes.add(new JLabel("R: "));
        inputBoxes.add(R);

        xCenter.addActionListener(FractalAxisChange);
        yCenter.addActionListener(FractalAxisChange);
        R.addActionListener(FractalAxisChange);
    }

}
