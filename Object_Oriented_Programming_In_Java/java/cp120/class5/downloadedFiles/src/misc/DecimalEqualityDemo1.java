package misc;

public class DecimalEqualityDemo1
{
    public static void main(String[] args)
    {
        double  num1    = .1 + .2;
        double  num2    = .9 / 3; 

        System.out.printf( "%f%n%f%n", num1, num2 );
        System.out.println( num1 == num2 );
    }
}
