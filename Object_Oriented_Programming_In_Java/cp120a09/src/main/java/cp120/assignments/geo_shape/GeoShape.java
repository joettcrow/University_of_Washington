package cp120.assignments.geo_shape;

import java.awt.*;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * The class extended by all those classes that represent shapes to be drawn on a GeoPlane.
 * @author jcrowley
 */
public abstract class GeoShape {
    private GeoPoint origin;
    private Color color;

    public static final GeoPoint DEFAULT_ORIGIN  = new GeoPoint( 0, 0 );
    public static final Color DEFAULT_COLOR = Color.BLUE;
    public static final Color DEFAULT_EDGE_COLOR = Color.BLUE;
    public static final double DEFAULT_EDGE_WIDTH = 1;

    private double edgeWidth = DEFAULT_EDGE_WIDTH;

    private Color edgeColor = DEFAULT_EDGE_COLOR;

    /**
     * Abstract constructor for GeoShape.
     * @param origin the origin for the shape as a GeoPoint.
     * @param color the color for the shape to be, can be null.
     * @throws IllegalArgumentException Exception if origin is null.
     */
    public GeoShape( GeoPoint origin, Color color) throws IllegalArgumentException{
        this.setColor(color);
        this.setOrigin(origin);
    }

    /**
     * Retrieves the edgeWidth of the shape
     * @return the width as a double
     */
    public double getEdgeWidth() {
        return edgeWidth;
    }

    /**
     * Sets the edgeWidth of the shape
     * @param edgeWidth a double value for the edgeWidth
     */
    public void setEdgeWidth(double edgeWidth) {
        this.edgeWidth = edgeWidth;
    }

    /**
     * Retrieves the edgeColor for the shape
     * @return the Color of the edge
     */
    public Color getEdgeColor() {
        return edgeColor;
    }

    /**
     * Sets the edge color for the shape
     * @param edgeColor the Color to set the edge to
     */
    public void setEdgeColor(Color edgeColor) {
        this.edgeColor = edgeColor;
    }

    /**
     * Sets the edge values for the shape
     * @param color the color to set the edge to
     * @param width the width to set the edge to
     */
    public void	setEdge (Color color, double width){
        setColor(color);
        setEdgeWidth(width);
    }

    /**
     * Gets the origin of the shape.
     * @return the origin.
     */
    public GeoPoint getOrigin() {
        return origin;
    }

    /**
     * Sets the origin of the shape.
     * @param origin the new origin.
     * @throws IllegalArgumentException if the new origin is null.
     */
    public void setOrigin(GeoPoint origin) throws IllegalArgumentException {
        if (origin == null){
            throw new IllegalArgumentException("Origin cannot be null");
        }
        else {
            this.origin = origin;
        }
    }

    /**
     * Gets the color of the shape.
     * @return the color as a Color value.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets the color.
     * @param color the new color as a Color Value.
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Draw a shape on a GeoPlane.
     * This is required to be implemented by every subclass of GeoShape.
     * @param gtx the graphics context to use for drawing, currently is undefined.
     */
    public abstract void draw(Graphics2D gtx);

    /**
     * This is a draw method to help other shapes create their shapes and draw them
     * @param shape The Shape to be drawn
     * @param gtx The gtx value of the shape
     */
    public void draw( java.awt.Shape shape, Graphics2D gtx ){
        if (getColor() != null){
            gtx.setColor(getColor());
            gtx.fill(shape);
        }

        if (getEdgeColor() != null && getEdgeWidth() > 0){
            gtx.setColor(getEdgeColor());
            Stroke stroke = new BasicStroke((float) getEdgeWidth());
            gtx.setStroke(stroke);
            gtx.draw(shape);
        }
    }

    /**
     * Overides the default toString.
     * Formats the origin using GeoPoint to string.
     * Formats the color to be in 6 digit hex notation.
     * @return the origin and color for the shape as a String.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        String colorHex;
        color = getColor();
        Color hexColor = getEdgeColor();

        builder.append("origin="+ this.origin.toString() + ",color=");

        if (color != null) {
            colorHex = Integer.toHexString(color.getRGB());

            colorHex = "#" + colorHex.substring(2);
        }
        else {
            colorHex = "null";
        }
        builder.append(colorHex);

        builder.append(",edgeColor=");
        if (color != null) {
            colorHex = Integer.toHexString(hexColor.getRGB());

            colorHex = "#" + colorHex.substring(2);
        }
        else {
            colorHex = "null";
        }
        builder.append(colorHex);

        DecimalFormat df = new DecimalFormat("0.0000");
        df.setRoundingMode(RoundingMode.HALF_UP);
        builder.append(",edgeWidth=");
        builder.append(df.format(edgeWidth));

        return builder.toString();

    }

    /**
     * An equals helper method that checks double values for the shape
     * @param dbl1 the first value to check as a double
     * @param dbl2 the second value to check as a double
     * @param epsilon the tolerance for the check as a double
     * @return the truth of the equality as a boolean
     */
    public boolean equalsDouble( double dbl1, double dbl2, double epsilon ){
        boolean shapeEquals = false;
        if (Math.abs(dbl1 - dbl2) < epsilon){
            shapeEquals = true;
        }
        return shapeEquals;
    }

    /**
     * An equals object thing, we don't seem to use it...
     * @param obj1 the first object to check
     * @param obj2 the second object to check
     * @return the truth of the equality as a boolean
     */
    public boolean equalsObject( Object obj1, Object obj2 ){
        boolean returner = false;
        if (obj1 == obj2){
            returner = true;
        }
        else if ((obj1 == null) || (obj2 == null)){
            returner = false;
        }
        else if (obj1.equals(obj2)){
            returner = true;
        }
        return returner;
    }

    /**
     * This overrides the equals method to make it specific to GeoShape
     * @param that the other shape to compare
     * @param epsilon the tolerance for double comparisons as a double
     * @return the truth of the equa
     */
    public boolean equals( GeoShape that, double epsilon ){
        boolean returner = false;
        if (that == null) {
            returner = false;
        }
        if (this == that){
            returner = true;
        }
        else if (that == null){
            returner = false;
        }
        else {
            returner = (this.getColor() == that.getColor()) &&
                    (this.getOrigin().equals(that.getOrigin())) &&
                    (this.getEdgeColor() == that.getEdgeColor()) &&
                    (equalsDouble(this.getEdgeWidth(), that.getEdgeWidth(),epsilon));
        }
        return returner;
    }

}
