package edu.uw.jtc.exchange;

import edu.uw.ext.framework.exchange.StockExchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

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
    private ExecutorService executorService;

    //    Look at diagram for this guy  it has command port, boolean listening seversocket and
// stockexchange  also it has an executor service


    public CommandListener(int commandPort,
                           StockExchange stockExchange){
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
        } catch (IOException e) {
            log.warn("Unable to create new serverSocket on port", e);
            while (listening){
                Socket socket = null;
                try {
                    socket = serverSocket.accept();
                    if (socket == null){
                        continue;
                    }
                } catch (IOException e1) {
                    log.warn("Unable to connect to socket", e1);
                }
                executorService.execute(socket);
                this.terminate();
            }
        }
//        create server serverSocket on port, while loop for listening, and create a null serverSocket
//        try serverSocket.accept and log it, catch a serverSocket exception,
//        if sock (the listening) is null just continue
//        after the try requestExecutor.exectue a new command Handler
//
//        eventually catch IO exception and finally terminate()

    }

    public void terminate(){
        listening = false;
//        try if server sock is not null and is not closed then server sock close
//        set sever sock to null
//        if the requestExecutor is not shutdown then shut it down and awaitTermination
//        Catch the exceptions intellij compalins about
    }

}
