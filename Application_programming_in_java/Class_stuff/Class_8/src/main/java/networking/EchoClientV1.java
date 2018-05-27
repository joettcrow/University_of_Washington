package networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchoClientV1
{
    private static final Logger log =
        LoggerFactory.getLogger( EchoClientV1.class );
                            
    public static void main( String[] args )
    {
        try
        {
            new EchoClientV1().execute( EchoDriverV1.DEFAULT_PORT );
        }
        catch ( IOException exc )
        {
            exc.printStackTrace();
        }
    }
    
    public void execute( int port )
        throws IOException
    {
        InetAddress addr    = InetAddress.getLoopbackAddress();
        log.info( addr.toString() );
        log.info( "Opening connection" );
        try ( Socket socket = new Socket( addr, port ) )
        {
            execute( socket );
        }
    }
    
    private void execute( Socket socket )
        throws IOException
    {
        InputStream         iStr    = socket.getInputStream();
        InputStreamReader   rIStr   = new InputStreamReader( iStr );
        BufferedReader      reader  = new BufferedReader( rIStr );
        
        OutputStream        oStr    = socket.getOutputStream();
        PrintWriter         writer  = new PrintWriter( oStr, true );

        log.info( "Executing command" );
        writer.println( "'Twas brillig, and the slithy toves" );
        log.info( "Waiting for echo" );
        String  line    = reader.readLine();
        System.out.println( line );
    }
}
