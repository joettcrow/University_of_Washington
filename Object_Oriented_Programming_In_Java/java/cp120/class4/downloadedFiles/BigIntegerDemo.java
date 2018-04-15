package cp120.class4.downloadedFiles;

import java.math.BigInteger;

public class BigIntegerDemo
{

    public static void main(String[] args)
    {
        BigPoint    first   = new BigPoint( 5, 10 );
        BigPoint    second  = new BigPoint( 6, 25 );
        System.out.println( slope( first, second ) );
    }

    private static BigInteger 
    slope( BigPoint point1, BigPoint point2 )
    {
        BigInteger  xco     = point1.xco;
        BigInteger  yco     = point1.yco;
        BigInteger  xco1    = point2.xco;
        BigInteger  yco1    = point2.yco;
        
        BigInteger  xDiff   = xco1.subtract( xco );
        BigInteger  yDiff   = yco1.subtract( yco );
        BigInteger  quot    = yDiff.divide( xDiff );
        
        return quot;
    }
    
    private static class BigPoint
    {
        public BigInteger  xco;
        public BigInteger  yco;
        
        public BigPoint( int xco, int yco )
        {
            this.xco = BigInteger.valueOf( xco );
            this.yco = BigInteger.valueOf( yco );
        }
    }
}
