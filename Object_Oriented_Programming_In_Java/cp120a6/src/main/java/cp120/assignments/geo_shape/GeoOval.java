package cp120.assignments.geo_shape;

import java.awt.*;

/**
 * Encapsulation of an oval. The oval is defined by a bounding rectangle. Properties include:
 * <ul>
 * <li>The origin (x- and y-coordinates of the upper left corner) of the bounding rectangle</li>
 * <li>The width bounding of the rectangle</li>
 * <li>The height bounding of the rectangle</li>
 * <li>The color of the oval</li>
 * </ul>
 * @author jcrowley
 */
public class GeoOval extends GeoRectangle{
    /**
     * Draws this oval on a plane.
     * @param gtx the graphics context to use for drawing
     */
    public void draw (Graphics2D gtx){
        System.out.println("Drawing oval");
    }
}
