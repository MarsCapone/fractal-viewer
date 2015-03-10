package settings;

import panels.JuliaPanel;
import panels.MandelbrotPanel;

import javax.swing.*;
import java.awt.*;

public class ReaxingSetting extends JPanel {

    private final FractalJumperSetting mandelbrotJump;
    private final FractalJumperSetting juliaJump;
    private final JuliaJumperSetting juliaConstantJump;

    /**
     * Create a panel to do reaxifying.
     *
     * @param mandelbrotPanel The mandelbrot panel.
     * @param juliaPanel      The julia panel.
     */
    public ReaxingSetting(MandelbrotPanel mandelbrotPanel, JuliaPanel juliaPanel) {
        setLayout(new FlowLayout(FlowLayout.LEFT));

        mandelbrotJump = new FractalJumperSetting(mandelbrotPanel, "Mandelbrot", true);
        juliaJump = new FractalJumperSetting(juliaPanel, "Julia");
        juliaConstantJump = new JuliaJumperSetting(juliaPanel);

        add(mandelbrotJump);
        add(juliaJump);
        add(juliaConstantJump);
    }

    /**
     * Get the mandelbrot reaxing panel.
     *
     * @return The mandelbrot reaxing panel.
     */
    public FractalJumperSetting getMandelbrotJump() {
        return mandelbrotJump;
    }

    /**
     * Get the julia reaxing panel.
     *
     * @return The julia reaxing panel.
     */
    public FractalJumperSetting getJuliaJump() {
        return juliaJump;
    }

    /**
     * Get the julia constant jumping panel.
     *
     * @return The julia constant jumping panel.
     */
    public JuliaJumperSetting getJuliaConstantJump() {
        return juliaConstantJump;
    }
}