package threads;

public class QuickDemo implements Runnable
{
    private static final String SPACES      = "                    ";
    
    private final String        indent;
    
    public static void main(String[] args)
    {
        QuickDemo   demo1   = new QuickDemo( 0 );
        QuickDemo   demo2   = new QuickDemo( 4 );
        QuickDemo   demo3   = new QuickDemo( 8 );
        
        Thread      thr1    = new Thread( demo1, "Demo 1" );
        Thread      thr2    = new Thread( demo2, "Demo 2" );
        Thread      thr3    = new Thread( demo3, "Demo 3" );
        
        thr1.start();
        thr2.start();
        thr3.start();
        
        pause( 5000 );
        thr1.interrupt();
        thr2.interrupt();
        thr3.interrupt();
    }
    
    public QuickDemo( int indent )
    {
        this.indent = SPACES.substring( 0, indent );
//        System.out.printf( "%d >>%s<<%n", indent, this.indent );
    }

    @Override
    public void run()
    {
        Thread  thisThread  = Thread.currentThread();
        String  threadName  = thisThread.getName();
        System.out.printf( "%s starting %s%n", indent, threadName );
        
        try
        {
            while ( !thisThread.isInterrupted() )
            {
                randomPause();
                System.out.printf( "%s%s%n", indent, threadName );
            }
        }
        catch ( InterruptedException exc )
        {
        }
        System.out.printf( "%s ending %s%n", indent, threadName );
    }
    
    private void randomPause() throws InterruptedException
    {
        long    pause   = (long)(Math.random() * 1500) + 500;
        Thread.sleep( pause );
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
