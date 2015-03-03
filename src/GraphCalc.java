public class GraphCalc {

    /**
     * Get the complex number that describes and point in a panel.
     * @param x The X value of the point
     * @param y The Y value of the point
     * @param maxX The width of the panel
     * @param maxY The height of the panel
     * @param abstractRangeX The range of the X axis of real values
     * @param abstractMinX The minimum X value on the abstract real axis
     * @param abstractRangeY The range of the Y axis of imaginary values
     * @param abstractMinY The minimum Y value on the abstract imaginary axis
     * @return A complex number. The point on the complex plane of the point in the panel.
     */
    public static Complex getComplexPoint(int x, int y, int maxX, int maxY, double abstractRangeX, double abstractMinX, double abstractRangeY, double abstractMinY) {
        double realPart = abstractMinX + (x * abstractRangeX)/maxX;
        double imaginaryPart = (abstractMinY + (y * abstractRangeY)/maxY);
        return new Complex(realPart, -imaginaryPart);
    }
}
