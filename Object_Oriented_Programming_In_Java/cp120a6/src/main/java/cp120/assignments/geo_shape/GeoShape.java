package cp120.assignments.geo_shape;

import java.awt.*;

/**
 * The class extended by all those classes that represent shapes to be drawn on a GeoPlane.
 * @author jcrowley
 */
public abstract class GeoShape {
    private GeoPoint origin;
    private Color color;

    /**
     * Gets the origin of the shape.
     * @return the origin
     */
    public GeoPoint getOrigin() {
        return origin;
    }

    /**
     * Sets the origin of the shape.
     * @param origin the new origin
     * @throws IllegalArgumentException if the new origin is null
     */
    public void setOrigin(GeoPoint origin) throws IllegalArgumentException {
        this.origin = origin;
    }

    /**
     * Gets the color of the shape.
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets the color.
     * @param color the new color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Draw a shape on a GeoPlane.
     * This is required to be implemented by every subclass of GeoShape.
     * @param gtx the graphics context to use for drawing
     */
    public abstract void draw(Graphics2D gtx);
}
