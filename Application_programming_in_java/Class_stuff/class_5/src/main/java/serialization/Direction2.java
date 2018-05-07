package serialization;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;

import javax.swing.JOptionPane;

/**
 * This class demonstrates a PROBLEM with serialization.
 * For a solution see {@link Direction2} and {@link ReadResolveExample2}.
 * @author jack
 * 
 * @see Direction2
 * @see ReadResolveExample1
 * @see ReadResolveExample2
 */
public class Direction2
    implements Serializable
{
    private static final long serialVersionUID = 5968535303798451851L;
    
    public static final Direction2   NORTH   = new Direction2( 90 );
    public static final Direction2   SOUTH   = new Direction2( 270 );
    public static final Direction2   EAST    = new Direction2( 0 );
    public static final Direction2   WEST    = new Direction2( 180 );
    
    private int degrees;
    
    public static void main( String[] args )
    {
        Direction2   train1Dir   = Direction2.NORTH;
        Direction2   train2Dir   = Direction2.EAST;
        
        whichWay( train1Dir, train2Dir );
        
        byte[]  bArr    = persist( train1Dir );   
        String  msg =   "Demo object has been persisted.\n"
                      + "press OK to restore";
        JOptionPane.showMessageDialog( null, msg );
        
        train1Dir = restore( bArr );
        whichWay( train1Dir, train2Dir );
    }
    
    private static void whichWay( Direction2 dir1, Direction2 dir2 )
    {
        if ( dir1 == Direction2.NORTH )
            System.out.println( "train1 is going north" );
        else
            System.out.println( "train1 is not going north" );
        
        if ( dir2 == Direction2.EAST )
            System.out.println( "train2 is going east" );
        else
            System.out.println( "train2 is not going east" );
    }
    
    private static byte[] persist( Direction2 dir )
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
    
    private static Direction2 restore( byte[] bArr )
    {
        Direction2   dir   = null;
        
        try (
            ByteArrayInputStream bStream = new ByteArrayInputStream( bArr );
            ObjectInputStream oStream = new ObjectInputStream( bStream );
        )
        {
            Object  obj = oStream.readObject();
            if ( !(obj instanceof Direction2) )
                throw new IOException( "Not a GeoPlane object" );
            dir = (Direction2)obj;
        }
        catch ( IOException | ClassNotFoundException exc )
        {
            exc.printStackTrace();
            System.exit( 1 );
        }
        
        return dir;
    }
    
    public Direction2( int degrees )
    {
        this.degrees = degrees;
    }
    public void setDegrees( int degrees )
    {
        this.degrees = degrees;
    }
    
    public int getDegrees()
    {
        return degrees;
    }
    
    @Override
    public boolean equals( Object obj )
    {
        boolean rval    = false;
        
        if ( obj == null)
            rval = false;
        else if ( obj == this )
            rval = true;
        else if ( !(obj instanceof Direction2) )
            rval = false;
        else
            rval = this.degrees == ((Direction2)obj).degrees;
        
        return rval;
    }
    
    @Override
    public int hashCode()
    {
        return degrees;
    }
    
    private Object readResolve() 
        throws ObjectStreamException
    {
        Direction2  dir= new Direction2( degrees );
        if ( dir.equals( NORTH ) )
            dir = NORTH; 
        else if ( dir.equals( SOUTH ) ) 
            dir = SOUTH;
        else if ( dir.equals( EAST) )
            dir = EAST;
        else if ( dir.equals( WEST ) )
            dir = WEST; 
        else 
            ;
        return dir;
    }
}