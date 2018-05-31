package singletons;

public class Thermostat
{
    private static Thermostat   thermostat  = null;
    
    private Thermostat()
    {
    }
    
    public synchronized static Thermostat instance()
    {
        if ( thermostat == null )
            thermostat = new Thermostat();
        return thermostat;
    }
}
