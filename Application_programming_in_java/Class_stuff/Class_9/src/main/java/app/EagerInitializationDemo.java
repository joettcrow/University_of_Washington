package app;

public class EagerInitializationDemo
{
    private final int hash;
    
    public EagerInitializationDemo()
    {
        hash = calculateHashCode();
    }
    
    private int calculateHashCode()
    {
        return 13;
    }
}
