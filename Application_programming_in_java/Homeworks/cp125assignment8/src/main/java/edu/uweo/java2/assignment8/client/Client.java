package edu.uweo.java2.assignment8.client;

import edu.uweo.java2.assignment8.AbstractCommand;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The client will send commands to the server.
 * All commands will be implemented using the command pattern.
 * @author jcrowley
 */
public class Client {
    private static final Logger log =
            LoggerFactory.getLogger( Client.class );
    private int port;

    /**
     * Constructor.
     * Determines the port on which to connect to the server.
     * Note that a connection is not established at this time;
     * all this method does is establish the port number.
     * @param port The client port to connect to
     */
    public Client(int port){
        this.port = port;
    }

    /**
     * This method forwards a given command to the server for execution. It will:
     *
     * Connect to the server on the port established by the constructor;
     * Send a single command object to the server implemented as required by the Command Pattern;
     * Wait for the server's response;
     * Log the server's response; and
     * Disconnect from the server
     * @param command The given command.
     */
    public void execute( AbstractCommand command ) {
        log.info( "Opening connection" );
        try ( Socket socket = new Socket(InetAddress.getLoopbackAddress(), port) )
        {
            execute(socket,command);
        } catch (Exception e){e.printStackTrace();}
        log.info("Closing Connection");

    }

    private void execute(Socket socket, AbstractCommand command)
            throws IOException, ClassNotFoundException {
        OutputStream oStream = socket.getOutputStream();
        ObjectOutputStream writer = new ObjectOutputStream(oStream);
        writer.writeObject( command );

        InputStream iStream = socket.getInputStream();
        ObjectInputStream objStream = new ObjectInputStream( iStream );
        Object obj = objStream.readObject();
        log.info( obj.toString() );
    }

}

