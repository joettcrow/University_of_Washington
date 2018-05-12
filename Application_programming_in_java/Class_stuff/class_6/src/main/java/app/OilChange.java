package app;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * @author jcrowley
 */

public class OilChange {
    private PropertyChangeSupport pcSupport = new PropertyChangeSupport(this);

    private boolean needed = false;

    public synchronized void addPropertyChangeListener( PropertyChangeListener listener){
        pcSupport.addPropertyChangeListener(listener);

    }

    public synchronized void removeProperyChangeListener(PropertyChangeListener listener){
        pcSupport.removePropertyChangeListener(listener);
    }

    public void setNeeded(boolean needed){
        boolean oldVal = this.needed;
        this.needed = needed;
        pcSupport.firePropertyChange("needed",oldVal,needed);
    }

    public boolean getNeeded(){
        return needed;
    }
}
