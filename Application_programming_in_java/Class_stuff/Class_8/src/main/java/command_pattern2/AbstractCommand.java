package command_pattern2;

public abstract class AbstractCommand
{
    private Receiver        receiver;
    private int             row;
    private int             col;
    
    public abstract void execute();
    
    public void setReceiver( Receiver receiver )
    {
        this.receiver = receiver;
    }
    
    public Receiver getReceiver()
    {
        return receiver;
    }

    public void setCoordinates( int row, int col )
    {
        this.row = row;
        this.col = col;
    }
    
    public int getRow()
    {
        return row;
    }
    
    public int getCol()
    {
        return col;
    }
    
    @Override
    public String toString()
    {
        String  str = receiver + ", " + row + ", " + col;
        return str;
    }
}
