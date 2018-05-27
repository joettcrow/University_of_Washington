package networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import util.JDK14LoggerConfig;

public class TimeServerV1
{
    private static final Logger log =
        LoggerFactory.getLogger( EchoClientV1.class );
                                        
    public static void main( String[] args )
    {
        JDK14LoggerConfig.setLogLevel( JDK14LoggerConfig.LevelProxy.HIGH );
        TimeServerV1    server  = new TimeServerV1();
        try
        {
            server.execute( TimeDriverV1.DEFAULT_PORT );
        }
        catch ( IOException exc )
        {
            exc.printStackTrace();
        }
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
        while ( true )
        {
            log.info( "listening for connections" );
            try ( Socket client = server.accept() )
            {
                log.info( "connection accepted" );
                InputStream         iStr    = client.getInputStream();
                InputStreamReader   rIStr   = new InputStreamReader( iStr );
                BufferedReader      reader  = new BufferedReader( rIStr );
                
                OutputStream        oStr    = client.getOutputStream();
                ObjectOutputStream  oOutStr = new ObjectOutputStream( oStr );
                
                Object              obj     = null;
                String              line    = reader.readLine();
                log.info( "read: " + line );
                
                if ( line.equals( TimeDriverV1.TIME ) )
                    obj = LocalTime.now();
                else if ( line.equals( TimeDriverV1.DATE ) )
                    obj = LocalDate.now();
                else if ( line.equals( TimeDriverV1.DATE_TIME ) )
                    obj = LocalDateTime.now();
                else
                    obj = "unrecognized command";
                oOutStr.writeObject( obj );
                client.close();
            }
        }
    }
}
