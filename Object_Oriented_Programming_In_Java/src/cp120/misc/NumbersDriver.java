package cp120.misc;

import java.math.BigInteger;

import cp120.assignments.assignment003.Numbers;

/**
 * A sample driver for the third class assignment.
 *
 * @author jack
 */
public class NumbersDriver
{

    /**
     * Main application entry point.
     *
     * @param args command line arguments; not used
     */
    public static void main( String[] args )
    {
        // Each of the following represents a suite of exercises.
        isPrime();
        newLine();
        lcm();
        newLine();
        gcf();
        newLine();
        digitSum();
        newLine();
        mean();
        newLine();
        median();
        newLine();
        factorial();
        newLine();
        factorialP();
    }

    /**
     * Convenience method to print a newline.
     */
    private static void newLine()
    {
        System.out.println();
    }

    /**
     * Exercises Numbers.isPrime().
     *
     * @see #isPrime(int)
     * @see Numbers#isPrime(int)
     */
    private static void isPrime()
    {
        int[]   pArr    = { 0, 1, 2, 3, 4, 5, 7, 9, 11, 13, 14, 15, 17, 19 };
        for ( int num : pArr )
            isPrime( num );
    }

    /**
     * Convenience routine to assist testing Numbers.isPrime().
     * Calls Numbers.isPrime() with the given integer, and
     * prints the result.
     *
     * @param num the given number
     *
     * @see #isPrime()
     * @see Numbers#isPrime(int)
     */
    private static void isPrime( int num )
    {
        boolean result  = Numbers.isPrime( num );
        System.out.printf( "isPrime( %3d ): %b\n", num, result );
    }

    /**
     * Exercises Numbers.LCM() (least common multiple).
     *
     * @see NumbersDriver#lcm(int, int)
     * @see Numbers#lcm(int,int)
     */
    private static void lcm()
    {
        int[][] arr =
                {
                        { 0, 0 }, { 0, 1 }, { 1, 1 }, { 1, 2 }, { 2, 2 }, { 2, 3 },
                        { 3, 3 }, { 2, 4 }, { 2, 6 }, { 2, 8 }, { 3, 5 }, { 3, 7 },
                        { 5, 7 }, { 5, 13 },
                        { 210, 16 }, { 125, 55 }, { 70, 34 }, { 21, 18 }
                        //  1680         1375        1190        126
                };

        for ( int[] pair : arr )
            lcm( pair[0], pair[1] );
    }

    /**
     * Convenience routine to assist testing Numbers.LCM().
     * Calls Numbers.LCM() with the given integers, and
     * prints the result.
     *
     * @param num1 the first given integer
     * @param num2 the second given integer
     *
     * @see NumbersDriver#lcm()
     * @see Numbers#lcm(int,int)
     */
    private static void lcm( int num1, int num2 )
    {
        int result  = Numbers.lcm( num1, num2 );
        System.out.printf( "lcm( %3d, %3d ): %4d\n", num1, num2, result );
    }

    /**
     * Exercises Numbers.GCF() (greatest common factor).
     *
     * @see #gcf(int, int)
     * @see Numbers#gcf(int, int)
     */
    private static void gcf()
    {
        int[][] arr =
                {
                        { 0, 0 }, { 0, 1 }, { 1, 1 }, { 1, 2 }, { 2, 2 }, { 2, 3 },
                        { 3, 3 }, { 2, 4 }, { 2, 6 }, { 2, 8 }, { 3, 5 }, { 3, 7 },
                        { 5, 7 }, { 5, 13 },
                        { 210, 16 }, { 125, 55 }, { 70, 34 }, { 21, 18 }
                };

        for ( int[] pair : arr )
            gcf( pair[0], pair[1] );
    }

    /**
     * Convenience routine to assist testing Numbers.GCF().
     * Calls Numbers.GCF() with the given integers, and
     * prints the result.
     *
     * @param num1 the first given integer
     * @param num2 the second given integer
     *
     * @see #gcf()
     * @see Numbers#gcf(int, int)
     */
    private static void gcf( int num1, int num2 )
    {
        int result  = Numbers.gcf( num1, num2 );
        System.out.printf( "gcf( %3d, %3d ): %d\n", num1, num2, result );
    }

    /**
     * Exercises Numbers.digitSum().
     *
     * @see #digitSum(int)
     * @see Numbers#digitSum(int)
     */
    private static void digitSum()
    {
        int[]   nums    =
                { 0, 1, 2, 10, 11, 12, 21, 101, 111, 121, 543,
                        1975, 945710
                };

        for ( int num : nums )
            digitSum( num );
    }

    /**
     * Convenience routine to assist testing Numbers.digitSum().
     * Calls Numbers.digitSum() with the given integer, and
     * prints the result.
     *
     * @param num the given number
     *
     * @see #digitSum()
     * @see Numbers#digitSum(int)
     */
    private static void digitSum( int num )
    {
        int sum = Numbers.digitSum( num );
        System.out.printf( "digitSum( %6d ): %2d\n", num, sum );
    }

    /**
     * Exercises Numbers.mean().
     *
     * @see #mean(double[])
     * @see Numbers#mean(double[])
     */

    private static void mean()
    {
        // The numbers in the comments are the expected values
        double[]    arr1    = { -1 }; // -1
        double[]    arr2    = { -2, 2 }; // 0
        double[]    arr3    = { -.1, -.3, -.2 }; // -.2
        double[]    arr4    = { 1, .5, -2.2, -.3, .5, -.4, 3.2 }; // 0.3286
        double[][]  allArrs = { arr1, arr2, arr3, arr4 };

        for ( double[] arr : allArrs )
            mean( arr );
    }

    /**
     * Convenience routine to assist testing Numbers.mean().
     * Calls Numbers.mean() with the given array, and
     * prints the result.
     *
     * @param arr the given array
     *
     * @see #mean()
     * @see Numbers#mean(double[])
     */
    private static void mean( double[] arr )
    {
        double          mean    = Numbers.mean( arr );
        String          strMean = String.format( "%5.4f", mean );
        StringBuilder   bldr    = new StringBuilder( "mean( " );
        bldr.append( arr[0] );
        for ( int inx = 1 ; inx < arr.length ; ++inx )
            bldr.append( ", " ).append( arr[inx] );
        bldr.append( " ): " ).append( strMean );
        System.out.println( bldr );
    }

    /**
     * Exercises Numbers.median().
     *
     * @see #median(double[])
     * @see Numbers#median(double[])
     */
    private static void median()
    {
        // The numbers in the comments are the expected values
        double[]    arr1    = { -1 }; // -1
        double[]    arr2    = { -2, 2 }; // 0
        double[]    arr3    = { -.1, -.3, -.2 }; // -.2
        double[]    arr4    = { -.5, .3, -.12, .6, -.8, 22 }; // .09
        double[]    arr5    = { 1, .5, -2.2, -.3, .5, -.4, 3.2 }; // 0.500
        double[][]  allArrs = { arr1, arr2, arr3, arr4, arr5 };

        for ( double[] arr : allArrs )
            median( arr );
    }

    /**
     * Convenience routine to assist testing Numbers.median().
     * Calls Numbers.median() with the given array, and
     * prints the result.
     *
     * @param arr the given array
     *
     * @see #median()
     * @see Numbers#median(double[])
     */
    private static void median( double[] arr )
    {
        double          median      = Numbers.median( arr );
        String          strMedian   = String.format( "%5.4f", median );
        StringBuilder   bldr    = new StringBuilder( "median( " );
        bldr.append( arr[0] );
        for ( int inx = 1 ; inx < arr.length ; ++inx )
            bldr.append( ", " ).append( arr[inx] );
        bldr.append( " ): " ).append( strMedian );
        System.out.println( bldr );
    }

    /**
     * Exercises Numbers.factorial( int ).
     *
     * @see #factorial(int)
     * @see Numbers#factorial(int)
     */
    private static void factorial()
    {
        // The numbers in the comments are the expected values
        int[]   nums    =
                {   0,   1,   2,   3,   5,
                        //   1    1    2    6    120
                        10,     20,     31 };
        //   3,628,800
        //           2,432,902,008,176,640,000
        //                   8,222,838,654,177,922,817,725,562,880,000,000
        for ( int num : nums )
            factorial( num );
    }

    /**
     * Convenience routine to assist testing Numbers.factorial( int ).
     * Calls Numbers.factorial(int ) with the given number, and
     * prints the result.
     *
     * @param num   the given number
     *
     * @see #factorial()
     * @see Numbers#factorial(int)
     */
    private static void factorial( int num )
    {
        BigInteger  factorial   = Numbers.factorial( num );
        System.out.printf( "factorial( %2d ): %,d\n", num, factorial );
    }

    /**
     * Exercises Numbers.factorial( BigInteger ).
     *
     * @see #factorial(BigInteger)
     * @see Numbers#factorial(BigInteger)
     */
    private static void factorialP()
    {
        int[]   nums    =
                {   0,   1,   2,   3,   5,
                        //   1    1    2    6    120
                        10,     20,     31 };
        //   3,628,800
        //           2,432,902,008,176,640,000
        //                   8,222,838,654,177,922,817,725,562,880,000,000
        for ( int num : nums )
        {
            BigInteger    bigNum  = BigInteger.valueOf( num );
            factorial( bigNum );
        }
    }

    /**
     * Convenience routine to assist testing Numbers.factorial( BigInteger ).
     * Calls Numbers.factorial(BigInteger) with the given number, and
     * prints the result.
     *
     * @param num   the given number
     *
     * @see #factorial(BigInteger)
     * @see Numbers#factorial(BigInteger)
     */
    private static void factorial( BigInteger num )
    {
        BigInteger  factorial   = Numbers.factorial( num );
        System.out.printf( "factorial'( %2d ): %,d\n", num, factorial );
    }
}
