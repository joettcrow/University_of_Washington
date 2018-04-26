package app;

import java.util.function.DoubleToIntFunction;

/**
 * @author jcrowley
 */

public class FunctionDemo {
    public static void main(String[] args) {
        DoubleToIntFunction round = x -> (int)(x + .5);

        for (int inx = 0; inx < 5; inx++) {
            double randy = Math.random()*1000;
//            System.out.println(round(randy));
        }
    }
}
