package edu.uweo.java2.assignment8;

/**
 * This class will encapsulate a receiver object, as described by the command pattern.
 * @author jcrowley
 */
public class Receiver
{
    public void action( OnCommand command )
    {
        System.out.println( "turning on" );
    }
    public void action( OffCommand command )
    {
        System.out.println( "turning off" );
    }
}
