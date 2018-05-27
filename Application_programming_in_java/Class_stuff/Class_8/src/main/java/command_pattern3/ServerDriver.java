package command_pattern3;

import javax.swing.SwingUtilities;

import util.JDK14LoggerConfig;

public class ServerDriver
{
    public static final int DEF_PORT    = 4885;
    
    public static void main( String[] args )
    {
        JDK14LoggerConfig.setLogLevel( JDK14LoggerConfig.LevelProxy.HIGH );
        
        Lightening  lightening  = new Lightening();
        Server      server      = new Server( lightening, DEF_PORT );
        
        try
        {
            SwingUtilities.invokeAndWait( lightening );
            server.execute();
        }
        catch ( Exception exc )
        {
            exc.printStackTrace();
        }
    }
}
