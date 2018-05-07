package serialization;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

@SuppressWarnings("unchecked")
public class CachedObjectDemo
{
    private byte[]  objStorage  = null;
    
    public static void main(String[] args)
    {
        new CachedObjectDemo().execute();
    }
    
    private void execute()
    {
        Integer                 obj     = 5;
        String                  ident   = "CachedObject test";
        long                    ttl     = 5000;
        CachedObject<Integer>   cObj    =
            new CachedObject<>( ident, ttl, obj );
        
        pause( 1000 );
        System.out.println( cObj );
        write( cObj );
        pause( 3000 );
        cObj = read();
        System.out.println( cObj );
        pause( 5000 );
        System.out.println( cObj );
    }
    
    private void write( CachedObject<Integer> obj )
    {
        try (
            ByteArrayOutputStream   bStream = new ByteArrayOutputStream();
            ObjectOutputStream oStream = new ObjectOutputStream( bStream );
        )
        {
            oStream.writeObject( obj );
            objStorage = bStream.toByteArray();
        }
        catch ( IOException exc )
        {
            exc.printStackTrace();
            System.exit( 1 );
        }
    }
    
    private CachedObject<Integer> read()
    {
        CachedObject<?> cObj    = null;
        
        if ( objStorage == null )
            throw new NullPointerException();
        try(
            ByteArrayInputStream bStream = 
                new ByteArrayInputStream( objStorage );
            ObjectInputStream oStream = new ObjectInputStream( bStream );
        )
        {
            Object  obj = oStream.readObject();
            if ( !(obj instanceof CachedObject<?>) )
                throw new IOException( "Not a cached object" );
            cObj = (CachedObject<?>)obj;
            
            Object temp = cObj.getCache();
            if ( !(temp instanceof Integer) )
                throw new IOException( "Wrong type" );
        }
        catch ( IOException | ClassNotFoundException exc )
        {
            exc.printStackTrace();
            System.exit( 1 );
        }
        
        return (CachedObject<Integer>)cObj;
    }

    private static void pause( long millis )
    {
        try
        {
            Thread.sleep( millis );
        }
        catch ( InterruptedException exc )
        {
            // I don't really care if I land here
        }
    }
}
