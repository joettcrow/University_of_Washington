package edu.uweo.java2.assignment9.server;

import edu.uweo.java2.assignment9.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * The server will (in a single thread):
 *
 * Wait for a client to connect on port 4885;
 * Wait for the client to send a command;
 * Set a receiver on the command as described by the Command Pattern;
 * Execute the command as described by the Command Pattern;
 * Return the command object to the client; and
 * Loop (wait for the next client to connect).
 * @author jcrowley
 */
public class Server implements Serializable {
    private static final long serialVersionUID = 1263728900915100450L;
    private final serverReceiver receiver = new serverReceiver();
    private final int port;
    private boolean isActive = true;
    private final static int SO_TIMEOUT = 2000;

    private static final Logger log =
            LoggerFactory.getLogger( Server.class );

    /**
     * Subclasses receiver to do stuff, like shutdown.
     */
    class serverReceiver extends Receiver{
        private static final long serialVersionUID = -2828673486065752842L;

        /**
         * Overrides the receiver shutdown command to actually do something
         * @param shutdownCommand the shutdown command
         */
        @Override
        public void action(ShutdownCommand shutdownCommand) {
            isActive = false;
        }
    }

    /**
     * Class for dealing with processing client requests in a thread
     */
    class clientProcessingThread implements Runnable{
        private Socket client;
        clientProcessingThread(Socket client){
            this.client = client;
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
            try {
                process(client);
            } catch (IOException e) {
                log.warn("IO Exception in thread run", e);
            } catch (ClassNotFoundException e) {
                log.warn("Class Not Found in Thread run", e);
            }
        }

        /**
         * Private process method
         * @param client the client to do the things with
         * @throws IOException if there's an IO problem
         * @throws ClassNotFoundException if the class is not found
         */
        private void process( Socket client )
                throws IOException, ClassNotFoundException
        {
            InputStream iStream = client.getInputStream();
            ObjectInputStream objStream = new ObjectInputStream( iStream );
            OutputStream oStream = client.getOutputStream();

            ObjectOutputStream oWriter = new ObjectOutputStream(oStream);
            Object obj = objStream.readObject();

            if ( !(obj instanceof AbstractCommand) ){
                oWriter.writeObject( new NAKCommand() );
            }
            else {
                AbstractCommand command = (AbstractCommand) obj;
                command.setReceiver(receiver);
                command.execute();
                oWriter.writeObject(command);
            }
        }
    }

    /**
     * Constructor. Determines the port on which to connect to the server.
     * Note that a connection is not established at this time;
     * all this method does is establish the port number.
     * @param port The port the client must use to connect to the server.
     */
    private Server(int port){
        this.port = port;
    }

    /**
     * This method starts the server.
     * @throws IOException if there's an IO exception
     */
    public void execute() throws IOException
    {
        try ( ServerSocket listener = new ServerSocket( port ) )
        {
            execute( listener );
        }
    }

    /**
     * This method Executes.
     * @param listener the listener to listen to
     * @throws IOException if there's an IO exception
     *
     */
    private void execute( ServerSocket listener)
            throws IOException
    {
        listener.setSoTimeout(SO_TIMEOUT);
        while (isActive)
        {
            log.info( "Listening for clients" );
            try
            {
                Socket client = listener.accept();
                log.info( "connection accepted" );
                clientProcessingThread pclient = new clientProcessingThread(client);
                Thread thread = new Thread(pclient);
                thread.start();
            } catch (SocketTimeoutException exc) {
                log.info("Checking active");
            }
            if (!isActive)
                log.info("Shutting Down");
        }
    }

    /**
     * Main method, it does things.
     * @param args the args to pass
     */
    public static void main(String[] args) {
        log.info("Creating server");
        Server server = new Server(4885);
        try {
            log.info("Running server");
            server.execute();
        } catch (IOException e) {
            log.warn("IO Exception because reasons", e);

        }

    }
}
