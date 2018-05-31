package threads;

public class UncaughtExceptionHandlerDemo
{
    public static void main(String[] args)
    {
        final String    msg = "Caught by DefaultUncaughtExceptionHandler";
        Thread.setDefaultUncaughtExceptionHandler(
            (t, e) -> System.out.println( msg )
        );
        
        Thread  catchInCatchBlockThread = 
            new Thread( new CatchInCatchBlock() );
        catchInCatchBlockThread.start();
        join( catchInCatchBlockThread );
        
        Thread  catchInCatchUEHThread   = 
            new Thread( new CatchInUEH() );
        catchInCatchUEHThread.start();
        join( catchInCatchUEHThread );
        
        Thread  catchInCatchDUEThread   = 
            new Thread( new CatchInDUEH() );
        catchInCatchDUEThread.start();
        join( catchInCatchDUEThread );
    }
    
    private static void join( Thread thread )
    {
        try
        {
            thread.join();
        }
        catch ( InterruptedException exc )
        {
            
        }
    }
    
    /**
     * Catch exception in catch block
     * 
     * @author jstra
     */
    private static class CatchInCatchBlock
        implements Runnable
    {
        /**
         * Throws an exception inside a try block. Expected to be
         * caught in catch block.
         */
        @Override
        public void run()
        {
            try
            {
                throw new RuntimeException();
            }
            catch ( Exception exc )
            {
                System.out.println( "Caught by catch block" );
            }
        }
    }
    
    /**
     * Catch exception in UncaughtExceptionHandler
     * 
     * @author jstra
     */
    private static class CatchInUEH
        implements Runnable
    {
        /**
         * Sets an UncaughtExcaughtExceptionHandler on this thread, 
         * then throws an exception(without a try block). Expected to be
         * caught by UncaughtExceptionHandler
         */
        @Override
        public void run()
        {
            final String    msg = "Caught by UncaughtExceptionHandler";
            Thread.currentThread().setUncaughtExceptionHandler(
                (t, e) -> System.out.println( msg )
            );
            throw new RuntimeException();
        }
    }
    
    /**
     * Catch exception in default UncaughtExceptionHandler
     * 
     * @author jstra
     */
    private static class CatchInDUEH
        implements Runnable
    {
        /**
         * Immediately throws an exception (without catch block or 
         * UncaughtExceptionHandler). Excepted to be caught by
         * DefaultUncaughtExceptionHandler.
         */
        @Override
        public void run()
        {
            throw new RuntimeException();
        }
    }
}
