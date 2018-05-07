package serialization;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
public class Direction
    implements Serializable
{
    private static final long serialVersionUID = 5968535303798451851L;
    
    public static final Direction   NORTH   = new Direction( 90 );
    public static final Direction   SOUTH   = new Direction( 270 );
    public static final Direction   EAST    = new Direction( 0 );
    public static final Direction   WEST    = new Direction( 180 );
    
    private int degrees;
    
    public static void main( String[] args )
    {
        Direction   train1Dir   = Direction.NORTH;
        Direction   train2Dir   = Direction.EAST;
        
        whichWay( train1Dir, train2Dir );
        
        byte[]  bArr    = persist( train1Dir );   
        String  msg =   "Demo object has been persisted.\n"
                      + "press OK to restore";
        JOptionPane.showMessageDialog( null, msg );
        
        train1Dir = restore( bArr );
        whichWay( train1Dir, train2Dir );
    }
    
    private static void whichWay( Direction dir1, Direction dir2 )
    {
        if ( dir1 == Direction.NORTH )
            System.out.println( "train1 is going north" );
        else
            System.out.println( "train1 is not going north" );
        
        if ( dir2 == Direction.EAST )
            System.out.println( "train2 is going east" );
        else
            System.out.println( "train2 is not going east" );
    }
    
    private static byte[] persist( Direction dir )
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
    
    private static Direction restore( byte[] bArr )
    {
        Direction   dir   = null;
        
        try (
            ByteArrayInputStream bStream = new ByteArrayInputStream( bArr );
            ObjectInputStream oStream = new ObjectInputStream( bStream );
        )
        {
            Object  obj = oStream.readObject();
            if ( !(obj instanceof Direction) )
                throw new IOException( "Not a GeoPlane object" );
            dir = (Direction)obj;
        }
        catch ( IOException | ClassNotFoundException exc )
        {
            exc.printStackTrace();
            System.exit( 1 );
        }
        
        return dir;
    }
    
    public Direction( int degrees )
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
        else if ( !(obj instanceof Direction) )
            rval = false;
        else
            rval = this.degrees == ((Direction)obj).degrees;
        
        return rval;
    }
    
    @Override
    public int hashCode()
    {
        return degrees;
    }
}