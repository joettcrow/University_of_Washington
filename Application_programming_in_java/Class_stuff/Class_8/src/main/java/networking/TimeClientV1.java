package networking;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeClientV1
{
    private static final Logger log =
        LoggerFactory.getLogger( TimeClientV1.class );
                            
    public static void main( String[] args )
    {
        try
        {
            TimeClientV1    client  = new TimeClientV1();
            client.execute( TimeDriverV1.DEFAULT_PORT, TimeDriverV1.TIME );
        }
        catch ( IOException | ClassNotFoundException exc )
        {
            exc.printStackTrace();
        }
    }
    
    public void execute( int port, String command )
        throws IOException, ClassNotFoundException
    {
        InetAddress addr    = InetAddress.getLoopbackAddress();
        log.info( addr.toString() );
        log.info( "Opening connection" );
        try ( Socket socket = new Socket( addr, port ) )
        {
            execute( socket, command );
        }
    }
    
    private void execute( Socket socket, String command )
        throws IOException, ClassNotFoundException
    {
        InputStream       iStr   = socket.getInputStream();
        ObjectInputStream objStr = new ObjectInputStream( iStr );
        
        OutputStream      oStr   = socket.getOutputStream();
        PrintWriter       writer = new PrintWriter( oStr, true );

        log.info( "Executing command" );
        writer.println( command );
        log.info( "Waiting for reponse" );
        Object  obj = objStr.readObject();
        log.info( obj.toString() );
    }
}
