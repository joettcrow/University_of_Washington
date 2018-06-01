package edu.uweo.java2.assignment8.server;


import edu.uweo.java2.assignment8.client.ClientTest;
import org.junit.Test;

import java.io.IOException;

/**
 * @author jcrowley
 */
public class ServerTest implements Runnable {
    private static final String[] ARGS = new String[0];

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
        Server.main(ARGS);
    }

//    @Test
//    public void serverGeneralTest() {
//        Thread thread = new Thread(new ClientTest());
//        Server server = new Server(4885);
//        thread.start();
//        try {
//            server.execute();
//        } catch (IOException e) {
//            System.out.println(e.getStackTrace());
//
//        } catch (ClassNotFoundException e) {
//            System.out.println(e.getException());
//        }
//        thread.interrupt();
//    }
}