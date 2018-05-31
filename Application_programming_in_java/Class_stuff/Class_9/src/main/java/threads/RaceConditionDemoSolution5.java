package threads;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class RaceConditionDemoSolution5
{
    private static final BlockingQueue<Task> tasks  = 
        new LinkedBlockingQueue<>();
    private static boolean          running         = true;
    
    public static void main( String[] args )
    {
        for ( int inx = 0 ; inx < 50 ; ++inx )
            tasks.add( new Task() );
        
        Thread  thread1 = 
            new Thread( new TaskExecutor(), "Executor 1" );
        Thread  thread2 = 
            new Thread( new TaskExecutor(), "Executor 2" );
        thread1.start();
        thread2.start();
        
        pause( 3000 );
        
        running = false;
        thread1.interrupt();
        thread2.interrupt();
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
    
    private static class TaskExecutor implements Runnable
    {
        public void run()
        {
            while ( running )
            {
                try
                {
                    Task    next    = tasks.poll( 1, TimeUnit.SECONDS );
                    if ( next != null )
                        next.execute();
                }
                catch ( InterruptedException exc )
                {
                }
            }
        }
    }

    private static class Task
    {
        private static final Object monitor = new Object();
        
        private static int    nextNum = 0;
        private final int     num;
        
        public Task()
        {
            synchronized ( monitor )
            {
                num = nextNum++;
            }
        }
        
        public void execute()
        {
            String  name    = Thread.currentThread().getName();
            System.out.println( name + " Executing: " + num );
            pause( 2 );
        }
    }
}
