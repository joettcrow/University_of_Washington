package cp120.assignments.geo_shape;

/**
 * Encapsulates the x- and y-coordinates of a point on a plane.
 * @author jcrowley
 */
public class GeoPoint {
    private double xco = 0;
    private double yco = 0;

    /**
     * Gets the x-coordinate of this point.
     * @return the x-coordinate of this point.
     */
    public double getXco() {
        return xco;
    }

    /**
     * Sets the x-coordinate of this point.
     * @param xco the new x-coordinate of this point
     */
    public void setXco(double xco) {
        this.xco = xco;
    }

    /**
     * Gets the y-coordinate of this point.
     * @return the y-coordinate of this point.
     */
    public double getYco() {
        return yco;
    }

    /**
     * Sets the y-coordinate of this point.
     * @param yco the new y-coordinate of this point
     */
    public void setYco(double yco) {
        this.yco = yco;
    }
}
