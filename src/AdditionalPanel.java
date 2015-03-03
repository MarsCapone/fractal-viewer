
import build.tools.javazic.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdditionalPanel extends JPanel {

    protected static int COLOURING_TYPE = 0;

    public AdditionalPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        MainPanel.juliaPanel = new JuliaPanel();
        MainPanel.juliaPanel.setPreferredSize(new Dimension(300, 300));
        
        this.add(MainPanel.juliaPanel, BorderLayout.NORTH);
        this.add(createSettingsPane(), BorderLayout.CENTER);
    }

    private Container createSettingsPane() {
        Container settings = new JPanel();
        settings.setLayout(new BoxLayout(settings, BoxLayout.PAGE_AXIS));
        
        JButton resetMandelbrot = new JButton("Reset Mandelbrot");
        JButton resetJulia = new JButton("Reset Julia");

        settings.add(new JLabel("Settings"));
        settings.add(resetJulia);
        settings.add(resetMandelbrot);
        
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
        
        return settings;
    }
}
