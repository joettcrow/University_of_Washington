package classtime.CP125;

import javax.swing.text.html.Option;
import java.util.Optional;

/**
 * @author jcrowley
 */

public class OptionalDemo {
    private int spot;
    private String jane;

    public static void main(String[] args) {
        new OptionalDemo();
    }

    public OptionalDemo(){
        jane = "sally";
        Optional<String> opt = getJane();
        if (opt.isPresent())
            System.out.println(opt.get());
        else
            System.out.println("eh?");
//        opt.isPresent(System.out::println );
    }

    public Optional<Integer> getSpot()
    {
        Optional<Integer> opt = Optional.of(spot);
        return opt;
    }

    public Optional<String> getJane(){
        Optional<String> str;
        if (jane != null)
            str = Optional.of(jane);
        else
            str = Optional.empty();
        return str;
    }
}
