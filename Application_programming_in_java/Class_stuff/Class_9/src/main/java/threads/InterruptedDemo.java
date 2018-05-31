package threads;

public class InterruptedDemo
{

    public static void main(String[] args)
    {
        Thread  mainThread  = Thread.currentThread();
        mainThread.interrupt();
        
        // A call to interrupted() will return true,
        // and will also reset the interrupt flag.
        System.out.println( Thread.interrupted() );
        System.out.println( Thread.interrupted() );
        
        mainThread.interrupt();
        
        // A call to isInterrupted() will return true;
        // it will not reset the interrupt flag
        System.out.println( mainThread.isInterrupted() );
        System.out.println( mainThread.isInterrupted() );
    }

}
