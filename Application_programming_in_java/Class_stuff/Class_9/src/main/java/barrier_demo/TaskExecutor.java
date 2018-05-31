package barrier_demo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TaskExecutor implements Runnable
{
    private static final Logger log =
        LoggerFactory.getLogger( TaskExecutor.class );

    private boolean             isRunning   = true;
    private final int           startInx;
    private final int           endInx;
    private final List<Task>    allTasks;
    
    public TaskExecutor( List<Task> tasks, int start, int end )
    {
        allTasks = tasks;
        startInx = start;
        endInx = end;
    }
    
    @Override
    public void run()
    {
        Thread  thisThread  = Thread.currentThread();
        
        log.info( "beginning {}", thisThread.getName() );
        for ( int inx = startInx ; isRunning && inx < endInx ; ++inx )
            allTasks.get( inx ).execute();

    }

    public void setIsRunning( boolean running )
    {
        this.isRunning = running;
    }
    
    public boolean getIsRunning()
    {
        return isRunning;
    }
}
