package calculation;

import java.security.InvalidParameterException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Complex {

    public static Complex ZERO = new Complex(0, 0);
    private double realPart, imaginaryPart;

    /**
     * Create a new complex number
     *
     * @param realPart      The real part of the number.
     * @param imaginaryPart The imaginary part of the number.
     */
    public Complex(double realPart, double imaginaryPart) {
        this.realPart = realPart;
        this.imaginaryPart = imaginaryPart;
    }

    /**
     * Create a new complex number from a string that looks like a complex number.
     *
     * @param stringValue To be matched it must look like " a.b + c.di "
     */
    public Complex(String stringValue) {
        Pattern p = Pattern.compile("\\s*((\\+?|-)\\s*\\d*\\.?\\d+)\\s*(\\+|-)\\s*(\\d*\\.?\\d+)\\s*(i|I)?\\s*");
        Matcher m = p.matcher(stringValue);
        if (m.matches()) {
            this.realPart = Double.valueOf(m.group(1));
            this.imaginaryPart = Double.valueOf(m.group(4));
        } else {
            throw new InvalidParameterException("calculation.Complex number not recognised. Must be in the form \"a.b + c.di\"");
        }

    }

    /**
     * Get the real part of the number.
     *
     * @return The real part of the number.
     */
    public double getReal() {
        return realPart;
    }

    /**
     * Get the imaginary part of the number.
     *
     * @return The imaginary part of the number.
     */
    public double getImaginary() {
        return imaginaryPart;
    }

    /**
     * Output the value of the complex number as a string.
     *
     * @return A string showing the complex number.
     */
    @Override
    public String toString() {
        if (imaginaryPart > 0) {
            return String.format("%f + %fi", realPart, imaginaryPart);
        } else {
            return String.format("%f - %fi", realPart, Math.abs(imaginaryPart));
        }
    }

    /**
     * Square a complex number
     *
     * @return A new complex number with the value of this number squared.
     */
    public Complex square() {
        double newRealPart = (realPart * realPart) - (imaginaryPart * imaginaryPart);
        double newImaginaryPart = (2 * realPart * imaginaryPart);
        return new Complex(newRealPart, newImaginaryPart);
    }

    /**
     * The square of the modulus of this number.
     *
     * @return The modulus squared of the number.
     */
    public double modulusSquared() {
        // actual modulus is square root of this
        return (realPart * realPart) + (imaginaryPart * imaginaryPart);
    }

    /**
     * Add a complex number to this one.
     *
     * @param d The complex number to add to this one.
     * @return A new complex number with the value of this number plus d.
     */
    public Complex add(Complex d) {
        double newRealPart = realPart + d.getReal();
        double newImaginaryPart = imaginaryPart + d.getImaginary();
        return new Complex(newRealPart, newImaginaryPart);
    }

    /**
     * Raise a complex number to a power.
     *
     * @param index The index.
     * @return The the complex number to the power [index]
     */
    public Complex pow(int index) {
        Complex base = this;
        for (int i = 0; i < index - 1; i++) {
            double newReal = (this.realPart * base.realPart) - (this.imaginaryPart * base.imaginaryPart);
            double newImaginary = (this.realPart * base.imaginaryPart) + (this.imaginaryPart * base.realPart);
            base = new Complex(newReal, newImaginary);
        }
        return base;
    }

    public Complex mult(double i) {
        return new Complex(realPart * i, imaginaryPart * i);
    }

    public Complex mult(Complex j) {
        double newReal = (this.realPart * j.getReal()) - (this.imaginaryPart * j.getImaginary());
        double newImag = (this.realPart * j.getImaginary()) + (this.imaginaryPart * j.getReal());
        return new Complex(newReal, newImag);
    }
}
