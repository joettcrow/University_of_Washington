package cp120.assignments.geo_shape;

import java.awt.*;
import java.math.RoundingMode;
import java.text.DecimalFormat;

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
     * Constructs a rectangle when passed two values.
     * @param width the width of the rectangle as a double.
     * @param height the height of the rectangle as a double.
     */
    public GeoRectangle( double width, double height ){
        this(DEFAULT_ORIGIN, DEFAULT_COLOR, width, height);
    }

    /**
     * Constructs a rectangle when passed three values.
     * @param origin the origin of the shape as a GeoPoint.
     * @param width the width of the rectangle as a double.
     * @param height the height of the rectangle as a double.
     */
    public GeoRectangle( GeoPoint origin, double width, double height ){
        this( origin, DEFAULT_COLOR, width, height );
    }

    /**
     * Constructs a rectangle when passed four values.
     * @param origin the origin of the shape as a GeoPoint.
     * @param color the color of the shape as a Color.
     * @param width the width of the rectangle as a double.
     * @param height the height of the rectangle as a double.
     * @throws IllegalArgumentException if the origin is null.
     */
    public GeoRectangle(
            GeoPoint origin,
            Color color,
            double width,
            double height
    ) throws IllegalArgumentException{

        super(origin,color);
        this.setWidth(width);
        this.setHeight(height);
    }

    /**
     * Draws this rectangle on a plane.
     * @param gtx the graphics context to use for drawing. Currently is undefined.
     */
    public void draw(Graphics2D gtx){
//        System.out.println("Drawing rectangle: " + toString());
        GeoPoint orig = this.getOrigin();
        double x = orig.getXco();
        double y = orig.getYco();
        if (getColor() != null){
            gtx.setColor(getColor());
            gtx.fillRect(
                    (int) x,
                    (int) y,
                    (int) getWidth(),
                    (int) getHeight()
            );
        }
        if (getEdgeColor() != null && getEdgeWidth() > 0){
            Stroke stroke = new BasicStroke((float) getEdgeWidth());
            gtx.setColor(getEdgeColor());
            gtx.setStroke(stroke);
            gtx.drawRect(
                    (int) x,
                    (int) y,
                    (int) getWidth(),
                    (int) getHeight()
            );
        }
    }

    /**
     * Gets the width of the rectangle.
     * @return the width as a double.
     */
    public double getWidth() {
        return width;
    }

    /**
     * Sets the width of the rectangle.
     * @param width the new width as a double.
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * Gets the height of the rectangle.
     * @return the height as a double.
     */
    public double getHeight() {
        return height;
    }

    /**
     * Sets the height of the rectangle.
     * @param height the new height as a double.
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * Calculates the area of the given rectangle.
     * @return the rectangle's area as a double.
     */
    public double area(){
        return width * height;
    }

    /**
     * Calculates the perimiter of the given rectangle.
     * @return the rectangle's perimeter as a double.
     */
    public double perimeter(){
        return height + height + width + width;
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
        DecimalFormat df = new DecimalFormat("0.0000");
        df.setRoundingMode(RoundingMode.HALF_UP);

        builder.append(super.toString());
        builder.append(",width=" + df.format(width) + ",height=" + df.format(height));

        return builder.toString();
    }

}
