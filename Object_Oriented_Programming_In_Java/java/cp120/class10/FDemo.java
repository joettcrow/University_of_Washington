package cp120.class10;

import java.util.List;
import java.util.ArrayList;

/**
 * @author jcrowley
 */

public class FDemo {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int inx = 0; inx < 10; inx++) {
            list.add(inx);
        list.forEach(System.out::println);

        List<Integer> odds = new ArrayList<>();
        list.stream().filter(e -> (e%2)!=0).forEach(odds::add);

        odds.forEach(System.out::println);

        }
    }
}
