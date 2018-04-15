package misc;

public class DivisionModuloDemo
{

    public static void main(String[] args)
    {
        printDigits( -4530 );
    }

    private static void printDigits( int num )
    {
        int temp    = num;
        while ( temp != 0 )
        {
            int digit   = temp % 10;
            temp = temp / 10;
            System.out.println( digit );
        }
    }
}
