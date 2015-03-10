package settings;

import panels.GeneralFractalPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FractalJumperSetting extends JPanel {

    private final JTextField xCenter;
    private final JTextField yCenter;
    private final JTextField R;

    /**
     * Create a panel to jump to a specific location on a panel.
     *
     * @param jumpingPanel The panel to create the jumper for.
     * @param title        The title of the panel.
     */
    public FractalJumperSetting(final GeneralFractalPanel jumpingPanel, String title) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(new JLabel(title));

        JPanel entry = new JPanel(new GridLayout(4, 2));

        xCenter = new JTextField(5);
        yCenter = new JTextField(5);
        R = new JTextField(5);

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

    /**
     * Set the X Center field to a formatted double.
     *
     * @param xC The center X coordinate.
     */
    public void setxCenter(double xC) {
        xCenter.setText(getDecimalString(xC));
    }

    /**
     * Set the Y Center field to a formatted double.
     *
     * @param yC The center Y coordinate.
     */
    public void setyCenter(double yC) {
        yCenter.setText(getDecimalString(yC));
    }

    /**
     * Set the "radius" field to a formatted double.
     *
     * @param r The "radius" - half the x axis range.
     */
    public void setR(double r) {
        R.setText(getDecimalString(r));
    }

    /**
     * Get a formatted string from a double.
     *
     * @param s The double to format.
     * @return A string in the form x.yyyyy
     */
    private String getDecimalString(double s) {
        return String.format("%.5f", s);
    }
}
