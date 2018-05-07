package cp120.assignments.geo_shape;


import static org.junit.Assert.*;

import java.awt.Graphics2D;
import java.lang.reflect.Modifier;

import org.junit.Test;

import java.awt.Color;

public class GeoShapeTest
{
    @Test
    public void test()
    {
        testType();
        ShapeTester tester  = 
            new ShapeTester( 5, 10, Color.RED, Color.ORANGE, 2.1 );
        tester.test();
        testNullOrigin();
    }
    
    @Test
    public void testEqualsDoubleObject()
    {
        // setup: test1 - test2 < epsilon
        //        test1 - test3 > epsilon
        double      epsilon = .0001;
        double      test1   = 12.0005;
        double      test2   = test1 + epsilon - epsilon / 10;
        double      test3   = test1 + epsilon + epsilon / 10;
        
        MiniTester  mini    = new MiniTester();
        assertTrue( mini.equalsDouble( test1, test1, epsilon ) );
        assertTrue( mini.equalsDouble( test1, test2, epsilon ) );
        assertFalse( mini.equalsDouble( test1, test3, epsilon ) );
        
        String      strObj1 = "aaa";
        String      strObj2 = "aaa";
        String      strObj3 = "bbb";
        Integer     intObj  = 5;
        
        assertTrue( mini.equalsObject( strObj1, strObj1 ) );
        assertTrue( mini.equalsObject( strObj1, strObj2 ) );
        assertTrue( mini.equalsObject( null, null ) );
        
        assertFalse( mini.equalsObject( strObj1, intObj ) );
        assertFalse( mini.equalsObject( strObj1, strObj3 ) );
        assertFalse( mini.equalsObject( strObj1, intObj ) );
        assertFalse( mini.equalsObject( strObj1, null ) );
        assertFalse( mini.equalsObject( null, strObj3 ) );
    }
    
    @Test
    public void testEquals()
    {
        double      epsilon     = .0001;
        GeoPoint    pointA      = new GeoPoint( 10, 20 );
        Color       colorA      = Color.ORANGE;
        Color       edgeColorA  = Color.BLUE;
        double      edgeWidthA  = 5;
        
        GeoPoint    pointB      = new GeoPoint( 20, -30 );
        Color       colorB      = colorA.darker();
        Color       edgeColorB  = edgeColorA.darker();
        double      edgeWidthB  = edgeWidthA - 2 * epsilon;
        
        GeoLine     line        = new GeoLine( pointA, Color.PINK, pointB );
        
        MiniTester  mini1       = new MiniTester( pointA, colorA );
        mini1.setEdgeColor( edgeColorA );
        mini1.setEdgeWidth( edgeWidthA );
        
        MiniTester  mini2       = new MiniTester( pointA, colorA );
        mini2.setEdgeColor( edgeColorA );
        mini2.setEdgeWidth( edgeWidthA );
        
        System.out.println( "***" + mini1 );
        System.out.println( "***" + mini2 );
        
        assertTrue( mini1.equals( mini1, epsilon ) );
        assertTrue( mini1.equals( mini2, epsilon ) );
        assertTrue( mini2.equals( mini1, epsilon ) );
        assertFalse( mini1.equals( null, epsilon ) );
        assertFalse( mini1.equals( line, epsilon ) );
        
        mini2.setOrigin( pointB );
        assertFalse( mini1.equals( mini2, epsilon ) );
        assertFalse( mini2.equals( mini1, epsilon ) );
        mini2.setOrigin( pointA );
        assertTrue( mini1.equals( mini2, epsilon ) );
       
        mini2.setColor( colorB );
        assertFalse( mini1.equals( mini2, epsilon ) );
        assertFalse( mini2.equals( mini1, epsilon ) );
        mini2.setColor( colorA );
        assertTrue( mini1.equals( mini2, epsilon ) );
        
        mini2.setEdgeColor( edgeColorB );
        assertFalse( mini1.equals( mini2, epsilon ) );
        assertFalse( mini2.equals( mini1, epsilon ) );
        mini2.setEdgeColor( edgeColorA );
        assertTrue( mini1.equals( mini2, epsilon ) );
        
        mini2.setEdgeWidth( edgeWidthB );
        assertFalse( mini1.equals( mini2 ) );
        assertFalse( mini2.equals( mini1 ) );
        mini2.setEdgeWidth( edgeWidthA );
        assertTrue( mini2.equals( mini1, epsilon ) );
    }
    
    @Test
    public void testDraw()
    {
        GeoPlane        plane   = new GeoPlane();
        GeoPoint        point1  = new GeoPoint( 10, 20 );
        GeoPoint        point2  = new GeoPoint( 100, 150 );
        GeoLine         line    = new GeoLine( point1, Color.RED, point2 );
        GeoRectangle    rect1   = 
            new GeoRectangle( point1, Color.BLUE, 100, 200 );
        GeoRectangle    rect2   = 
            new GeoRectangle( point2, null, 75, 175 );
        rect2.setEdgeColor( Color.BLACK );
        rect2.setEdgeWidth( 5.5 );
        GeoRectangle    rect3   = 
            new GeoRectangle( point2, Color.CYAN, 200, 100 );
        rect3.setEdgeColor(  Color.GREEN );
        rect3.setEdgeWidth( 0 );
        GeoRectangle    rect4   = 
            new GeoRectangle( point2, Color.MAGENTA, 50, 300 );
        rect4.setEdgeColor( null );
        rect4.setEdgeWidth( 10 );
        
        plane.addShape( line );
        plane.addShape( rect1 );
        plane.addShape( rect2 );
        plane.addShape( rect3 );
        plane.addShape( rect4 );
        plane.addShape( line );
        plane.show();
        Utils.pause( 2000 );
    }
    
    @SuppressWarnings( "unused")
    private void testNullOrigin()
    {
        // Test constructor
        try
        {
            GeoShape    tester = new MiniTester( null );
            fail( "Expected IllegalArgumentException not thrown" );
        }
        catch ( IllegalArgumentException exc )
        {
            // Exception expected; not an error.
        }
        
        // Test setter
        try
        {
            GeoShape    tester = new MiniTester();
            tester.setOrigin( null );
            fail( "Expected IllegalArgumentException not thrown" );
        }
        catch ( IllegalArgumentException exc )
        {
            // Exception expected; not an error.
        }
    }
    
    private void testType()
    {
        try
        {
            Class<?>    geoType         = 
                new MiniTester().getClass().getSuperclass();
            Class<?>    expSuperType    = new Object().getClass();
            Class<?>    actSuperType    = geoType.getSuperclass();
            assertEquals( expSuperType, actSuperType );
            
            assertTrue( Modifier.isAbstract( geoType.getModifiers() ) );
        }
        catch ( Exception exc )
        {
            fail( "failed to detect class type" );
        }
    }
    
    private class MiniTester extends GeoShape
    {
        private static final long serialVersionUID = 428767954656767442L;

        public MiniTester()
        {
            super( GeoShape.DEFAULT_ORIGIN, null );
        }
        
        public MiniTester( GeoPoint origin )
        {
            super( origin, null );
        }
        
        public MiniTester( GeoPoint origin, Color color )
        {
            super( origin, color );
        }
        
        @Override
        public void draw( Graphics2D gtx )
        {
        }
    }
    
    private class ShapeTester extends GeoShape
    {
        private static final long serialVersionUID = -9136656800943035772L;
        
        private final double    xco;
        private final double    yco;
        private final Color     color;
        private final Color     edgeColor;
        private final double    edgeWidth;
        
        public ShapeTester(
                int xco,
                int yco, 
                Color color,
                Color edgeColor, 
                double edgeWidth
        )
        {
            super( GeoShape.DEFAULT_ORIGIN, null );
            this.xco = xco;
            this.yco = yco;
            this.color = color;
            this.edgeColor = edgeColor;
            this.edgeWidth = edgeWidth;
        }
        
        public void test()
        {
            testDefaults();
            testNonDefaults();
        }
        
        private void testDefaults()
        {
            GeoPoint    origin  = getOrigin();
            assertEquals( 0., origin.getXco(), .01 );
            assertEquals( 0., origin.getYco(), .01 );
            assertNull( getColor() );
            assertEquals( 1., getEdgeWidth(), .01 );
            assertNotNull( getEdgeColor() );
            testToString();
        }
        
        private void testNonDefaults()
        {
            GeoPoint    origin  = new GeoPoint( xco, yco );
            setOrigin( origin );
            setColor( color );
            setEdgeColor( edgeColor );
            setEdgeWidth( edgeWidth );

            GeoPoint    temp    = getOrigin();
            assertEquals( xco, temp.getXco(), .01 );
            assertEquals( yco, temp.getYco(), .01 );
            assertEquals( color, getColor() );
            assertEquals( edgeColor, getEdgeColor() );
            assertEquals( edgeWidth, getEdgeWidth(), .01 );
            testToString();
        }
        
        public void testToString()
        {
            testToString( null, null );
            testToString( color, edgeColor );
        }
        
        public void
        testToString(  Color thisColor, Color thisEdgeColor )
        {
            String  originStr       = 
                "origin=" + getOrigin().toString() + ",";
            String  colorStr        = formatColorString( thisColor );
            String  edgeWidthStr    = 
                String.format( ",edgeWidth=%.4f", edgeWidth );
            String  edgeColorStr    = formatColorString( thisEdgeColor );
                    
            setEdgeWidth( edgeWidth );
            setEdgeColor( thisEdgeColor );
            setColor( thisColor );
            String  str             = toString();
            System.out.println( str );
            
            assertTrue( str.contains( originStr ) );
            assertTrue( str.contains( colorStr ) );
            assertTrue( str.contains( edgeWidthStr ) );
            assertTrue( str.contains( edgeColorStr ) );
        }
        
        @Override
        public void draw( Graphics2D gtx )
        {
            // Not used           
        }
    }
    
    private String formatColorString( Color color )
    {
        String  colorStr    =
            color == null ? "null" : 
            Integer.toHexString( color.getRGB() & 0x00ffffff );
        return colorStr;
    }
}
