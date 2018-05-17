package util;

import java.util.Objects;

public class Address
{
    public Integer  ident   = null;
    public String   address1;
    public String   address2;
    public String   city;
    public String   state;
    public String   zip_code;
    
    public Address( )
    {
        this( "", "", "", "", "" );
    }
    
    public Address(
        String  address1,
        String  address2,
        String  city,
        String  state,
        String  zip_code
    )
    {
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.state = state;
        this.zip_code = zip_code;
    }
    
    @Override
    public boolean equals( Object other )
    {
        boolean rval    = false;
        
        if ( other == null )
            rval = false;
        else if ( this == other )
            rval = true;
        else if ( !(other instanceof Address) )
            rval = false;
        else
        {
            Address that    = (Address)other;
            rval = Objects.equals( this.address1, that.address1 )
                && Objects.equals( this.address2, that.address2 )
                && Objects.equals( this.city, that.city )
                && Objects.equals( this.state, that.state )
                && Objects.equals( this.zip_code, that.zip_code );
        }
        return rval;
    }
    
    @Override
    public int hashCode()
    {
        int hash    =
              address1.hashCode()
            ^ address2.hashCode()
            ^ city.hashCode()
            ^ state.hashCode()
            ^ zip_code.hashCode();
        return hash;
    }
    
    @Override
    public String toString()
    {
        StringBuilder   bldr    = new StringBuilder();
        bldr.append( address1 ).append( "," );
        bldr.append( address2 ).append( "," );
        bldr.append( city ).append( "," );
        bldr.append( state ).append( "," );
        bldr.append( zip_code );
        
        return bldr.toString();
    }
}