package serialization;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectInputValidation;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.JOptionPane;

public class ValidationDemo
    implements Serializable, ObjectInputValidation
{
    private static final long serialVersionUID = -7517072649567918665L;
    
    private int     counter;
    private double  degrees;

    public static void main(String[] args)
    {
        ValidationDemo  demo    = new ValidationDemo( 5, 260 );
        System.out.println( demo );
        
        byte[]  bArr    = persist( demo );   
        String  msg =   "VALID demo object has been persisted.\n"
                      + "press OK to restore";
        JOptionPane.showMessageDialog( null, msg );
        
        demo = restore( bArr );
        demo.counter = -1;
        bArr    = persist( demo );   
        System.out.println( demo );

        msg =   "INVALID demo object has been persisted.\n"
                      + "press OK to restore";
        JOptionPane.showMessageDialog( null, msg );
        demo = restore( bArr );
        System.out.println( demo );
    }
    
    public ValidationDemo( int counter, double degrees )
    {
        // In real life: validate counter >= 0, |degrees| <= 360
        // For purposes of demonstration: allow invalid data
        this.counter = counter;
        this.degrees = degrees;
    }
    
    private void readObject( ObjectInputStream inStr ) 
        throws IOException, ClassNotFoundException
    { 
        inStr.registerValidation( this, 0 );
        inStr.defaultReadObject();
    }
    
    @Override
    public void validateObject() throws InvalidObjectException
    {
        if ( counter < 0 )
            throw new InvalidObjectException( "counter < 0" );
        if ( Math.abs( degrees ) > 360 )
            throw new InvalidObjectException( "invalid degrees" );
    }
    
    @Override
    public String toString()
    {
        StringBuilder   bldr    = new StringBuilder();
        bldr.append( "counter=" ).append( counter )
            .append( ",degrees=" ).append( degrees );
        return bldr.toString();
    }
    
    private static byte[] persist( ValidationDemo demo )
    {
        byte[]  rval    = null;
        try(
            ByteArrayOutputStream bStream = new ByteArrayOutputStream();
            ObjectOutputStream oStream = new ObjectOutputStream( bStream );
        )
        {
            oStream.writeObject( demo );
            rval = bStream.toByteArray();
        }
        catch ( IOException exc )
        {
            exc.printStackTrace();
            System.exit( 1 );
        }
        
        return rval;
    }
    
    private static ValidationDemo restore( byte[] bArr )
    {
        ValidationDemo   demo  = null;
        
        try (
            ByteArrayInputStream bStream = new ByteArrayInputStream( bArr );
            ObjectInputStream oStream = new ObjectInputStream( bStream );
        )
        {
            Object  obj = oStream.readObject();
            if ( !(obj instanceof ValidationDemo) )
                throw new IOException( "Not a GeoPlane object" );
            demo = (ValidationDemo)obj;
        }
        catch ( IOException | ClassNotFoundException exc )
        {
            exc.printStackTrace();
            System.exit( 1 );
        }
        
        return demo;
    }
}
