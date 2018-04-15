package misc;

public class TwoDArrayDemo2
{

    public static void main(String[] args)
    {
        int[][] test   =
        {
            { 1, 2, 3, 'a', 'b' },
            { 4, 5, 6, 'c', 'd' },
            { 7, 8, 9, 'e', 'f' }
        };
        
        for ( int inx = 0 ; inx < test.length ; ++inx )
            for ( int jnx = 0 ; jnx < test[inx].length ; ++ jnx )
                System.out.println( test[inx][jnx] );
    }

}
