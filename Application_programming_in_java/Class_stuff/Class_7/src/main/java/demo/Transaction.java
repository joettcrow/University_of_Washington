package demo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Transaction
{
    private Integer         ident;
    private String          name;
    private LocalDateTime   time;
    private BigDecimal      amount;
    
    public Transaction( String name, LocalDateTime time, BigDecimal amount)
    {
        this.name = name;
        this.time = time;
        this.amount = amount;
        ident = null;
    }

    public Integer getIdent()
    {
        return ident;
    }

    public void setIdent( int ident )
    {
        this.ident = ident;
    }

    public String getName()
    {
        return name;
    }
    
    public void setName( String name )
    {
        this.name = name;
    }

    public LocalDateTime getTime()
    {
        return time;
    }
    
    public void setTime( LocalDateTime time )
    {
        this.time = time;
    }

    public BigDecimal getAmount()
    {
        return amount;
    }
    
    public void setAmount( BigDecimal amount )
    {
        this.amount = amount;
    }
    
    @Override
    public boolean equals( Object other )
    {
        boolean rval    = false;
        if ( other == null )
            rval = false;
        else if ( this == other )
            rval = true;
        else if ( !(other instanceof Transaction) )
            rval = false;
        else
        {
            Transaction that    = (Transaction)other;
            rval = 
                Objects.equals( this.name, that.name )
                && Objects.equals( this.time, that.time )
                && Objects.equals( amount, amount );
        }
        
        return rval;
    }
    
    @Override
    public int hashCode()
    {
        int hash    = Objects.hash( name, time, amount );
        return hash;
    }
    
    @Override
    public String toString()
    {
        StringBuilder   bldr    = new StringBuilder( name );
        bldr.append( "," ).append( time ).append( "," ).append( amount );
        return bldr.toString();
    }
}
