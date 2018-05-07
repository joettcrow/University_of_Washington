package cp120.assignments.geo_shape;

import static org.junit.Assert.*;

import org.junit.Test;

public class GeoPointTest
{
    private static final double DELTA  = .001;

    @Test
    public void test()
    {
        testType();
        testAccessors();
        testDistance();
        testToString();
        testEquals();
    }
    
    private void testAccessors()
    {
        GeoPoint    point   = new GeoPoint( 0, 0 );
        assertEquals( 0., point.getXco(), DELTA );
        assertEquals( 0., point.getYco(), DELTA );
        
        double  xco = 3;
        double  yco = 5;
        point.setXco( xco );
        point.setYco( yco );
        assertEquals( xco, point.getXco(), DELTA );
        assertEquals( yco, point.getYco(), DELTA );
    }
    
    private void testDistance()
    {
        double      xco1    = 10;
        double      yco1    = 10;
        double      xco2    = xco1 + 3;
        double      yco2    = yco1 + 4;
        double      expLen  = 5;
        GeoPoint    point1  = new GeoPoint( xco1, yco1 );
        GeoPoint    point2  = new GeoPoint( xco2, yco2 );
        
        assertEquals( expLen, point1.distance( point2 ), .01 );
        assertEquals( expLen, point2.distance( point1 ), .01 );
        assertEquals( 0, point2.distance( null ), .01 );
    }
    
    private void testToString()
    {
        double      xco     = 5.123456;
        double      yco     = 10.123456;
        GeoPoint    point   = new GeoPoint( xco, yco );
        
        String  strX    = String.format( "%.4f,", xco );
        String  strY    = String.format( "%.4f", yco );
        String  str     = point.toString();
        assertTrue( str.contains( strX ) );
        assertTrue( str.contains( strY ) );
    }
    
    private void testEquals()
    {
        double      epsilon = .0001;
        double      xco     = 5.5;
        double      yco     = 6.6;
        double      diffXco = xco + .25;
        double      diffYco = yco + .25;
        GeoPoint    point1  = new GeoPoint( xco, yco );
        GeoPoint    point2  = new GeoPoint( xco, yco );
        
        assertFalse( point1.equals( null, epsilon ) );
        assertTrue( point1.equals( point1, epsilon ) );
        assertTrue( point1.equals( point2, epsilon ) );
        assertTrue( point2.equals( point1, epsilon ) );
        
        point2.setXco( diffXco );
        assertFalse( point1.equals( point2 ) );
        assertFalse( point2.equals( point1 ) );
        point2.setXco( xco );
        assertTrue( point1.equals( point2, epsilon ) );
        
        point2.setYco( diffYco );
        assertFalse( point1.equals( point2 ) );
        assertFalse( point2.equals( point1 ) );
        point2.setYco( yco );
        assertTrue( point1.equals( point2, epsilon ) );
    }
    
    private void testType()
    {
        Class<?>    expType     = new Object().getClass();
        Class<?>    superType   =
            GeoShape.DEFAULT_ORIGIN.getClass().getSuperclass();
        assertEquals( expType, superType );
    }
}
