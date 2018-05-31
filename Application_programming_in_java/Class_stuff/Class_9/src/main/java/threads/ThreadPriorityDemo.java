package threads;

public class ThreadPriorityDemo
{
    private static Thread       mainThread  = Thread.currentThread();
    private static ThreadGroup  mainGroup   = mainThread.getThreadGroup();
    public static void main(String[] args)
    {        
        Thread  childThread = new Thread();
        System.out.println( "Intial state" );
        dumpPriorities( childThread );
        
        int newPri          = mainThread.getPriority() + 1;
        childThread.setPriority( newPri );
        System.out.println( "Changing child priority to " + newPri );
        dumpPriorities( childThread );
        
        int newMaxPri       = mainGroup.getMaxPriority() - 2;
        mainGroup.setMaxPriority( newMaxPri );
        System.out.println( "Changing max priority to " + newMaxPri );
        dumpPriorities( childThread );
        
        newPri = newMaxPri + 1;
        childThread.setPriority( newPri );
        System.out.println( "Changing child priority to " + newPri );
        dumpPriorities( childThread );
        
        newMaxPri = mainThread.getPriority() - 1;
        mainGroup.setMaxPriority( newMaxPri );
        System.out.println( "Changing max priority to " + newMaxPri );
        dumpPriorities( childThread );
        
        newMaxPri = childThread.getPriority() + 1;
        mainGroup.setMaxPriority( newMaxPri );
        System.out.println( "Changing max priority to " + newMaxPri );
        dumpPriorities( childThread );
    }
    
    private static void dumpPriorities( Thread childThread )
    {
        int     threadPriority  = mainThread.getPriority();
        int     groupPriority   = mainThread.getThreadGroup().getMaxPriority();
        int     childPriority   = childThread.getPriority();
        String  fmt             = 
            "Priorities: group max = %d, main = %d, child = %d%n";
        
        System.out.printf( fmt, groupPriority, threadPriority, childPriority );
    }
}
