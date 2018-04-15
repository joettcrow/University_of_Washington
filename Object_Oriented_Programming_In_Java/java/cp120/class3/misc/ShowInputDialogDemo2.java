package misc;

import javax.swing.JOptionPane;

public class ShowInputDialogDemo2
{
    public static void main(String[] args)
    {
        String  prompt  = "Enter your name";
        String  result  = null;
        String  name    =
            JOptionPane.showInputDialog( null, prompt );
        if ( name == null )
            result = "Operation Canceled";
        else if ( name.isEmpty() )
            result = "Forgot your name?";
        else
            result = "Your name is " + name;
        JOptionPane.showMessageDialog( null,  result );
    }

}
