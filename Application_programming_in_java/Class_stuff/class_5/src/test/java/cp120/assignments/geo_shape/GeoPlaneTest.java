package cp120.assignments.geo_shape;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import org.junit.Test;

public class GeoPlaneTest
{

    @Test
    public void test()
    {
        testType();
        testGetShapes();
        testAddRemove();
        testRedraw();
        testGetContentPane();
    }
    
    private void testType()
    {
        Class<?>    expType     = new Object().getClass();
        Class<?>    superType   = new GeoPlane().getClass().getSuperclass();
        assertEquals( expType, superType );
    }
    
    private void testGetShapes()
    {
        GeoPlane    plane   = new GeoPlane();
        GeoShape    shape   = new GeoRectangle( 0, 0 );
        plane.addShape( shape );
        
        List<GeoShape>  list    = plane.getShapes();
        assertEquals( 1, list.size() );
        assertTrue( list.contains( shape ) );
    }
    
    private void testAddRemove()
    {
        int             count   = 0;
        int             limit   = 10;
        List<GeoShape>  tracker = new ArrayList<>();
        GeoPlane        plane   = new GeoPlane();
        
        for ( count = 0 ; count < limit ; ++count )
        {
            GeoRectangle    shape   = new GeoRectangle( count, count );
            tracker.add( shape );
            plane.addShape( shape );
        }
        
        for ( ; count > 0 ; --count )
        {
            List<GeoShape>  list    = plane.getShapes();
            int             size    = list.size();
            assertEquals( count, list.size() );
            
            for ( int inx = 0 ; inx < size ; ++inx )
                assertEquals( tracker.get( inx ), list.get( inx ) );
            plane.removeShape( tracker.remove( 0 ) );
        }
        
        assertTrue( plane.getShapes().isEmpty() );
    }
    
    private void testGetContentPane()
    {
        GeoPlane    plane   = new GeoPlane();
        JPanel      panel   = plane.getContentPane();
        assertNotNull( panel );
    }
    
    private void testRedraw()
    {
        GeoPlane    plane   = new GeoPlane();
        plane.addShape( new GeoRectangle( 100, 100 ) );
        plane.show();
        plane.redraw();
        Utils.pause(  2000 );
    }
}
