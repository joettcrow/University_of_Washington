package app;

public class AdHoc
{
    public static void main( String[] args )
    {
        Thread  thread1 = new Thread( new TaskExecutor(), "Demo 1" );
        Thread  thread2 = new Thread( new TaskExecutor(), "Demo 2" );
        
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
        }
    }
    
    private static class TaskExecutor implements Runnable
    {
        public void run()
        {
            Thread  thisThread  = Thread.currentThread();
            String  name        = thisThread.getName() + ": ";
            while ( !thisThread.isInterrupted() )
            {
                pause( 1000 );
                boolean interrupted = Thread.currentThread().isInterrupted();
                System.out.println( name + interrupted );
            }
        }
    }
}
