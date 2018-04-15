package cp120.assignments.geo_shape;

import java.awt.*;

/**
 * Encapsulation of an Line. Properties include:
 * <ul>
 * <li>The origin (x- and y-coordinates of the upper left corner) of the bounding rectangle</li>
 * <li>The width bounding of the rectangle</li>
 * <li>The height bounding of the rectangle</li>
 * <li>The color of the oval</li>
 * </ul>
 * @author jcrowley
 */
public class GeoLine extends GeoShape {

    private GeoPoint end;

    /**
     * Constructor for a new line shape
     * @param start A GeoPoint for the starting point
     * @param color A color for the shape
     * @param end A GeoPoint for the starting point
     */
    public GeoLine(GeoPoint start, Color color, GeoPoint end ){
        super(start,color);
        this.setEnd(end);
        this.setEdgeColor(color);
    }

    /**
     * Constructor for a new line shape
     * @param start A GeoPoint for the starting point
     * @param end A GeoPoint for the starting point
     */
    public GeoLine( GeoPoint start, GeoPoint end ){
        this(start,DEFAULT_COLOR,end);
    }

    /**
     * Retrieves the start point
     * @return Start as a GeoPoint
     */
    public GeoPoint getStart(){
        return getOrigin();
    }

    /**
     * Sets the start point
     * @param start A GeoPoint for the start point
     */
    public void setStart( GeoPoint start ){
        this.setOrigin(start);
    }

    /**
     * Retrieves the end point
     * @return End as a GeoPoint
     */
    public GeoPoint getEnd(){
        return end;
    }

    /**
     * Sets the end point
     * @param end A GeoPoint for the end point
     */
    public void setEnd( GeoPoint end ){
        this.end = end;

    }

    /**
     * Calculates the length of the line
     * @return The length of the line as a Double
     */
    public double length(){
        return end.distance(getStart());
    }

    /**
     * Calculates the slope of the line
     * @return The slope of the line as a double value
     */
    public double slope(){
        double x = getStart().getXco();
        double x1 = end.getXco();
        double y = getStart().getYco();
        double y1 = end.getYco();
        double num = y1 - y;
        double den = x1 -x;

        return num/den;
    }

    /**
     * Draws the line.
     * @param gtx the graphics context to use for drawing, currently is undefined.
     */
    public void draw(Graphics2D gtx){
        if (getEdgeColor() == null || getEdgeWidth() <= 0){

        }
        else {
            double xOrig = getStart().getXco();
            double yOrig = getStart().getYco();
            double xEnd = end.getXco();
            double yEnd = end.getYco();
            gtx.setColor(getEdgeColor());
            Stroke stroke = new BasicStroke((float) getEdgeWidth());
            gtx.setStroke(stroke);
            gtx.drawLine(
                    (int) xOrig,
                    (int) yOrig,
                    (int) xEnd,
                    (int) yEnd
            );
        }
    }







}
