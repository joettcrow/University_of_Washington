package producer_consumer1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Consumer implements Runnable
{
    private static final Logger log =
        LoggerFactory.getLogger( Producer.class );
    
    private boolean         shutdown    = false;
    private ProductQueue    queue       = ProductQueue.INSTANCE;
    private int             count       = 0;
    
    public Consumer()
    {
        ShutdownMgr.INSTANCE.addPropertyChangeListener( 
            e -> shutdown = (Boolean)(e.getNewValue())
        );
    }

    @Override
    public void run()
    {
        while ( !shutdown )
        {
            Product product = queue.remove();
            if ( product != null )
            {
                product.execute();
                log.info( "        {} items consumed", ++count );
            }
        }
    }
    
    @Override
    public String toString()
    {
        StringBuilder   bldr    = new StringBuilder();
        bldr.append( "Consumed: " ).append( count );
        return bldr.toString();
    }
}
