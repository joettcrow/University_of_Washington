package command_pattern3;

import java.io.Serializable;

public class OnCommand
    extends AbstractCommand
    implements Serializable
{
    private static final long serialVersionUID = -5363209757512685590L;

    @Override
    public void execute()
    {
        getReceiver().action( this );
    }
}
