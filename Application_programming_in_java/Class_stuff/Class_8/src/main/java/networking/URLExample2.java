package networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class URLExample2
{
    public static void main(String[] args)
    {
        new URLExample2().execute();
    }

    private void execute()
    {
        try
        {
            URL             url         = 
                new URL( "http://faculty.washington.edu/jstraub" );
            execute( url );
        }
        catch ( IOException exc )
        {
            exc.printStackTrace();
        }
    }
    
    private void execute( URL url ) throws IOException
    {
        try (
            InputStream inStr = url.openStream();
            InputStreamReader reader = new InputStreamReader( inStr );
            BufferedReader bufReader = new BufferedReader( reader );
        )
        {
            String  line    = null;
            while ( (line = bufReader.readLine()) != null )
                System.out.println( line );
        }
    }
}
