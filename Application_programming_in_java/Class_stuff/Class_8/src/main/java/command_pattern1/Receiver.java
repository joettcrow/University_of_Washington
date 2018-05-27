package command_pattern1;

public class Receiver
{
    public void action( OnCommand command )
    {
        System.out.println( "lights on" );
    }
    
    public void action( OffCommand command )
    {
        System.out.println( "lights off" );
    }
}
