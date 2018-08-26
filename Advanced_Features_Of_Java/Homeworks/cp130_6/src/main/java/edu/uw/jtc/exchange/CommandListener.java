package edu.uw.jtc.exchange;

import edu.uw.ext.framework.exchange.StockExchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author jcrowley
 */
public class CommandListener implements Runnable{
    private static final Logger log =
            LoggerFactory.getLogger(CommandListener.class);
    private int commandPort;
    private Boolean listening = true;
    private ServerSocket serverSocket;
    private StockExchange stockExchange;
    private ExecutorService executorService = Executors.newCachedThreadPool();

    //    Look at diagram for this guy  it has command port, boolean listening seversocket and
// stockexchange  also it has an executor service


    public CommandListener(final int commandPort,
                           final StockExchange stockExchange){
        this.commandPort = commandPort;
        this.stockExchange = stockExchange;
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
        try {
            serverSocket = new ServerSocket(commandPort);

            while (listening){
                Socket socket = null;
                try {
                    socket = serverSocket.accept();

                } catch (SocketException e1) {
                    if (serverSocket != null && !serverSocket.isClosed()){
                        log.warn("Can't accept the connection");
                    }
                    log.warn("Unable to connect to socket", e1);
                }
                if (socket == null){
                    continue;
                }
                executorService.execute(new CommandHandler(socket, stockExchange));
            }
        } catch (IOException e) {
            log.warn("Unable to create new serverSocket on port", e);

        } finally {
            this.terminate();
        }
    }

    public void terminate(){
        listening = false;
        try {
            if (serverSocket != null && !serverSocket.isClosed()){
                serverSocket.close();
            }
            serverSocket = null;
            if (!executorService.isShutdown()){
                executorService.shutdown();
                executorService.awaitTermination(4,TimeUnit.SECONDS);
            }
        } catch (IOException e) {
            log.warn("IO Exception Thrown", e);

        } catch (InterruptedException e) {
            log.warn("Interrupted Exception Thrown", e);

        }
    }

}
