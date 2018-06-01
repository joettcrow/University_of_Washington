package edu.uweo.java2.assignment8.client;

import app.ClientDriver;
import edu.uweo.java2.assignment8.*;
import edu.uweo.java2.assignment8.server.ServerTest;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author jcrowley
 */

public class ClientTest implements Runnable {
    private static final String[] ARGS = new String[0];

    private final Client client = new Client(4885);

    private final static BigDecimal add1 = BigDecimal.valueOf(0.5);
    private final static BigDecimal add2 = BigDecimal.valueOf(-0.7);
    private final static BigDecimal sub1 = BigDecimal.valueOf(0.5);
    private final static BigDecimal sub2 = BigDecimal.valueOf(-0.7);
    private final static BigDecimal mul1 = BigDecimal.valueOf(0.9);
    private final static BigDecimal mul2 = BigDecimal.valueOf(2.0);
    private final static BigDecimal div1 = BigDecimal.valueOf(0.9);
    private final static BigDecimal div2 = BigDecimal.valueOf(3.0);
    private final static AddCommand addCommand = new AddCommand(add1,add2);
    private final static SubCommand subCommand = new SubCommand(sub1,sub2);
    private final static MulCommand mulCommand = new MulCommand(mul1,mul2);
    private final static DivCommand divCommand = new DivCommand(div1,div2);
    private final static ShutdownCommand shutdownCommand = new ShutdownCommand();

    @Test
    public void clientGeneralTest() {
        Thread thread = new Thread(new ServerTest());
        thread.start();

        client.execute(addCommand);
        thread.getState();
        client.execute(subCommand);
        thread.getState();
        client.execute(mulCommand);
        thread.getState();
        client.execute(divCommand);
        thread.getState();
        client.execute(shutdownCommand);
        thread.interrupt();
    }

    @Test
    public void clientWrongPortTest(){
        Thread thread = new Thread(new ServerTest());
        thread.start();
        Client badClient = new Client(8888);
        badClient.execute(addCommand);
        System.out.println("The above line should show a failure");
        client.execute(shutdownCommand);
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    public void run() {
        ClientDriver.main(ARGS);
        client.execute(shutdownCommand);
    }

}