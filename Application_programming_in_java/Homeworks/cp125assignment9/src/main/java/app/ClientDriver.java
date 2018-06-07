package app;

import edu.uweo.java2.assignment9.*;
import edu.uweo.java2.assignment9.client.Client;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * This application will use the Client class to send four commands to the server:
 * @author jcrowley
 */
public class ClientDriver implements Runnable{
    private final static BigDecimal add1 = BigDecimal.valueOf(0.5);
    private final static BigDecimal add2 = BigDecimal.valueOf(-0.7);
    private final static BigDecimal sub1 = BigDecimal.valueOf(0.5);
    private final static BigDecimal sub2 = BigDecimal.valueOf(-0.7);
    private final static BigDecimal mul1 = BigDecimal.valueOf(0.9);
    private final static BigDecimal mul2 = BigDecimal.valueOf(2.0);
    private final static BigDecimal div1 = BigDecimal.valueOf(0.9);
    private final static BigDecimal div2 = BigDecimal.valueOf(3.0);

    private Client client;
    private String name;

    private final static int addMin = 250;
    private final static int addMax = 1000;
    private final static int subMin = 125;
    private final static int subMax = 500;
    private final static int mulMin = 100;
    private final static int mulMax = 600;
    private final static int divMin = 250;
    private final static int dibMax = 500;

    private final static AddCommand addCommand = new AddCommand(add1,add2,addMin,addMax);
    private final static SubCommand subCommand = new SubCommand(sub1,sub2,subMin,subMax);
    private final static MulCommand mulCommand = new MulCommand(mul1,mul2,mulMin,mulMax);
    private final static DivCommand divCommand = new DivCommand(div1,div2,divMin,dibMax);

    /**
     * Main method for the driver
     * @param args can be empty
     */
    public static void main(String[] args){
        int port = 4885;
        Client client = new Client(port);

        List<Thread> threads = new ArrayList<Thread>();
        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(new ClientDriver(port,"Client Thread" + i));
            threads.add(t);
            t.start();
        }

        for (Thread t: threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }

        client.execute(new ShutdownCommand());
    }

    /**
     *
     * ClientDriver constructor for threading
     * @param port the port to run on
     * @param name the name of the thread
     */
    public ClientDriver(int port, String name){
        this.name = name;
        this.client = new Client(port);
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
    @Override
    public void run() {
        client.execute(addCommand);
        client.execute(subCommand);
        client.execute(divCommand);
        client.execute(mulCommand);

    }
}
