package misc;

import edu.uweo.javaintro.tools.Turtle;

public class SuperTurtle
    extends Turtle
{
    public void drawHexagon( double side )
    {
        drawPolygon( side, 6 );
    }
    
    public void drawOctagon( double side )
    {
        drawPolygon( side, 8 );
    }
    
    public void drawPolygon( double side, int numSides )
    {
        double  turn    = 360 / numSides;
        for ( int inx = 0 ; inx < numSides ; ++inx )
            paint( turn, side );
    }
}
