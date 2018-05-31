package threads;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class FutureTaskDemo2
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
    
    public static void main(String[] args)
    {
        new FutureTaskDemo2().execute();
    }
    
    public void execute()
    {
        List<Thread>                    threads = new ArrayList<>();
        List<FutureTask<TaskResult>>    tasks   = new ArrayList<>();
        
        for ( String name : hostNames )
        {
            String                  threadName  = "thread " + name;
            Ping                    pinger      = new Ping( name );
            FutureTask<TaskResult>  task        = new FutureTask<>( pinger );
            tasks.add( task );
            threads.add( new Thread( task, threadName ) );
        }
        
        threads.forEach( Thread::start );
        int     size        = threads.size();
        boolean complete    = false;
        long    start       = System.currentTimeMillis();
        while ( !complete )
        {
            pause( 2 );
            long    numComplete = 0;
            for ( Future<TaskResult> task : tasks )
                if ( task.isDone() )
                    ++numComplete;
            complete = numComplete == size;
        }
        
        try
        {
            long    duration    = System.currentTimeMillis() - start;
            for ( FutureTask<TaskResult> task : tasks )
                System.out.println( task.get() );
            System.out.printf( "Duration: %d millis%n", duration );
        }
        catch ( InterruptedException | ExecutionException exc )
        {
            exc.printStackTrace();
        }
    }
    
    private static void pause( long millis )
    {
        try
        {
            Thread.sleep( millis );
        }
        catch ( InterruptedException exc )
        {
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
            String  frag    = reachable ? 
                "reachable" : "not reachable";
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
