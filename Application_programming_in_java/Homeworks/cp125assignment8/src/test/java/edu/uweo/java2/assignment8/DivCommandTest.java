package edu.uweo.java2.assignment8;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author jcrowley
 */

public class DivCommandTest {
    private final Receiver receiver = new Receiver();

    @Test
    public void defaultConstructorTest() {
        DivCommand divCommand = new DivCommand();
        assertEquals(BigDecimal.ZERO,divCommand.getOperand1());
        assertEquals(BigDecimal.ZERO,divCommand.getOperand2());
        assertNull(divCommand.getReceiver());
        assertNull(divCommand.getResult());
    }

    @Test
    public void parameterBigConstructorTest() {
        DivCommand divCommand = new DivCommand(BigDecimal.ONE,BigDecimal.TEN);
        assertEquals(BigDecimal.ONE,divCommand.getOperand1());
        assertEquals(BigDecimal.TEN,divCommand.getOperand2());
        assertNull(divCommand.getReceiver());
        assertNull(divCommand.getResult());
    }

    @Test
    public void parameterDoubleConstructorTest() {
        DivCommand divCommand = new DivCommand(1.0,10.0);
        assertEquals(BigDecimal.valueOf(1.0),divCommand.getOperand1());
        assertEquals(BigDecimal.valueOf(10.0),divCommand.getOperand2());
        assertNull(divCommand.getReceiver());
        assertNull(divCommand.getResult());
    }

    @Test
    public void executeTest() {
        DivCommand divCommand = new DivCommand(BigDecimal.TEN,BigDecimal.ONE);
        divCommand.setReceiver(receiver);
        divCommand.execute();
        assertEquals(BigDecimal.valueOf(10), divCommand.getResult());

    }

    @Test
    public void toStringTest() {
        DivCommand divCommand = new DivCommand(BigDecimal.TEN,BigDecimal.ONE);
        divCommand.setReceiver(receiver);
        divCommand.execute();
        String divCommandString = "10 / 1 = 10";
        assertEquals(divCommandString, divCommand.toString());

    }
}