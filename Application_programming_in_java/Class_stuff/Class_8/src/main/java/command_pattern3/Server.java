package command_pattern3;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Server
{
    private final Receiver  receiver;
    private final int       port;
    
    private static final Logger log =
        LoggerFactory.getLogger( Server.class );
    
    public Server( Lightening lightening, int port )
    {
        this.receiver = new Receiver( lightening );
        this.port = port;
    }
    
    public void execute()
        throws IOException, ClassNotFoundException
    {
        try ( ServerSocket listener = new ServerSocket( port ) )
        {
            execute( listener );
        }
    }
    
    private void execute( ServerSocket listener )
        throws IOException, ClassNotFoundException
    {
        while ( true )
        {
            log.trace( "Listening for clients" );
            try ( Socket client = listener.accept() )
            {
                log.trace( "connection accepted" );
                process( client );
            }
        }
    }
    
    private void process( Socket client )
        throws IOException, ClassNotFoundException
    {
        InputStream         iStream     = client.getInputStream();
        ObjectInputStream   objStream   = new ObjectInputStream( iStream );
        OutputStream        oStream     = client.getOutputStream();
        PrintWriter         writer      = new PrintWriter( oStream, true );
        
        AbstractCommand     command     = 
            (AbstractCommand)objStream.readObject();
        command.setReceiver( receiver );
        command.execute();
        writer.println( "ACK" );
    }
}
