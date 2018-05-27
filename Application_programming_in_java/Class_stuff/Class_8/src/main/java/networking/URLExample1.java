package networking;

import java.net.MalformedURLException;
import java.net.URL;

public class URLExample1
{
    public static void main(String[] args)
    {
        new URLExample1().execute();
    }

    private void execute()
    {
        try
        {
            URL     url         = 
                new URL( "http://faculty.washington.edu/jstraub" );
            String  protocol    = url.getProtocol();
            String  auth        = url.getUserInfo();
            String  host        = url.getHost();
            int     port        = url.getPort();
            int     defPort     = url.getDefaultPort();
            String  path        = url.getPath();
            String  query       = url.getQuery();
            String  frag        = url.getRef();
            
            System.out.printf( "%-10s = %s%n", "protocol", protocol );
            System.out.printf( "%-10s = %s%n", "user info", auth );
            System.out.printf( "%-10s = %s%n", "host", host );
            System.out.printf( "%-10s = %d%n", "port", port );
            System.out.printf( "%-10s = %d%n", "def port", defPort );
            System.out.printf( "%-10s = %s%n", "path", path );
            System.out.printf( "%-10s = %s%n", "query", query );
            System.out.printf( "%-10s = %s%n", "fragment", frag );
        }
        catch ( MalformedURLException exc )
        {
            exc.printStackTrace();
        }
    }
}
