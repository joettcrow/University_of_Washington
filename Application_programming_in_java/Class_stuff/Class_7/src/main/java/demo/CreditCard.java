package demo;

import java.time.LocalDate;
import java.util.Objects;

import util.Address;

public class CreditCard
{
    private Integer     ident   = null;
    private Integer     custID  = null;
    private String      name;
    private Address     address;
    private String      number;
    private String      cvv;
    private LocalDate   expiry;
    
    public CreditCard()
    {
    }
    
    public CreditCard( 
        String      name,
        Address     address,
        String      number,
        String      cvv,
        LocalDate   expiry
    )
    {
        this.name = name;
        this.address = address;
        this.number = number;
        this.cvv = cvv;
        this.expiry = expiry; 
    }

    public Integer getIdent()
    {
        return ident;
    }

    public void setIdent(Integer ident)
    {
        this.ident = ident;
    }

    public Integer getCustID()
    {
        return custID;
    }

    public void setCustID(Integer custID)
    {
        this.custID = ident;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Address getAddress()
    {
        return address;
    }

    public void setAddress(Address address)
    {
        this.address = address;
    }

    public String getNumber()
    {
        return number;
    }

    public void setNumber(String number)
    {
        this.number = number;
    }

    public String getCvv()
    {
        return cvv;
    }

    public void setCvv(String cvv)
    {
        this.cvv = cvv;
    }

    public LocalDate getExpiry()
    {
        return expiry;
    }

    public void setExpiry(LocalDate expiry)
    {
        this.expiry = expiry;
    }
    
    @Override
    public boolean equals( Object other )
    {
        boolean result  = false;
        if ( other == null )
            result = false;
        else if ( this == other )
            result = true;
        else if ( !(other instanceof CreditCard ) )
            result = false;
        else
        {
            CreditCard  that    = (CreditCard)other;
            result =
                this.name.equals( that.name )
                && this.address.equals( that.address )
                && this.number.equals( that.number )
                && this.cvv.equals( that.cvv );
        }
        
        return result;
    }
    
    @Override
    public int hashCode()
    {
        int hash    = Objects.hash( name, address, number, cvv, expiry );
        return hash;
    }
    
    @Override
    public String toString()
    {
        StringBuilder   bldr    = new StringBuilder();
        bldr.append( ident ).append( "," );
        bldr.append( name ).append( "," );
        bldr.append( address ).append( "," );
        bldr.append( number ).append( "," );
        bldr.append( cvv ).append( "," );
        bldr.append( expiry );
        
        return bldr.toString();
    }
}
