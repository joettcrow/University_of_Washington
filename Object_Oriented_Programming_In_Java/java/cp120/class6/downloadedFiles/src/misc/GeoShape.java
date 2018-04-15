package misc;

import java.awt.Color;

public abstract class GeoShape
{
    private double originX;
    private double originY;
    private Color color;
    
    public void setOriginX( double originX )
    {
        this.originX = originX;
    }
    
    public double getOriginX()
    {
        return originX;
    }
    
    public void setOriginY( double originY )
    {
        this.originY = originY;
    }
    
    public double getOriginY()
    {
        return originY;
    }
    
    public void setColor( Color color )
    {
        this.color = color;
    }
    
    public Color getColor()
    {
        return color;
    }
}
// Complete