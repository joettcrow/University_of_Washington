package misc;

public class Swapper
{
    public static void main(String[] args)
    {
        int num1    = 27;
        int num2    = -135;
        System.out.println(num1 + " " + num2 );

        num1 ^= num2;
        num2 ^= num1;
        num1 ^= num2;
        System.out.println( num1 + " " + num2 );
    }

}
