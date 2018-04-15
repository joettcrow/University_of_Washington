package misc;

public class DecimalEqualityDemo2
{
    public static void main(String[] args)
    {
        double  num1    = .1 + .2;
        double  num2    = .9 / 3;
        long    man1    = Double.doubleToLongBits( num1 );
        long    man2    = Double.doubleToLongBits( num2 );

        System.out.printf( "%20.19f%n%20.19f%n", num1, num2 );
        System.out.println( man1 );
        System.out.println( man2 );
    }
}
