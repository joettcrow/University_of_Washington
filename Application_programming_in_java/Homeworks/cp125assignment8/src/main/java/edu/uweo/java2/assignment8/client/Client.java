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
public class
Client {
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

    public void execute( AbstractCommand command ) throws IOException {
        log.info( "Opening connection" );
        try ( Socket socket = new Socket(InetAddress.getLocalHost(), port) )
        {
            execute(socket,command);
        } catch (Exception e){e.printStackTrace();}

    }

    private void execute(Socket socket, AbstractCommand command)
            throws IOException {
        InputStream iStream = socket.getInputStream();
        InputStreamReader reader = new InputStreamReader(iStream);
        BufferedReader bReader = new BufferedReader(reader);
        OutputStream oStream = socket.getOutputStream();
        ObjectOutputStream writer = new ObjectOutputStream(oStream);

        socket.connect(socket.getLocalSocketAddress());
        command.execute();
        System.out.println("Command is: " + command.toString());

        // wait for ACK from server
        String line = bReader.readLine();
        if (!line.equalsIgnoreCase("ACK"))
            throw new Error("invalid acknowledgement from server");
        log.info( command.toString() );
        writer.writeObject(command);

//        pause(250);
    }
//
//    private static void pause( long millis )
//    {
//        try
//        {
//            Thread.sleep( millis );
//        }
//        catch ( InterruptedException exc )
//        {
//        }
//    }
}

