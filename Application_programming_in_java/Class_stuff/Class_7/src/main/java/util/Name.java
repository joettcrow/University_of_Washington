package util;

import java.util.Objects;

public class Name
{
    public String   first;
    public String   last;
    public String   middle;
    
    public Name()
    {
        this( "", "" );
    }
    
    public Name( String first, String last )
    {
        this( first, last, "" );
    }
    
    public Name( String first, String last, String middle )
    {
        this.first = first;
        this.last = last;
        this.middle = middle;
    }
    
    @Override
    public boolean equals( Object obj )
    {
        boolean result  = false;
        if ( obj == null )
            result = false;
        else if ( this == obj )
            result = true;
        else if ( !(obj instanceof Name) )
            result = false;
        else
        {
            Name    that    = (Name)obj;
            result =
                Objects.equals( this.first, that.first )
                && Objects.equals( this.last, that.last )
                && Objects.equals( this.middle, that.middle );            
        }
        
        return result;
    }
    
    @Override
    public int hashCode()
    {
        int hash    = Objects.hash( last, first, middle );
        return hash;
    }
    
    @Override
    public String toString()
    {
        StringBuilder   bldr    = new StringBuilder();
        bldr.append( last ).append( "," );
        bldr.append( first ).append( "," );
        bldr.append( middle );
        
        return bldr.toString();
    }
}