package producer_consumer1;

import java.util.LinkedList;
import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum ProductQueue
{
    INSTANCE;    
    private static final Logger log =
        LoggerFactory.getLogger( Consumer.class );
    
    private static int  MAX_SIZE        = 10;
    
    private Queue<Product>  products        = new LinkedList<>();
    private boolean         shutdown        = false;
    private int             addWaitCount    = 0;
    private int             remWaitCount    = 0;
    
    private ProductQueue()
    {
        ShutdownMgr.INSTANCE.addPropertyChangeListener( 
            e -> shutdown = (Boolean)(e.getNewValue())
        );
    }
    
    public boolean add( Product product )
    {
        if ( product == null )
            throw new NullPointerException();
        
        boolean added   = false;
        synchronized ( products )
        {
            while ( !shutdown && !added )
            {
                if ( products.size() < MAX_SIZE )
                {
                    products.offer( product );
                    products.notify();
                    added = true;
                }
                else
                {
                    try
                    {
                        ++addWaitCount;
                        log.info( "Wait on full queue" );
                        products.wait( 100 );
                    }
                    catch ( InterruptedException exc )
                    {
                    }
                }
            }
        }
        return added;
    }
    
    public Product remove()
    {
        Product product = null;
        
        synchronized ( products )
        {
            while ( !shutdown && product == null )
            {
                product = products.poll();
                if ( product == null )
                {
                    try
                    {
                        ++remWaitCount;
                        log.info( "Wait on empty queue" );
                        products.wait( 100 );
                    }
                    catch ( InterruptedException exc )
                    {
                    }
                }
            }
        }
        
        return product;
    }
    
    @Override
    public String toString()
    {
        StringBuilder   bldr    = new StringBuilder();
        bldr.append( "ProductQueue: " );
        bldr.append( "addWaits = " ).append( addWaitCount )
            .append( ", remWaits = " ).append( remWaitCount );
        return bldr.toString();
    }
}
