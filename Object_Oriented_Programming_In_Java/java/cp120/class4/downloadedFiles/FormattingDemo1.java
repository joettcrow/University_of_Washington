package cp120.class4.downloadedFiles;

public class FormattingDemo1
{

    public static void main(String[] args)
    {
        int op1 = 5;
        int op2 = 10;
        int sum = op1 + op2;
        String  str = String.format( "%d + %d = %d", op1, op2, sum );
        System.out.println( str );
        
        System.out.printf( "%d, %f%n", 5, 3.1415 );
    }
}
