public class Complex {
    
    double realPart, imaginaryPart;
    
    public Complex(double realPart, double imaginaryPart) {
        this.realPart = realPart;
        this.imaginaryPart = imaginaryPart;
    }
    
    public double getReal() {
        return realPart;
    }
    
    public double getImaginary() {
        return imaginaryPart;
    }
    
    @Override
    public String toString() {
        if (imaginaryPart > 0) {
            return String.format("%f + %fi", realPart, imaginaryPart);
        } else {
            return String.format("%f - %fi", realPart, Math.abs(imaginaryPart));
        }
    }
    
    public Complex square() {
        double newRealPart = (realPart*realPart) - (imaginaryPart*imaginaryPart);
        double newImaginaryPart = (2*realPart*imaginaryPart);
        return new Complex(newRealPart, newImaginaryPart);
    }
    
    public double modulusSquared() {
        return (realPart*realPart) + (imaginaryPart*imaginaryPart);
    }
    
    public Complex add(Complex d) {
        double newRealPart = realPart + d.getReal();
        double newImaginaryPart = imaginaryPart + d.getImaginary();
        return new Complex(newRealPart, newImaginaryPart);
    }
    
    
}
