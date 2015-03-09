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
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(new JLabel(title));

        JPanel entry = new JPanel(new GridLayout(4, 2));

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
                jumpingPanel.paintImage();
            }
        };

        entry.add(new JLabel("X: "));
        entry.add(xCenter);

        entry.add(new JLabel("Y: "));
        entry.add(yCenter);

        entry.add(new JLabel("R: "));
        entry.add(R);

        add(entry);

        xCenter.addActionListener(FractalAxisChange);
        yCenter.addActionListener(FractalAxisChange);
        R.addActionListener(FractalAxisChange);
    }

}
