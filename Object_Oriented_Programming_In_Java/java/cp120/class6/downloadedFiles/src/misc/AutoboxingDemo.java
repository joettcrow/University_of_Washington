package misc;

import java.util.ArrayList;
import java.util.List;

public class AutoboxingDemo
{
    public static void main(String[] args)
    {
        List<Integer>   list    = new ArrayList<>();
        list.add( new Integer( 100 ) );
        list.add( 200 );
        list.add( 300 );
        list.add( 2, 400 );
        list.add( 3, 500 );
        list.remove( new Integer( 100 ) );
        list.remove( 3 );
        for ( int num : list )
            System.out.println( num );
    }
}
