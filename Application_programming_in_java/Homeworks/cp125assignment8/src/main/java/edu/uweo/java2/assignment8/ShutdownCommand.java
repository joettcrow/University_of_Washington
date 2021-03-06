package edu.uweo.java2.assignment8;

/**
 * Shutdown command, this also does things
 * @author jcrowley
 */
public class ShutdownCommand extends AbstractCommand{
    private static final long serialVersionUID = -6326870781903768629L;

    /**
     * This is the abstract execute method required by the Command Pattern.
     */
    @Override
    public void execute() {
        System.out.println("Shutting Down");
    }
}
