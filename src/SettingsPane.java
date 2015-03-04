import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsPane extends JPanel {

    private MandelbrotPanel mandelbrotPanel;
    private JuliaPanel juliaPanel;

    public SettingsPane(MandelbrotPanel mandelbrotPanel, JuliaPanel juliaPanel) {
        this.mandelbrotPanel = mandelbrotPanel;
        this.juliaPanel = juliaPanel;

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        add(new JLabel("Settings"));
        add(createSetResetPane());
        add(createGenerationSettings());
        add(createColouringPane());
        add(createCompleteReaxingPanel());
        
    }

    /**
     * Create panel for reaxing the mandelbrot and julia panels.
     * @return
     */
    private JPanel createCompleteReaxingPanel() {
        JPanel reaxing = new JPanel(new GridLayout(3, 1));
        reaxing.add(createJuliaConstantJumper());
        reaxing.add(createFractalJumper(mandelbrotPanel, "Mandelbrot Jump: "));
        reaxing.add(createFractalJumper(juliaPanel, "Julia Jump: "));
        return reaxing;
    }

    /**
     * Create a panel that allows the jumping of a Julia Set to the Julia Set of a specific constant.
     * @return A panel to change settings regarding the jumping to a specific Julia set.
     */
    private JPanel createJuliaConstantJumper() {
        JPanel jumper = new JPanel(new FlowLayout());
        JLabel label = new JLabel("Julia Jump: ");
        final JTextField constantField = new JTextField(20);

        jumper.add(label);
        jumper.add(constantField);

        constantField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String text = constantField.getText();
                Complex constant = new Complex(text);

                System.out.println(constant);

                juliaPanel.resetAxes();
                juliaPanel.setConstant(constant);
                juliaPanel.repaint();
            }
        });

        return jumper;
    }

    /**
     * Create a panel to reset the the mandelbrot and/or julia panels to their initial view.
     * @return A panel containing reset buttons.
     */
    private JPanel createSetResetPane() {
        JPanel resetPane = new JPanel();
        resetPane.setLayout(new BoxLayout(resetPane, BoxLayout.LINE_AXIS));
        
        JButton resetMandelbrot = new JButton("Reset Mandelbrot");
        JButton resetJulia = new JButton("Reset Julia");
        
        resetPane.add(resetMandelbrot);
        resetPane.add(Box.createHorizontalGlue());
        resetPane.add(resetJulia);
        
        resetJulia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                juliaPanel.resetAxes();
            }
        });

        resetMandelbrot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                mandelbrotPanel.resetAxes();
            }
        });
        
        return resetPane;
    }

    /**
     * Create a panel to jump to a specific location on a panel.
     * @param jumpingPanel The panel to create the jumper for.
     * @param title The title of the panel.
     * @return A panel with jump fields.
     */
    private JPanel createFractalJumper(final GeneralFractalPanel jumpingPanel, String title) {
        JPanel reaxing = new JPanel(new GridLayout(2, 1));
        JPanel inputBoxes = new JPanel(new FlowLayout());
        reaxing.add(new JLabel(title));
        reaxing.add(inputBoxes);

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

        return reaxing;
    }

    /**
     * Create a panel to configure set colouring settings.
     * @return A panel with colour settings.
     */
    private JPanel createColouringPane() {
        JPanel colouringPane = new JPanel();
        colouringPane.setLayout(new BoxLayout(colouringPane, BoxLayout.PAGE_AXIS));
        
        JPanel types = new JPanel(new FlowLayout());
        
        String[] colouringTypes = {"Black & White", "type4"};
        JLabel colourLabel = new JLabel("Colouring Type: ");
        final JComboBox<String> comboBox = new JComboBox<String>(colouringTypes);
        types.add(colourLabel);
        types.add(comboBox);
        
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                GeneralFractalPanel.COLOUR_TYPE = comboBox.getSelectedIndex();
                repaint();
            }
        });
        
        colouringPane.add(types);

        return colouringPane;
    }

    /**
     * Create a panel with settings to change how the sets are generated.
     * @return A panel with generation settings.
     */
    private JPanel createGenerationSettings() {
        JPanel generationSettings = new JPanel(new FlowLayout());
        
        JLabel iterations = new JLabel("Iterations: ");
        JLabel modulus = new JLabel("Modulus: ");
        
        final JTextField iterationField = new JTextField(5);
        iterationField.setToolTipText("Maximum number of iterations to attempt before a pixel counts as never diverging.");
        iterationField.setText(String.valueOf(GeneralFractalPanel.getIterationLimit()));
        
        final JTextField modulusField = new JTextField(5);
        modulusField.setToolTipText("The value the modulus of the complex number must be under in order to count as not having diverged.");
        modulusField.setText(String.valueOf(GeneralFractalPanel.getModulusLimit()));
        
        iterationField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                GeneralFractalPanel.setIterationLimit(Double.parseDouble(iterationField.getText()));
                mandelbrotPanel.repaint();
                juliaPanel.repaint();
            }
        });
        
        modulusField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                GeneralFractalPanel.setModulusLimit(Double.parseDouble(modulusField.getText()));
                mandelbrotPanel.repaint();
                juliaPanel.repaint();
            }
        });
        
        generationSettings.add(iterations);
        generationSettings.add(iterationField);
        generationSettings.add(Box.createHorizontalGlue());
        generationSettings.add(modulus);
        generationSettings.add(modulusField);

        return generationSettings;
    }
}
