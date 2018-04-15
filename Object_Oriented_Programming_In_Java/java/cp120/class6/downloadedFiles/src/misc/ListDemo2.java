package misc;

import java.util.ArrayList;
import java.util.List;

public class ListDemo2
{
    public static void main(String[] args)
    {
        List<Integer>   list    = new ArrayList<>();
        list.add( new Integer( 100 ) );
        list.add( new Integer( 200 ) );
        list.add( new Integer( 300 ) );
        list.add( 2, new Integer( 400 ) );
        list.add( 3, new Integer( 500 ) );
        list.remove( new Integer( 100 ) );
        list.remove( 3 );
        for ( Integer num : list )
            System.out.println( num );
    }
}
