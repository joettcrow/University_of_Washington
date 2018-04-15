package misc;

import java.math.BigInteger;

public class BigIntegerDemo
{
    public static void main(String[] args)
    {
        BigInteger  big1    = BigInteger.valueOf( 1000 );
        BigInteger  big2    = BigInteger.valueOf( 2000 );
        BigInteger  big3    = big1.multiply( big2 );
        System.out.println( big1 + ", " + big2 + ", " + big3 );
    }
}
