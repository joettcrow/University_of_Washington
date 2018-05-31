package threads;

import java.util.ArrayList;
import java.util.List;

public class RaceConditionDemo2
{
    private static final    List<Task>  tasks   = new ArrayList<>();
    
    public static void main(String[] args)
    {
        for ( int inx = 0 ; inx < 10 ; ++inx )
        {
            tasks.add( new Task( Task.NOOP ) );
            tasks.add( new Task( Task.LOW ) );
            tasks.add( new Task( Task.HIGH ) );
        }
        
        Thread  thread1 = 
            new Thread( new TaskExecutor(), "Executer 1" );
        Thread  thread2 = 
            new Thread( new TaskExecutor(), "Executer 2" );
        thread1.start();
        thread2.start();
        
        pause( 3000 );
        
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
            // Interrupted status is reset when this exception is thrown,
            // so put it back in the interrupted state.
            Thread.currentThread().interrupt();
        }
    }
    
    private static class TaskExecutor implements Runnable
    {
        public void run()
        {
            Thread  thisThread  = Thread.currentThread();
            while ( !thisThread.isInterrupted() )
            {
                pause( 50 );
                process();
            }
        }
        
        public void process()
        {
            Task    max     = null;
            Task    min     = null;
            int     inx     = 0;
            while ( max == null && inx < tasks.size() )
            {
                Task    next    = tasks.get( inx );
                int     pri     = next.getPri();
                if ( pri == Task.HIGH )
                    max = tasks.remove( inx );
                else if ( pri == Task.NOOP )
                    ;
                else if ( min == null )
                    min = next;
                else
                    ;
                ++inx;  
            }
                
            if ( max != null )
                max.execute();
            else if ( min != null )
            {
                tasks.remove( min );
                min.execute();
            }
            else
                ;
        }
    }

    private static class Task
    {
        public static final int HIGH    = 10;
        public static final int LOW     = 5;
        public static final int NOOP    = 0;
        
        private final int       pri;
        
        public Task( int pri )
        {
            this.pri = pri;
        }
        
        public int getPri()
        {
            return pri;
        }
        
        public void execute()
        {
            String  name    = Thread.currentThread().getName();
            System.out.println( name + " Executing: " + pri );
            pause( 2 );
        }
    }
}
