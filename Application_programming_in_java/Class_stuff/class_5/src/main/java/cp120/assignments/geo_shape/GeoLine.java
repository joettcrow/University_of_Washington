package cp120.assignments.geo_shape;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.io.Serializable;

/**
 * Encapsulates a line represented as two end points.
 */
public class GeoLine extends GeoShape
    implements Serializable
{
    
    /**
     * 
     */
    private static final long serialVersionUID = -6180187415880819057L;
    /** The end. */
    private GeoPoint    end;
    
    /**
     * Instantiates a new GeoLine.
     *
     * @param start     the start of the line
     * @param end       the end of the line
     */
    public GeoLine( GeoPoint start, GeoPoint end )
    {
        this( start, DEFAULT_COLOR, end );
    }
    
    /**
     * Instantiates a new GeoLine.
     *
     * @param start     the start of the line
     * @param color     the color of the line
     * @param end       the end of the line
     */
    public GeoLine( GeoPoint start, Color color, GeoPoint end )
    {
        super( start, null );
        setEdgeColor( color );
        this.end = end;
    }
    
    /**
     * Calculates the slope of the line.
     *
     * @return the slope of the line
     */
    public double slope()
    {
        GeoPoint    start   = getStart();
        double      xco1    = start.getXco();
        double      yco1    = start.getYco();
        double      xco2    = end.getXco();
        double      yco2    = end.getYco();
        double      diffX   = xco2 - xco1;
        double      diffY   = yco2 - yco1;
        double      slope   = diffY / diffX;
        
        return slope;
    }
    
    /**
     * Calculates the length of the line.
     *
     * @return the length of the line
     */
    public double length()
    {
        double  len = end.distance( getStart() );
        return len;
    }
    
    /** 
     * Draws this line using the given graphics context.
     * 
     * @param gtx   the given graphics context
     */
    @Override
    public void draw( Graphics2D gtx )
    {
        GeoPoint    start   = getStart();
        double      xco1    = start.getXco();
        double      yco1    = start.getYco();
        double      xco2    = end.getXco();
        double      yco2    = end.getYco();
        Line2D      line    = 
            new Line2D.Double( xco1, yco1, xco2, yco2 );
        draw( line, gtx );
    }

    /**
     * Gets the end of the line.
     *
     * @return the end of the line
     */
    public GeoPoint getEnd()
    {
        return end;
    }

    /**
     * Sets the end of the line.
     *
     * @param end the new end of the line
     */
    public void setEnd( GeoPoint end )
    {
        this.end = end;
    }

    /**
     * Gets the start of the line.
     *
     * @return the start of the line
     */
    public GeoPoint getStart()
    {
        return getOrigin();
    }

    /**
     * Sets the start of the line.
     *
     * @param start the new start of the line
     */
    public void setStart( GeoPoint start )
    {
        setOrigin( start );
    }
    
    @Override
    public boolean equals( GeoShape shape, double epsilon )
    {
        boolean rcode   = false;
        if ( shape instanceof GeoLine )
        {
            GeoLine that    = (GeoLine)shape;
            rcode = 
                equalsObject( this.end, that.end )
                && super.equals( shape, epsilon );
        }
        return rcode;
    }
    
    /**
     * Sets the color of the line.
     * Equivalent to calling <em>setEdgeColor()</em>.
     * 
     * @param color     The new color for the line.
     */
    @Override
    public void setColor( Color color )
    {
        setEdgeColor( color );
    }
    
    /**
     * Gets the color of the line.
     * Equivalent to calling <em>getEdgeColor()</em>.
     * 
     * @param color     The new color for the line.
     */
    @Override
    public Color getColor()
    {
        return getEdgeColor();
    }
    
    /**
     * Returns a string representing the properties of this line.
     * 
     * @return a string representing the properties of this line
     */
    @Override
    public String toString()
    {
        StringBuilder   bldr    = new StringBuilder( super.toString() );
        bldr.append( ",end=").append( end );
        return bldr.toString();
    }
}
