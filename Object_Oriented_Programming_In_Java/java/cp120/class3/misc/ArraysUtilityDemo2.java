package misc;

import java.util.Arrays;

public class ArraysUtilityDemo2
{
    private final static String[]   allNames =
    { "sam", "abe", "sally", "knot1", "jane", "knot2", "manny", "moe", "jack" };
    
    private final static String[]   present =
    { "sam", "abe", "sally", "jane", "manny", "moe", "jack" };
    
    public static void main(String[] args)
    {
        report( allNames, present );
    }

    private static void report( String[] registered, String[] signedIn )
    {
        // To do a binary search the array must be sorted
        Arrays.sort( signedIn );
        for ( String name : registered )
        {
            String  msg = name + ": ";
            int     pos = Arrays.binarySearch( signedIn, name );
            if ( pos < 0 )
                msg = msg + "not ";
            msg = msg + "present";
            System.out.println( msg );
        }
    }
}
