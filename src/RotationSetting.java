import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class RotationSetting extends JPanel {

    public RotationSetting(final BigPanel bigPanel, final SmallPanel smallPanel) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JPanel bigRotator = new JPanel(new FlowLayout());
        JPanel smallRotator = new JPanel(new FlowLayout());

        SpinnerNumberModel model = new SpinnerNumberModel(0, 0, 360, 90);
        final JSpinner bigRotSpin = new JSpinner(model);
        final JSpinner smallRotSpin = new JSpinner(model);

        bigRotator.add(new JLabel("Rotation: "));
        bigRotator.add(bigRotSpin);
        bigRotSpin.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                int value = (Integer) bigRotSpin.getValue();
                bigPanel.setFractalRotation(value);
                bigPanel.paintImage();
                bigPanel.repaint();
            }
        });


        smallRotator.add(new JLabel("Small Panel: "));
        smallRotator.add(smallRotSpin);
        smallRotSpin.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                int value = (Integer) smallRotSpin.getValue();
                smallPanel.setFractalRotation(value);
                smallPanel.paintImage();
                smallPanel.repaint();
            }
        });

        add(bigRotator);
        add(smallRotator);
    }
}
