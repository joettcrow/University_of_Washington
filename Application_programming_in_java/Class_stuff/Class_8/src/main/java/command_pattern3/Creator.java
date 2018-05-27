package command_pattern3;

import static command_pattern2.Lightening.COLS;
import static command_pattern2.Lightening.ROWS;

import java.util.Random;

import javax.swing.SwingUtilities;

public class Creator
{
    private final Random        randy       = new Random();
    private final Lightening    lightening  = new Lightening();
    
    public static void main(String[] args)
    {
        new Creator().start();
    }

    public void start()
    {
        try
        {
            SwingUtilities.invokeAndWait( lightening );
        }
        catch ( Exception exc )
        {
            exc.printStackTrace();
            System.exit( 1 );
        }
        
        Receiver        receiver    = new Receiver( lightening );
        ToggleCommand   cmd         = new ToggleCommand();
        while ( true )
        {
            int     row = randy.nextInt( ROWS );
            int     col = randy.nextInt( COLS );
            cmd.setReceiver( receiver );
            cmd.setCoordinates( row, col );
            cmd.execute();
            pause( 125 );
        }
    }
    
    private void pause( long millis )
    {
        try
        {
            Thread.sleep( millis );        
        }
        catch ( InterruptedException exc )
        {            
        }
    }
}
