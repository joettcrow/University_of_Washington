package demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import util.Address;
import util.Name;

public class Customer
{
    private Integer                 ident;
    private Integer                 addrID;
    private Name                    name;
    private Address                 address;
    private final List<CreditCard>  creditCards;
    
    public Customer()
    {
        this( null, null, null );
    }
    
    public Customer( Name name, Address address )
    {
        this( name, address, null );
    }
    
    public Customer( Name name, Address address, Integer ident )
    {
        this.ident = ident;
        this.name = name;
        this.address = address;
        this.creditCards = new ArrayList<>();
    }

    public Integer getIdent()
    {
        return ident;
    }

    public void setIdent(Integer ident)
    {
        this.ident = ident;
    }

    public Name getName()
    {
        return name;
    }

    public void setName(Name name)
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

    public List<CreditCard> getCreditCards()
    {
        return creditCards;
    }
    
    public Integer getAddrID()
    {
        return addrID;
    }

    public void setAddrID(Integer addrID)
    {
        this.addrID = addrID;
    }

    public void addCreditCard( CreditCard creditCard )
    {
        creditCards.add( creditCard );
    }
    
    @Override
    public boolean equals( Object obj )
    {
        boolean result  = false;
        if ( obj == null )
            result = false;
        else if ( this == obj )
            result = false;
        else if ( !(obj instanceof Customer) )
            result = false;
        else
        {
            Customer    that    = (Customer)obj;
            result = Objects.equals( this.name, that.name )
                && Objects.equals( this.address, that.address )
                && testCreditCardList( that );
        }
        
        return result;
    }
    
    @Override
    public int hashCode()
    {
        int hash    = Objects.hash( name, address, creditCards );
        return hash;
    }
    
    private boolean testCreditCardList( Customer that )
    {
        boolean result = true;
        List<CreditCard>    thisList    = this.creditCards;
        List<CreditCard>    thatList    = that.creditCards;
        
        if ( thisList.size() != thatList.size() )
            result = false;
        
        int limit   = thisList.size();
        for ( int inx = 0 ; inx < limit && result ; ++inx )
            result = thisList.contains( thatList.get( inx ) );
        
        return result;
    }
    
    @Override
    public String toString()
    {
        StringBuilder   bldr    = new StringBuilder();
        bldr.append( name ).append( "," ).append( address );
        bldr.append( " {");
        for ( CreditCard card : creditCards )
            bldr.append( " " ).append( card.toString() );
        bldr.append( " }" );
        
        return bldr.toString();
    }
}
