package app;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.scg.domain.ClientAccount;

public class Assignment05Test
{
    private static final File       clientFile  =
            new File( InitLists.CLIENT_FILE );
    private static final String[]   ARGS        = new String[0];
    @Test
    public void testMain()
    {
        try
        {
            InitLists.main( ARGS );
            Assignment05.main( ARGS );
        }
        catch ( IOException  exc )
        {
            exc.printStackTrace();
        }
    }

//    @Test ( expected = IOException.class )
//    public void testFileNotFound()
//            throws IOException, ClassNotFoundException
//    {
//        if ( clientFile.exists() )
//            clientFile.delete();
//        Assignment05.main( ARGS );
//    }

//    @Test ( expected = IOException.class )
//    public void testNotAList()
//            throws IOException, ClassNotFoundException
//    {
//        try ( FileOutputStream fstream = new FileOutputStream( clientFile );
//              ObjectOutputStream ostream = new ObjectOutputStream( fstream );
//        )
//        {
//            ostream.writeObject( "This is not a list" );
//        }
//        catch ( IOException exc )
//        {
//            exc.printStackTrace();
//        }
//        Assignment05.main( ARGS );
//    }

//    @Test ( expected = IOException.class )
//    public void testEmptyList()
//            throws IOException, ClassNotFoundException
//    {
//        try ( FileOutputStream fstream = new FileOutputStream( clientFile );
//              ObjectOutputStream ostream = new ObjectOutputStream( fstream );
//        )
//        {
//            List<ClientAccount> emptyList   = new ArrayList<>();
//            ostream.writeObject( emptyList );
//        }
//        catch ( IOException exc )
//        {
//            exc.printStackTrace();
//        }
//        Assignment05.main( ARGS );
//    }

//    @Test ( expected = IOException.class )
//    public void testWrongListType()
//            throws IOException, ClassNotFoundException
//    {
//        try ( FileOutputStream fstream = new FileOutputStream( clientFile );
//              ObjectOutputStream ostream = new ObjectOutputStream( fstream );
//        )
//        {
//            List<String>    stringList  = new ArrayList<>();
//            stringList.add( " not type ClientAccount" );
//            ostream.writeObject( stringList );
//        }
//        catch ( IOException exc )
//        {
//            exc.printStackTrace();
//        }
//        Assignment05.main( ARGS );
//    }

//    @Test ( expected = IOException.class )
//    public void testClassNotFound()
//            throws IOException, ClassNotFoundException
//    {
//        try ( FileOutputStream fstream = new FileOutputStream( clientFile );
//              ObjectOutputStream ostream = new ObjectOutputStream( fstream );
//        )
//        {
//            List<TestClassNotFound> list    = new ArrayList<>();
//            list.add( new TestClassNotFound() );
//            ostream.writeObject( list );
//        }
//        catch ( IOException exc )
//        {
//            exc.printStackTrace();
//        }
//        Assignment05.main( ARGS );
//    }

    private static class TestClassNotFound
            implements Serializable
    {
        private static final long serialVersionUID = 1L;
        @SuppressWarnings("unused")
        private int anyData = -1;
    }
}
