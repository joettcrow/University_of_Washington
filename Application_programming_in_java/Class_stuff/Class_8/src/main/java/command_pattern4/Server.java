package command_pattern4;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import command_pattern2.AbstractCommand;
import command_pattern2.CloseCommand;
import command_pattern2.Lightening;
import command_pattern2.Receiver;
import command_pattern2.ShutdownCommand;

public class Server
{
    private static final Logger log =
        LoggerFactory.getLogger( Server.class );
                
    private final int           port;
    private final Lightening    lightening;
    private boolean             shutdown    = false;
    
    public Server( Lightening lightening, int port )
    {
        this.lightening = lightening;
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
        while ( !shutdown )
        {
            log.trace( "Listening for clients" );
            Socket  client  = listener.accept();
            log.trace( "connection accepted" );
            ClientProcessor cProc   = new ClientProcessor( client );
            new Thread( cProc ).start();
        }
    }
    
    private class ClientProcessor
        implements Runnable
    {
        private final Socket    socket;
        boolean                 closing     = false;
        
        public ClientProcessor( Socket socket )
        {
            this.socket = socket;
        }
        
        public void run()
        {
            try ( Socket temp = socket )
            {
                process();
            }
            catch ( Exception exc )
            {
                log.error( "premature client exit", exc );
            }
        }
        
        private void process()
            throws IOException, ClassNotFoundException
        {
            InputStream         iStream     = socket.getInputStream();
            ObjectInputStream   objStream   = new ObjectInputStream( iStream );
            OutputStream        oStream     = socket.getOutputStream();
            PrintWriter         writer      = new PrintWriter( oStream, true );
            InternalReceiver    receiver    = 
                new InternalReceiver( lightening );
            
            while ( !closing && socket.isConnected() )
            {
                AbstractCommand     command     = 
                    (AbstractCommand)objStream.readObject();
                log.trace( command.toString() );
                command.setReceiver( receiver );
                command.execute();
                writer.println( "ACK" );
            }
        }
        
        private class InternalReceiver
            extends Receiver
        {
            public InternalReceiver( Lightening lightening )
            {
                super( lightening );
            }
            
            public void action( CloseCommand cmd )
            {
                closing = true;
                log.info( "close command detected" );
            }
            
            public void action( ShutdownCommand cmd )
            {
                shutdown = true;
                closing = true;
                log.info( "shutdown command detected" );
            }
        }
    }
}
