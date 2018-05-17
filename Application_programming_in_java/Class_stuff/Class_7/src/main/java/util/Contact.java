package util;

import java.util.Objects;

public class Contact
{
    private String  lastName;
    private String  firstName;
    private String  phone;
    private String  eMail;
    
    public Contact()
    {
        this( "", "", "", "" );
    }
    
    public 
    Contact( String lastName, String firstName, String phone, String eMail )
    {
        super();
        this.lastName = lastName;
        this.firstName = firstName;
        this.phone = phone;
        this.eMail = eMail;
    }
    
    public String getLastName()
    {
        return lastName;
    }
    
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }
    
    public String getFirstName()
    {
        return firstName;
    }
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }
    
    public String getPhone()
    {
        return phone;
    }
    
    public void setPhone(String phone)
    {
        this.phone = phone;
    }
    
    public String getEMail()
    {
        return eMail;
    }
    
    public void setEMail(String eMail)
    {
        this.eMail = eMail;
    }
    
    @Override
    public boolean equals( Object other )
    {
        boolean result  = false;
        if ( other == null )
            result = false;
        else if ( this == other )
            result = true;
        else if ( !(other instanceof Contact ) )
            result = false;
        else
        {
            Contact that    = (Contact)other;
            result =
                Objects.equals( this.firstName, that.firstName )
                && Objects.equals( this.lastName, that.lastName )
                && Objects.equals( this.phone, that.phone )
                && Objects.equals( this.eMail, that.eMail );
        }
        return result;
    }
    
    @Override
    public int hashCode()
    {
        int hash    =
            Objects.hash( firstName, lastName, phone, eMail );
        return hash;
    }
    
    @Override
    public String toString()
    {
        StringBuilder   bldr    = new StringBuilder();
        bldr.append( lastName ).append( ", " ).append( firstName )
            .append( ", " ).append( phone ).append( "; " ).append( eMail );
        return bldr.toString();
    }
}
