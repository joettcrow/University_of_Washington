//package cp120.abbot;
//
//import javax.swing.JButton;
//import javax.swing.JOptionPane;
//import javax.swing.JTextField;
//
//import abbot.finder.ComponentFinder;
//import abbot.finder.ComponentSearchException;
//import abbot.finder.Matcher;
//import abbot.finder.matchers.ClassMatcher;
//import abbot.tester.ComponentTester;
//import junit.extensions.abbot.ComponentTestFixture;
//
///**
// * A JUnit test case to exercise the PasswordValidator class. This test
// * may also be run as an application. It uses one environment variable:
// * <code>SHORTTEST</code>. If this variable exists, some of the testing will
// * be abbreviated.
// * <p>
// * The principal purpose of this tester, and the PasswordValidator class,
// * is to demonstrate how to use Abbot to automate GUI testing.
// *
// * @author Jack
// * @see PasswordValidator#getPassword()
// */
//public class PasswordValidatorTest extends ComponentTestFixture
//{
//    /** Indicates that a valid response is expected. */
//    private static final int    EXPECT_VALID    = 0;
//
//    /** Indicates that an invalid response is expected. */
//    private static final int    EXPECT_INVALID  = 1;
//
//    /** Indicates that a cancel response is expected. */
//    private static final int    EXPECT_CANCEL   = 2;
//
//    /** The special characters that are allowed in a password. */
//    private static final String SPECIAL_CHARS   = "^%$&\\";
//
//    /** Abbot object to manage testing. */
//    private ComponentTester tester;
//
//    /** Abbot object used to search for GUI components. */
//    private ComponentFinder finder;
//
//    /** The dialog currently being managed. */
//    private JOptionPane     dialog;
//
//    /** A lambda used to find dialogs. */
//    private final Matcher   dialogMatcher   =
//        (c)-> {return c.getClass().equals(JOptionPane.class)
//        && ((JOptionPane)c).isVisible();};
//
//    /** A lambda used to find the text field in an input dialog. */
//    private Matcher textMatcher     = new ClassMatcher( JTextField.class );
//
//    /** A lambda used to find the OK button in an input or message dialog. */
//    private Matcher okMatcher       =
//            (c)-> {return c.getClass().equals(JButton.class)
//            && ((JButton)c).getText().equals( "OK" );};
//
//    /** A lambda used to find the cancel button in an input dialog. */
//    private Matcher cancelMatcher   =
//            (c)-> {return c.getClass().equals(JButton.class)
//            && ((JButton)c).getText().equals( "Cancel");};
//
//    /** The text field currently being exercised. */
//    private JTextField  textField;
//
//    /** The OK button currently being exercised. */
//    private JButton     okButton;
//
//    /** The cancel button currently being exercised. */
//    private JButton     cancelButton;
//
//    /** Indicates the test should be run in <em>short</em> mode. */
//    private static boolean     shortTest   = false;
//
//    /**
//     * Entry point to run this test as an application rather than
//     * a JUnit test. Really just kicks off JUnit, anyway.
//     *
//     * @param args command line arguments; not used
//     */
////    public static void main(String[] args)
////    {
////        TestHelper.runTests( args, PasswordValidatorTest.class );
////    }
//
//    /**
//     * Instantiates a new password validator test.
//     *
//     * @param name the name of the test;
//     */
//    public PasswordValidatorTest( String name )
//    {
//        super( name );
//    }
//
//    /**
//     * Prepares the testing environment prior to the initiation of testing.
//     *
//     * @see junit.framework.TestCase#setUp()
//     */
//    protected void setUp()
//    {
//        finder = getFinder();
//        tester = new ComponentTester();
//        tester.actionDelay( 1500 );
//
//        if ( System.getenv( "SHORTTEST" ) != null )
//            shortTest = true;
//    }
//
//    /**
//     * Cleans up the testing environment after testing.
//     * @see junit.framework.TestCase#tearDown()
//     */
//    protected void tearDown()
//    {
//        // Default JUnit test runner keeps references to Tests for its
//        // lifetime, so TestCase fields won't be GC'd for the lifetime of the
//        // runner.
//        tester = null;
//        finder = null;
//    }
//
//    /**
//     * Entry point for testing the PasswordValidator class.
//     */
//    public void testValidatorDialog()
//    {
//        // Miscellaneous strings that should be validated by
//        // PasswordValidator.getPassword()
//        String[]    miscValidStrings    =
//        {
//            "Ab0%zzzzz",
//            "Ab0%zzzzzzz",
//            "zAzbz0%zz",
//            "zzzzzAb0%",
//        };
//
//        // Specific, invalid strings to use to exercise
//        // PasswordValidator.getPassword().
//        String[]    invalidStrings      =
//        {
//            "Ab0%zzzz",  // too short
//            "Ab0zzzzzz", // missing special char
//            "AB%%zzzzz", // missing digit
//            "ab0%zzzzz", // missing upper case char
//            "AB0%ZZZZZ", // missing lower case char
//        };
//
//        // Validate behavior when the get password operation is canceled.
//        checkCancel();
//
//        // Positive testing
//        for ( String str : miscValidStrings )
//            checkNonCancel( EXPECT_VALID, str );
//
//        // Positive testing; adding any digit to an otherwise valid password
//        // should pass.
//        for( int inx = 0 ; inx < 10 ; ++inx )
//            checkNonCancel( EXPECT_VALID, "Ab%zzzzzz" + inx );
//
//        // Positive testing; adding valid special character to an otherwise
//        // valid password should pass.
//        int limit   = SPECIAL_CHARS.length();
//        for ( int inx = 0 ; inx < limit ; ++inx )
//        {
//            char    test    = SPECIAL_CHARS.charAt(  inx );
//            checkNonCancel( EXPECT_VALID, "Ab0zzzzzz" + test );
//        }
//
//        // Negative testing
//        for ( String str : invalidStrings )
//            checkNonCancel( EXPECT_INVALID, str );
//
//        // Negative testing; make sure any invalid, printable character
//        // in the UTF-7 character set will fail validation.
//        // Note: The first non-control, non-whitespace char is 33
//        limit = shortTest ? 38 : 128;
//        for ( char inx = 33 ; inx < limit ; ++inx )
//        {
//            boolean testChar    =
//                !Character.isLetter( inx )
//                && !SPECIAL_CHARS.contains( "" + inx )
//                && !Character.isDigit( inx )
//                && !Character.isWhitespace( inx );
//
//            if ( testChar )
//                checkNonCancel( EXPECT_INVALID, "Ab0zzzzzz" + inx );
//        }
//    }
//
//    /**
//     * Test default constructor. The default constructor is not used;
//     * this test is purely for improving test coverage.
//     */
//    public void testDefaultConstructor()
//    {
//        new PasswordValidator();
//    }
//
//    /**
//     * Validate the behavior of getPassword() when the operator
//     * cancels the operation.
//     * <ul>
//     * <li>null should be returned</li>
//     * </ul>
//     */
//    private void checkCancel()
//    {
//        try
//        {
//            DialogExecutor  exec    = new DialogExecutor( EXPECT_CANCEL, "" );
//            showModalDialog( exec );
//
//            trialSetup();
//            tester.actionClick( cancelButton );
//        }
//        catch ( ComponentSearchException exc )
//        {
//            exc.printStackTrace();
//            System.exit( 1 );
//        }
//    }
//
//    /**
//     * Validate the behavior of getPassword() when the operator
//     * OKs the operation.
//     * <ul>
//     * <li>
//     * If the expected state is valid, the test is complete
//     * </li>
//     * <li>
//     * If the expected state is <em>invalid,</em> the validator will post
//     * a message dialog, so:
//     * <ul  style="list-style-type:square">
//     * <li>Find the message dialog</li>
//     * <li>Find the OK button in the message dialog</li>
//     * <li>Activate the OK button</li>
//     * </ul>
//     * </li>
//     * </ul>
//     *
//     * @param state     the expected state of the operation after the
//     *                  test is performed; valid values are
//     *                  EXPECT_VALID and EXPECT_INVALID.
//     * @param password  the password to pass to the validator
//     */
//    private void checkNonCancel( int state, String password )
//    {
//        try
//        {
//            // Show the dialog
//            DialogExecutor  exec    = new DialogExecutor( state, password );
//            showModalDialog( exec );
//
//            // setup internal state
//            trialSetup();
//
//            // give focus to the text field in the dialog
//            tester.focus( textField );
//
//            // feed the given password to the text field
//            tester.actionKeyString( password );
//
//            // poke the OK button
//            tester.actionClick( okButton );
//
//            if ( state == EXPECT_INVALID )
//            {
//                pause( 500 ); // So operator can see what's happening
//
//                // get the message dialog
//                JOptionPane errDialog   =
//                    (JOptionPane)finder.find( dialogMatcher );
//
//                // get the OK button in the message dialog
//                JButton dismissButton   =
//                    (JButton)finder.find( errDialog, okMatcher );
//
//                // click the OK button in the message dialog
//                tester.actionClick( dismissButton );
//            }
//        }
//        catch ( ComponentSearchException exc )
//        {
//            // We get here if the message dialog or OK button we're
//            // looking for can't be found.
//            exc.printStackTrace();
//            System.exit( 1 );
//        }
//    }
//
//    /**
//     * Sets up the test state. This includes:
//     * <ul>
//     * <li>Finding the input dialog</li>
//     * <li>Finding the text field in the input dialog</li>
//     * <li>Finding the OK button in the input dialog</li>
//     * <li>Finding the cancel button in the input dialog</li>
//     * </ul>
//     */
//    private void trialSetup()
//    {
//        try
//        {
//            dialog = (JOptionPane)finder.find(  dialogMatcher );
//            textField = (JTextField)finder.find( dialog, textMatcher );
//            okButton = (JButton)finder.find( dialog, okMatcher );
//            cancelButton = (JButton)finder.find( dialog, cancelMatcher );
//        }
//        catch ( ComponentSearchException exc )
//        {
//            exc.printStackTrace();
//            System.exit( 1 );
//        }
//    }
//
//    /**
//     * Pause for the given number of milliseconds.
//     * This method may be called after initiating a GUI operation.
//     * It is isolated to a single method so we don't have to
//     * add the try/catch block on every pause.
//     *
//     * @param millis the millis
//     */
//    private void pause( int millis )
//    {
//        try
//        {
//            Thread.sleep( millis );
//        }
//        catch ( InterruptedException exc)
//        {
//        }
//    }
//
//    /**
//     * Drives the test GUI. Encapsulates the logic to:
//     * <ol>
//     * <li>post the getPassword() input dialog;</li>
//     * <li>enter a given string into the input dialog;</li>
//     * <li>push either the OK or cancel button in the input dialog;</li>
//     * <li>validate the result
//     * </ol>
//     *
//     * @author Jack
//     */
//    private class DialogExecutor implements Runnable
//    {
//
//        /** True if this test is expecting a valid response. */
//        private final boolean   validExpected;
//
//        /** True if the operator is expected to cancel the operation. */
//        private final boolean   cancelExpected;
//
//        /** The expected password. */
//        private final String    expectedPassword;
//
//        /**
//         * Instantiates a new dialog executor.
//         *
//         * @param expState
//         *     the expected state of the operation on test completion;
//         *     valid values are EXPECT_VALID, EXPECT_INVALID
//         *     and EXPECT_CANCEL
//         * @param expPassword
//         *     the password to feed to the input dialog
//         */
//        public
//        DialogExecutor( int expState, String expPassword  )
//        {
//            switch ( expState )
//            {
//            case EXPECT_VALID:
//                validExpected = true;
//                cancelExpected = false;
//                expectedPassword = expPassword;
//                break;
//            case EXPECT_CANCEL:
//                validExpected = false;
//                cancelExpected = true;
//                expectedPassword = null;
//                break;
//            case EXPECT_INVALID:
//                validExpected = false;
//                cancelExpected = false;
//                expectedPassword = PasswordValidator.INVALID_PASSWORD;
//                break;
//            default:
//                throw new IllegalArgumentException( "test malfunction" );
//            }
//        }
//
//        /* (non-Javadoc)
//         * @see java.lang.Runnable#run()
//         */
//        public void run()
//        {
//            final String    cancelExpErr    = "Cancel expected, not received";
//            final String    invalidExpErr   = "Invalid expected, not received";
//            final String    passwordErr     = "Wrong password returned";
//
//            final String  invPassword   = PasswordValidator.INVALID_PASSWORD;
//            String  actPassword         = PasswordValidator.getPassword();
//            System.out.println( "Result = " + actPassword );
//
//            // The value returned for <em>invalid password</em> is a
//            // flag object; it must be tested for equality using the
//            // identity operator (==).
//            if ( cancelExpected )
//                assertNull( cancelExpErr, actPassword );
//            else if ( !validExpected )
//                assertTrue( invalidExpErr, actPassword == invPassword );
//            else
//                assertEquals( passwordErr, expectedPassword, actPassword );
//        }
//    }
//}
