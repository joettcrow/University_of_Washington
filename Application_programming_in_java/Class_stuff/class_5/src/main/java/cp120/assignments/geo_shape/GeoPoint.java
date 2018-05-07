package cp120.assignments.geo_shape;

import java.io.Serializable;

/**
 * Encapsulates the x- and y-coordinates of a point on a plane.
 * 
 * @author jack
 */
public class GeoPoint
    implements Serializable
{
    private static final long serialVersionUID = -3852834186305251648L;

    /** The x-coordinate of this point. */
    private double   xco;
    
    /** The y-coordinate of this point. */
    private double   yco;
    
    /**
     * Instantiates a new GeoPoint.
     *
     * @param xco the x coordinate of the point
     * @param yco the y coordinate of the point
     */
    public GeoPoint( double xco, double yco )
    {
        this.xco = xco;
        this.yco = yco;
    }
    
    /**
     * Gets the x-coordinate of this point..
     *
     * @return the x-coordinate of this point.
     */
    public double getXco()
    {
        return xco;
    }
    
    /**
     * Sets the x-coordinate of this point..
     *
     * @param xco the new x-coordinate of this point.
     */
    public void setXco( double xco )
    {
        this.xco = xco;
    }
    
    /**
     * Gets the y-coordinate of this point..
     *
     * @return the y-coordinate of this point.
     */
    public double getYco()
    {
        return yco;
    }
    
    /**
     * Sets the x-coordinate of this point..
     *
     * @param yco the new y-coordinate of this point.
     */
    public void setYco( double yco )
    {
        this.yco = yco;
    }
    
    /**
     * Calculates the distance between this point and another GeoPoint.
     *
     * @param other the other point
     * @return the calculated distance
     */
    public double distance( GeoPoint other )
    {
        double  dist    = 0;
        if ( other != null )
        {
            double  xDiff   = xco - other.xco;
            double  yDiff   = yco - other.yco;
            dist = Math.sqrt( xDiff * xDiff + yDiff * yDiff );
        }
        
        return dist;
    }
    
    /**
     * Determine if another GeoPoint is equal to this one. Two
     * GeoPoints are equal if they are not null,
     * and if they have equal x- and y-coordinates.
     * Equality of coordinates is determine use the epsilon test.
     * 
     * @param other
     *     The other GeoPoint to test for equality; may be null
     * @param epsilon
     *     The value to use when performing the epsilon test
     *     for double-precision values
     *     
     * @return true if the two points are equal, false otherwise
     */
    public boolean equals( GeoPoint other, double epsilon )
    {
        boolean rval    = false;
        if ( other != null )
        {
            double  xcoDiff =   Math.abs( this.xco - other.xco );
            double  ycoDiff =   Math.abs( this.yco - other.yco );
            
            if ( xcoDiff < epsilon && ycoDiff < epsilon )
                rval = true;
        }
        
        return rval;
    }
    
    /**
     * Returns a formatted string representing this object.
     * The format of the string is "(xco,yco)", where xco and yco are
     * this object's x- and y-coordinates with four decimal places
     * of precision.
     * 
     * @return the formatted string
     */
    @Override
    public String toString()
    {
        String  strX    = String.format( "%.4f",xco );
        String  strY    = String.format( "%.4f",yco );
        StringBuilder   bldr    = new StringBuilder( "(" );
        bldr.append( strX ).append( "," ).append( strY ).append( ")" );
        return bldr.toString();
    }
}
