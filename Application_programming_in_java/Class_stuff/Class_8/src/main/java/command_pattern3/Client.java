package command_pattern3;

import static command_pattern3.Lightening.COLS;
import static command_pattern3.Lightening.ROWS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Random;

public class Client
{
    private final Random    randy   = new Random();
    
    public static void main(String[] args)
    {
        Client  client  = new Client();
        while ( true )
        {
            try
            {
                client.execute();
                pause( 125 );
            }
            catch ( IOException exc )
            {
                exc.printStackTrace();
                System.exit( 1 );
            }
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
        InputStream        iStream = socket.getInputStream();
        InputStreamReader  reader  = new InputStreamReader( iStream );
        BufferedReader     bReader = new BufferedReader( reader );
        OutputStream       oStream = socket.getOutputStream();
        ObjectOutputStream writer  = new ObjectOutputStream( oStream );
        
        ToggleCommand   cmd = new ToggleCommand();
        int             row = randy.nextInt( ROWS );
        int             col = randy.nextInt( COLS );
        cmd.setCoordinates( row, col );
        writer.writeObject( cmd );
        
        // wait for ACK from server
        String  line    = bReader.readLine();
        if ( !line.equalsIgnoreCase( "ACK" ) )
            throw new Error( "invalid acknowledgement from server" );
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
