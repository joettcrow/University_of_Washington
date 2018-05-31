package singletons;

public class PressureGuageDemo
{

    public static void main(String[] args)
    {
        PressureGauge   gauge   = PressureGauge.INSTANCE;
        try
        {
            System.out.println( gauge.getPressure() );
        }
        catch ( InterruptedException exc )
        {
        }
    }

}
