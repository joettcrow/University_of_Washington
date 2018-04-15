package misc;

import javax.swing.JOptionPane;

public class ShowConfirmDialogDemo1
{

    public static void main(String[] args)
    {
        String  msg     = "Are you sure?";
        int     result  =
            JOptionPane.showConfirmDialog( null, msg );
        if ( result == JOptionPane.YES_OPTION )
            System.out.println( "Formatting disk" );
        else
            System.out.println( "Operation canceled" );
    }

}
