package serialization;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SerializingCollectionsDemo
{
    public static void main(String[] args)
    {
        List<Integer>   list    =
            Stream.iterate( 1, i -> i + 1 )
                .limit( 20 )
                .collect( Collectors.toList() );
        byte[]          array   = write( list );
        @SuppressWarnings("unchecked")
        List<Integer>   newList = (List<Integer>)read( array, Integer.class );
        newList.forEach( System.out::println );
    }

    private static byte[] write( List<Integer> list )
    {
        ByteArrayOutputStream   endStream   = null;
        try(
            ByteArrayOutputStream  bStream = new ByteArrayOutputStream();
            ObjectOutputStream oStream = new ObjectOutputStream( bStream );
        )
        {
            endStream = bStream;
            oStream.writeObject( list );
        }
        catch ( IOException exc )
        {
            exc.printStackTrace();
            System.exit( 1 );
        }
        
        return endStream.toByteArray();
    }
    
    private static List<?> read( byte[] array, Class<?> clazz )
    {
        List<?> retList = null;
        try(
            ByteArrayInputStream bStream = new ByteArrayInputStream( array );
            ObjectInputStream oStream = new ObjectInputStream( bStream );
        )
        {
            Object  obj = oStream.readObject();
            if ( !(obj instanceof List<?> ) )
                throw new IOException( "Not a list" );
            retList = (List<?>)obj;
            if ( retList.isEmpty() )
                throw new IOException( "Unknown list type" );
            if ( !clazz.isAssignableFrom( retList.get( 0 ).getClass() ) )
                throw new IOException( "Wrong list type" );
        }
        catch ( IOException | ClassNotFoundException exc )
        {
            exc.printStackTrace();
            System.exit( 1 );
        }
        
        return retList;
    }
}
