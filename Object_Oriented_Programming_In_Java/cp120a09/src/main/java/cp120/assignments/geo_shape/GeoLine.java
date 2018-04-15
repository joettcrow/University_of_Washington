package cp120.assignments.geo_shape;

import java.awt.*;
import java.awt.geom.Line2D;

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
     * Overrides setting the color of the line to use the edge color
     * @param color the new color as a Color Value.
     */
    @Override
    public void setColor(Color color) {
        super.setEdgeColor(color);
    }

    /**
     * Overrides the get color method from the shape and uses the edge color instead
     * @return the color of the line
     */
    @Override
    public Color getColor() {
        return super.getEdgeColor();
    }

    /**
     * Creates an equals methos for line in particular
     * @param shape the shape to compare
     * @param epsilon the difference allowed between double values
     * @return a boolean truth value of if they are equal
     */
    @Override
    public boolean equals( GeoShape shape, double epsilon ){
        boolean response;
        if (!(shape instanceof GeoLine)){
            response = false;
        }
        else {
            response = super.equals(shape,epsilon);
        }
        return response;
    }

    /**
     * Overrides the default toString.
     * Formats the shape by calling the shape toString.
     * adds the end point.
     * @return the shape and height at width as a String.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(super.toString());
        builder.append(",end=" + end.toString());

        return builder.toString();
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
     * @param gtx the graphics context to use for drawing.
     */
    public void draw(Graphics2D gtx){
        if (getEdgeColor() == null || getEdgeWidth() <= 0){

        }
        else {
            Line2D line = new Line2D.Double(
                    getStart().getXco(),
                    getStart().getYco(),
                    end.getXco(),
                    end.getYco()
            );
            super.draw(line,gtx);
        }
    }







}
