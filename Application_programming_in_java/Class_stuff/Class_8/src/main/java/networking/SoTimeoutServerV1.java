package networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import util.JDK14LoggerConfig;

public class SoTimeoutServerV1
{
    private static final Logger log =
        LoggerFactory.getLogger( EchoClientV1.class );
    
    // Set this to false to shutdown the server
    private boolean isActive    = true;
                                        
    public static void main( String[] args )
    {
        JDK14LoggerConfig.setLogLevel( JDK14LoggerConfig.LevelProxy.HIGH );
        SoTimeoutServerV1    server  = new SoTimeoutServerV1();
        try
        {
            server.execute( SoTimeoutClientV1.DEFAULT_PORT );
        }
        catch ( IOException exc )
        {
            exc.printStackTrace();
        }
    }
    
    public SoTimeoutServerV1()
    {
        JDK14LoggerConfig.setLogLevel( JDK14LoggerConfig.LevelProxy.HIGH );
    }
    
    public void execute( int port )
        throws IOException
    {
        try ( ServerSocket server = new ServerSocket( port ) )
        {
            execute( server );
        }
    }
    
    private void execute( ServerSocket server )
        throws IOException
    {
        server.setSoTimeout( 2000 );
        while ( isActive )
        {
            log.info( "listening for connections" );
            try ( Socket client = server.accept() )
            {
                log.info( "connection accepted" );
                InputStream       iStr   = client.getInputStream();
                InputStreamReader rIStr  = new InputStreamReader( iStr );
                BufferedReader    reader = new BufferedReader( rIStr );
                
                OutputStream      oStr   = client.getOutputStream();
                PrintWriter       writer = new PrintWriter( oStr, true );
                
                String            line   = reader.readLine();
                log.info( "read: " + line );
                writer.println( line );
                client.close();
            }
            catch ( SocketTimeoutException exc )
            {
                log.info( "isActive = {}", isActive );
            }
        }
        log.info( "shutting down" );
    }

    public boolean isActive()
    {
        return isActive;
    }

    public void setActive(boolean isActive)
    {
        this.isActive = isActive;
    }
}
