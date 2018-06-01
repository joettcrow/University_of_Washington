package edu.uweo.java2.assignment8.server;

import edu.uweo.java2.assignment8.AbstractCommand;
import edu.uweo.java2.assignment8.NAKCommand;
import edu.uweo.java2.assignment8.Receiver;
import edu.uweo.java2.assignment8.ShutdownCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

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
public class Server {
    private final Receiver receiver = new Receiver();
    private final int port;
    private boolean running = true;

    private static final Logger log =
            LoggerFactory.getLogger( Server.class );

    /**
     * Constructor. Determines the port on which to connect to the server.
     * Note that a connection is not established at this time;
     * all this method does is establish the port number.
     * @param port The port the client must use to connect to the server.
     */
    public Server( int port ){
        this.port = port;
    }

    /**
     * This method starts the server.
     * @throws IOException if there's an IO exception
     * @throws ClassNotFoundException if the class is not found
     */
    public void execute()
            throws IOException, ClassNotFoundException
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
     * @throws ClassNotFoundException if the class is not found
     */
    private void execute( ServerSocket listener )
            throws IOException, ClassNotFoundException
    {
        class shutdownReceiver extends Receiver{
            public void action( ShutdownCommand command )
            {
                running = false;
            }
        }
        while ( running )
        {
            log.info( "Listening for clients" );
            try ( Socket client = listener.accept() )
            {
                log.info( "connection accepted" );
                process( client );
            }
        }
    }

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

        } catch (ClassNotFoundException e) {
            log.warn("Class Not Found Exception because reasons", e);

        }

    }
}
