package edu.uweo.java2.assignment9;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author jcrowley
 */

public class AbstractCommandTest {

    @Test
    public void abstractConstructorFirstNullTest() {
        try {
            AddCommand command = new AddCommand(
                    null,
                    BigDecimal.ONE,
                    0,
                    1);
        } catch (NullPointerException exp){
            assertEquals("Operand1 cannot be null",exp.getMessage());
        }
    }

    @Test
    public void abstractConstructorSecondNullTest() {
        try {
            AddCommand command = new AddCommand(
                    BigDecimal.ONE,
                    null,
                    0,
                    1);
        } catch (NullPointerException exp){
            assertEquals("Operand2 cannot be null",exp.getMessage());
        }
    }

    @Test
    public void setOperand1() {
        AddCommand command = new AddCommand();
        command.setOperand1(BigDecimal.ONE);
        assertEquals(BigDecimal.ONE,command.getOperand1());
        assertEquals(BigDecimal.ZERO,command.getOperand2());
    }

    @Test
    public void setOperand2() {
        AddCommand command = new AddCommand();
        command.setOperand2(BigDecimal.ONE);
        assertEquals(BigDecimal.ONE,command.getOperand2());
        assertEquals(BigDecimal.ZERO,command.getOperand1());
    }

    @Test
    public void setWorkMillisMinTest() {
        AddCommand command = new AddCommand();
        command.setWorkMillisMin(1000);
        assertEquals(1000,command.getWorkMillisMin());
    }

    @Test
    public void setWorkMillisMaxTest() {
        AddCommand command = new AddCommand();
        command.setWorkMillisMax(10000);
        assertEquals(10000,command.getWorkMillisMax());
    }
}