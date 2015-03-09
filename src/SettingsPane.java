import javax.swing.*;


public class SettingsPane extends JPanel {

    private static JPanel generationPane, colourPane, reaxingPane, juliaFavourites, setResetPane;

    /**
     * Create the main settings pane
     * @param mandelbrotPanel The mandelbrot panel being used
     * @param juliaPanel The julia panel being used
     */
    public SettingsPane(MandelbrotPanel mandelbrotPanel, JuliaPanel juliaPanel) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        setResetPane = new SetResetSetting(mandelbrotPanel, juliaPanel);
        generationPane = new GenerationSetting(mandelbrotPanel, juliaPanel);
        colourPane = new ColourSetting(mandelbrotPanel, juliaPanel);
        reaxingPane = new ReaxingSetting(mandelbrotPanel, juliaPanel);
        juliaFavourites = new JuliaFavouriteSetting(juliaPanel);

        add(new JLabel("Settings"));
        add(setResetPane);
        add(generationPane);
        add(colourPane);
        add(reaxingPane);
        add(juliaFavourites);
        
    }

    /**
     * Get the reset panel
     * @return The reset panel
     */
    public static JPanel getSetResetPane() {
        return setResetPane;
    }

    /**
     * Get the generation panel
     * @return The generation panel
     */
    public static JPanel getGenerationPane() {
        return generationPane;
    }

    /**
     * Get the colour panel
     * @return The colour panel
     */
    public static JPanel getColourPane() {
        return colourPane;
    }

    /**
     * Get the reaxing panel
     * @return The reaxing panel
     */
    public static JPanel getReaxingPane() {
        return reaxingPane;
    }

    /**
     * Get the julia favouriting panel
     * @return The julia favouriting panel
     */
    public static JPanel getJuliaFavourites() {
        return juliaFavourites;
    }
}
