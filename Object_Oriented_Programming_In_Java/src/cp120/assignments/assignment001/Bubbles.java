package cp120.assignments.assignment001;

import edu.uweo.javaintro.tools.Turtle;

public class Bubbles{
    public static void main(String[] args) {
        Turtle bubbles = new Turtle();

        bubbles.move(180,128);
        bubbles.switchTo( Turtle.GREEN );
        bubbles.fillCircle( 128 );
        bubbles.move( 180, 0 );
        for (int i = 1; i < 6; i++) {
            int divisor = (int)Math.pow(2.0, (double) i);
            int fill = 128 / divisor;
            int mv = fill + fill*2;
            bubbles.move( 0, mv );
            bubbles.fillCircle( fill );
        }
    }
}
