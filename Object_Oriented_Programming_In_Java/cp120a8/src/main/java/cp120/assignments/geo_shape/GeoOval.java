package cp120.assignments.geo_shape;

import java.awt.*;
import java.math.RoundingMode;
import java.text.DecimalFormat;

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
     * Constructs a rectangle when passed two values.
     * @param width the width of the rectangle as a double.
     * @param height the height of the rectangle as a double.
     */
    public GeoOval( double width, double height ){
        this(DEFAULT_ORIGIN, DEFAULT_COLOR, width, height);
    }

    /**
     * Constructs a rectangle when passed three values.
     * @param origin the origin of the shape as a GeoPoint.
     * @param width the width of the rectangle as a double.
     * @param height the height of the rectangle as a double.
     */
    public GeoOval( GeoPoint origin, double width, double height ){
        this( origin, DEFAULT_COLOR, width, height );
    }

    /**
     * Constructs a rectangle when passed four values.
     * @param origin the origin of the shape as a GeoPoint.
     * @param color the color of the shape as a Color.
     * @param width the width of the rectangle as a double.
     * @param height the height of the oval as a double.
     * @throws IllegalArgumentException if the origin is null.
     */
    public GeoOval(
            GeoPoint origin,
            Color color,
            double width,
            double height
    ) throws IllegalArgumentException{
        super(origin,color,width,height);
    }

    /**
     * Overrides the default toString.
     * Formats the shape by calling the shape toString.
     * adds the width and height.
     * @return the shape and height at width as a String.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(super.toString());

        return builder.toString();
    }

    /**
     * Draws this oval on a plane.
     * @param gtx the graphics context to use for drawing, currently undefined.
     */
    @Override
    public void draw (Graphics2D gtx){
        System.out.println("Drawing oval: " + toString());
    }

}
