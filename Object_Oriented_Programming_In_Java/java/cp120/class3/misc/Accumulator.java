package misc;

public class Accumulator
{
    private double  accum;
    
    public Accumulator()
    {
        accum = 0;
    }
    
    public void add( double val )
    {
        accum = accum + val;
    }
    
    public double getCurrentValue()
    {
        return accum;
    }
}
