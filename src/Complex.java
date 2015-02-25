public class Complex {
    
    private double realPart, imaginaryPart;

    /**
     * Create a new complex number
     * @param realPart The real part of the number.
     * @param imaginaryPart The imaginary part of the number.
     */
    public Complex(double realPart, double imaginaryPart) {
        this.realPart = realPart;
        this.imaginaryPart = imaginaryPart;
    }

    /**
     * Get the real part of the number.
     * @return The real part of the number.
     */
    public double getReal() {
        return realPart;
    }

    /**
     * Get the imaginary part of the number.
     * @return The imaginary part of the number.
     */
    public double getImaginary() {
        return imaginaryPart;
    }

    /**
     * Output the value of the complex number as a string.
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
     * @return A new complex number with the value of this number squared.
     */
    public Complex square() {
        double newRealPart = (realPart*realPart) - (imaginaryPart*imaginaryPart);
        double newImaginaryPart = (2*realPart*imaginaryPart);
        return new Complex(newRealPart, newImaginaryPart);
    }

    /**
     * The square of the modulus of this number.
     * @return The modulus squared of the number.
     */
    public double modulusSquared() {
        // actual modulus is square root of this
        return (realPart*realPart) + (imaginaryPart*imaginaryPart); 
    }

    /**
     * Add a complex number to this one.
     * @param d The complex number to add to this one.
     * @return A new complex number with the value of this number plus d.
     */
    public Complex add(Complex d) {
        double newRealPart = realPart + d.getReal();
        double newImaginaryPart = imaginaryPart + d.getImaginary();
        return new Complex(newRealPart, newImaginaryPart);
    }
    
    public Complex pow(int index) {
        Complex base = this;
        for (int i=0; i<index; i++) {
            double newReal = (this.realPart * base.realPart) - (this.imaginaryPart * base.imaginaryPart);
            double newImaginary = (this.realPart * base.imaginaryPart) + (this.imaginaryPart * base.realPart);
            base = new Complex(newReal, newImaginary);
        }
        return base;
    }
}
