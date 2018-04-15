package cp120.class5;

import edu.uweo.javaintro.tools.Turtle;

/**
 * @author jcrowley
 */

public class ReluctantTurtle extends Turtle{

    public static void main(String[] args) {
        Turtle toots = new ReluctantTurtle();
        System.out.println(toots instanceof ReluctantTurtle);
        toots.paint(90, 128);
        System.out.println(toots);
    }

    public Turtle paint ( double deg, double dist){
        say( "OMG fine!  I'll do it.  Gosh.");
        super.paint(deg, dist);
        say("Are you happy now?  Now can I take a nap? \nDo I bother you this way?");
        return this;
    }

    @Override
    public String toString() {
//        return "not now, please";
        return super.toString();
    }
}
