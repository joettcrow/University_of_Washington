package misc;

import java.io.File;
import java.net.Socket;

public class NullDemo1
{
    public static void main(String[] args)
    {
        NullDemo1   demo    = new NullDemo1();
        System.out.println( demo.stripPrefix( "spot" ) );
        demo.execute();
    }

    // Strips the prefix from a string; prefix
    // is presently a single character.
    private String stripPrefix( String str )
    {
        if ( str == null )
            throw new IllegalArgumentException( "str may not be null" );
        String  result  = str.substring( 1 );
        return result;
    }
    
    private File    file = null;
    private Socket  sock = null;
    private void execute()
    {
        if ( !openFile() )
            System.out.println( "open file error" );
        else if ( !openConnection() )
            System.out.println( "open connection failure" );
        else if ( !process() )
            System.out.println( "process failure" );
        else
            System.out.println( "processing complete" );
        
        if ( file != null )
            close( file );
        if ( sock != null )
            close( sock );
    }
    
    private void close( Object obj )
    {
    }
    
    private boolean process()
    {
        return false;
    }
    
    private boolean openFile()
    {
        return false;
    }
    
    private boolean openConnection()
    {
        return false;
    }
}
