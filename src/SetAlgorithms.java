/*
 * Using SetAlgorithms
 * ===================
 * 
 * This class determines which fractals are generated and how.
 * 
 * The names of the different types of fractals should be in setTypes.
 * 
 * There are private static methods returning Complex and accepting a 
 * previous z and c value, which determine the algorithm for fractal 
 * generation. 
 */

public class SetAlgorithms {

    private static int ORDER = 2; // the power used in set generation

    private static int FRACTAL_OPTION = 0;

    private static String[] setTypes = {"Mandelbrot", "Burning Ship", "Tricorn", "Phoenix"}; // add names of new fractals here

    private static Complex PREVIOUS_Z = Complex.ZERO;

    /**
     * Get the power of the fractal generation. f(z) = z^d + c
     *
     * @return The order of the fractal generation.
     */
    public static int getOrder() {
        return ORDER;
    }

    /**
     * Set the order of the fractal generation. f(z) = z^d + c
     *
     * @param d The power to raise Z to.
     */
    public static void setOrder(int d) {
        ORDER = d;
    }

    private static Complex nextMandelbrot(Complex z, Complex c) {
        return z.pow(ORDER).add(c);
    }

    private static Complex nextBurningShip(Complex z, Complex c) {
        return new Complex(Math.abs(z.getReal()), Math.abs(z.getImaginary())).pow(ORDER).add(c);
    }

    private static Complex nextTricorn(Complex z, Complex c) {
        return new Complex(z.getReal(), -1 * z.getImaginary()).pow(ORDER).add(c);
    }

    private static Complex nextPhoenix(Complex z, Complex c) {
        c = new Complex("0.56667 - 0.5i");
        PREVIOUS_Z = z;
        Complex p1 = z.pow(ORDER);
        Complex p2 = new Complex(p1.getReal() + c.getReal(), p1.getImaginary());
        Complex p3 = new Complex(PREVIOUS_Z.getReal() * c.getImaginary(), PREVIOUS_Z.getImaginary() * c.getImaginary());

        double real = z.pow(ORDER).getReal() + c.getReal() + (c.getImaginary()*PREVIOUS_Z.getReal());
        double imag = z.pow(ORDER).getImaginary() + (c.getImaginary()*PREVIOUS_Z.getImaginary());

        return new Complex(real, imag);
    }

    /**
     * Get the next value from the equation. The option determines the type of fractal drawn.
     * The case number is the index of the name in setTypes.
     *
     * @param z      The complex z value.
     * @param c      The complex c value.
     * @param option The option for which set to generate.
     * @return The next z value.
     */
    public static Complex getNext(Complex z, Complex c, int option) {
        switch (option) {
            case 1: // burning ship
                return nextBurningShip(z, c);
            case 2: // tricorn
                return nextTricorn(z, c);
            case 3:
                return nextPhoenix(z, c);
            default: // mandelbrot
                return nextMandelbrot(z, c);
        }
    }

    public static Complex getNext(Complex z, Complex c) {
        return getNext(z, c, FRACTAL_OPTION);
    }

    public static String[] getSetTypes() {
        return setTypes;
    }

    public static void setFractalOption(int opt) {
        FRACTAL_OPTION = opt;
    }

    public static String getType() {
        return setTypes[FRACTAL_OPTION];
    }
}
