package barrier_demo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BarrierDemo
{
    private static final int    MIN_TIME    = 125;
    private static final int    MAX_TIME    = 250;
    private static final int    NUM_TASKS   = 1000;
    private static final int    NUM_THREADS = 5;
    private List<Thread>        allThreads  = new ArrayList<>();;
    private List<Task>          allTasks;
    
    public static void main(String[] args)
    {
        new BarrierDemo().execute();
    }

    public void execute()
    {
        allTasks = Stream.iterate( 1, n -> n + 1 )
            .limit( NUM_TASKS )
            .map( n -> new Task( n, MIN_TIME, MAX_TIME ) )
            .collect( Collectors.toList() );
        
        int range   = NUM_TASKS / NUM_THREADS;
        int bottom  = 0;
        System.out.println( NUM_TASKS );
        for ( int inx = 0 ; bottom < NUM_TASKS ; ++inx )
        {
            String  name    = "Exec thread " + inx;
            int     top     = bottom + range;
            if ( top > NUM_TASKS )
                top = NUM_TASKS;
            
            System.out.println( "[" + bottom + "," + top + ")" );
            TaskExecutor    exec    =
                new TaskExecutor( allTasks, bottom, top );
            Thread          thread  = new Thread( exec, name );
            allThreads.add( thread );
            bottom = top;
        }
        
        long    start   = System.currentTimeMillis();
        allThreads.forEach( Thread::start );
        int     limit   = allThreads.size();
        for ( int inx = 0 ; inx < limit ; ++inx )
        {
            Thread  testThread  = allThreads.get( inx );
            while ( testThread.isAlive() )
            {
                try
                {
                    allThreads.get( inx ).join();
                }
                catch ( InterruptedException exc )
                {
                }
            }
        }
        long    duration    = System.currentTimeMillis() - start;
        double  seconds     = duration / 1000.;
        System.out.printf( "duration: %4.3f seconds", seconds );
    }
}
