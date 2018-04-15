//package cp120.abbot;
//
//import java.awt.Component;
//
//import javax.swing.JButton;
//
//import abbot.finder.Matcher;
//
///**
// * Simple demonstration of the use of the Matcher class
// * in the Abbot library.
// * <p>
// * Components from a GUI (potentially <em>all</em> the components
// * in a GUI) are passed to the <em>matches()</em>. The method returns
// * <em>true</em> when it detects the target has been found.
// * </p>
// * @author jack
// * @see #matches(Component)
// */
//public class MatcherDemo implements Matcher
//{
//
//    /**
//     * Examines a GUI component, looking for a JButton
//     * with a label of OK.
//     *
//     * @param comp  The component to examine.
//     *
//     * @return true if the component is the target, false otherwise
//     */
//    public boolean matches( Component comp )
//    {
//        boolean found   = false;
//        if ( comp instanceof JButton )
//        {
//            String  label   = ((JButton)comp).getText();
//            found = label.equals( "OK" );
//        }
//
//        return found;
//    }
//}
