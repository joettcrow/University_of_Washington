package misc;

public class BitwiseOperationsOr
{
    public static void main(String[] args)
    {
        byte    op1     = (byte)0b11000011;
        byte    op2     = (byte)0b00011000;
        String  strOp1  = Integer.toBinaryString( op1 ).substring( 24 );
        String  strOp2  = "000" + Integer.toBinaryString( op2 );
        int     binOr   = op1 | op2;
        String  strOr   = Integer.toBinaryString( binOr ).substring( 24 );
        String  sep     = "----------";
        String  fmt     = "  %s\n| %s\n%s\n  %s\n";
        System.out.printf( fmt, strOp1, strOp2, sep, strOr );
    }
}
