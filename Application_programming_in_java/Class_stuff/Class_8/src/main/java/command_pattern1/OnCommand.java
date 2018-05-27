package command_pattern1;

public class OnCommand
    implements Command
{
    private Receiver    receiver;

    public void setReceiver( Receiver receiver )
    {
        this.receiver = receiver;
    }
    
    public void execute()
    {
        receiver.action( this );
    }
}
