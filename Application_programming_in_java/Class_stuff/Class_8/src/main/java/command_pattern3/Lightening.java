package command_pattern3;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Lightening
    implements Runnable
{
    public static final int     ROWS    = 30;
    public static final int     COLS    = 20;
    
    private final JPanel[][]    panels  = new JPanel[ROWS][COLS];
    private final JFrame        frame   = new JFrame( "Lightening" );
    
    public static void main(String[] args)
    {
        Lightening  obj = new Lightening();
        SwingUtilities.invokeLater( obj );
    }
    
    public void run()
    {
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        
        Container   pane    = frame.getContentPane();
        pane.setLayout( new GridLayout( ROWS, COLS, 1, 1 ) );
        
        Dimension   size    = new Dimension( 25, 10 );
        for ( int inx = 0 ; inx < ROWS ; ++inx )
            for ( int jnx = 0 ; jnx < COLS ; ++jnx )
            {
                JPanel  panel   = new JPanel();
                panel.setPreferredSize( size );
                panel.setBackground( Color.BLACK );
                pane.add( panel );
                panels[inx][jnx] = panel;
            }
        
        frame.pack();
        frame.setVisible( true );
    }
    
    public void setLight( int row, int col, boolean state )
    {
        JPanel  panel   = panels[row][col];
        Color   color   = state ? Color.WHITE : Color.BLACK;
        panel.setBackground( color );
    }
    
    public void toggleLight( int row, int col )
    {
        JPanel  panel       = panels[row][col];
        Color   color       = panel.getBackground();
        boolean currState   = color == Color.WHITE;
        setLight( row, col, !currState );
    }
}
