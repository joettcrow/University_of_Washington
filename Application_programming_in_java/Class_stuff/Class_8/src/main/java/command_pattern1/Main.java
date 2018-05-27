package command_pattern1;

public class Main
{
    public static void main(String[] args)
    {
       Creator  invoker = new Creator();
       
       invoker.addCommand( new OnCommand() );
       invoker.addCommand( new OffCommand() );
       invoker.addCommand( new OnCommand() );
       invoker.addCommand( new OffCommand() );
       invoker.addCommand( new OnCommand() );
       invoker.addCommand( new OffCommand() );
       
       invoker.start();
    }
}
