import  edu.uweo.javaintro.tools.Turtle;
import java.awt.Color;

public class Colors
{
    public static void main(String[] args) {
      Turtle toots = new Turtle();
      toots.switchTo( Turtle.RED );

      toots.fillCircle( 64);
      toots.move( 45, 200 );
      toots.switchTo( new Color ( .5f, .75f, .90f ) );
      toots.fillBox ( 64, 128 );

      toots.switchTo( Turtle.BLACK );
      toots.say( "HUH?");
    }
}
