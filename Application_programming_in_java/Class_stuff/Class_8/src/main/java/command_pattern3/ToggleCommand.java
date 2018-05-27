package command_pattern3;

import java.io.Serializable;

public class ToggleCommand
    extends AbstractCommand
    implements Serializable
{
    private static final long serialVersionUID = -8243668057967697884L;

    public void execute()
    {
        getReceiver().action( this );
    }
}
