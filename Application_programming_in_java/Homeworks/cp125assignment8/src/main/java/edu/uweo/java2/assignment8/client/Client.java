package edu.uweo.java2.assignment8.client;

/**
 * The client will send commands to the server.
 * All commands will be implemented using the command pattern.
 * @author jcrowley
 */
public class Client {

    private final int port;

    /**
     * Constructor.
     * Determines the port on which to connect to the server.
     * Note that a connection is not established at this time;
     * all this method does is establish the port number.
     * @param port The client port to connect to
     */
    public Client( int port ){
        this.port = port;
    }

    public execute( AbstractCommand command ){

    }
}
