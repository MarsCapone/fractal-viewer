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
    public FractalJumperSetting(final FractalPanel jumpingPanel, String title, boolean doLabels) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(new JLabel(title));

        xCenter = new JTextField(8);
        yCenter = new JTextField(8);
        R = new JTextField(8);

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

        if (doLabels) {
            JPanel entry = new JPanel(new GridLayout(3, 2));
            entry.add(new JLabel("X "), RIGHT_ALIGNMENT);
            entry.add(xCenter);
            entry.add(new JLabel("Y "), RIGHT_ALIGNMENT);
            entry.add(yCenter);
            entry.add(new JLabel("R "), RIGHT_ALIGNMENT);
            entry.add(R);

            add(entry);
        } else {
            add(xCenter);
            add(yCenter);
            add(R);
        }

        xCenter.addActionListener(FractalAxisChange);
        yCenter.addActionListener(FractalAxisChange);
        R.addActionListener(FractalAxisChange);
    }

    public FractalJumperSetting(final FractalPanel jumpingPanel, String title) {
        this(jumpingPanel, title, false);
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
