package app;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author jcrowley
 */

public class LambdaDemo2 {

    public static void main(String[] args) {
        Integer[] nums =
                {1, 3, 2, 20, 7, 9, 8, 12, 13, 6, 5};
        List<Integer> list = Arrays.asList(nums);
        Collections.sort(list, (Integer num1, Integer num2) ->
                {
                    if (num1 % 2 == 1 && num2 % 2 == 0) return -1;
                    if (num1 % 2 == 0 && num2 % 2 == 1) return 1;
                    return num1.compareTo(num2);
                }
        );
        list.forEach(System.out::println);

        Collections.sort( list, (num1, num2) ->
        { if ( num1 % 2 == 1 && num2 % 2 == 0 ) return -1;
            if ( num1 % 2 == 0 && num2 % 2 == 1 ) return 1;
            return num1.compareTo( num2 );
        }
        );

    }
}


