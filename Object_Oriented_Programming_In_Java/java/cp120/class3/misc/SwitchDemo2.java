package misc;

public class SwitchDemo2
{

    public static void main(String[] args)
    {
        for ( int inx = 1 ; inx <= 10 ; ++inx )
            System.out.println(  inx + ": " + getGroup( inx ) );
    }

    private static String getGroup( int seqNum )
    {
        String  group   = "invalid sequence number";
        
        switch ( seqNum )
        {
        case 1:
        case 2:
        case 3:
            group = "red";
            break;
        case 4:
        case 5:
        case 6:
            group = "orange";
            break;
        case 7:
        case 8:
        case 9:
            group = "purple";
            break;
        }
        
        return group;
    }
}
