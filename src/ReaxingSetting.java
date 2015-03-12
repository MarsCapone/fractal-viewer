import javax.swing.*;
import java.awt.*;

public class ReaxingSetting extends JPanel {

    private final FractalJumperSetting mandelbrotJump;
    private final FractalJumperSetting juliaJump;
    private final RotationSetting rotator;

    /**
     * Create a panel to do reaxifying.
     *
     * @param bigPanel The mandelbrot panel.
     * @param smallPanel      The julia panel.
     */
    public ReaxingSetting(BigPanel bigPanel, SmallPanel smallPanel) {
        setLayout(new FlowLayout(FlowLayout.LEFT));

        mandelbrotJump = new FractalJumperSetting(bigPanel, "Big", true);
        juliaJump = new FractalJumperSetting(smallPanel, "Small");
        rotator = new RotationSetting(bigPanel, smallPanel);

        add(mandelbrotJump);
        add(juliaJump);
        // UNCOMMENT TO ADD ROTATOR (BROKEN)
        //add(rotator);
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

    public RotationSetting getRotator() {
        return rotator;
    }
}
