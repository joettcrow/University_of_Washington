package cp120.assignments.geo_shape;

public class Utils
{
    public static int round( double dNum )
    {
        int rounded = (int)(dNum + .5);
        return rounded;
    }
    
    public static void pause( long millis )
    {
        try
        {
            Thread.sleep( millis );
        }
        catch ( InterruptedException exc )
        {
        }
    }
}
