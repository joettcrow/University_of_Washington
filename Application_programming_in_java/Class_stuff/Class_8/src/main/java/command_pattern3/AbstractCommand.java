package command_pattern3;

import java.io.Serializable;

public abstract class AbstractCommand
    implements Serializable
{
    private static final long serialVersionUID = 1358232994329564854L;

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
