package cp120.class4.downloadedFiles;

import java.math.BigDecimal;
import java.math.MathContext;

public class BigDecimalDemo
{

    public static void main(String[] args)
    {
        BigDecimal  radius  = new BigDecimal( 5.5 );
        BigDecimal  height  = new BigDecimal( 3.3 );
        System.out.println( cylinderVolume( radius, height ) );
    }

    private static BigDecimal
    cylinderVolume( BigDecimal radius, BigDecimal height )
    {
        BigDecimal  pii     = new BigDecimal( Math.PI );
        BigDecimal  rSquare = radius.pow( 2 );
        BigDecimal  product = rSquare.multiply( pii ).multiply( height );
        
        MathContext ctx     = new MathContext( 10 );
        BigDecimal  volume  = product.round( ctx );
        
        return volume;
    }
}
