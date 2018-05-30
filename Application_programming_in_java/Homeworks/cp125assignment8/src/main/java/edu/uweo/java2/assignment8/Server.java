package edu.uweo.java2.assignment8;

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
public class Server implements Runnable {
//    private final Receiver receiver;
    private final int port;

    private static final Logger log =
            LoggerFactory.getLogger( Server.class );

    public Server( int port ){
        this.port = port;
    }

    public void execute()
            throws IOException, ClassNotFoundException
    {
        try ( ServerSocket listener = new ServerSocket( port ) )
        {
            execute( listener );
        }
    }

    private void execute( ServerSocket listener )
            throws IOException, ClassNotFoundException
    {
        while ( true )
        {
            log.trace( "Listening for clients" );

            try ( Socket client = listener.accept() )
            {
                log.trace( "connection accepted" );
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
        PrintWriter writer = new PrintWriter( oStream, true );

        AbstractCommand command = (AbstractCommand)objStream.readObject();
        Receiver receiver = new Receiver(command);
        command.setReceiver( receiver );
        command.execute();
        writer.println( "ACK" );
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
            this.execute();
        } catch (IOException e) {
            log.warn("IO Exception", e);

        } catch (ClassNotFoundException e) {
            log.warn("Class Not Found", e);

        }
    }
}
