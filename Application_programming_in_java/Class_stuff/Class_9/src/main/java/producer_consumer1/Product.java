package producer_consumer1;

public class Product
{
    private final long  workMillisMin;
    private final long  workMillisMax;
    
    public Product( long workMillisMin, long workMillisMax )
    {
        this.workMillisMin = workMillisMin;
        this.workMillisMax = workMillisMax;
    }
    
    public void execute()
    {
        pause();
    }
    
    private void pause()
    {
        long    millisBase  = workMillisMax - workMillisMin;
        long    factor      = (long)(Math.random() * millisBase + .5 );
        long    actual      = factor + workMillisMin;
        try
        {
            Thread.sleep( actual );
        }
        catch ( InterruptedException exc )
        {
        }
    }

}
