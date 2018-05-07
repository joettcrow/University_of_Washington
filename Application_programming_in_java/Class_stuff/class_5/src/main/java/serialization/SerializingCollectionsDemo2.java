package serialization;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SerializingCollectionsDemo2
{
    public static void main(String[] args)
    {
        Set<Integer>   set    =
            Stream.iterate( 1, i -> i + 1 )
                .limit( 20 )
                .collect( Collectors.toSet() );
        byte[]          array   = write( set );
        @SuppressWarnings("unchecked")
        Set<Integer>   newSet = (Set<Integer>)read( array, Integer.class );
        newSet.forEach( System.out::println );
    }

    private static byte[] write( Set<Integer> set )
    {
        ByteArrayOutputStream   endStream   = null;
        try(
            ByteArrayOutputStream  bStream = new ByteArrayOutputStream();
            ObjectOutputStream oStream = new ObjectOutputStream( bStream );
        )
        {
            endStream = bStream;
            oStream.writeObject( set );
        }
        catch ( IOException exc )
        {
            exc.printStackTrace();
            System.exit( 1 );
        }
        
        return endStream.toByteArray();
    }
    
    private static Set<?> read( byte[] array, Class<?> clazz )
    {
        Set<?> retSet = null;
        try(
            ByteArrayInputStream bStream = new ByteArrayInputStream( array );
            ObjectInputStream oStream = new ObjectInputStream( bStream );
        )
        {
            Object  obj = oStream.readObject();
            if ( !(obj instanceof Set<?> ) )
                throw new IOException( "Not a set" );
            retSet = (Set<?>)obj;
            if ( retSet.isEmpty() )
                throw new IOException( "Unknown set type" );
            if ( !clazz.isAssignableFrom( retSet.toArray()[0].getClass() ) )
                throw new IOException( "Wrong set type" );
        }
        catch ( IOException | ClassNotFoundException exc )
        {
            exc.printStackTrace();
            System.exit( 1 );
        }
        
        return retSet;
    }
}
