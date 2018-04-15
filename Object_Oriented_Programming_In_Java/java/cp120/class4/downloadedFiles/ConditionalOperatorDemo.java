package cp120.class4.downloadedFiles;

public class ConditionalOperatorDemo
{

    public static void main(String[] args)
    {
        System.out.println( abs( 42 ) );
        System.out.println( abs( -42 ) );
    }

    private static int abs( int num )
    {
        int result  = num >= 0 ? num : -num;
        return result;
    }
}
