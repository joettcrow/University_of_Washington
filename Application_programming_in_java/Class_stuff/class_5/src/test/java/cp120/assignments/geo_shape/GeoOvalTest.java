package cp120.assignments.geo_shape;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Color;

import javax.swing.JOptionPane;

import org.junit.Test;

public class GeoOvalTest
{
    @Test
    public void test()
    {
        testType();
        testTwoArgCtor();
        testThreeArgCtor();
        testFourArgCtor();
        testDraw();
        testPerimeter();
        testArea();
        testEquals();
    }
    
    @Test
    public void testEqualsGeoShapeDouble()
    {
        double      epsilon     = .0001;
        
        GeoPoint    originA     = new GeoPoint( 10, 20 );
        double      widthA      = 100;
        double      heightA     = 200;
        Color       colorA      = Color.RED;
        Color       edgeColorA  = Color.GREEN;
        double      edgeWidthA  = 2.2;
        
        GeoPoint    originB     = new GeoPoint( 20, 10 );
        double      widthB      = widthA + 50;
        double      heightB     = heightA + 50;
        Color       colorB      = colorA.darker();
        Color       edgeColorB  = edgeColorA.darker();
        double      edgeWidthB  = edgeWidthA + .4;
        
        GeoLine     line        = new GeoLine( originA, colorA, originB );
        
        GeoOval     oval1   = 
            new GeoOval( originA, colorA, widthA, heightA );
        oval1.setEdgeColor( edgeColorA );
        oval1.setEdgeWidth( edgeWidthA );
        
        GeoOval     oval2   = 
            new GeoOval( originA, colorA, widthA, heightA );
        oval2.setEdgeColor( edgeColorA );
        oval2.setEdgeWidth( edgeWidthA );
        
        assertTrue( oval1.equals( oval1, epsilon ) );
        assertTrue( oval1.equals( oval2, epsilon ) );
        assertTrue( oval2.equals( oval1, epsilon ) );
        assertFalse( oval1.equals( null, epsilon ) );
        assertFalse( oval1.equals( line, epsilon ) );
        
        oval2.setColor( colorB );
        assertFalse( oval1.equals( oval2, epsilon ) );
        assertFalse( oval2.equals( oval1, epsilon ) );
        oval2.setColor( colorA );
        assertTrue( oval1.equals( oval2, epsilon ) );
        
        oval2.setEdgeColor( edgeColorB );
        assertFalse( oval1.equals( oval2, epsilon ) );
        assertFalse( oval2.equals( oval1, epsilon ) );
        oval2.setEdgeColor( edgeColorA );
        assertTrue( oval1.equals( oval2, epsilon ) );
        
        oval2.setEdgeWidth( edgeWidthB );
        assertFalse( oval1.equals( oval2, epsilon ) );
        assertFalse( oval2.equals( oval1, epsilon ) );
        oval2.setEdgeWidth( edgeWidthA );
        assertTrue( oval1.equals( oval2, epsilon ) );
        
        oval2.setWidth( widthB );
        assertFalse( oval1.equals( oval2, epsilon ) );
        assertFalse( oval2.equals( oval1, epsilon ) );
        oval2.setWidth( widthA );
        assertTrue( oval1.equals( oval2, epsilon ) );
        
        oval2.setHeight( heightB );
        assertFalse( oval1.equals( oval2, epsilon ) );
        assertFalse( oval2.equals( oval1, epsilon ) );
        oval2.setHeight( heightA );
        assertTrue( oval1.equals( oval2, epsilon ) );
    }    
    
    private void testType()
    {
        Class<?>    expType     = new GeoRectangle( 0, 0 ).getClass();
        Class<?>    superType   = 
            new GeoOval( 0, 0 ).getClass().getSuperclass();
        assertEquals( expType, superType );
    }
    
    private void testDraw()
    {
        GeoPlane        plane   = new GeoPlane();
        GeoPoint        origin  = new GeoPoint( 100, 100 );
        GeoOval         oval    = 
            new GeoOval( origin, Color.YELLOW, 200, 200 );
        oval.setEdgeColor( Color.ORANGE );
        oval.setEdgeWidth( 5 );
        plane.addShape( oval );
        plane.show();
        JOptionPane.showMessageDialog( null, "Oval OK?" );
    }
    
    private void testTwoArgCtor()
    {
        double      width   = 10;
        double      height  = 20;
        GeoOval     oval    = new GeoOval( width, height );
        assertEquals( GeoShape.DEFAULT_ORIGIN, oval.getOrigin() );
        assertEquals( width, oval.getWidth(), .01 );
        assertEquals( height, oval.getHeight(), .01 );
    }
    
    private void testThreeArgCtor()
    {
        double      xco         = 50;
        double      yco         = 100;
        double      width       = 10;
        double      height      = 20;
        GeoPoint    origin      = new GeoPoint( xco, yco );
        GeoOval     oval        = new GeoOval( origin, width, height );
        GeoPoint    actOrigin   = oval.getOrigin();
        assertEquals( origin, oval.getOrigin() );
        assertEquals( width, oval.getWidth(), .01 );
        assertEquals( height, oval.getHeight(), .01 );
        assertEquals( origin.getXco(), actOrigin.getXco(), .01 );
        assertEquals( origin.getYco(), actOrigin.getYco(), .01 );
    }
    
    private void testFourArgCtor()
    {
        double      xco         = 50;
        double      yco         = 100;
        double      width       = 10;
        double      height      = 20;
        GeoPoint    origin      = new GeoPoint( xco, yco );
        Color       color       = Color.RED;
        GeoOval     oval        = new GeoOval( origin, color, width, height );
        GeoPoint    actOrigin   = oval.getOrigin();
        Color       actColor    = oval.getColor();
        assertEquals( width, oval.getWidth(), .01 );
        assertEquals( height, oval.getHeight(), .01 );
        assertEquals( origin.getXco(), actOrigin.getXco(), .01 );
        assertEquals( origin.getYco(), actOrigin.getYco(), .01 );
        assertEquals( color, actColor );
    }
    
    private void testPerimeter()
    {
        double[][]  testData    =
        {
            { 5.1, 10.2 },
            { 6.25, 11.3 },
            { 7.5, 3.1 },
            { 111.22, 53.75 }
        };
        for ( double[] test : testData )
        {
            GeoOval oval    = new GeoOval( test[0], test[1] );
            assertEquals( perimeter( test ), oval.perimeter(), .0001 );
        }
    }
    
    private double perimeter(  double[] data )
    {
        double  width       = data[0];
        double  height      = data[1];
        double  halfWidth   = width / 2;
        double  halfHeight  = height / 2;
        double  sumSquares  = halfWidth * halfWidth + halfHeight * halfHeight;
        double  radical     = Math.sqrt( sumSquares / 2 );
        double  peri        = radical * 2 * Math.PI;
        return peri;
    }
    
    private void testArea()
    {
        double[][]  testData    =
        {
            { 5.1, 10.2 },
            { 6.25, 11.3 },
            { 7.5, 3.1 },
            { 111.22, 53.75 }
        };
        for ( double[] test : testData )
        {
            GeoOval oval    = new GeoOval( test[0], test[1] );
            assertEquals( area( test ), oval.area(), .0001 );
        }
    }
    
    private double area( double[] data )
    {
        double  width       = data[0];
        double  height      = data[1];
        double  halfWidth   = width / 2;
        double  halfHeight  = height / 2;
        double  area        = halfWidth * halfHeight * Math.PI;
        return area;
    }
    
    private void testEquals()
    {
        final double    epsilon     = .0001;
        
        GeoPoint    origin      = new GeoPoint( 111.11, 222.22 );
        double      width       = 5.5;
        double      height      = 10.1;
        Color       color       = Color.RED;
        Color       edgeColor   = Color.YELLOW;
        double      edgeWidth   = 3.3;
        GeoOval     oval1       = new GeoOval( origin, color, width, height );
        GeoOval     oval2       = new GeoOval( origin, color, width, height );
        
        oval1.setEdgeColor( edgeColor );
        oval1.setEdgeWidth( edgeWidth );
        oval2.setEdgeColor( edgeColor );
        oval2.setEdgeWidth( edgeWidth );
        
        assertTrue( oval1.equals( oval2, epsilon ) );
        assertTrue( oval2.equals( oval1, epsilon ) );
        
        double  xco = origin.getXco() + .5;
        oval2.setOrigin( new GeoPoint( xco, origin.getYco() ) );
        assertFalse( oval1.equals( oval2, epsilon ) );
        assertFalse( oval2.equals( oval1, epsilon ) );
        oval2.setOrigin( origin );
        assertTrue( oval1.equals( oval2, epsilon ) );
        
        oval2.setWidth( width + .5 );
        assertFalse( oval1.equals( oval2, epsilon ) );
        assertFalse( oval2.equals( oval1, epsilon ) );
        oval2.setWidth(  width );
        assertTrue( oval1.equals( oval2, epsilon ) );
        
        oval2.setHeight( height + .5 );
        assertFalse( oval1.equals( oval2, epsilon ) );
        assertFalse( oval2.equals( oval1, epsilon ) );
        oval2.setHeight(  height );
        assertTrue( oval1.equals( oval2, epsilon ) );
        
        oval2.setEdgeWidth( edgeWidth + .5 );
        assertFalse( oval1.equals( oval2, epsilon ) );
        assertFalse( oval2.equals( oval1, epsilon ) );
        oval2.setEdgeWidth( edgeWidth );
        assertTrue( oval1.equals( oval2, epsilon ) );
        
        oval2.setColor( color.darker() );
        assertFalse( oval1.equals( oval2, epsilon ) );
        assertFalse( oval2.equals( oval1, epsilon ) );
        oval2.setColor( null );
        assertFalse( oval1.equals( oval2, epsilon ) );
        assertFalse( oval2.equals( oval1, epsilon ) );
        oval1.setColor( null );
        assertTrue( oval1.equals( oval2, epsilon ) );
        assertTrue( oval2.equals( oval1, epsilon ) );
        oval1.setColor( color );
        oval2.setColor( color );
        assertTrue( oval1.equals( oval2, epsilon ) );
        
        oval2.setEdgeColor( edgeColor.darker() );
        assertFalse( oval1.equals( oval2, epsilon ) );
        assertFalse( oval2.equals( oval1, epsilon ) );
        oval2.setEdgeColor( null );
        assertFalse( oval1.equals( oval2, epsilon ) );
        assertFalse( oval2.equals( oval1, epsilon ) );
        oval1.setEdgeColor( null );
        assertTrue( oval1.equals( oval2, epsilon ) );
        assertTrue( oval2.equals( oval1, epsilon ) );
        oval1.setEdgeColor( edgeColor );
        oval2.setEdgeColor( edgeColor );
        assertTrue( oval1.equals( oval2, epsilon ) );
    }
}
