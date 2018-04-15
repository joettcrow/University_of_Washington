package misc;

import java.awt.Color;

import edu.uweo.javaintro.tools.Turtle;

public class NamedTurtle
    extends Turtle
{
    private String  name;
    
    public String getName()
    {
        return name;
    }
    public void setName( String turtleName )
    {
        name = turtleName;
    }
    
    public static void main(String[] args)
    {
        NamedTurtle sally   = new NamedTurtle();
        NamedTurtle spot    = new NamedTurtle();
        sally.drawSquare( 128, BLUE );
        spot.move( 0, 133 );
        spot.drawSquare( 128, GREEN );
    }
    
    public void drawSquare( double side, Color color )
    {
        switchTo( color );
        for ( int inx = 0 ; inx < 4 ; ++inx )
            paint( 90, side );
    }
}
