package cp120.class4.downloadedFiles;

import javax.swing.JOptionPane;

public class StringNumberConversionDemo1
{

    public static void main(String[] args)
    {
        int test    = askInt( "Enter an integer" );
        System.out.println( test );

    }

    private static int askInt( String prompt )
    {
        int     result  = -1;
        String  str     = null;
        try
        {
            str     = JOptionPane.showInputDialog( null, prompt );
            result  = Integer.parseInt( str );
        }
        catch ( NumberFormatException exc )
        {
            String  err = "\"" + str + "\" is not an integer";
            System.out.println( err );
            result = -1;
        }
        return result;
    }
}
