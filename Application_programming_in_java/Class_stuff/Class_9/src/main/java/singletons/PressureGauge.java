package singletons;

public enum PressureGauge
{
    INSTANCE;
    
    public synchronized double getPressure()
        throws InterruptedException
    {
        Double  pressure    = null;
        
        // normally would sample device
        // this might include I/O blocking
        pressure = 32.7;
        
        return pressure;
    }
}
