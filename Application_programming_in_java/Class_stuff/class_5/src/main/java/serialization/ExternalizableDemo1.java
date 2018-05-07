package serialization;

import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JOptionPane;

import cp120.assignments.geo_shape.GeoPlane;
import cp120.assignments.geo_shape.GeoPoint;
import cp120.assignments.geo_shape.GeoRectangle;

public class ExternalizableDemo1
{
    public static void main(String[] args)
    {
        GeoPlane        plane   = new GeoPlane();
        GeoPoint        origin  = new GeoPoint( 128, 128 );
        GeoRectangle    rect    = 
            new GeoRectangle( origin, Color.RED, 256, 128 );
        rect.setEdgeColor( Color.GREEN.darker() );
        rect.setEdgeWidth( 4 );
        plane.addShape( rect );
        plane.show();
        
        String  msg = "Press OK to serialize and close the GeoPlane";
        JOptionPane.showConfirmDialog( null, msg );
        
        byte[]  bArr    = persist( plane );
        plane.setVisible( false );
        
        msg = "Press OK to deserialize and display the GeoPlane";
        JOptionPane.showConfirmDialog( null, msg );
        plane = restore( bArr );
        plane.setVisible( true );
    }
    
    private static byte[] persist( GeoPlane plane )
    {
        byte[]  rval    = null;
        try(
            ByteArrayOutputStream bStream = new ByteArrayOutputStream();
            ObjectOutputStream oStream = new ObjectOutputStream( bStream );
        )
        {
            oStream.writeObject( plane );
            rval = bStream.toByteArray();
        }
        catch ( IOException exc )
        {
            exc.printStackTrace();
            System.exit( 1 );
        }
        
        return rval;
    }
    
    private static GeoPlane restore( byte[] bArr )
    {
        GeoPlane    plane   = null;
        
        try (
            ByteArrayInputStream bStream = new ByteArrayInputStream( bArr );
            ObjectInputStream oStream = new ObjectInputStream( bStream );
        )
        {
            Object  obj = oStream.readObject();
            if ( !(obj instanceof GeoPlane) )
                throw new IOException( "Not a GeoPlane object" );
            plane = (GeoPlane)obj;
        }
        catch ( IOException | ClassNotFoundException exc )
        {
            exc.printStackTrace();
            System.exit( 1 );
        }
        
        return plane;
    }
}
