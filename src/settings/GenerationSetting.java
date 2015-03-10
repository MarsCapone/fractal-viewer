package settings;

import extras.SetAlgorithms;
import panels.BigPanel;
import panels.FractalPanel;
import panels.SmallPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GenerationSetting extends JPanel {

    /**
     * Create a panel containing settings regarding the generation of fractals.
     *
     * @param bigPanel The Mandelbrot panel being used.
     * @param smallPanel      The Julia panel being used.
     */
    public GenerationSetting(final BigPanel bigPanel, final SmallPanel smallPanel) {
        setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel iterations = new JLabel("Iterations: ");
        JLabel modulus = new JLabel("Modulus: ");
        JLabel order = new JLabel("Order: ");

        // re the max iterations
        final JTextField iterationField = new JTextField(5);
        iterationField.setToolTipText("Maximum number of iterations to attempt before a pixel counts as never diverging.");
        iterationField.setText(String.valueOf(FractalPanel.getIterationLimit()));

        // re the modulus
        final JTextField modulusField = new JTextField(5);
        modulusField.setToolTipText("The value the modulus of the complex number must be under in order to count as not having diverged.");
        modulusField.setText(String.valueOf(Math.sqrt(FractalPanel.getModulusSquaredLimit())));

        // re the power
        final JTextField orderField = new JTextField(5);
        orderField.setToolTipText("The order of the Multibrot set.");
        orderField.setText(String.valueOf(SetAlgorithms.getOrder()));

        // update the max iterations
        iterationField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String iter = iterationField.getText();
                FractalPanel.setIterationLimit(Double.parseDouble(iter));
                System.out.printf("Set iteration field to %s. Calculating. Please wait...\n", iter);

                bigPanel.paintImage();
                smallPanel.paintImage();

                bigPanel.repaint();
                smallPanel.repaint();
            }
        });

        // update the max modulus
        modulusField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String mod = modulusField.getText();
                FractalPanel.setModulusSquaredLimit(Math.pow(Double.parseDouble(mod), 2));
                System.out.printf("Set modulus field to %s. Calculating. Please wait...\n", mod);

                bigPanel.paintImage();
                smallPanel.paintImage();

                bigPanel.repaint();
                smallPanel.repaint();
            }
        });

        // update the order field
        orderField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String order = orderField.getText();
                SetAlgorithms.setOrder(Integer.valueOf(order));
                System.out.printf("Setting order to %s. Calculating. Please wait...\n", order);

                bigPanel.paintImage();
                smallPanel.paintImage();

                bigPanel.repaint();
                smallPanel.repaint();
            }
        });

        // add everything to the panel.
        add(iterations);
        add(iterationField);
        add(Box.createHorizontalGlue());
        add(modulus);
        add(modulusField);
        add(Box.createHorizontalGlue());
        add(order);
        add(orderField);
    }
}
