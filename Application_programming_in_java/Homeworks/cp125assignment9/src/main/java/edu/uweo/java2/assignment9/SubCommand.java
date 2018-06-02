package edu.uweo.java2.assignment9;

import java.math.BigDecimal;

/**
 * The second operand is subtracted from the first,  and the result is stored in the command's
 * result field.
 * @author jcrowley
 */
public class SubCommand extends AbstractCommand {
    private static final long serialVersionUID = 1130740805334352540L;

    /**
     * Constructor.
     * Following invocation, the operand1 and operand2 properties will be set to 0,
     * and the receiver and result properties will be set to null
     */
    public SubCommand() {
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
    public SubCommand(BigDecimal operand1, BigDecimal operand2) throws NullPointerException {
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
    public SubCommand(double operand1, double operand2) throws NullPointerException{
        super(BigDecimal.valueOf(operand1),BigDecimal.valueOf(operand2));
    }

    /**
     * This is the abstract execute method required by the Command Pattern.
     */
    public void execute(){
        getReceiver().action(this);
    }

    /**
     * Overrides the toString to return operand1 operation operand2 = result
     * @return operand1 operation operand2 = result
     */
    @Override
    public String toString() {
        return getOperand1() +
                " - " +
                getOperand2() +
                " = " +
                getResult();
    }
}
