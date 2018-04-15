package cp120.class4.downloadedFiles;

public class StringCompareDemo
{

    public static void main(String[] args)
    {
        String[]    testPairs   =
        { "Zebra", "Aardvark", "Zulu", "antEater", "200", "30", "100", "-100" };
        for ( int inx = 0 ; inx < testPairs.length ; inx += 2 )
            doCompare( testPairs[inx], testPairs[inx + 1] );
    }

    private static void doCompare( String str1, String str2 )
    {
        int     result  = str1.compareTo( str2 );
        String  val     = "";
        
        if ( result < 0 )
            val = " is less than ";
        else if ( result > 0 )
            val = " is greater than ";
        else
            val = " is equal to ";
        
        System.out.println( str1 + val + str2  );
    }
}
