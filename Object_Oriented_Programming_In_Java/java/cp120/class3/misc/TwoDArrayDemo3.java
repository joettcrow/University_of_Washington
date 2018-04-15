package misc;

public class TwoDArrayDemo3
{
    public static void main(String[] args)
    {
        int[][] test   =
        {
            { 1, 2, 3, 4, 5 },
            { 6, 7, 8, 9, 0 },
            { 1, 3, 3, 4, 5 }
        };
        for ( int row = 0 ; row < test.length ; ++row )
            System.out.println(  sumRow( test[row] ) );
    }

    private static int sumRow( int[] row )
    {
        int sum = 0;
        for ( int inx = 0 ; inx < row.length ; ++inx )
            sum += row[inx];
        return sum;
    }
}
