package threads;

public class InterruptedNoWaitDemo implements Runnable
{

    public static void main(String[] args)
    {
        new Thread( new InterruptedNoWaitDemo() ).start();
    }

    public void run()
    {
        Thread  thread  = Thread.currentThread();
        long    start   = System.currentTimeMillis();
        thread.interrupt();
        
        synchronized ( thread )
        {
            wait( thread, 1000 );
            long    delta   = System.currentTimeMillis() - start;
            System.out.println( delta );
        }
    }
    
    private static void wait( Object mutex, long millis )
    {
        try
        {
            mutex.wait( millis );
        }
        catch ( InterruptedException exc )
        {
            System.out.println( "interrupted" );
        }
    }
}
