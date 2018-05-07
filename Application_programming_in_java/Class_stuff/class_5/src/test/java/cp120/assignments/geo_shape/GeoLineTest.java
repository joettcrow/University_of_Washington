package cp120.assignments.geo_shape;

import static cp120.assignments.geo_shape.GeoShape.DEFAULT_COLOR;
import static cp120.assignments.geo_shape.GeoShape.DEFAULT_ORIGIN;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.awt.Graphics2D;

import javax.swing.JOptionPane;

import org.junit.Test;

public class GeoLineTest
{
    @Test
    public void test()
    {
        testType();
        testTwoArgCtor();
        testThreeArgCtor();
        testAccessors();
        testSlopeLength();
        testToString();
        testDraw();
        testEquals();
    }
    
    @Test
    public void testEqualsGeoShapeDouble()
    {
        double      epsilon     = .0001;
        
        GeoPoint    originA     = new GeoPoint( 10, 20 );
        GeoPoint    endA        = new GeoPoint( 20, 30 );
        Color       colorA      = Color.RED;
        
        GeoPoint    originB     = new GeoPoint( 20, 30 );
        GeoPoint    endB        = new GeoPoint( 30, 40 );
        Color       colorB      = colorA.darker();
        
        GeoLine     line1       = new GeoLine( originA, colorA, endA );
        GeoLine     line2       = new GeoLine( originA, colorA, endA );
        
        GeoRectangle    rect    = new GeoRectangle( originA, 5, 10 );
        
        assertTrue( line1.equals( line1, epsilon ) );
        assertTrue( line1.equals( line2, epsilon ) );
        assertTrue( line2.equals( line1, epsilon ) );
        assertFalse( line1.equals( null, epsilon ) );
        assertFalse( line1.equals( rect, epsilon ) );
        
        line2.setStart( originB );
        assertFalse( line1.equals( line2, epsilon ) );
        assertFalse( line2.equals( line1, epsilon ) );
        line2.setStart( originA );
        assertTrue( line1.equals( line2, epsilon ) );
        
        line2.setEnd( endB );
        assertFalse( line1.equals( line2, epsilon ) );
        assertFalse( line2.equals( line1, epsilon ) );
        line2.setEnd( endA );
        assertTrue( line1.equals( line2, epsilon ) );
        
        line2.setColor( colorB );
        assertFalse( line1.equals( line2, epsilon ) );
        assertFalse( line2.equals( line1, epsilon ) );
        line2.setColor( colorA );
        assertTrue( line1.equals( line2, epsilon ) );
    }

    private void testType()
    {
        @SuppressWarnings("serial")
        GeoShape    shape       = 
            new GeoShape( DEFAULT_ORIGIN, DEFAULT_COLOR )
                { public void draw( Graphics2D gtx ){} };
        GeoLine     line        =
            new GeoLine( DEFAULT_ORIGIN, DEFAULT_ORIGIN );
        Class<?>    expType     = shape.getClass().getSuperclass();
        Class<?>    superType   = line.getClass().getSuperclass();
        assertEquals( expType, superType );
    }
    
    private void testTwoArgCtor()
    {
        double      startX      = 10;
        double      startY      = 20;
        double      endX        = 10;
        double      endY        = 20;
        GeoPoint    expStart    = new GeoPoint( startX, startY );
        GeoPoint    expEnd      = new GeoPoint( endX, endY );
        GeoLine     line        = new GeoLine( expStart, expEnd );
        
        GeoPoint    actStart    = line.getStart();
        GeoPoint    actEnd      = line.getEnd();
        Color       actColor    = line.getColor();
        assertEquals( startX, actStart.getXco(), .01 );
        assertEquals( startY, actStart.getYco(), .01 );
        assertEquals( endX, actEnd.getXco(), .01 );
        assertEquals( endY, actEnd.getYco(), .01 );
        assertEquals( DEFAULT_COLOR, actColor );
    }
    
    private void testThreeArgCtor()
    {
        double      startX      = 10;
        double      startY      = 20;
        double      endX        = 10;
        double      endY        = 20;
        GeoPoint    expStart    = new GeoPoint( startX, startY );
        GeoPoint    expEnd      = new GeoPoint( endX, endY );
        Color       expColor    = Color.RED;
        GeoLine     line        = new GeoLine( expStart, expColor, expEnd );
        
        GeoPoint    actStart    = line.getStart();
        GeoPoint    actEnd      = line.getEnd();
        Color       actColor    = line.getColor();
        assertEquals( startX, actStart.getXco(), .01 );
        assertEquals( startY, actStart.getYco(), .01 );
        assertEquals( endX, actEnd.getXco(), .01 );
        assertEquals( endY, actEnd.getYco(), .01 );
        assertEquals( expColor, actColor );
    }
    
    private void testAccessors()
    {
        GeoLine     line        = new GeoLine( DEFAULT_ORIGIN, DEFAULT_ORIGIN );
        double      startX      = 10;
        double      startY      = 20;
        double      endX        = 10;
        double      endY        = 20;
        GeoPoint    expStart    = new GeoPoint( startX, startY );
        GeoPoint    expEnd      = new GeoPoint( endX, endY );
        
        line.setStart( expStart );
        line.setEnd( expEnd );
        
        GeoPoint    actStart    = line.getStart();
        GeoPoint    actEnd      = line.getEnd();
        assertEquals( startX, actStart.getXco(), .01 );
        assertEquals( startY, actStart.getYco(), .01 );
        assertEquals( endX, actEnd.getXco(), .01 );
        assertEquals( endY, actEnd.getYco(), .01 );
    }
    
    private void testSlopeLength()
    {
        new LineTester( 100, 100, 200, 200 ).test();
        new LineTester( 200, 200, 100, 100 ).test();
        new LineTester( 100, 100, 100, 200 ).test();
        new LineTester( 100, 100, 200, 100 ).test();
    }
    
    private class LineTester
    {
        private final double    xco1;
        private final double    yco1;
        private final double    xco2;
        private final double    yco2;
        private final double    diffX;
        private final double    diffY;
        
        public LineTester( double xco1, double yco1, double xco2, double yco2 )
        {
            this.xco1 = xco1;
            this.yco1 = yco1;
            this.xco2 = xco2;
            this.yco2 = yco2;
            diffX = xco2 - xco1;
            diffY = yco2 - yco1;
        }
        
        public void test()
        {
            double  expSlope    = diffY / diffX;
            double  diffXSq     = diffX * diffX;
            double  diffYSq     = diffY * diffY;
            double  expLen      = Math.sqrt( diffXSq + diffYSq );
            
            GeoPoint    start   = new GeoPoint( xco1, yco1 );
            GeoPoint    end     = new GeoPoint( xco2, yco2 );
            GeoLine     line    = new GeoLine( start, end );
            
            System.out.println( "len: " + expLen + " slope: " + expSlope );
            
            assertEquals( expSlope, line.slope(), .01 );
            assertEquals( expLen, line.length(), .01 );
        }
        
//        public void testLen
    }
    
    private void testDraw()
    {
        GeoPoint    start   = new GeoPoint( 100, 200 );
        GeoPoint    end     = new GeoPoint( 400, 500 );
        GeoLine     line    = new GeoLine( start, Color.GREEN, end );
        GeoPlane    plane   = new GeoPlane();
        plane.addShape( line );
        plane.show();
        
        JOptionPane.showMessageDialog( null, "OK?" );
    }
    
    private void testToString()
    {
        double          startX      = 5.123456;
        double          startY      = 10.123456;
        double          endX        = 15.333333;
        double          endY        = 20.3333666;
        String          strStartX   = String.format( "%.4f,", startX );
        String          strStartY   = String.format( "%.4f", startY );
        String          strEndX     = String.format( "%.4f,", startX );
        String          strEndY     = String.format( "%.4f", startY );
        GeoPoint        start       = new GeoPoint( startX, startY );
        GeoPoint        end         = new GeoPoint( endX, endY );        
        Color           color       = Color.BLUE;
        String          strColor    = 
            Integer.toHexString( color.getRGB() & 0x00ffffff );
        GeoLine         line        = 
            new GeoLine( start, color, end );
        
        String  str     = line.toString();
        assertTrue( str.contains( strStartX ) );
        assertTrue( str.contains( strStartY ) );
        assertTrue( str.contains( strColor ) );
        assertTrue( str.contains( strEndX ) );
        assertTrue( str.contains( strEndY ) );
    }
    
    private void testEquals()
    {
        final double    epsilon     = .0001;
        
        GeoPoint    start       = new GeoPoint( 111.11, 222.22 );
        GeoPoint    end         = new GeoPoint( -44.55, 122.1 );
        Color       edgeColor   = Color.YELLOW;
        double      edgeWidth   = 3.3;
        GeoLine     line1       = new GeoLine( start, edgeColor, end );
        GeoLine     line2       = new GeoLine( start, edgeColor, end );
        
        line1.setEdgeWidth( edgeWidth );
        line2.setEdgeWidth( edgeWidth );
        
        assertTrue( line1.equals( line2, epsilon ) );
        assertTrue( line2.equals( line1, epsilon ) );
        
        double  xco = start.getXco() + .5;
        line2.setOrigin( new GeoPoint( xco, start.getYco() ) );
        assertFalse( line1.equals( line2, epsilon ) );
        assertFalse( line2.equals( line1, epsilon ) );
        line2.setOrigin( start );
        assertTrue( line1.equals( line2, epsilon ) );
        
        xco = end.getXco();
        line2.setEnd( new GeoPoint( xco, start.getYco() ) );
        assertFalse( line1.equals( line2, epsilon ) );
        assertFalse( line2.equals( line1, epsilon ) );
        line2.setEnd( end );
        assertTrue( line1.equals( line2, epsilon ) );
        
        line2.setEdgeWidth( edgeWidth + .5 );
        assertFalse( line1.equals( line2, epsilon ) );
        assertFalse( line2.equals( line1, epsilon ) );
        line2.setEdgeWidth( edgeWidth );
        assertTrue( line1.equals( line2, epsilon ) );
        
        line2.setEdgeColor( edgeColor.darker() );
        assertFalse( line1.equals( line2, epsilon ) );
        assertFalse( line2.equals( line1, epsilon ) );
        line2.setEdgeColor( null );
        assertFalse( line1.equals( line2, epsilon ) );
        assertFalse( line2.equals( line1, epsilon ) );
        line1.setEdgeColor( null );
        assertTrue( line1.equals( line2, epsilon ) );
        assertTrue( line2.equals( line1, epsilon ) );
        line1.setEdgeColor( edgeColor );
        line2.setEdgeColor( edgeColor );
        assertTrue( line1.equals( line2, epsilon ) );
    }
}
