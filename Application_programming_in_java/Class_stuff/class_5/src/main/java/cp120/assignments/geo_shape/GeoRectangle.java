package cp120.assignments.geo_shape;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Encapsulates a rectangle as described by a width, height, 
 * and upper-left corner coordinates.
 * 
 * @author jack
 */
public class GeoRectangle extends GeoShape
    implements Externalizable
{
    private static final long serialVersionUID = 3256248872144364306L;

    private static final Logger log     =
        LoggerFactory.getLogger( GeoRectangle.class );

    /** The width of the rectangle. */
    private double  width;
    
    /** The height of the rectangle. */
    private double  height;
    
    /**
     * Default constructor. Required by implementation of Externalizable.
     */
    public GeoRectangle()
    {
        this( DEFAULT_ORIGIN, DEFAULT_COLOR, 0, 0 );
    }
    
    /**
     * Instantiates a new GeoRectangle.
     *
     * @param width the width of the rectangle
     * @param height the height of the rectangle
     */
    public GeoRectangle( double width, double height )
    {
        this( DEFAULT_ORIGIN, DEFAULT_COLOR, width, height );
    }
    
    /**
     * Instantiates a new GeoRectangle.
     *
     * @param origin the upper-left corner  of the rectangle
     * @param width the width of the rectangle
     * @param height the height of the rectangle
     */
    public GeoRectangle( GeoPoint origin, double width, double height )
    {
        this( origin, DEFAULT_COLOR, width, height );
    }
    
    /**
     * Instantiates a new GeoRectangle.
     *
     * @param origin the upper-left corner of the rectangle
     * @param color the color of the rectangle
     * @param width the width of the rectangle
     * @param height the height of the rectangle
     */
    public 
    GeoRectangle( GeoPoint origin, Color color, double width, double height )
    {
        super( origin, color );
        this.width = width;
        this.height = height;
    }
    
    /**
     * Calculates the area of the rectangle.
     *
     * @return the area of the rectangle
     */
    public double area()
    {
        return width * height;
    }
    
    /**
     * Calculates the perimeter of the rectangle
     *
     * @return the perimeter of the rectangle
     */
    public double perimeter()
    {
        return 2 * (width + height);
    }
    
    /**
     * Gets the width of the rectangle.
     *
     * @return the width of the rectangle
     */
    public double getWidth()
    {
        return width;
    }

    /**
     * Sets the width of the rectangle.
     *
     * @param width the new width of the rectangle
     */
    public void setWidth( double width )
    {
        this.width = width;
    }

    /**
     * Gets the height of the rectangle.
     *
     * @return the height of the rectangle
     */
    public double getHeight()
    {
        return height;
    }

    /**
     * Sets the height of the rectangle.
     *
     * @param height the new height of the rectangle
     */
    public void setHeight( double height )
    {
        this.height = height;
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
    public boolean equals( GeoShape shape, double epsilon )
    {
        boolean rcode   = false;
        if ( shape instanceof GeoRectangle )
        {
            GeoRectangle    that            = (GeoRectangle)shape;
            double          thisEdgeWidth   = this.getEdgeWidth();
            double          thatEdgeWidth   = that.getEdgeWidth();
            rcode  =
                equalsDouble( this.width, that.width, epsilon )
                && equalsDouble( this.height, that.height, epsilon )
                && equalsObject( this.getOrigin(), that.getOrigin() )
                && equalsObject( this.getColor(), that.getColor() )
                && equalsObject( this.getEdgeColor(), that.getEdgeColor() )
                && equalsDouble( thisEdgeWidth, thatEdgeWidth, epsilon );
        }
        return rcode;
    }

    /** 
     * Draws this rectangle using the given graphics context.
     * 
     * @param gtx   the given graphics context
     */
    @Override
    public void draw( Graphics2D gtx )
    {
        GeoPoint    origin  = getOrigin();
        double      xco     = origin.getXco();
        double      yco     = origin.getYco();
        Rectangle2D rect    = 
            new Rectangle2D.Double( xco, yco, width, height );
        draw( rect, gtx );
    }

    /**
     * Returns a string representing the properties of this rectangle.
     * 
     * @return a string representing the properties of this rectangle
     */
    @Override
    public String toString()
    {
        StringBuilder   bldr    = new StringBuilder( super.toString() );
        String  strWidth    = String.format( "%.4f", width );
        String  strHeight   = String.format( "%.4f", height );
        bldr.append( ",width=" ).append( strWidth );
        bldr.append( ",height=").append( strHeight );
        return bldr.toString();
    }

    @Override
    public void readExternal( ObjectInput inStr )
        throws IOException, ClassNotFoundException
    {
        GeoPoint    origin  = (GeoPoint)inStr.readObject();
        Color       edge    = (Color)inStr.readObject();
        Color       fill    = (Color)inStr.readObject();
        setOrigin( origin ); 
        setEdgeColor( edge ); 
        setColor( fill );
        width = inStr.readDouble();
        height = inStr.readDouble();
        
        log.info( "Deserialized {}", this );
    }

    @Override
    public void writeExternal( ObjectOutput outStr )
        throws IOException
    {
        GeoPoint    origin  = getOrigin();
        Color       edge    = getEdgeColor();
        Color       fill    = getColor();
        
        outStr.writeObject( origin );
        outStr.writeObject( edge ) ;
        outStr.writeObject( fill );
        outStr.writeDouble( width );
        outStr.writeDouble( height );
        
        log.info( "Serialized {}", this );
    }
}
