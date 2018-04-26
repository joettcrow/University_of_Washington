package app;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author jcrowley
 */

public class SupCon2 {
    private static int count = 0;

    public static void main(String[] args) {
        final String str;
//        Supplier<String> sup = () -> "" + count++;
//        Consumer<String> con = (x) -> System.out.println(x);
//        execute(sup,con);

        execute(() -> "" + count++, System.out::println);
    }

    static private void execute(
            Supplier<String> sup,
            Consumer<String > con){
        for (int inx = 0; inx < 10; ++inx) {
            String val = sup.get();
            con.accept(val);
        }
    }
}
