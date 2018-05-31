package threads;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorServiceDemo
{
    private String[]    hostNames   =
    {
        "kuow.org",
        "kexp.org",
        "kcls.org",
        "npr.org",
        "wnew.org",
        "nbc.org",
        "abc.org",
        "cbs.org"
    };
    
    public static void main(String[] args) throws ExecutionException
    {
        new ExecutorServiceDemo().execute();
    }
    
    public void execute()
    {
        List<Ping>  pingers = new ArrayList<>();
        
        for ( String name : hostNames )
        {
            Ping                    pinger      = new Ping( name );
            pingers.add( pinger );
        }
        
        ExecutorService service = Executors.newCachedThreadPool();
        
        try
        {
            long                        start   = System.currentTimeMillis();
            List<Future<TaskResult>>    results = service.invokeAll( pingers );
            long    duration    = System.currentTimeMillis() - start;
            for ( Future<TaskResult> result : results )
                System.out.println( result.get() );
            System.out.printf( "Duration: %d millis%n", duration );
            service.shutdown();
        }
        catch ( InterruptedException | ExecutionException exc )
        {
            exc.printStackTrace();
            System.exit( 1 );
        }
    }
    
    private class TaskResult
    {
        public String  host;
        public boolean reachable;
        
        public TaskResult( String host, boolean reachable )
        {
            this.host = host;
            this.reachable = reachable;
        }
        
        public String toString()
        {
            String  frag    = reachable ? "reachable" : "not reachable";
            return host + ": " + frag; 
        }
    }

    private class Ping implements Callable<TaskResult>
    {
        private final   String      host;
        
        public Ping( String host )
        {
            this.host = host;
        }
        
        public TaskResult call()
        {
            boolean isReachable = false;
            try
            {
                InetAddress addr    = InetAddress.getByName( host );
                if ( addr.isReachable( 2000 ) )
                    isReachable = true;
                else
                    isReachable = false;
            }
            catch ( UnknownHostException exc )
            {
                isReachable = false;
            }
            catch ( IOException exc )
            {
                exc.printStackTrace();
            }
            
            TaskResult  result  = new TaskResult( host, isReachable );
            return result;
        }
    }
}
