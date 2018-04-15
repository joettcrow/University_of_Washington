package misc;

public class RaggedArrayDemo2
{

    public static void main(String[] args)
    {
        int[][] test    = new int[][]
        {
            { 0, 1, 2 },
            { 0, 1, 2, 3, 4, 5 },
            { 0, 1, 2, 3, 4 },
            { 0, 1 },
            { 0, 1, 2, 3 }
        };
        for ( int inx = 0  ; inx < test.length ; ++inx )
        {
            for ( int jnx = 0 ; jnx < test[inx].length ; ++jnx )
                System.out.print(  test[inx][jnx] + " " );
            System.out.println();
        }
    }
}
