package serialization;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;

import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProxyDemo extends ProxyDemoSuperclass
    implements Serializable
{
    private static final long serialVersionUID = 7064784556043324636L;
    
    private static final    Logger log     =
        LoggerFactory.getLogger( Proxy.class );
        
    private final double dField;

    public static void main(String[] args)
    {
        ProxyDemo   demo    = new ProxyDemo( 5, 3.14 );
        System.out.println( demo );
        
        byte[]  bArr    = persist( demo );   
        String  msg =   "Demo object has been persisted.\n"
                      + "press OK to restore";
        JOptionPane.showMessageDialog( null, msg );
        
        demo = restore( bArr );
        System.out.println( demo );
    }
    
    private static byte[] persist( ProxyDemo dir )
    {
        byte[]  rval    = null;
        try(
            ByteArrayOutputStream bStream = new ByteArrayOutputStream();
            ObjectOutputStream oStream = new ObjectOutputStream( bStream );
        )
        {
            oStream.writeObject( dir );
            rval = bStream.toByteArray();
        }
        catch ( IOException exc )
        {
            exc.printStackTrace();
            System.exit( 1 );
        }
        
        return rval;
    }
    
    private static ProxyDemo restore( byte[] bArr )
    {
        ProxyDemo   demo   = null;
        
        try (
            ByteArrayInputStream bStream = new ByteArrayInputStream( bArr );
            ObjectInputStream oStream = new ObjectInputStream( bStream );
        )
        {
            Object  obj = oStream.readObject();
            if ( !(obj instanceof ProxyDemo) )
                throw new IOException( "Not a ProxyDemo object" );
            demo = (ProxyDemo)obj;
        }
        catch ( IOException | ClassNotFoundException exc )
        {
            exc.printStackTrace();
            System.exit( 1 );
        }
        
        return demo;
    }
    
    public ProxyDemo( int iData, double dData )
    {
        super( iData );
        dField = dData;
    }

    public double getDField()
    {
        return dField;
    }
    
    private Object writeReplace() throws ObjectStreamException 
    {
        log.info( "Writing data" ); 
        Proxy   proxy   = new Proxy( this );
        return proxy;
    }
    
    /*
     * Deserialization shouldn't ever execute this bit of logic;
     * throw an exception if we ever get here.
     */
    private void readObject( ObjectInputStream in )
        throws InvalidObjectException
    { 
        throw new InvalidObjectException( "proxy required" );
    }
    
    @Override
    public String toString()
    {
        StringBuilder   bldr    = new StringBuilder();
        bldr.append( "int data=" ).append( getData() )
            .append( ",double data=" ).append( dField );
        return bldr.toString();
    }
    
    private static class Proxy
        implements Serializable
    {
        private static final long serialVersionUID = 1767942656792847970L;

        private final int       iFieldCopy;
        private final double    dFieldCopy;
        
        public Proxy( ProxyDemo obj )
        {
            iFieldCopy = obj.getData();
            dFieldCopy = obj.getDField();
        }
        
        public Object readResolve()
        {
            ProxyDemo   result  = new ProxyDemo( iFieldCopy, dFieldCopy );
            log.info( "reading data" );
            
            return result;
        }
    }
}
