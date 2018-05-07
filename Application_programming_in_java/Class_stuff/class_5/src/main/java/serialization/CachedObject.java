package serialization;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CachedObject <T extends Serializable>
    implements Serializable
{
    private static final long serialVersionUID = -5101053775931535686L;

    private static final Logger log     =
        LoggerFactory.getLogger( CachedObject.class );
    
    private transient long      begin;
    private transient long      ttl;
    private final T             cache;
    private final String        ident;
    
    public CachedObject( String ident, long ttl, T cache )
    {
        begin = System.currentTimeMillis();
        this.ident = ident;
        this.ttl = ttl;
        this.cache = cache;
    }
    
    public long getTimeToLive()
    {
        long    elapsed = System.currentTimeMillis() - begin;
        long    rval    = ttl - elapsed;
        if ( rval < 0 )
            rval = 0;
        
        return rval;
    }
    
    public boolean isExpired()
    {
        boolean rcode   = getTimeToLive() == 0;
        return rcode;
    }
    
    public T getCache()
    {
        T   obj = isExpired() ? null : cache;
        return obj;
    }
    
    @Override
    public String toString()
    {
        StringBuilder   bldr    = new StringBuilder( ident );
        bldr.append( " ttl=" ).append( getTimeToLive() );
        return bldr.toString();
    }
    
    private void writeObject( ObjectOutputStream outStr )
        throws IOException
    {
        long    timeLeft    = getTimeToLive();
        outStr.defaultWriteObject();
        outStr.writeLong( timeLeft );
        log.info( "\"{}\" saved; ttl = {}", ident, timeLeft );
    }
    
    private void readObject( ObjectInputStream inStr )
        throws IOException, ClassNotFoundException
    {
        inStr.defaultReadObject();
        ttl = inStr.readLong();
        begin = System.currentTimeMillis();
        log.info( "\"{}\" read; ttl = {}", ident, ttl );
    }
}
