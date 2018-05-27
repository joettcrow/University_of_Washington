package command_pattern2;

public class OnCommand
    extends AbstractCommand
{
    @Override
    public void execute()
    {
        getReceiver().action( this );
    }
}
