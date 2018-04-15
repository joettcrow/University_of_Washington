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
}
