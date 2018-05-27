package command_pattern4;

import static command_pattern2.Lightening.COLS;
import static command_pattern2.Lightening.ROWS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import command_pattern2.CloseCommand;
import command_pattern2.ToggleCommand;

public class Client
{
    private static final Logger log =
        LoggerFactory.getLogger( Client.class );
                            
    private final Random    randy   = new Random();
    
    public static void main(String[] args)
    {
        Client  client  = new Client();
        try
        {
            client.execute();
        }
        catch ( IOException exc )
        {
            exc.printStackTrace();
        }
    }
    
    public void execute()
        throws IOException
    {
        InetAddress addr    = InetAddress.getLoopbackAddress();
        int         port    = ServerDriver.DEF_PORT;
        try ( Socket socket = new Socket( addr, port ) )
        {
            execute( socket );
        }
    }
    
    private void execute( Socket socket )
        throws IOException
    {
        InputStream         iStream     = socket.getInputStream();
        InputStreamReader   reader      = new InputStreamReader( iStream );
        BufferedReader      bReader     = new BufferedReader( reader );
        OutputStream        oStream     = socket.getOutputStream();
        ObjectOutputStream  writer      = new ObjectOutputStream( oStream );
        
        for ( int inx = 0 ; inx < 10 ; ++inx )
        {
            ToggleCommand   cmd = new ToggleCommand();
            int             row = randy.nextInt( ROWS );
            int             col = randy.nextInt( COLS );
            cmd.setCoordinates( row, col );
            log.info( cmd.toString() );
            writer.writeObject( cmd );
            
            // wait for ACK from server
            String  line    = bReader.readLine();
            if ( !line.equalsIgnoreCase( "ACK" ) )
                throw new Error( "invalid acknowledgement from server" );
            
            pause( 250 );
        }
        
        writer.writeObject( new CloseCommand() );
    }
    
    private static void pause( long millis )
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
