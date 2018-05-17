package util;

import java.time.LocalDate;

public class Vendor
{
    private Integer     ident;
    private String      name;
    private Address     addr;
    private LocalDate   lastContact;

    public Vendor( String name, Address addr, LocalDate lastContact )
    {
        this.name = name;
        this.addr = addr;
        this.lastContact = lastContact;
        ident = null;
    }
    public Integer getIdent()
    {
        return ident;
    }
    public void setIdent(Integer ident)
    {
        this.ident = ident;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public Address getAddr()
    {
        return addr;
    }
    public void setAddr(Address addr)
    {
        this.addr = addr;
    }
    public LocalDate getLastContact()
    {
        return lastContact;
    }
    public void setLastContact(LocalDate lastContact)
    {
        this.lastContact = lastContact;
    }
}
