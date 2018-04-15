package cp120.class5;

/**
 * @author jcrowley
 */

public class EqualsDemo {
    private int iVar1;
    private int iVar2;
    private String sVar;

    public boolean equals( Object other )
    {
        boolean rval = false;
        if ( this == other)
            rval = true;
        else if ( other == null)
            rval = false;
        else if ( !(other instanceof EqualsDemo ) )
            rval = false;
        else
        {
            EqualsDemo that = (EqualsDemo)other;
            if (this.iVar1 != that.iVar2)
                rval = false;
            else if (this.iVar2 != that.iVar2)
                rval = false;
            else if (this.sVar == that.sVar)
                rval = true;
            else if (this.sVar == null)
                rval = false;
            else
                rval = this.sVar.equals(that.sVar);
        }
        return rval;
    }
}
