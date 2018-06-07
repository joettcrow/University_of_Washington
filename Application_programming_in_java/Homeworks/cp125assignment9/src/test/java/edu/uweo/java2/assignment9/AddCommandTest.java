package edu.uweo.java2.assignment9;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author jcrowley
 */

public class AddCommandTest {
    private final Receiver receiver = new Receiver();

    @Test
    public void defaultConstructorTest() {
        AddCommand addCommand = new AddCommand();
        assertEquals(BigDecimal.ZERO,addCommand.getOperand1());
        assertEquals(BigDecimal.ZERO,addCommand.getOperand2());
        assertNull(addCommand.getReceiver());
        assertNull(addCommand.getResult());
    }

    @Test
    public void parameterBigConstructorTest() {
        AddCommand addCommand = new AddCommand(
                BigDecimal.ONE,
                BigDecimal.TEN,
                0,
                1);
        assertEquals(BigDecimal.ONE,addCommand.getOperand1());
        assertEquals(BigDecimal.TEN,addCommand.getOperand2());
        assertNull(addCommand.getReceiver());
        assertNull(addCommand.getResult());
    }

    @Test
    public void parameterDoubleConstructorTest() {
        AddCommand addCommand = new AddCommand(
                1.0,
                10.0,
                0,
                1);
        assertEquals(BigDecimal.valueOf(1.0),addCommand.getOperand1());
        assertEquals(BigDecimal.valueOf(10.0),addCommand.getOperand2());
        assertNull(addCommand.getReceiver());
        assertNull(addCommand.getResult());
    }

    @Test
    public void executeTest() {
        AddCommand addCommand = new AddCommand(
                BigDecimal.ONE,
                BigDecimal.TEN,
                0,
                1);
        addCommand.setReceiver(receiver);
        addCommand.execute();
        assertEquals(BigDecimal.valueOf(11), addCommand.getResult());

    }

    @Test
    public void toStringTest() {
        AddCommand addCommand = new AddCommand(
                BigDecimal.ONE,
                BigDecimal.TEN,
                0,
                1);
        addCommand.setReceiver(receiver);
        addCommand.execute();
        String addCommandString = "1 + 10 = 11";
        assertEquals(addCommandString, addCommand.toString());

    }
}