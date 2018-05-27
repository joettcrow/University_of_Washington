package command_pattern1;

public interface Command
{
    void execute();
    void setReceiver( Receiver receiver );
}
