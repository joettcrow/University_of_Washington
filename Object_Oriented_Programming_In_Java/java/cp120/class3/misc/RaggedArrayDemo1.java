package misc;

import java.util.Random;

public class RaggedArrayDemo1
{

    public static void main(String[] args)
    {
        Random      randy   = new Random( 1 );
        char[][]    test    = new char[10][];
        
        for ( int inx = 0 ; inx < test.length ; ++inx )
        {
            int rowLen  = randy.nextInt(  10 ) + 1;
            test[inx] = new char[rowLen];
        }
        
        for ( int inx = 0 ; inx < test.length ; ++inx )
            for ( int jnx = 0 ; jnx < test[inx].length ; ++jnx )
                test[inx][jnx] = 'X';

        for ( int inx = 0 ; inx < test.length ; ++inx )
        {
            for ( int jnx = 0 ; jnx < test[inx].length ; ++jnx )
                System.out.print(  test[inx][jnx] + " " );
            System.out.println();
        }
                

    }
}
