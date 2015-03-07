import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class SettingsPane extends JPanel {

    private MandelbrotPanel mandelbrotPanel;
    private JuliaPanel juliaPanel;

    public SettingsPane(MandelbrotPanel mandelbrotPanel, JuliaPanel juliaPanel) {
        this.mandelbrotPanel = mandelbrotPanel;
        this.juliaPanel = juliaPanel;

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JPanel setResetPane = createSetResetPane();
        JPanel generationPane = createGenerationSettings();
        JPanel colourPane = createColouringPane();
        JPanel reaxingPane = createCompleteReaxingPanel();
        JPanel juliaFavourites = createFavouriteJuliaAdder();

        add(new JLabel("Settings"));
        add(setResetPane);
        add(generationPane);
        add(colourPane);
        add(reaxingPane);
        //add(juliaFavourites);
        
    }

    /**
     * Create panel for reaxing the mandelbrot and julia panels.
     * @return
     */
    private JPanel createCompleteReaxingPanel() {
        JPanel reaxing = new JPanel(new GridLayout(3, 1));
        reaxing.add(createJuliaConstantJumper());
        reaxing.add(new FractalJumperSetting(mandelbrotPanel, "Mandelbrot Jump: "));
        reaxing.add(new FractalJumperSetting(juliaPanel, "Julia Jump: "));
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
        constantField.setText(juliaPanel.getConstant().toString());

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
     * Create a panel to configure set colouring settings.
     * @return A panel with colour settings.
     */
    private JPanel createColouringPane() {
        JPanel colouringPane = new JPanel();
        colouringPane.setLayout(new BoxLayout(colouringPane, BoxLayout.PAGE_AXIS));
        
        JPanel types = new JPanel(new FlowLayout());
        
        String[] colouringTypes = {"Black & White", "Binary Decomposition", "Divergence", "Fire", "Inverse Fire"};
        JLabel colourLabel = new JLabel("Colouring Type: ");
        final JComboBox<String> comboBox = new JComboBox<String>(colouringTypes);
        comboBox.setSelectedIndex(3);

        types.add(colourLabel);
        types.add(comboBox);
        
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                GeneralFractalPanel.COLOUR_TYPE = comboBox.getSelectedIndex();
                juliaPanel.repaint();
                mandelbrotPanel.repaint();
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

    private JPanel createFavouriteJuliaAdder() {
        final JPanel juliaFavouriteAdder = new JPanel(new FlowLayout());

        final ImageIcon[] savedSets = new ImageIcon[10];
        final JComboBox<ImageIcon> favouriteSpace = new JComboBox<ImageIcon>();

        JButton addFavourite = new JButton("Add");

        addFavourite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                BufferedImage currentJuliaImage = juliaPanel.getImage();
                //BufferedImage currentJuliaIcon = currentJuliaImage.getSubimage(currentJuliaImage.getWidth()/2, currentJuliaImage.getHeight()/2, 50, 50);
                ImageIcon currentJuliaIcon = new ImageIcon(currentJuliaImage);
                Complex currentJuliaConstant = juliaPanel.getConstant();
                currentJuliaIcon.setDescription(currentJuliaConstant.toString());

                savedSets[0] = currentJuliaIcon;

                favouriteSpace.revalidate();
            }
        });

        favouriteSpace.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ImageIcon icon = (ImageIcon) favouriteSpace.getSelectedItem();
                String constantString = icon.getDescription();
                Complex constant = new Complex(constantString);

                juliaPanel.setConstant(constant);
                juliaPanel.repaint();
            }
        });

        juliaFavouriteAdder.add(new JLabel("Julia Set Favourites"));
        juliaFavouriteAdder.add(favouriteSpace);
        juliaFavouriteAdder.add(addFavourite);

        return juliaFavouriteAdder;
    }
}
