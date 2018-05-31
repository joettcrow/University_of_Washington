package producer_consumer1;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public enum ShutdownMgr
{
    INSTANCE;
    
    private volatile boolean shutdown   = false;
    
    private final PropertyChangeSupport pcs = 
        new PropertyChangeSupport( this );
    
    public synchronized void 
    addPropertyChangeListener( PropertyChangeListener listener )
    {
        this.pcs.addPropertyChangeListener(listener);
    }

    public synchronized void 
    removePropertyChangeListener( PropertyChangeListener listener ) 
    {
        this.pcs.removePropertyChangeListener(listener);
    }
    
    public synchronized boolean isShuttingDown()
    {
        return shutdown;
    }
    
    public synchronized boolean isRunning()
    {
        return !shutdown;
    }
    
    public synchronized void setShutdown( boolean shutdown )
    {
        boolean oldValue    = this.shutdown;
        this.shutdown = shutdown;
        this.shutdown = shutdown;
        pcs.firePropertyChange("shutDown", oldValue, this.shutdown );
    }
}
