package app;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * @author jcrowley
 */

public class TemperatureMonitor {
    private double celsius;

    private PropertyChangeSupport pcSupport = new PropertyChangeSupport(this);

    public void addPropertyChangeListener(PropertyChangeListener listener){
        pcSupport.addPropertyChangeListener(listener);
    }

    public void removeProperyChangeListener(PropertyChangeListener listener){
        pcSupport.removePropertyChangeListener(listener);
    }

    public double getCelsius() {
        return celsius;
    }

    public void setCelsius(double celsius) {
        double oldVal = this.celsius;
        this.celsius = celsius;
        pcSupport.firePropertyChange("celsius",oldVal,celsius);
    }
}
