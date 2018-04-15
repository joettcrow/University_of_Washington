package misc;

import static org.junit.Assert.*;

import org.junit.Test;

public class ShortMethodDemoTest
{
    private char[][]    board       = new char[3][3];
    
    @Test
    public void testCheckRow()
    {
        for ( int inx = 0 ; inx < board.length ; ++inx )
        {
            freshBoard();
            for ( int jnx = 0 ; jnx < board[inx].length ; ++jnx )
            {
                assertFalse( ShortMethodDemo.checkRow( inx, board ) );
                board[inx][jnx] = '1';                
            }
            assertTrue( ShortMethodDemo.checkRow( inx, board ) );
        }
    }

    @Test
    public void testCheckCol()
    {
        for ( int inx = 0 ; inx < board.length ; ++inx )
        {
            freshBoard();
            for ( int jnx = 0 ; jnx < board[inx].length ; ++jnx )
            {
                assertFalse( ShortMethodDemo.checkCol( inx, board ) );
                board[jnx][inx] = '1';                
            }
            assertTrue( ShortMethodDemo.checkCol( inx, board ) );
        }
    }

    @Test
    public void testCheckLeftDiagonal()
    {
        freshBoard();
        for ( int inx = 0, jnx = 0 ; inx < board.length ; ++inx, ++jnx )
        {
            assertFalse( ShortMethodDemo.checkLeftDiagonal( board ) );
            board[inx][jnx] = '1';                
        }
        assertTrue( ShortMethodDemo.checkLeftDiagonal( board ) );
    }

    @Test
    public void testCheckRightDiagonal()
    {
        freshBoard();
        int len = board.length;
        for ( int inx = 0, jnx = len - 1 ; inx < len ; ++inx, --jnx )
        {
            assertFalse( ShortMethodDemo.checkRightDiagonal( board ) );
            board[inx][jnx] = '1';                
        }
        assertTrue( ShortMethodDemo.checkRightDiagonal( board ) );
    }

    private void freshBoard()
    {
        for ( int inx = 0; inx < board.length ; ++inx )
            for ( int jnx = 0 ; jnx < board[inx].length ; ++jnx )
                board[inx][jnx] = '\u0000';
    }
}
