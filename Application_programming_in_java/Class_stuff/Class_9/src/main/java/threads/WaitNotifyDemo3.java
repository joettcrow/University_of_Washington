package threads;

public class WaitNotifyDemo3
{
    private Object  monitorA    = new Object();
    private Object  monitorB    = new Object();
    public static void main(String[] args)
    {
        new WaitNotifyDemo3().execute();
    }

    private void execute()
    {
        new Thread( new Thread1() ).start();
        new Thread( new Thread2() ).start();
    }
    
    private class Thread1 implements Runnable
    {
        public void run()
        {
            println( "Thread1 waiting on monitorA" );
            synchronized ( monitorA )
            {
                try
                {
                    monitorA.wait();
                }
                catch ( InterruptedException exc )
                {
                    println( "Thread1 interrupted" );
                }
                println( "Thread1 waking" );
            }
            
            println( "Thread1 waiting on monitorB" );
            synchronized ( monitorB )
            {
                try
                {
                    monitorB.wait( 2000 );
                }
                catch ( InterruptedException exc )
                {
                    println( "Thread1 interrupted" );
                }
                println( "Thread1 waking" );
            }
            println( "Thread1 done" );
        }
    }

    private class Thread2 implements Runnable
    {
        public void run()
        {
            println( "    Thread2 pausing" );
            pause( 10 );
            
            println( "    Thread2 signaling monitorB" );
            synchronized ( monitorB )
            {
                monitorB.notify();
            }
            
            println( "    Thread2 pausing" );
            pause( 10 );
            
            println( "    Thread2 signaling monitorA" );
            synchronized ( monitorA )
            {
                monitorA.notify();
            }
            println( "    Thread2 done" );
        }
    }
    
    private static void println( String str )
    {
        System.out.println( str );
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
