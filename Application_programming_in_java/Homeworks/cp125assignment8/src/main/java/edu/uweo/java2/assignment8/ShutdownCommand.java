package edu.uweo.java2.assignment8;

/**
 * @author jcrowley
 */

public class ShutdownCommand extends AbstractCommand{
    /**
     * This is the abstract execute method required by the Command Pattern.
     */
    @Override
    public void execute() {
        System.out.println("Shutting Down");
    }
}
