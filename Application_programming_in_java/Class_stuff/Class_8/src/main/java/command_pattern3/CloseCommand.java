package command_pattern3;

import java.io.Serializable;

public class CloseCommand
    extends AbstractCommand
    implements Serializable
{
    private static final long serialVersionUID = -6832669356230979968L;

    public void execute()
    {
        this.getReceiver().action( this );
    }
}
