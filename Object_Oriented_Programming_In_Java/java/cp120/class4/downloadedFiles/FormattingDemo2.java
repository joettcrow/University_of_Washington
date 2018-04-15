package cp120.class4.downloadedFiles;

public class FormattingDemo2
{
    public static void main(String[] args)
    {
        boolean bVal    = true;
        int     iVal1   = 21;
        int     iVal2   = 2;
        String  sVal    = "WRONG";
        double  dVal    = 22.1;
        
        String  bStr    = String.format( "The answer is %b%n", bVal );
        String  iStr    =
            String.format( "%d * %d = %d%n", iVal1, iVal2, iVal1 * iVal2 );
        String  sStr    = String.format( "The solution is %s%n", sVal );
        String  dStr    = String.format( "Area = %f%n", dVal * Math.PI );
        
        System.out.print( bStr );
        System.out.print( iStr );
        System.out.print( sStr );
        System.out.print( dStr );
    }

}
