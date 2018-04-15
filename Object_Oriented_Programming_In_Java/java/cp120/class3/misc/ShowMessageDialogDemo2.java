package misc;

import javax.swing.JOptionPane;

public class ShowMessageDialogDemo2
{
    public static void main(String[] args)
    {
        String  msg = "This is a clever message";
        JOptionPane.showMessageDialog(
            null,   // Parent
            msg,    // Message to display
            "Demo", // Dialog title
            JOptionPane.WARNING_MESSAGE 
                   // Determines icon to display
        );
    }
}
