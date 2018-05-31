package producer_consumer1;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo
{
    private static final Logger log =
        LoggerFactory.getLogger( Producer.class );
    
    private static int  PRODUCERS   = 2;
    private static int  CONSUMERS   = 1;
    
    public static void main(String[] args)
    {
        new Demo().execute();
    }

    private void execute()
    {
        List<Thread>    allThreads  = new ArrayList<>();
        List<Object>    allWorkers  = new ArrayList<>();
        
        allWorkers.add( ProductQueue.INSTANCE );
        
        for ( int inx = 0 ; inx < PRODUCERS ; ++inx )
        {
            String      name        = "producer " + inx;
            Producer    producer    = new Producer();
            Thread      thread      = new Thread( producer, name );
            allThreads.add( thread );
            allWorkers.add( producer );
        }
        
        for ( int inx = 0 ; inx < CONSUMERS ; ++inx )
        {
            String      name        = "consumer " + inx;
            Consumer    consumer    = new Consumer();
            Thread      thread      = new Thread( consumer, name );
            allThreads.add( thread );
            allWorkers.add( consumer );
        }
        
        allThreads.forEach( Thread::start );
        log.info( "started {} threads" );
        pause( 10000 );
        ShutdownMgr.INSTANCE.setShutdown( true );
        allThreads.forEach( Demo::join );
        allWorkers.forEach( System.out::println );
    }
    
    private static void join( Thread thread )
    {
        String  name    = thread.getName();
        log.info( "joining {}", name );
        while ( thread.isAlive() )
        {
            try
            {
                thread.join();
            }
            catch ( InterruptedException exc )
            {
            }
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
}
