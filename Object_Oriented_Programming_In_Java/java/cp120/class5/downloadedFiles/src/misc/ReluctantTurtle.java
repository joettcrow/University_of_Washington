package misc;

import java.awt.Color;

import javax.swing.JOptionPane;

import edu.uweo.javaintro.tools.Turtle;
import edu.uweo.javaintro.tools.Turtlet;

public class ReluctantTurtle extends Turtle
{
    public static void main(String[] args)
    {
        ReluctantTurtle mavis = new ReluctantTurtle();
        //mavis.paint( 90, 100 );
        mavis.switchTo( BLUE );
        mavis.fillCircle( 128, RED );
        mavis.fillBox( 64, 64 );
    }
    
    public Turtlet paint( double degrees, double length )
    {
        say( "If you insist" );
        Turtlet rval = super.paint( degrees, length );
        say( "Happy now?" );
        return rval;
    }
    
    public void fillCircle( double radius )
    {
        String  msg     =
            "Fill a circle with radius " + radius + "?";
        int     choice  = JOptionPane.showConfirmDialog( null, msg );
        if ( choice == JOptionPane.YES_OPTION )
            super.fillCircle( radius );
    }
    
    public void fillCircle( double radius, Color color )
    {
        Color   saveColor   = switchTo( color );
        super.fillCircle( radius );
        switchTo( saveColor );
    }
}