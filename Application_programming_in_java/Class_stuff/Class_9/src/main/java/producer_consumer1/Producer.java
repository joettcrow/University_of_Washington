package producer_consumer1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Producer implements Runnable
{
    private static final Logger log =
        LoggerFactory.getLogger( Producer.class );
    
    private boolean         shutdown    = false;
    private ProductQueue    queue       = ProductQueue.INSTANCE;
    private int             count       = 0;
    
    public Producer()
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
            Product product = new Product( 250, 1000 );
            product.execute();
            if ( queue.add( product ) )
                log.info( "    {} items produced", ++count );
        }
    }
    
    @Override
    public String toString()
    {
        StringBuilder   bldr    = new StringBuilder();
        bldr.append( "Produced: " ).append( count );
        return bldr.toString();
    }
}
