package command_pattern1;

import java.util.ArrayList;
import java.util.List;

public class Creator
{
    private final List<Command> commands    = new ArrayList<>();
    
    public void addCommand( Command command )
    {
        commands.add( command );
    }
    
    public void start()
    {
        Receiver    receiver    = new Receiver();
        while ( !commands.isEmpty() )
        {
            Command command = commands.remove( 0 );
            command.setReceiver( receiver );
            command.execute();
        }
    }
}
