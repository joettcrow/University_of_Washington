package app;

import java.math.BigDecimal;

public class AdHoc
{

    public static void main(String[] args)
    {
        BigDecimal  big1    = new BigDecimal( "123.45" );
        BigDecimal  big2    = new BigDecimal( "123.4500" );
        System.out.println( big1 );
        System.out.println( big2 );
        System.out.println( big1.equals( big2 ) );
    }
}
