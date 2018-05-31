package app;

import java.util.Optional;

public class LazyInitializationDemo
{
    private volatile Optional<Integer>  hash    = Optional.empty();    
    // ...
    public int hashCode()
    {
        if ( !hash.isPresent() )
        {
            synchronized( hash )
            {
                if ( !hash.isPresent() )
                    calculateHash();
            }
        }
        
        return hash.get();
    }
    // ...
    
    private void calculateHash()
    {
        hash = Optional.of( 13 );
    }
}
