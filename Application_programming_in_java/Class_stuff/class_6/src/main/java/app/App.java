package app;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String... args )
    {
        System.out.println( "Hello World!" );
        System.out.println( int.class);
        Object[] objects = new Object[3];
        method(objects);
    }

    private static void method( Object... objects){
        System.out.println(objects.length);
    }
}
