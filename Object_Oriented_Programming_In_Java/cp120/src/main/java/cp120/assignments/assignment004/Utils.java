package cp120.assignments.assignment004;

import javax.swing.*;

/**
 * Utility class for checking numbers
 * @author jcrowley
 */
public class Utils {
    /**
     * Asks the user to put an integer into the field
     * Checks if it is between the provided values
     * @param min integer for the minimum value
     * @param max integer for the maximum value
     * @return The integer the user entered that is between min and max
     * @throws OpCanceledException returns nothing in this case
     */
    public static int askInt( int min, int max )
    throws OpCanceledException
    {
        int i;
        String str;
        String prompt = String.format("Enter an integer between %d and %d", min,max);
        String message;
        while (true){
            String pane = JOptionPane.showInputDialog(null, prompt);
            if (pane != null){
                str = pane;
            }
            else {
                throw new OpCanceledException();
            }

            try {
                i = Integer.parseInt(str);
                if ((i <= max) && (i >= min)){
                    break;
                }
                else {
                    message = String.format("\"%s\" is not valid", str);
                    JOptionPane.showMessageDialog(null, message);
                }
            }
            catch (NumberFormatException err){
                message = String.format("\"%s\" is not valid", str);
                JOptionPane.showMessageDialog(null, message);
            }

        }
        return i;
    }
}
