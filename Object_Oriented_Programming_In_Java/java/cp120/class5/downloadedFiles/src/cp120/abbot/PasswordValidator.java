package cp120.abbot;

import javax.swing.JOptionPane;

/**
 * A class to show an input dialog and validate the operator input.
 * Its primary use is to demonstrate how to automate a GUI test
 * with Abbot.
 * 
 * The rules for password validation are:
 * <ol>
 * <li>Must be at least nine characters</li>
 * <li>Must contain at least one upper case character</li>
 * <li>Must contain at least one lower case character</li>
 * <li>Must contain at least one digit</li>
 * <li>
 * Must contain at least one of the special characters
 * ^, %, $, &amp; or \
 * </li>
 * </ol>
 * 
 * @author Jack
 */
public class PasswordValidator
{   
    /** The value returned when the operator enters an invalid password. */
    public static final String      INVALID_PASSWORD    = new String();
    
    /** The minimum number of characters in a password. */
    private static final int        MIN_CHARS           = 9;
    
    /** The message displayed to the operator at the start of the operation. */
    private static final String     PASSWORD_MESSAGE    =
        "Please enter your password. It must be at least nine characters,\n"
        + "and include at least one upper and lower case character, one\n"
        + "digit, and one of the characters ^, %, $, & or \\";
    
    /** The message displayed when the operator makes an invalid entry. */
    private static final String     PASSWORD_ERR        = 
        "The password you entered is invalid.";
    
    /** The special characters allowed in a password. */
    private static final String     SPECIAL_CHARS   = "^%$&\\";
    
    /**
     * This constructor is not used, except to improve
     * test coverage.
     */
    public PasswordValidator()
    {
    }
    
    /**
     * Ask the operator to enter a password, then validate it.
     * The rules for validating a password are listed in the 
     * class description.
     * <p>
     * If the operator cancels the operation, null is returned. If
     * the operator enters a valid password, the password will be
     * returned. If the operator enters an invalid password,
     * an error dialog will be displayed and INVALID_PASSWORD will
     * be returned.
     * <p>
     * Note that INVALID_PASSWORD is a flag object; test it for equality
     * using the identity operator (==).
     *
     * @return the password, null or INVALID_PASSWORD, as described above
     */
    public static String getPassword()
    {
        String  str = JOptionPane.showInputDialog( null, PASSWORD_MESSAGE );
        if ( str != null )
        {
            char[]  content = str.toCharArray();
            int     len     = content.length;
            boolean minLen  = false;
            boolean upper   = false;
            boolean lower   = false;
            boolean digit   = false;
            boolean special = false;
            
            if ( len >= MIN_CHARS )
            {
                minLen = true;
                for ( int inx = 0 ; inx < len ; ++inx )
                {
                    char    test    = content[inx];
                    if ( Character.isUpperCase( test ) )
                        upper = true;
                    else if ( Character.isLowerCase( test ) )
                        lower = true;
                    else if ( Character.isDigit(  test ) )
                        digit = true;
                    else if ( SPECIAL_CHARS.contains(  "" + test ) )
                        special = true;
                    else
                        ;
                }
            }
            if ( !(minLen && upper && lower && digit && special) )
            {
                str = INVALID_PASSWORD;
                JOptionPane.showMessageDialog(  null, PASSWORD_ERR );
            }
        }
        
        return str;
    }
}
