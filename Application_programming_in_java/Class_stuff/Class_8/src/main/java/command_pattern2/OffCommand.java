package command_pattern2;

public class OffCommand
    extends AbstractCommand
{
    @Override
    public void execute()
    {
        getReceiver().action( this );
    }
}
