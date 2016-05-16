package calculation;

import panel.FractalPanel;

import java.awt.*;

public class SetColours {

    private static String[] colourTypes = {"Black and White", "Grayscale", "Divergence", "Binary Decomposition", "Fire", "Inverse Fire", "Continuous-ish"};
    private static int COLOUR_OPTION = 4;
    private static int ITERATION_LIMIT = 0;

    public static String[] getColourTypes() {
        return colourTypes;
    }

    private static Color getBlackWhite(int iterations) {
        if (iterations == ITERATION_LIMIT) {
            return Color.BLACK;
        } else {
            return Color.WHITE;
        }
    }

    private static Color getFire(int iterations) {
        if (iterations == ITERATION_LIMIT) {
            return Color.WHITE;
        } else {
            int colourValue = (int) Math.floor(iterations * 255 * 3 / ITERATION_LIMIT);
            if (colourValue <= 255) {
                return new Color(colourValue, 0, 0);
            } else if (colourValue <= 255 * 2) {
                return new Color(255, colourValue % 255, 0);
            } else {
                return new Color(255, 255, colourValue % 255);
            }
        }
    }

    private static Color getInverseFire(int iterations) {
        if (iterations == ITERATION_LIMIT) {
            return Color.BLACK;
        } else {
            int colourValue = (int) Math.floor(iterations * 255 * 3 / ITERATION_LIMIT);
            if (colourValue <= 255) {
                return new Color(255 - colourValue, 0, 0);
            } else if (colourValue <= 255 * 2) {
                return new Color(0, 255 - colourValue % 255, 255);
            } else {
                return new Color(0, 0, 255 - colourValue % 255);
            }
        }
    }

    private static Color getDivergence(int iterations, Complex endingComplex, FractalPanel panel) {
        if (iterations == ITERATION_LIMIT) {
            return Color.BLACK;
        } else {
            int grey = (int) ((Math.sqrt(endingComplex.modulusSquared()) * 255) / (2 * panel.getXRange())) % 255;
            return new Color(grey, grey, grey);
        }
    }

    private static Color getBinaryDecomposition(int iterations, Complex endingComplex) {
        if (iterations == ITERATION_LIMIT) {
            return Color.BLACK;
        } else {
            if (endingComplex.getImaginary() > 0) {
                return Color.BLACK;
            } else {
                return Color.WHITE;
            }
        }
    }

    private static Color getGrayscale(int iterations, FractalPanel panel) {
        iterations = iterations * 255 / ITERATION_LIMIT;
        return new Color(iterations, iterations, iterations);
    }

    public static Color getColour(Complex startingComplex, Complex endingComplex, int iterations, int colourType, FractalPanel panel) {
        ITERATION_LIMIT = FractalPanel.getIterationLimit();
        switch (colourType) {
            case 0:
                return getBlackWhite(iterations);
            case 1:
                return getGrayscale(iterations, panel);
            case 2:
                return getDivergence(iterations, endingComplex, panel);
            case 3:
                return getBinaryDecomposition(iterations, endingComplex);
            case 4:
                return getFire(iterations);
            case 5:
                return getInverseFire(iterations);
            case 6:
                return getContinuous(iterations, endingComplex);
            default:
                return getBlackWhite(iterations);
        }
    }

    public static Color getContinuous(int iterations, Complex endingCompex) {
        if (iterations >= ITERATION_LIMIT) {
            return Color.BLACK;
        } else {
                float hue = (float) ((iterations + 1) - (Math.log(Math.log(Math.sqrt(endingCompex.modulusSquared()))) / Math.log(2)));
                return new Color(Color.HSBtoRGB(hue/ITERATION_LIMIT, (float) 1, (float) 1));
            }
    }

    public static Color getColour(Complex startingComplex, Complex endingComplex, int iterations, FractalPanel panel) {
        return getColour(startingComplex, endingComplex, iterations, COLOUR_OPTION, panel);
    }

    public static int getColourOption() {
        return COLOUR_OPTION;
    }

    public static void setColourOption(int opt) {
        COLOUR_OPTION = opt;
    }

    public static String getColourType() {
        return colourTypes[COLOUR_OPTION];
    }


}
