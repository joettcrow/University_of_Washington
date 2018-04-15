package misc;

public class EpsilonCompareDemo
{
    public static void main(String[] args)
    {
        double  num1    = .1 + .2;
        double  num2    = .9 / 3;
        double  epsilon = .000001;
        boolean equal   = Math.abs( num1 - num2 ) < epsilon;

        System.out.printf( "%15.14f, %15.14f%n", num1, num2 );
        System.out.println( equal );
    }
}
