package networking;

import java.io.IOException;

public class TimeDriverV1
{
    public static final int     DEFAULT_PORT    = 6567;
    public static final String  TIME            = "time";
    public static final String  DATE            = "date";
    public static final String  DATE_TIME       = "date-time";
    
    public static void main( String[] args )
    {
        String[]    allCmds = 
            { TimeDriverV1.TIME, TimeDriverV1.DATE, TimeDriverV1.DATE_TIME };
        try
        {
            TimeClientV1    client  = new TimeClientV1();
            for ( String cmd : allCmds)
                client.execute( TimeDriverV1.DEFAULT_PORT, cmd );
        }
        catch ( IOException | ClassNotFoundException exc )
        {
            exc.printStackTrace();
        }
    }
}
