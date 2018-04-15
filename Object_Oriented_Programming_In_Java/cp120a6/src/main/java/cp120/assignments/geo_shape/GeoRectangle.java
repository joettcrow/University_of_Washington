package cp120.assignments.geo_shape;

import java.awt.*;

/**
 * Encapsulates a rectangle drawn on a plane. Properties include
 * <ul>
 * <li>The origin (x- and y-coordinates of the upper left corner) of the rectangle</li>
 * <li>The width of the rectangle</li>
 * <li>The height of the rectangle</li>
 * <li>The color of the rectangle</li>
 * </ul>
 * @author jcrowley
 */
public class GeoRectangle extends GeoShape{
    private double width = 0;
    private double height = 0;

    /**
     * Draws this rectangle on a plane.
     * @param gtx the graphics context to use for drawing
     */
    public void draw(Graphics2D gtx){
        System.out.println("Drawing rectangle");
    }

    /**
     * Gets the width of the rectangle.
     * @return the width
     */
    public double getWidth() {
        return width;
    }

    /**
     * Sets the width of the rectangle
     * @param width the new width
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * Gets the height of the rectangle
     * @return the height
     */
    public double getHeight() {
        return height;
    }

    /**
     * Sets the height of the rectangle
     * @param height the new height
     */
    public void setHeight(double height) {
        this.height = height;
    }
}
