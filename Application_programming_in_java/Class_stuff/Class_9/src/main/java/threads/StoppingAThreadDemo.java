package threads;

public class StoppingAThreadDemo
{
    private static volatile boolean  isRunning   = true;
    
    public static void main(String[] args)
    {
        String  indent  = "       *** ";
        Thread  thread  = new Thread( new Alarm(), "Alarm thread" );
        System.out.println( indent + "starting alarm thread" );
        thread.start();
        
        System.out.println( indent + "main thread sleeping" );
        pause( 10000 );
        
        System.out.println( indent + "shutting down" );
        isRunning = false;
        thread.interrupt();
    }

    private static class Alarm implements Runnable
    {
        public void run()
        {
            System.out.println( "Alarm thread starting" );
            while ( isRunning )
            {
                System.out.println( "!!!! Alarm thread alarming" );
                pause( 3000 );
            }
            System.out.println( "Alarm thread terminating" );
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
            // Don't really care
        }
    }
}
