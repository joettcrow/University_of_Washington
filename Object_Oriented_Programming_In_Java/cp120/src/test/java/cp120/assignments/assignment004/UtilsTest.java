package cp120.assignments.assignment004;

import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.*;

/**
 * These are test, but they are not fully automated.
 * The operator will need to follow the first dialog prompts
 * @author jcrowley
 */
public class UtilsTest {

    @Test
    public void askIntHappyTest() throws OpCanceledException {
        // For this the user needs to put in a the following invalid values
        // A letter, a value greater than the max and a value less than the min
        // Then they need to put in the right value
        int min = 10;
        int max = 20;
        String message = "For this test put in a letter and hit Ok, \n " +
                "then put in a number less than 10 and hit Ok \n" +
                "then put in a number greater than 10 and hit Ok \n" +
                "then put in a number between 10 and 20 and hit Ok.\n" +
                "Then it will stop";
        JOptionPane.showMessageDialog(null,message);
        int response = Utils.askInt(min, max);
        boolean greaterThan = (response >= min);
        boolean lessThan = (response <= max);
        assertEquals(true, greaterThan && lessThan);
    }

    @Test
    public void opCanceledExceptionTest(){
        // For this the user needs to put in a the following invalid values
        // A letter, a value greater than the max and a value less than the min
        // Then they need to put in the right value
        int min = 10;
        int max = 20;
        boolean thrown = false;
        String message = "For this test just hit Cancel";
        JOptionPane.showMessageDialog(null,message);
        try {
            int response = Utils.askInt(min, max);
        }
        catch (OpCanceledException except){
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void utilsInstantiationTest(){
        new Utils();
    }
}
