package threads;

import java.util.stream.Stream;

public class ShutdownHookDemo
{
    public static void main(String[] args)
    {
        Runtime     rtime       = Runtime.getRuntime();
        Runnable    runnable    =
            () -> System.out.println( Thread.currentThread().getName() );
        Stream.iterate( 1, n -> n + 1 )
            .limit( 5 )
            .map( n -> "hook #"  + n )
            .map( s -> new Thread( runnable, s ) )
            .forEach( t -> rtime.addShutdownHook( t ) );
    }
}
