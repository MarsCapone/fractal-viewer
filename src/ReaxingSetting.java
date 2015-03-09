import javax.swing.*;

public class ReaxingSetting extends JPanel {

    public ReaxingSetting(MandelbrotPanel mandelbrotPanel, JuliaPanel juliaPanel) {
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

        add(new FractalJumperSetting(mandelbrotPanel, "Mandelbrot Jump: "));
        add(new FractalJumperSetting(juliaPanel, "Julia Jump: "));
        add(new JuliaJumperSetting(juliaPanel));
    }
}
