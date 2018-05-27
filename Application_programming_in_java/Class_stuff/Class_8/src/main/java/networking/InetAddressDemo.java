package networking;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressDemo
{
    public static void main(String[] args)
    {
        InetAddressDemo demo    = new InetAddressDemo();
        try
        {
            demo.execute();
        }
        catch ( UnknownHostException exc )
        {
            exc.printStackTrace();
        }
    }

    private void execute() throws UnknownHostException
    {
        InetAddress kexp        = InetAddress.getByName( "kexp.org" );
        InetAddress localHost   = InetAddress.getLocalHost();
        InetAddress loopback    = InetAddress.getLoopbackAddress();
        for ( InetAddress addr :
            new InetAddress[] { kexp, localHost, loopback } )
        {
            String  ipAddr  = addr.getHostAddress();
            String  name    = addr.getHostName();
            System.out.printf( "%-16s %s%n", ipAddr, name );
        }
    }
}
