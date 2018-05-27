package command_pattern2;

public class Receiver
{
    private final Lightening    lightening;
    
    public Receiver( Lightening lightening )
    {
        this.lightening = lightening;
    }
    
    public void action( OnCommand cmd )
    {
        int row = cmd.getRow();
        int col = cmd.getCol();
        lightening.setLight( row,  col,  true );
    }
    
    public void action( OffCommand cmd )
    {
        int row = cmd.getRow();
        int col = cmd.getCol();
        lightening.setLight( row,  col,  false );
    }
    
    public void action( ToggleCommand cmd )
    {
        int row = cmd.getRow();
        int col = cmd.getCol();
        lightening.toggleLight( row,  col );
    }
    
    public void action( CloseCommand cmd )
    {
    }
    
    public void action( ShutdownCommand cmd )
    {
    }
}
