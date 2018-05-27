package command_pattern3;

import java.io.Serializable;

public class OffCommand
    extends AbstractCommand
    implements Serializable
{
    private static final long serialVersionUID = -6132769504147472431L;

    @Override
    public void execute()
    {
        getReceiver().action( this );
    }
}
