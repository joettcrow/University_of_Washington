//package serialization;
//
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.io.ObjectOutputStream;
//import java.io.Serializable;
//
//public class SeralizedDataFormatDemo
//{
//    public static void main(String[] args)
//    {
//        int     lineLen = 10;
//        RawData data    = new RawData();
//        byte[]  bArray  = write( data );
//
//        int     limit   = bArray.length;
//        System.out.println( limit );
//        for ( int inx = 0 ; inx < limit ; inx += lineLen )
//        {
//            for ( int jnx = 0 ; jnx < lineLen ; ++jnx )
//            {
//                int target  = inx + jnx;
//                if ( target < limit )
//                    System.out.printf( "%02x ", bArray[target] );
//                else
//                    System.out.print( "   " );
//            }
//
//            for ( int jnx = 0 ; jnx < lineLen ; ++jnx )
//            {
//                int target  = inx + jnx;
//                if ( target < limit )
//                {
//                    byte    bbb = bArray[target];
//                    if ( bbb > 32 && bbb < 127 )
//                        System.out.printf( "%c", (char)bbb );
//                    else
//                        System.out.print( "." );
//                }
//                else
//                    System.out.print( " " );
//            }
//            System.out.println();
//        }
//    }
//
//    private static byte[] write( RawData data )
//    {
//        ByteArrayOutputStream bStream = new ByteArrayOutputStream();
//        try(
//            ObjectOutputStream oStream =
//                new ObjectOutputStream( bStream );
//        )
//        {
//            oStream.writeObject( data );
//            oStream.flush();
//            oStream.close();
//        }
//        catch ( IOException exc )
//        {
//            exc.printStackTrace();
//            System.exit( 1 );
//        }
//
//        return bStream.toByteArray();
//    }
//
//    private static class RawData
//        implements Serializable
//    {
//        private static final long serialVersionUID = 0x749f900eb2ab857eL;
//
//        @SuppressWarnings("unused")
//        private final int       numCopies   = 23;
//        @SuppressWarnings("unused")
//        private final String    name        = "spot";
//
//        public RawData()
//        {
//            System.out.printf( "%x%n", serialVersionUID );
//        }
//    }
//}
