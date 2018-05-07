package cp120.assignments.geo_shape;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Stroke;
import java.io.Serializable;

/**
 * Base class for support of all geometry classes in the package.
 */
public abstract class GeoShape
    implements Serializable
{
    private static final long serialVersionUID = -4288355581336359477L;

    /** The default origin for a shape in this library. */
    public static final GeoPoint    DEFAULT_ORIGIN  = new GeoPoint( 0, 0 );
    
    /** The default color for a shape in this library. */
    public static final Color       DEFAULT_COLOR   = Color.BLUE;
    
    /** The default edge color for a shape in this library. */
    public static final Color       DEFAULT_EDGE_COLOR  = Color.BLUE;
    
    /** The edge width for a shape in this library. */
    public static final double      DEFAULT_EDGE_WIDTH  = 1.0;
    
    /** The Constant NULL_ORIGIN_ERR. */
    private static final String     NULL_ORIGIN_ERR = 
        "Origin may not be null";
    
    /** The origin. */
    private GeoPoint    origin;
    
    /** The color. */
    private Color       color;
    
    /** The edge color. */
    private Color       edgeColor   = DEFAULT_EDGE_COLOR;
    
    /** The edge width. */
    private double      edgeWidth   = DEFAULT_EDGE_WIDTH;
    
    /**
     * Draw.
     *
     * @param gtx the gtx
     */
    public abstract void draw( Graphics2D gtx );
    
    /**
     * Instantiates a new GeoShape.
     *
     * @param origin    the origin of the shape
     * @param color     the color of the shape
     * @throws IllegalArgumentException if the origin is null.
     */
    public GeoShape( GeoPoint origin, Color color )
        throws IllegalArgumentException
    {
        if ( origin == null )
            throw new IllegalArgumentException( NULL_ORIGIN_ERR );
        this.origin = origin;
        this.color = color;
    }
    
    /**
     * Gets the origin of the shape.
     *
     * @return the origin of the shape
     */
    public GeoPoint getOrigin()
    {
        return origin;
    }
    
    /**
     * Sets the origin of the shape.
     *
     * @param origin    the new origin; may <em>not</em> null
     * @throws IllegalArgumentException if the input is null.
     */
    public void setOrigin( GeoPoint origin )
        throws IllegalArgumentException
    {
        if ( origin == null )
            throw new IllegalArgumentException( NULL_ORIGIN_ERR );
        this.origin = origin;
    }
    
    /**
     * Gets the color of the shape.
     *
     * @return the color of the shape
     */
    public Color getColor()
    {
        return color;
    }
    
    /**
     * Sets the color of the shape.
     *
     * @param color the new color of the shape; may be null
     */
    public void setColor( Color color )
    {
        this.color = color;
    }
    
    /**
     * Gets the edge color of the shape.
     *
     * @return the edge color of the shape; may be null.
     */
    public Color getEdgeColor()
    {
        return edgeColor;
    }

    /**
     * Sets the edge color of the shape.
     *
     * @param edgeColor the new edge color of the shape; may be null
     */
    public void setEdgeColor( Color edgeColor )
    {
        this.edgeColor = edgeColor;
    }

    /**
     * Gets the edge width of the shape.
     *
     * @return the edge width of the shape
     */
    public double getEdgeWidth()
    {
        return edgeWidth;
    }

    /**
     * Sets the edge width of the shape.
     *
     * @param edgeWidth the new edge width of the shape
     */
    public void setEdgeWidth( double edgeWidth )
    {
        this.edgeWidth = edgeWidth;
    }
    
    /**
     * Determine if a given shape is equal to this rectangle. The given shape
     * is equal to this rectangle if:
     * <ol>
     * <li>It is a GeoRectangle;</li>
     * <li>It has the same width and height as this rectangle;</li>
     * <li>It has the same origin as this rectangle;</li>
     * <li>It has the same color as this rectangle; and</li>
     * <li>It has the same edge width and edge color as this rectangle.</li>
     * </ol
     * 
     * @param shape     the given shape
     * @param epsilon   the epsilon value to for testing double values
     *                  for equality
     *                  
     * @return true if the given GeoShape is equal to this GeoRectangle.
     */
    public boolean equals( GeoShape that, double epsilon )
    {
        boolean rcode   = false;
        
        if ( that != null )
        {
            rcode  =
                equalsObject( this.getOrigin(), that.getOrigin() )
                && equalsObject( this.getColor(), that.getColor() )
                && equalsObject( this.getEdgeColor(), that.getEdgeColor() )
                && equalsDouble( this.edgeWidth, that.edgeWidth, epsilon );
        }
        return rcode;
    }
    
    /**
     * Convenience method to draw a shape. If the shape's color is non-null,
     * the shape is filled. If the edge color is non-null, and the edge width
     * is greater than 0, the edges of the shape are drawn.
     * 
     * @param shape The shape to draw
     * @param gtx   The graphics context to use to draw the shape
     */
    public void draw( Shape shape, Graphics2D gtx )
    {
        if ( color != null )
        {
            gtx.setColor( getColor() );
            gtx.fill( shape );
        }
        
        if ( edgeColor != null && edgeWidth > 0 )
        {
            Stroke  stroke  = new BasicStroke( (float)edgeWidth );
            gtx.setStroke( stroke );
            gtx.setColor( edgeColor );
            gtx.draw( shape );
        }
    }

    /**
     * Returns a string representing those properties of the shape that
     * are maintained in this base class.
     * 
     * @return a string representing those properties of the shape that
     * are maintained in this base class.
     */
    @Override
    public String toString()
    {
        StringBuilder   bldr        = new StringBuilder( "origin=" );
        String          widthStr    = String.format( "%.4f", edgeWidth );
        bldr.append( origin ).append( ",color=" );
        bldr.append( getRGBString( color ) );
        bldr.append( ",edgeColor=" ).append( getRGBString( edgeColor ) );
        bldr.append( ",edgeWidth=" ).append( widthStr );
        
        return bldr.toString();
    }
    
    /**
     * Gets the hexadecimal value of a color using the RGB model,
     * and returns it as a string.
     *
     * @param color the color to encode
     * @return the RGB string
     */
    private String getRGBString( Color color )
    {
        String  result  = null;
        if ( color == null )
            result = "null";
        else
        {
            int rgb = color.getRGB() & 0x00ffffff;
            result = String.format( "#%06x", rgb );
        }
        
        return result;
    }
    
    /**
     * Uses the epsilon test to determine if two give doubles are equal.
     * 
     * @param dbl1      The first given double
     * @param dbl2      The second given double
     * @param epsilon   The epsilon value to use in the test
     * 
     * @return true if the two given doubles are equal
     */
    public boolean equalsDouble( double dbl1, double dbl2, double epsilon )
    {
        boolean rcode   = Math.abs( dbl1 - dbl2) < epsilon;
        return rcode;
    }
    
    /**
     * Determine if two given objects are equal. The two objects are
     * equal if:
     * <ol>
     * <li>They are both null; or</li>
     * <li>They are both non-null, and obj1.equals( obj2 )</l1>
     * </ol>
     * 
     * @param obj1  The first given object
     * @param obj2  The second given object
     * 
     * @return true, if the two given objects are equal.
     */
    public boolean equalsObject( Object obj1, Object obj2 )
    {
        boolean rcode   = false;
        
        if ( obj1 == obj2 )
            rcode = true;
        else if ( obj1 == null || obj2 == null )
            rcode = false;
        else
            rcode = obj1.equals( obj2 );
        return rcode;
    }
}
