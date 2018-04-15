package misc;

public class HashCodeDemo
{
    private int     intField    = 5;
    private String  strField    = "spot";
    private boolean boolField   = true;
    
    public static void main(String[] args)
    {
        int     sameInt     = 13;
        String  sameStr     = "spot";
        boolean sameBool    = true;

        int     diffInt     = 17;
        String  diffStr     = "pots";
        boolean diffBool    = !sameBool;
        
        HashCodeDemo    sameHash1   =
            new HashCodeDemo( sameInt, sameStr, sameBool );
        HashCodeDemo    sameHash2   =
            new HashCodeDemo( sameInt, sameStr, sameBool );
        HashCodeDemo    diffHash    =
            new HashCodeDemo( diffInt, diffStr, diffBool );
        
        System.out.println( sameHash1.hashCode() );
        System.out.println( sameHash2.hashCode() );
        System.out.println( diffHash.hashCode() );
    }
    
    private HashCodeDemo( int intVal, String strVal, boolean boolVal )
    {
        intField = intVal;
        strField = strVal;
        boolField = boolVal;
    }

    @Override
    public boolean equals( Object obj )
    {
        boolean rcode   = false;
        
        if (obj == null )
            rcode = false;
        else if ( this == obj )
            rcode = true;
        else if ( !(obj instanceof HashCodeDemo) )
            rcode = false;
        else
        {
            HashCodeDemo    that    = (HashCodeDemo)obj;
            rcode =    this.intField == that.intField
                    && this.strField.equals( that.strField )
                    && this.boolField == that.boolField;
        }
        
        return rcode;
    }
    
    @Override
    public int hashCode()
    {
        int xier        = 29; // should be prime
        int boolHash    = boolField ? 1 : 0;
        int hash        = 
               intField * xier
             + boolHash * xier
             + strField.hashCode() * xier;
        return hash;
    }
}
