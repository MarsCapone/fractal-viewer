import javax.swing.*;


public class SettingsPane extends JPanel {

    private static JPanel generationPane, colourPane, reaxingPane, juliaFavourites, setResetPane, constantJump;

    /**
     * Create the main settings pane
     *
     * @param bigPanel The mandelbrot panel being used
     * @param smallPanel      The julia panel being used
     */
    public SettingsPane(BigPanel bigPanel, SmallPanel smallPanel) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        JPanel settingSpace = new JPanel();
        settingSpace.setLayout(new BoxLayout(settingSpace, BoxLayout.PAGE_AXIS));

        JScrollPane scroller = new JScrollPane(settingSpace, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroller.setPreferredSize(this.getPreferredSize());


        setResetPane = new SetResetSetting(bigPanel, smallPanel);
        generationPane = new GenerationSetting(bigPanel, smallPanel);
        colourPane = new ColourSetting(bigPanel, smallPanel);
        reaxingPane = new ReaxingSetting(bigPanel, smallPanel);
        juliaFavourites = new SmallFavouriteSetting(smallPanel);
        constantJump = new SmallJumperSetting(smallPanel);

        settingSpace.add(setResetPane);
        settingSpace.add(generationPane);
        settingSpace.add(colourPane);
        settingSpace.add(constantJump);
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
    public static JPanel getFavourites() {
        return juliaFavourites;
    }

    /**
     * Get the julia constant jumping panel.
     *
     * @return The julia constant jumping panel.
     */
    public static JPanel getConstantJump() {
        return constantJump;
    }
}
