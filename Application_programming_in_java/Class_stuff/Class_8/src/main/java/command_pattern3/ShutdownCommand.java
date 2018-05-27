package command_pattern3;

import java.io.Serializable;

public class ShutdownCommand
    extends AbstractCommand
    implements Serializable
{
    private static final long serialVersionUID = 3779605113560998022L;

    public void execute()
    {
        getReceiver().action( this );
    }
}
