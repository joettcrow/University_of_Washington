package misc;

public class EqualsDemo2
{
    private int     iVar1;
    private int     iVar2;
    private String  sVar;
    
    public void setIVar1( int iVar1 )
    {
        this.iVar1 = iVar1;
    }
    
    public int getIVar1()
    {
        return iVar1;
    }
    
    public void setIVar2( int iVar2 )
    {
        this.iVar2 = iVar2;
    }
    
    public int getIVar2()
    {
        return iVar2;
    }
    
    public String getSVar()
    {
        return sVar;
    }
    
    public void setSVar( String sVar )
    {
        this.sVar = sVar;
    }
    
    @Override
    public boolean equals( Object other )
    {
        boolean rval    = false;
        if ( other == null )
            rval = false;
        else if ( this == other )
            rval = true;
        else if ( !(other instanceof EqualsDemo2 ) )
            rval = false;
        else
        {
            EqualsDemo2 test    = (EqualsDemo2)other;
            rval = iVar1 == test.iVar1 && iVar2 == test.iVar2
                   && sVar.equals( test.sVar );
        }
        return rval;
    }
}
