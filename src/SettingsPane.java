import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class SettingsPane extends JPanel {
    
    public SettingsPane() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        add(new JLabel("Settings"));
        add(createSetResetPane());
        add(createGenerationSettings());
        add(createColouringPane());
        add(createJuliaConstantJumper());
        //add(createMandelbrotReAxing());
        
    }

    private JPanel createJuliaConstantJumper() {
        JPanel jumper = new JPanel(new FlowLayout());
        JLabel label = new JLabel("Julia Set Constant: ");
        final JTextField constantField = new JTextField(20);

        jumper.add(label);
        jumper.add(constantField);

        constantField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String text = constantField.getText();
                Complex constant = new Complex(text);

                System.out.println(constant);

                MainPanel.juliaPanel.resetAxes();
                MainPanel.juliaPanel.setConstant(constant);
                MainPanel.juliaPanel.repaint();
            }
        });

        return jumper;
    }
    
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
                MainPanel.juliaPanel.resetAxes();
            }
        });

        resetMandelbrot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MainPanel.mandelbrotPanel.resetAxes();
            }
        });
        
        return resetPane;
    }
    
    private JPanel createMandelbrotReAxing() {
        JPanel reaxing = new JPanel(new GridLayout(2, 1));
        JPanel inputBoxes = new JPanel(new GridLayout(2, 2));
        reaxing.add(new JLabel("Mandelbrot Axes:"), LEFT_ALIGNMENT);
        reaxing.add(inputBoxes);
        
        ActionListener mandelbrotAxisChange = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JTextField source = (JTextField) actionEvent.getSource();
                double sourceValue = Double.valueOf(source.getText());
                String sourceType = source.getToolTipText();

                HashMap<String, Double> axes = MainPanel.mandelbrotPanel.getAxes();
                axes.put(sourceType, sourceValue);
                
                MainPanel.mandelbrotPanel.resetAxes(axes);
            }
        };
        
        String[] types = {"minX", "maxX", "minY", "maxY"};
        HashMap<String, Double> mandelbrotAxes = MainPanel.mandelbrotPanel.getAxes();
        for (int i=0; i<4; i++) {
            JPanel p = new JPanel(new FlowLayout());
            String text = types[i];
            JTextField tf = new JTextField(5);
            tf.setToolTipText(text);
            tf.setText(String.valueOf(mandelbrotAxes.get(text)));
            tf.addActionListener(mandelbrotAxisChange);
            
            p.add(new JLabel(text));
            p.add(tf);  
            inputBoxes.add(p);
        }
        
        return reaxing;
    }
    
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
    
    private JPanel createGenerationSettings() {
        JPanel generationSettings = new JPanel(new FlowLayout());
        
        JLabel iterations = new JLabel("Iterations: ");
        JLabel modulus = new JLabel("Modulus: ");
        
        final JTextField iterationField = new JTextField(5);
        iterationField.setToolTipText("Maximum number of iterations to attempt before a pixel counts as never diverging.");
        iterationField.setText(String.valueOf(MainPanel.COUNT_LIMIT));
        
        final JTextField modulusField = new JTextField(5);
        modulusField.setToolTipText("The value the modulus of the complex number must be under in order to count as not having diverged.");
        modulusField.setText(String.valueOf(MainPanel.MODULUS_LIMIT));
        
        iterationField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MainPanel.COUNT_LIMIT = Integer.parseInt(iterationField.getText());
                MainPanel.mandelbrotPanel.repaint();
                MainPanel.juliaPanel.repaint();
            }
        });
        
        modulusField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MainPanel.MODULUS_LIMIT = Double.parseDouble(modulusField.getText());
                MainPanel.mandelbrotPanel.repaint();
                MainPanel.juliaPanel.repaint();
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
