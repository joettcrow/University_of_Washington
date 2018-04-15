package misc;

import java.util.ArrayList;
import java.util.List;

public class ListDemo1
{
    public static void main(String[] args)
    {
        List<String>    list    = new ArrayList<>();
        list.add( "first" );
        list.add( "second" );
        list.add( "third" );
        list.add( "fourth" );
        list.add( "fifth" );
        
        for ( String str : list )
            System.out.println( str );
    }
}
