package threads;

public class InterruptedNoSleepDemo implements Runnable
{

    public static void main(String[] args)
    {
        new Thread( new InterruptedNoSleepDemo() ).start();
    }

    public void run()
    {
        Thread  thread  = Thread.currentThread();
        long    start   = System.currentTimeMillis();
        thread.interrupt();
        
        pause( 1000 );
        long    delta   = System.currentTimeMillis() - start;
        System.out.println( delta );
    }
    
    private static void pause( long millis )
    {
        try
        {
            Thread.sleep( millis );
        }
        catch ( InterruptedException exc )
        {
            System.out.println( "interrupted" );
        }
    }
}
