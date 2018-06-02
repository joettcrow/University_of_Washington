package edu.uweo.java2.assignment9;


import java.io.Serializable;

/**
 * This class will encapsulate a receiver object, as described by the command pattern.
 * @author jcrowley
 */
public class Receiver implements Serializable {

    private static final long serialVersionUID = 4319989422249122646L;

    /**
     * The second operand is added to the first,
     * and the result is stored in the command's result field.
     * @param command the DddCommand to use
     */
    public void action( AddCommand command )
    {
        command.setResult(command.getOperand1().add(command.getOperand2()));
    }

    /**
     * The second operand is subtracted from the first,
     * and the result is stored in the command's result field.
     * @param command the SubCommand to use
     */
    public void action( SubCommand command )
    {
        command.setResult(command.getOperand1().subtract(command.getOperand2()));
    }

    /**
     * The first operand is multiplied by the second,
     * and the result is stored in the command's result field.
     * @param command the MulCommand to use
     */
    public void action( MulCommand command )
    {
        command.setResult(command.getOperand1().multiply(command.getOperand2()));
    }

    /**
     * The first operand is divided by the second,
     * and the result is stored in the command's result field.
     * @param command the DivCommand to use
     */
    public void action( DivCommand command )
    {
        command.setResult(command.getOperand1().divide(command.getOperand2()));
    }

}
