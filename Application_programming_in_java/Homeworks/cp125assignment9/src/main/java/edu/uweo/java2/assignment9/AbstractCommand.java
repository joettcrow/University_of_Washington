package edu.uweo.java2.assignment9;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * This is the superclass of all command objects;
 * its behavior is dictated by the Command Pattern.
 * It has the following properties:
 * @author jcrowley
 */
public abstract class AbstractCommand implements Serializable {

    private static final long serialVersionUID = 6577411532539011475L;
    private Receiver receiver;
    private BigDecimal operand1;
    private BigDecimal operand2;
    private BigDecimal result;

    /**
     * Constructor.
     * Following invocation, the operand1 and operand2 properties will be set to 0,
     * and the receiver and result properties will be set to null
     */
    public AbstractCommand(){
        this.operand1 = BigDecimal.valueOf(0);
        this.operand2 = BigDecimal.valueOf(0);
        this.receiver = null;
        this.result = null;
    }

    /**
     * Constructor.
     * Sets the operand1 and operand2 properties to the given parameter values;
     * sets the receiver and result properties to null.
     * Throws NullPointerException if operand1 or operand2 is null.
     * @param operand1 the first operand value
     * @param operand2 the second operand value
     * @throws NullPointerException if either operand is null
     */
    public AbstractCommand(BigDecimal operand1, BigDecimal operand2) throws NullPointerException{
        if (operand1 == null){
            throw new NullPointerException("Operand1 cannot be null");
        }
        else if (operand2 == null){
            throw new NullPointerException("Operand2 cannot be null");
        }
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.receiver = null;
        this.result = null;
    }

    /**
     * This is the abstract execute method required by the Command Pattern.
     */
    public abstract void execute();

    /**
     * Gets operand1.
     *
     * @return Value of operand1.
     */
    public BigDecimal getOperand1() {
        return operand1;
    }

    /**
     * Gets receiver.
     *
     * @return Value of receiver.
     */
    public Receiver getReceiver() {
        return receiver;
    }

    /**
     * Gets result.
     *
     * @return Value of result.
     */
    public BigDecimal getResult() {
        return result;
    }

    /**
     * Gets operand2.
     *
     * @return Value of operand2.
     */
    public BigDecimal getOperand2() {
        return operand2;
    }

    /**
     * Sets new operand1.
     *
     * @param operand1 New value of operand1.
     */
    public void setOperand1(BigDecimal operand1) {
        this.operand1 = operand1;
    }

    /**
     * Sets new receiver.
     *
     * @param receiver New value of receiver.
     */
    public void setReceiver(Receiver receiver) {
        this.receiver = receiver;
    }

    /**
     * Sets new result.
     *
     * @param result New value of result.
     */
    public void setResult(BigDecimal result) {
        this.result = result;
    }

    /**
     * Sets new operand2.
     *
     * @param operand2 New value of operand2.
     */
    public void setOperand2(BigDecimal operand2) {
        this.operand2 = operand2;
    }
}
