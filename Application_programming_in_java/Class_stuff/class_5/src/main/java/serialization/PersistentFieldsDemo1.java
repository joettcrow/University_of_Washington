package serialization;

import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamField;
import java.io.Serializable;
import java.time.LocalTime;

import javax.swing.JOptionPane;

public class PersistentFieldsDemo1
    implements Serializable
{
    private static final long serialVersionUID = -6909527694820918271L;
    
    private LocalTime   time    = LocalTime.now();
    private Color       color;
    private double      width;
    private double      height;
    
    private static final ObjectStreamField[] serialPersistentFields =
    {
        new ObjectStreamField( "color", Color.class ),
        new ObjectStreamField( "width", double.class ),
        new ObjectStreamField( "height", double.class )
    };
    
    public static void main(String[] args)
    {
        PersistentFieldsDemo1    demo    =
            new PersistentFieldsDemo1( Color.BLUE, 32, 64 ); 
        System.out.println( demo );
        
        byte[] bArr = persist( demo );
        
        String  msg =   "Demo object has been persisted.\n"
                      + "press OK to restore";
        JOptionPane.showMessageDialog( null, msg );
        
        demo = restore( bArr );
        System.out.println( demo );
    }
    
    
    private static byte[] persist( PersistentFieldsDemo1 obj )
    {
        byte[]  rval    = null;
        try(
            ByteArrayOutputStream bStream = new ByteArrayOutputStream();
            ObjectOutputStream oStream = new ObjectOutputStream( bStream );
        )
        {
            oStream.writeObject( obj );
            rval = bStream.toByteArray();
        }
        catch ( IOException exc )
        {
            exc.printStackTrace();
            System.exit( 1 );
        }
        
        return rval;
    }
    
    private static PersistentFieldsDemo1 restore( byte[] bArr )
    {
        PersistentFieldsDemo1    demo    = null;
        
        try (
            ByteArrayInputStream bStream = new ByteArrayInputStream( bArr );
            ObjectInputStream oStream = new ObjectInputStream( bStream );
        )
        {
            Object  obj = oStream.readObject();
            if ( !(obj instanceof PersistentFieldsDemo1) )
                throw new IOException( "Not a PersistentFieldsDemo object" );
            demo = (PersistentFieldsDemo1)obj;
        }
        catch ( IOException | ClassNotFoundException exc )
        {
            exc.printStackTrace();
            System.exit( 1 );
        }
        
        return demo;
    }


    public PersistentFieldsDemo1( Color color, double width, double height )
    {
        this.color = color;
        this.width = width;
        this.height = height;
    }
    
    @Override
    public String toString()
    {
        StringBuilder   bldr    = new StringBuilder();
        bldr.append( time ).append( " Geometry: color=" ).append( color )
            .append( ",width=" ).append( width )
            .append( ",height=" ).append( height );
        return bldr.toString();
    }
}
