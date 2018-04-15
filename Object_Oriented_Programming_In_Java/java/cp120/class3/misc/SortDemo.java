package misc;

import java.util.Arrays;

public class SortDemo
{
    public static void main(String[] args)
    {
        int         len         = 1000000;
        int         samples     = 100;
        double[]    arr1        = getArray( len );
        
        long[]      sortNums    = new long[samples];
        for ( int inx = 0 ; inx < samples ; ++inx )
            sortNums[inx] = sort( Arrays.copyOf( arr1, len ) );
        
        parallelSort( Arrays.copyOf( arr1, len ) );
        long[]      pSortNums   = new long[samples];
        for ( int inx = 0 ; inx < samples ; ++inx )
            pSortNums[inx] = parallelSort( Arrays.copyOf( arr1, len ) );
        
        printStats( "Sort", sortNums );
        printStats( "ParallelSort", pSortNums );
    }

    /**
     * @param len
     * @return
     */
    private static double[] getArray( int len )
    {
        double[]    arr     = new double[len];
        for ( int inx = 0 ; inx < len ; ++inx )
            arr[inx] = Math.random();
        
        return arr;
    }
    
    private static long sort( double[] arr )
    {
        long    start   = System.currentTimeMillis();
        Arrays.sort( arr );
        long    end     = System.currentTimeMillis();
        long    diff    = end - start;
        
        return diff;
    }
    
    private static long parallelSort( double[] arr )
    {
        long    start   = System.currentTimeMillis();
        Arrays.parallelSort( arr );
        long    end     = System.currentTimeMillis();
        long    diff    = end - start;
        
        return diff;
    }
    
    private static void printStats( String label, long[] samples )
    {
        long    min     = samples[0];
        long    max     = min;
        double  sum     = 0;
        for ( int inx = 0 ; inx < samples.length ; ++inx )
        {
            long    sample  = samples[inx];
            sum += sample;
            if ( sample < min )
                min = sample;
            else if ( sample > max )
                max = sample;
            else
                ;
        }
        
        double  avg = sum / samples.length;
        StringBuilder   bldr    = new StringBuilder( label );
        bldr.append( ": " ).append( min ).append( " " ).append( max );
        bldr.append( " " ).append( avg );
        
        System.out.println( bldr );
    }
} 
