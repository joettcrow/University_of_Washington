package threads;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskDemo1
{
    public static void main(String[] args)
    {
        try
        {
            new FutureTaskDemo1().execute();
        }
        catch ( ExecutionException | InterruptedException exc )
        {
            exc.printStackTrace();
        }

    }
    
    public void execute() 
        throws ExecutionException, InterruptedException
    {
        Ping                pinger  = new Ping( "kexp.org" );
        FutureTask<String>  task    = new FutureTask<>( pinger );
        new Thread( task ).start();
        // go do something else for a while
        String              str     = task.get();
        System.out.println( str );
    }

    private class Ping implements Callable<String>
    {
        private final String    host;
        public Ping( String host )
        {
            this.host = host;
        }
        
        public String call() throws Exception
        {
            String  result  = host + ": ";
            try
            {
                InetAddress addr    = InetAddress.getByName( host );
                if ( !addr.isReachable( 2000 ) )
                    result += "not ";
            }
            catch ( UnknownHostException exc )
            {
                result += "not ";
            }
            
            result += "reachable";
            return result;
        }
    }
}
