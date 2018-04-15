package misc;

import java.util.Date;

public class EqualsDemo1
{

    public static void main(String[] args)
    {
        new EqualsDemo1();
    }
    
    public EqualsDemo1()
    {
        NamedTurtle noma    = new NamedTurtle();
        SuperTurtle supes   = new SuperTurtle();
        String      alpha   = "alpha";
        Date        date    = new Date();
        Object[]    objs    = { noma, supes, alpha, date };
        System.out.println( indexOf( alpha, objs ) );
    }

    private int indexOf( Object test, Object[] arr )
    {
        int index = -1;
        int len   = arr.length;
        for ( int inx = 0 ; inx < len && index < 0 ; ++inx )
            if ( test.equals( arr[inx] ) )
                index = inx;
        return index;
    }
}
