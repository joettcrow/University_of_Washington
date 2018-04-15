package misc;

/**
 * Use to demonstrate JUnit testing.
 * 
 * @author jack
 */
public class ShortMethodDemo
{
    public static boolean checkRow( int row, char[][] board )
    {
        char    owner   = board[row][0];
        boolean result  = owner != '\u0000';
        for ( int inx = 1 ; result && inx < board[row].length ; ++inx )
            result = board[row][inx] == owner;
        return result;
    }
    
    public static boolean checkCol( int col, char[][] board )
    {
        char    owner   = board[0][col];
        boolean result  = owner != '\u0000';
        for ( int inx = 1 ; result && inx < board.length ; ++inx )
            result = board[inx][col] == owner;
        return result;
    }
    
    public static boolean checkLeftDiagonal( char[][] board )
    {
        char    owner   = board[0][0];
        boolean result  = owner != '\u0000';
        for ( int inx = 0, jnx = 0 ;
              result && inx < board.length ;
              ++inx, ++jnx
            )
            result = board[inx][jnx] == owner;
        return result;
    }
    
    public static boolean checkRightDiagonal( char[][] board )
    {
        int     last    = board.length - 1;
        char    owner   = board[0][last];
        boolean result  = owner != '\u0000';
        for ( int inx = 0, jnx = last ;
              result && inx < board.length ;
              ++inx, --jnx
            )
            result = board[inx][jnx] == owner;
        return result;
    }
}
