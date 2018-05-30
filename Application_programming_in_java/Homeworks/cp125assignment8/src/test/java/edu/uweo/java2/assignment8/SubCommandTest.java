package edu.uweo.java2.assignment8;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author jcrowley
 */

public class SubCommandTest {

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
        SubCommand subCommand = new SubCommand(BigDecimal.ONE,BigDecimal.TEN);
        assertEquals(BigDecimal.ONE,subCommand.getOperand1());
        assertEquals(BigDecimal.TEN,subCommand.getOperand2());
        assertNull(subCommand.getReceiver());
        assertNull(subCommand.getResult());
    }

    @Test
    public void parameterDoubleConstructorTest() {
        SubCommand subCommand = new SubCommand(1.0,10.0);
        assertEquals(BigDecimal.valueOf(1.0),subCommand.getOperand1());
        assertEquals(BigDecimal.valueOf(10.0),subCommand.getOperand2());
        assertNull(subCommand.getReceiver());
        assertNull(subCommand.getResult());
    }

    @Test
    public void executeTest() {
        SubCommand subCommand = new SubCommand(BigDecimal.ONE,BigDecimal.TEN);
        Receiver receiver = new Receiver(subCommand);
        subCommand.setReceiver(receiver);
        subCommand.execute();
        assertEquals(BigDecimal.valueOf(-9), subCommand.getResult());

    }

    @Test
    public void toStringTest() {
        SubCommand subCommand = new SubCommand(BigDecimal.ONE,BigDecimal.TEN);
        Receiver receiver = new Receiver(subCommand);
        subCommand.setReceiver(receiver);
        subCommand.execute();
        String subCommandString = "1 - 10 = -9";
        assertEquals(subCommandString, subCommand.toString());

    }
}