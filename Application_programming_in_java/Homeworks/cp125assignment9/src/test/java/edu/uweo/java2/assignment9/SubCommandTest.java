package edu.uweo.java2.assignment9;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author jcrowley
 */

public class SubCommandTest {
    private final Receiver receiver = new Receiver();

    @Test
    public void defaultConstructorTest() {
        SubCommand subCommand = new SubCommand();
        assertEquals(BigDecimal.ZERO,subCommand.getOperand1());
        assertEquals(BigDecimal.ZERO,subCommand.getOperand2());
        assertNull(subCommand.getReceiver());
        assertNull(subCommand.getResult());
    }

    @Test
    public void parameterBigConstructorTest() {
        SubCommand subCommand = new SubCommand(
                BigDecimal.ONE,
                BigDecimal.TEN,
                0,
                1);
        assertEquals(BigDecimal.ONE,subCommand.getOperand1());
        assertEquals(BigDecimal.TEN,subCommand.getOperand2());
        assertNull(subCommand.getReceiver());
        assertNull(subCommand.getResult());
    }

    @Test
    public void parameterDoubleConstructorTest() {
        SubCommand subCommand = new SubCommand(
                1.0,
                10.0,
                0,
                1);
        assertEquals(BigDecimal.valueOf(1.0),subCommand.getOperand1());
        assertEquals(BigDecimal.valueOf(10.0),subCommand.getOperand2());
        assertNull(subCommand.getReceiver());
        assertNull(subCommand.getResult());
    }

    @Test
    public void executeTest() {
        SubCommand subCommand = new SubCommand(
                BigDecimal.ONE,
                BigDecimal.TEN,
                0,
                1);
        subCommand.setReceiver(receiver);
        subCommand.execute();
        assertEquals(BigDecimal.valueOf(-9), subCommand.getResult());

    }

    @Test
    public void toStringTest() {
        SubCommand subCommand = new SubCommand(
                BigDecimal.ONE,
                BigDecimal.TEN,
                0,
                1);
        subCommand.setReceiver(receiver);
        subCommand.execute();
        String subCommandString = "1 - 10 = -9";
        assertEquals(subCommandString, subCommand.toString());

    }
}