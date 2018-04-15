package cp120.class4.downloadedFiles;

import java.io.FileNotFoundException;

public class TryCatchDemo1
{
    public static void main(String[] args)
    {
        execute();
    }

    private static void execute()
    {
        try
        {
            openFile( "Test.tmp" );
        }
        catch ( FileNotFoundException exc )
        {
            exc.printStackTrace();
            System.exit( 1 );
        }
    }
    
    private static void openFile( String name ) throws FileNotFoundException
    {
        throw new FileNotFoundException( name + " not found" );
    }
    
    public void methodA() throws Exception
    {
        methodB();
    }
    
    public void methodB() throws Exception
    {
        methodC();
    }
    
    public void methodC() throws Exception
    {
        throw new Exception();
    }
}
