package command_pattern2;

public class ShutdownCommand
    extends AbstractCommand
{
    public void execute()
    {
        getReceiver().action( this );
    }
}
