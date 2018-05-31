package edu.uweo.java2.assignment8;

import java.math.BigDecimal;

/**
 * The first operand is divided by the second,
 * and the result is stored in the command's result field.
 * @author jcrowley
 */
public class DivCommand extends AbstractCommand {
    private static final long serialVersionUID = 5086022590910646240L;

    /**
     * Constructor.
     * Following invocation, the operand1 and operand2 properties will be set to 0,
     * and the receiver and result properties will be set to null
     */
    public DivCommand() {
        super();
    }

    /**
     * Constructor.
     * Sets the operand1 and operand2 properties to the given parameter values;
     * sets the receiver and result properties to null.
     * Throws NullPointerException if operand1 or operand2 is null.
     *
     * @param operand1 the first operand value
     * @param operand2 the second operand value
     * @throws NullPointerException if either operand is null
     */
    public DivCommand(BigDecimal operand1, BigDecimal operand2) throws NullPointerException {
        super(operand1, operand2);
    }

    /**
     * Constructor.
     * Sets the operand1 and operand2 properties to the given parameter values;
     * sets the receiver and result properties to null.
     * Throws NullPointerException if operand1 or operand2 is null.
     *
     * @param operand1 the first operand value
     * @param operand2 the second operand value
     * @throws NullPointerException if either operand is null
     */
    public DivCommand(double operand1, double operand2) throws NullPointerException {
        super(BigDecimal.valueOf(operand1), BigDecimal.valueOf(operand2));
    }

    /**
     * This is the abstract execute method required by the Command Pattern.
     */
    public void execute() {
        getReceiver().action(this);
    }

    /**
     * Overrides the toString to return operand1 operation operand2 = result
     * @returnm operand1 operation operand2 = result
     */
    @Override
    public String toString() {
        return getOperand1()+
                " / " +
                getOperand2()+
                " = " +
                getResult();
    }
}
