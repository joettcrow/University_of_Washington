package cp120.class4.downloadedFiles;

import javax.swing.JOptionPane;

public class WritingExceptionsDemo
{
    @SuppressWarnings( "unused" )
    public static void main(String[] args)
    {
        try
        {
            String  str = getInput();
            // ...
        }
        catch ( OpCanceledException exc )
        {
            // ...
        }
    }

    private static String getInput()
        throws OpCanceledException
    {
        String  msg     = "Enter data";
        String  data    = 
            JOptionPane.showInputDialog( null, msg );
        if ( data == null )
            throw new OpCanceledException();
        
        return data;
    }
}
