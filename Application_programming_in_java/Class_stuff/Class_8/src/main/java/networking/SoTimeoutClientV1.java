package networking;

import java.io.IOException;

public class SoTimeoutClientV1 implements Runnable
{
    public static final int DEFAULT_PORT    = 4884;
    
    private static SoTimeoutServerV1    server;
    
    public static void main(String[] args)
    {
        SoTimeoutClientV1   client  = new SoTimeoutClientV1();
        server = new SoTimeoutServerV1();
        new Thread( client, "Server Thread" ).start();
        try
        {
            Thread.sleep( 10000 );
            server.setActive( false );
        }
        catch ( InterruptedException exc )
        {
        }
    }
    
    public void run()
    {
        try
        {
            server.execute( DEFAULT_PORT );
        }
        catch ( IOException exc )
        {
            exc.printStackTrace();
            System.exit( 1 );
        }
    }
}
