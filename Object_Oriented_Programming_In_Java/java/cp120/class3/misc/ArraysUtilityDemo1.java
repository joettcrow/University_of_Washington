package misc;

import java.util.Arrays;

public class ArraysUtilityDemo1
{
    private final static String[]   testArr =
    { "sam", "abe", "sally", "jane", "manny", "moe", "jack" };
    
    public static void main( String[] args )
    {
        System.out.println( "positive test" );
        for ( String name : testArr )
        {
            String  msg = name + ": ";
            if ( !isPresent( testArr, name ) )
                msg += "not ";
            System.out.println( msg + " present");
        }
        
        System.out.println( "negative test" );
        for ( String name : testArr )
        {
            String  test    = name.substring( 1 );
            String  msg = test + ": ";
            if ( !isPresent( testArr, test ) )
                msg += "not ";
            System.out.println( msg + " present");
        }
    }

    // 1. Doing a binary search requires a sorted array
    // 2. The input array may need to be sorted prior to searching
    private static boolean
    isPresent( String[] registration, String student )
    {
        boolean result  = false;
        Arrays.sort( registration );
        
        int pos = Arrays.binarySearch( registration, student );
        if ( pos >= 0 )
            result = true;
        
        return result;
    }
}
