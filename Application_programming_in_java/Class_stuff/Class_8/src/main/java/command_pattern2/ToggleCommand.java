package command_pattern2;

public class ToggleCommand
    extends AbstractCommand
{
    public void execute()
    {
        getReceiver().action( this );
    }
}
