package barrier_demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Task
{
    private static final Logger log =
        LoggerFactory.getLogger( Task.class );

    private static long baseTime    = System.currentTimeMillis();
    
    private final int       ident;
    private final int       pauseMin;
    private final int       pauseMax;

    private final long      createTime;
    private long            startTime;
    private long            endTime;
    
    public Task( int ident, int lowMillis, int highMillis )
    {
        if ( lowMillis < 0 || highMillis < lowMillis )
            throw new IllegalArgumentException( "invalid time range" );
        
        this.ident = ident;
        this.pauseMin = lowMillis;
        this.pauseMax = highMillis;
        createTime = System.currentTimeMillis();
        log.info( "created task {}: {}", ident, createTime - baseTime );
    }
    
    public void execute()
    {
        String  name    = Thread.currentThread().getName();
        startTime = System.currentTimeMillis();
        log.info( "begin task {}: {}", ident, startTime - baseTime );
        pause();
        endTime = System.currentTimeMillis();
        long    duration    = endTime - startTime;
        log.info( "({}) end task: {}: {}; ", name, ident, endTime - baseTime, duration );
    }

    public int getIdent()
    {
        return ident;
    }

    public int getPauseMin()
    {
        return pauseMin;
    }

    public int getPauseMax()
    {
        return pauseMax;
    }

    public long getStartTime()
    {
        return startTime;
    }

    public long getEndTime()
    {
        return endTime;
    }
    
    public long getExecutionDuration()
    {
        return endTime - startTime;
    }
    
    public long getCreateTime()
    {
        return createTime;
    }
    
    public long getBaseTime()
    {
        return baseTime;
    }
    
    private void pause()
    {
        long    millisBase  = pauseMax - pauseMin;
        long    factor      = (long)(Math.random() * millisBase + .5);
        long    actual      = factor + pauseMin;
        try
        {
            Thread.sleep( actual );
        }
        catch ( InterruptedException exc )
        {
            Thread.currentThread().interrupt();
        }
    }
}
