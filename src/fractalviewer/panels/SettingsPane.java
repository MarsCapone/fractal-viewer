package fractalviewer.panels;

import fractalviewer.settings.*;

import javax.swing.*;


public class SettingsPane extends JPanel {

    private static JPanel generationPane, colourPane, reaxingPane, juliaFavourites, setResetPane;

    /**
     * Create the main settings pane
     *
     * @param mandelbrotPanel The mandelbrot panel being used
     * @param juliaPanel      The julia panel being used
     */
    public SettingsPane(MandelbrotPanel mandelbrotPanel, JuliaPanel juliaPanel) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JPanel settingSpace = new JPanel();
        settingSpace.setLayout(new BoxLayout(settingSpace, BoxLayout.PAGE_AXIS));

        JScrollPane scroller = new JScrollPane(settingSpace, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroller.setPreferredSize(this.getPreferredSize());


        setResetPane = new SetResetSetting(mandelbrotPanel, juliaPanel);
        generationPane = new GenerationSetting(mandelbrotPanel, juliaPanel);
        colourPane = new ColourSetting(mandelbrotPanel, juliaPanel);
        reaxingPane = new ReaxingSetting(mandelbrotPanel, juliaPanel);
        juliaFavourites = new JuliaFavouriteSetting(juliaPanel);

        settingSpace.add(setResetPane);
        settingSpace.add(generationPane);
        settingSpace.add(colourPane);
        settingSpace.add(reaxingPane);
        settingSpace.add(juliaFavourites);

        add(scroller);
    }

    /**
     * Get the reset panel
     *
     * @return The reset panel
     */
    public static JPanel getSetResetPane() {
        return setResetPane;
    }

    /**
     * Get the generation panel
     *
     * @return The generation panel
     */
    public static JPanel getGenerationPane() {
        return generationPane;
    }

    /**
     * Get the colour panel
     *
     * @return The colour panel
     */
    public static JPanel getColourPane() {
        return colourPane;
    }

    /**
     * Get the reaxing panel
     *
     * @return The reaxing panel
     */
    public static JPanel getReaxingPane() {
        return reaxingPane;
    }

    /**
     * Get the julia favourites panel
     *
     * @return The julia favourites panel
     */
    public static JPanel getJuliaFavourites() {
        return juliaFavourites;
    }
}
