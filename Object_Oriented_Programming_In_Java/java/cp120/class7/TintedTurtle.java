package cp120.class7;

import edu.uweo.javaintro.tools.Turtle;

import java.awt.*;

/**
 * @author jcrowley
 */

public class TintedTurtle extends Turtle{
    public static void main(String[] args) {
        TintedTurtle tainted = new TintedTurtle(Color.RED);
        tainted.fillCircle(64);

        TintedTurtle toots = new TintedTurtle();

        TintedTurtle tainted2 = new TintedTurtle(Color.cyan, 45, 98);
        tainted2.fillCircle(64);
    }

    public TintedTurtle(Color param){
        switchTo(param);
    }

    public TintedTurtle(){

    }

    public TintedTurtle( Color param, double degrees, double pixels){
        switchTo(param);
        move(degrees, pixels);
    }
}
