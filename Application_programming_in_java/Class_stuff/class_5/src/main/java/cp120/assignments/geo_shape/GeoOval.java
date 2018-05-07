package cp120.assignments.geo_shape;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Arc2D;
import java.io.Serializable;

/**
 * Encapsulates an oval as a bounding box, and upper-left corner of the box.
 */
public class GeoOval extends GeoRectangle
    implements Serializable
{
    private static final long serialVersionUID = -2486046625699897092L;

    /**
     * Instantiates a new GeoOval.
     *
     * @param width the width of the oval
     * @param height the height of the oval
     */
    public GeoOval( double width, double height )
    {
        this( DEFAULT_ORIGIN, DEFAULT_COLOR, width, height );
    }
    
    /**
     * Instantiates a new GeoOval.
     *
     * @param origin the origin of the oval
     * @param width the width of the oval
     * @param height the height of the oval
     */
    public GeoOval( GeoPoint origin, double width, double height )
    {
        this( origin, DEFAULT_COLOR, width, height );
    }
    
    /**
     * Instantiates a new GeoOval.
     *
     * @param origin the origin of the oval
     * @param color the color of the oval
     * @param width the width of the oval
     * @param height the height of the oval
     */
    public GeoOval( GeoPoint origin, Color color, double width, double height )
    {
        super( origin, color, width, height );
    }

    /**
     * Returns an approximation of the perimeter of the oval.
     * @return an approximation of the perimeter of the oval.
     */
    public double perimeter()
    {
        double  rad1    = getWidth() / 2;
        double  rad2    = getHeight() / 2;
        double  expr    = (rad1 * rad1 + rad2 * rad2) / 2;
        double  result  = 2 * Math.PI * Math.sqrt( expr );
        return result;
    }
    
    /**
     * Returns the area of the oval.
     * @return The area of the oval.
     */
    public double area()
    {
        double  rad1    = getWidth() / 2;
        double  rad2    = getHeight() / 2;
        double  result  = Math.PI * rad1 * rad2;
        return result;
    }
    
    /**
     * Determine if a given shape is equal to this oval. The given shape
     * is equal to this oval if:
     * <ol>
     * <li>It is a GeoOval;</li>
     * <li>It has the same width and height as this oval;</li>
     * <li>It has the same origin as this oval;</li>
     * <li>It has the same color as this oval; and</li>
     * <li>It has the same edge width and edge color as this oval.</li>
     * </ol
     * 
     * @param shape     the given shape
     * @param epsilon   the epsilon value to for testing double values
     *                  for equality
     *                  
     * @return true if the given GeoShape is equal to this GeoRectangle.
     */
    public boolean equals( GeoShape shape, double epsilon )
    {
        boolean rcode   = false;
        if ( shape instanceof GeoOval )
            rcode = super.equals( shape, epsilon );
        return rcode;
    }
    
    /**
     * Draws the oval using the given graphics context.
     * 
     * @param gtx   The given graphics context.
     */
    public void draw( Graphics2D gtx )
    {
        GeoPoint    origin      = getOrigin();
        double      xco         = origin.getXco();
        double      yco         = origin.getYco();
        double      width       = getWidth();
        double      height      = getHeight();
        Shape       shape       = 
            new Arc2D.Double( xco, yco, width, height, 0, 360, Arc2D.OPEN );
        draw( shape, gtx );
    }
}
