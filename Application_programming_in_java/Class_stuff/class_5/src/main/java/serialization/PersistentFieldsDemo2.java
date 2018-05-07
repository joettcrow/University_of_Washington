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

public class PersistentFieldsDemo2
    implements Serializable
{
    private static final long serialVersionUID = -6909527694820918271L;
    
    private LocalTime   time    = LocalTime.now();
    private Color       color;
    private double      width;
    private double      height;
    private boolean     isPublic;
    
    private static final ObjectStreamField[] serialPersistentFields =
    {
        new ObjectStreamField( "color", Color.class ),
        new ObjectStreamField( "width", double.class ),
        new ObjectStreamField( "height", double.class ),
        new ObjectStreamField( "isPublic", boolean.class )
    };
    
    public static void main(String[] args)
    {
        PersistentFieldsDemo2    demo    =
            new PersistentFieldsDemo2( Color.BLUE, 32, 64 ); 
        demo.setPublic( true );
        System.out.println( demo );
        
        byte[] bArr = persist( demo );
        
        String  msg =   "Demo object has been persisted.\n"
                      + "press OK to restore";
        JOptionPane.showMessageDialog( null, msg );
        
        demo = restore( bArr );
        System.out.println( demo );
    }

    public PersistentFieldsDemo2( Color color, double width, double height )
    {
        this.color = color;
        this.width = width;
        this.height = height;
        isPublic = false;
    }
    
    public void setPublic( boolean isPublic )
    {
        this.isPublic = isPublic;
    }
    
    private void readObject( ObjectInputStream inStr)
        throws IOException, ClassNotFoundException 
    {
        ObjectInputStream.GetField  fields  = inStr.readFields(); 
        color = (Color)fields.get( "color", null );
        width = fields.get( "width", 0.0 ); 
        height = fields.get( "height", 0.0 ); 
        isPublic = fields.get( "isPublic", false );
    }
    
    private static byte[] persist( PersistentFieldsDemo2 obj )
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
    
    private static PersistentFieldsDemo2 restore( byte[] bArr )
    {
        PersistentFieldsDemo2    demo    = null;
        
        try (
            ByteArrayInputStream bStream = new ByteArrayInputStream( bArr );
            ObjectInputStream oStream = new ObjectInputStream( bStream );
        )
        {
            Object  obj = oStream.readObject();
            if ( !(obj instanceof PersistentFieldsDemo2) )
                throw new IOException( "Not a PersistentFieldsDemo object" );
            demo = (PersistentFieldsDemo2)obj;
        }
        catch ( IOException | ClassNotFoundException exc )
        {
            exc.printStackTrace();
            System.exit( 1 );
        }
        
        return demo;
    }
    
    @Override
    public String toString()
    {
        StringBuilder   bldr    = new StringBuilder();
        bldr.append( time ).append( " Geometry: color=" ).append( color )
            .append( ",width=" ).append( width )
            .append( ",height=" ).append( height )
            .append( ",isPublic=" ).append( isPublic );
        return bldr.toString();
    }
}
