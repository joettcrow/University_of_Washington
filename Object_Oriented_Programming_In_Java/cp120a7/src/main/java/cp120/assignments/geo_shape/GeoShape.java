package cp120.assignments.geo_shape;

import java.awt.*;

/**
 * The class extended by all those classes that represent shapes to be drawn on a GeoPlane.
 * @author jcrowley
 */
public abstract class GeoShape {
    private GeoPoint origin;
    private Color color;

    public static final GeoPoint DEFAULT_ORIGIN  = new GeoPoint( 0, 0 );
    public static final Color DEFAULT_COLOR = Color.BLUE;

    /**
     * Abstract constructor for GeoShape.
     * @param origin the origin for the shape as a GeoPoint.
     * @param color the color for the shape to be, can be null.
     * @throws IllegalArgumentException Exception if origin is null.
     */
    public GeoShape( GeoPoint origin, Color color) throws IllegalArgumentException{
        if (color != null){
            this.setColor(color);
        }
        try {
            this.setOrigin(origin);
        } catch (IllegalArgumentException exc){}
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

        builder.append("origin="+ this.origin.toString() + ",color=");

        if (color != null) {
            colorHex = Integer.toHexString(color.getRGB() & 0xffffff);
            if(colorHex.length() < 6)
            {
                if(colorHex.length()==5)
                    colorHex = "0" + colorHex;
                if(colorHex.length()==4)
                    colorHex = "00" + colorHex;
                if(colorHex.length()==3)
                    colorHex = "000" + colorHex;
                if(colorHex.length()==2)
                    colorHex = "0000" + colorHex;
                if(colorHex.length()==1)
                    colorHex = "00000" + colorHex;
            }
            colorHex = "#" + colorHex;
        }
        else {
            colorHex = "null";
        }
        builder.append(colorHex);
        return builder.toString();

    }
}
