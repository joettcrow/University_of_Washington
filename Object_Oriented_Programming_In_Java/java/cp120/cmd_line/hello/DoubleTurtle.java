package cp120.cmd_line.hello;

import edu.uweo.javaintro.tools.Turtle;

public class DoubleTurtle{
    public static void main(String[] args) {
        Turtle left = new Turtle();
        Turtle right = new Turtle();

        left.move(180, 256);
        left.swingAround( 128 );
        right.move(0, 256);
        right.fillBox( 128, 128);
    }
}