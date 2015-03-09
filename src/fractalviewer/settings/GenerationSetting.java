package fractalviewer.settings;

import fractalviewer.panels.GeneralFractalPanel;
import fractalviewer.panels.JuliaPanel;
import fractalviewer.panels.MandelbrotPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GenerationSetting extends JPanel {

    /**
     * Create a panel containing settings regarding the generation of fractals.
     * @param mandelbrotPanel The Mandelbrot panel being used.
     * @param juliaPanel The Julia panel being used.
     */
    public GenerationSetting(final MandelbrotPanel mandelbrotPanel, final JuliaPanel juliaPanel) {

        setLayout(new FlowLayout());

        JLabel iterations = new JLabel("Iterations: ");
        JLabel modulus = new JLabel("Modulus: ");
        JLabel order = new JLabel("Order: ");

        // re the max iterations
        final JTextField iterationField = new JTextField(5);
        iterationField.setToolTipText("Maximum number of iterations to attempt before a pixel counts as never diverging.");
        iterationField.setText(String.valueOf(GeneralFractalPanel.getIterationLimit()));

        // re the modulus
        final JTextField modulusField = new JTextField(5);
        modulusField.setToolTipText("The value the modulus of the complex number must be under in order to count as not having diverged.");
        modulusField.setText(String.valueOf(Math.sqrt(GeneralFractalPanel.getModulusSquaredLimit())));

        // re the power
        final JTextField orderField = new JTextField(5);
        orderField.setToolTipText("The order of the Multibrot set.");
        orderField.setText(String.valueOf(GeneralFractalPanel.getOrder()));

        // update the max iterations
        iterationField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String iter = iterationField.getText();
                GeneralFractalPanel.setIterationLimit(Double.parseDouble(iter));
                System.out.printf("Set iteration field to %s. Calculating. Please wait...\n", iter);

                mandelbrotPanel.paintImage();
                juliaPanel.paintImage();

                mandelbrotPanel.repaint();
                juliaPanel.repaint();
            }
        });

        // update the max modulus
        modulusField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String mod = modulusField.getText();
                GeneralFractalPanel.setModulusSquaredLimit(Math.pow(Double.parseDouble(mod), 2));
                System.out.printf("Set modulus field to %s. Calculating. Please wait...\n", mod);

                mandelbrotPanel.paintImage();
                juliaPanel.paintImage();

                mandelbrotPanel.repaint();
                juliaPanel.repaint();
            }
        });

        // update the order field
        orderField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String order = orderField.getText();
                GeneralFractalPanel.setOrder(Integer.valueOf(order));
                System.out.printf("Setting order to %s. Calculating. Please wait...\n", order);

                mandelbrotPanel.paintImage();
                juliaPanel.paintImage();

                mandelbrotPanel.repaint();
                juliaPanel.repaint();
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
