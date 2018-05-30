package edu.uweo.java2.assignment8;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author jcrowley
 */

public class MulCommandTest {

    @Test
    public void defaultConstructorTest() {
        MulCommand mulCommand = new MulCommand();
        assertEquals(BigDecimal.ZERO,mulCommand.getOperand1());
        assertEquals(BigDecimal.ZERO,mulCommand.getOperand2());
        assertNull(mulCommand.getReceiver());
        assertNull(mulCommand.getResult());
    }

    @Test
    public void parameterBigConstructorTest() {
        MulCommand mulCommand = new MulCommand(BigDecimal.ONE,BigDecimal.TEN);
        assertEquals(BigDecimal.ONE,mulCommand.getOperand1());
        assertEquals(BigDecimal.TEN,mulCommand.getOperand2());
        assertNull(mulCommand.getReceiver());
        assertNull(mulCommand.getResult());
    }

    @Test
    public void parameterDoubleConstructorTest() {
        MulCommand mulCommand = new MulCommand(1.0,10.0);
        assertEquals(BigDecimal.valueOf(1.0),mulCommand.getOperand1());
        assertEquals(BigDecimal.valueOf(10.0),mulCommand.getOperand2());
        assertNull(mulCommand.getReceiver());
        assertNull(mulCommand.getResult());
    }

    @Test
    public void executeTest() {
        MulCommand mulCommand = new MulCommand(BigDecimal.ONE,BigDecimal.TEN);
        Receiver receiver = new Receiver(mulCommand);
        mulCommand.setReceiver(receiver);
        mulCommand.execute();
        assertEquals(BigDecimal.valueOf(10), mulCommand.getResult());

    }

    @Test
    public void toStringTest() {
        MulCommand mulCommand = new MulCommand(BigDecimal.ONE,BigDecimal.TEN);
        Receiver receiver = new Receiver(mulCommand);
        mulCommand.setReceiver(receiver);
        mulCommand.execute();
        String mulCommandString = "1 * 10 = 10";
        assertEquals(mulCommandString, mulCommand.toString());

    }
}