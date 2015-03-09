import javax.swing.*;

public class ReaxingSetting extends JPanel {

    FractalJumperSetting mandelbrotJump, juliaJump;
    JuliaJumperSetting juliaConstantJump;

    /**
     * Create a panel to do reaxifying.
     * @param mandelbrotPanel The mandelbrot panel.
     * @param juliaPanel The julia panel.
     */
    public ReaxingSetting(MandelbrotPanel mandelbrotPanel, JuliaPanel juliaPanel) {
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

        mandelbrotJump = new FractalJumperSetting(mandelbrotPanel, "Mandelbrot Jump: ");
        juliaJump = new FractalJumperSetting(juliaPanel, "Julia Jump: ");
        juliaConstantJump = new JuliaJumperSetting(juliaPanel);

        add(mandelbrotJump);
        add(juliaJump);
        add(juliaConstantJump);
    }

    /**
     * Get the mandelbrot reaxing panel.
     * @return The mandelbrot reaxing panel.
     */
    public FractalJumperSetting getMandelbrotJump() {
        return mandelbrotJump;
    }

    /**
     * Get the julia reaxing panel.
     * @return The julia reaxing panel.
     */
    public FractalJumperSetting getJuliaJump() {
        return juliaJump;
    }

    /**
     * Get the julia constant jumping panel.
     * @return The julia constant jumpiing panel.
     */
    public JuliaJumperSetting getJuliaConstantJump() {
        return juliaConstantJump;
    }
}
