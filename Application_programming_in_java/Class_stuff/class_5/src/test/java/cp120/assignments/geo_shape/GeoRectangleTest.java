package cp120.assignments.geo_shape;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.awt.Graphics2D;

import javax.swing.JOptionPane;

import org.junit.Test;

public class GeoRectangleTest
{
    @Test
    public void test()
    {
        testType();
        testAccessors();
        testTwoArgCtor();
        testThreeArgCtor();
        testFourArgCtor();
        testToString();
        testDraw();
        testArea();
        testPerimeter();
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
        
        GeoRectangle    rect1   = 
            new GeoRectangle( originA, colorA, widthA, heightA );
        rect1.setEdgeColor( edgeColorA );
        rect1.setEdgeWidth( edgeWidthA );
        
        GeoRectangle    rect2   = 
            new GeoRectangle( originA, colorA, widthA, heightA );
        rect2.setEdgeColor( edgeColorA );
        rect2.setEdgeWidth( edgeWidthA );
        
        assertTrue( rect1.equals( rect1, epsilon ) );
        assertTrue( rect1.equals( rect2, epsilon ) );
        assertTrue( rect2.equals( rect1, epsilon ) );
        assertFalse( rect1.equals( null, epsilon ) );
        assertFalse( rect1.equals( line, epsilon ) );
        
        rect2.setColor( colorB );
        assertFalse( rect1.equals( rect2, epsilon ) );
        assertFalse( rect2.equals( rect1, epsilon ) );
        rect2.setColor( colorA );
        assertTrue( rect1.equals( rect2, epsilon ) );
        
        rect2.setEdgeColor( edgeColorB );
        assertFalse( rect1.equals( rect2, epsilon ) );
        assertFalse( rect2.equals( rect1, epsilon ) );
        rect2.setEdgeColor( edgeColorA );
        assertTrue( rect1.equals( rect2, epsilon ) );
        
        rect2.setEdgeWidth( edgeWidthB );
        assertFalse( rect1.equals( rect2, epsilon ) );
        assertFalse( rect2.equals( rect1, epsilon ) );
        rect2.setEdgeWidth( edgeWidthA );
        assertTrue( rect1.equals( rect2, epsilon ) );
        
        rect2.setWidth( widthB );
        assertFalse( rect1.equals( rect2, epsilon ) );
        assertFalse( rect2.equals( rect1, epsilon ) );
        rect2.setWidth( widthA );
        assertTrue( rect1.equals( rect2, epsilon ) );
        
        rect2.setHeight( heightB );
        assertFalse( rect1.equals( rect2, epsilon ) );
        assertFalse( rect2.equals( rect1, epsilon ) );
        rect2.setHeight( heightA );
        assertTrue( rect1.equals( rect2, epsilon ) );
    }    
    
    private void testType()
    {
        @SuppressWarnings("serial")
        Class<?>    expType     = 
            new GeoShape( GeoShape.DEFAULT_ORIGIN, GeoShape.DEFAULT_COLOR )
            { public void draw( Graphics2D gtx ){} }.getClass().getSuperclass();
        Class<?>    superType   =
            new GeoRectangle( 0, 0 ).getClass().getSuperclass();
        assertEquals( expType, superType );
    }
    
    private void testAccessors()
    {
        double          initWidth   = 10;
        double          initHeight  = 20;
    
        GeoRectangle    rect        =
            new GeoRectangle( initWidth, initHeight );
        assertEquals( initWidth, rect.getWidth(), .01 );
        assertEquals( initHeight, rect.getHeight(), .01 );
        
        double  newWidth    = 20;
        double  newHeight   = 40;
        rect.setWidth( newWidth );
        rect.setHeight( newHeight );
        assertEquals( newWidth, rect.getWidth(), .01 );
        assertEquals( newHeight, rect.getHeight(), .01 );
    }
    
    private void testToString()
    {
        double          xco         = 5.123456;
        double          yco         = 10.123456;
        String          strX        = String.format( "%.4f,", xco );
        String          strY        = String.format( "%.4f", yco );
        GeoPoint        point       = new GeoPoint( xco, yco );
        point.setXco( xco );
        point.setYco( yco );
        
        Color           color       = Color.BLUE;
        double          width       = 6.6;
        double          height      = 7.7;
        String          strWidth    = String.format( "%.4f,", width );
        String          strHeight   = String.format( "%.4f", height );
        String          strColor    = 
            Integer.toHexString( color.getRGB() & 0x00ffffff );
        GeoRectangle    rect        = 
            new GeoRectangle( point, color, width, height );
        
        String  str     = rect.toString();
        assertTrue( str.contains( strX ) );
        assertTrue( str.contains( strColor ) );
        assertTrue( str.contains( strY ) );
        assertTrue( str.contains( strWidth ) );
        assertTrue( str.contains( strHeight ) );
    }
    
    private void testTwoArgCtor()
    {
        double      width   = 10;
        double      height  = 20;
        GeoRectangle     rect    = new GeoRectangle( width, height );
        assertEquals( GeoShape.DEFAULT_ORIGIN, rect.getOrigin() );
        assertEquals( width, rect.getWidth(), .01 );
        assertEquals( height, rect.getHeight(), .01 );
    }
    
    private void testThreeArgCtor()
    {
        double          xco         = 50;
        double          yco         = 100;
        double          width       = 10;
        double          height      = 20;
        GeoPoint        origin      = new GeoPoint( xco, yco );
        GeoRectangle    rect        = new GeoRectangle( origin, width, height );
        GeoPoint        actOrigin   = rect.getOrigin();
        assertEquals( origin, rect.getOrigin() );
        assertEquals( width, rect.getWidth(), .01 );
        assertEquals( height, rect.getHeight(), .01 );
        assertEquals( origin.getXco(), actOrigin.getXco(), .01 );
        assertEquals( origin.getYco(), actOrigin.getYco(), .01 );
    }
    
    private void testFourArgCtor()
    {
        double          xco         = 50;
        double          yco         = 100;
        double          width       = 10;
        double          height      = 20;
        GeoPoint        origin      = new GeoPoint( xco, yco );
        Color           color       = Color.RED;
        GeoRectangle    rect        = new GeoRectangle( origin, color, width, height );
        GeoPoint        actOrigin   = rect.getOrigin();
        Color           actColor    = rect.getColor();
        assertEquals( width, rect.getWidth(), .01 );
        assertEquals( height, rect.getHeight(), .01 );
        assertEquals( origin.getXco(), actOrigin.getXco(), .01 );
        assertEquals( origin.getYco(), actOrigin.getYco(), .01 );
        assertEquals( color, actColor );
    }
    
    private void testArea()
    {
        int limit   = 10;
        for ( int inx = 0 ; inx < limit ; ++inx )
        {
            double          width   = inx;
            double          height  = 2 * width;
            double          expArea = width * height;
            GeoRectangle    rect    = new GeoRectangle( width, height );
            assertEquals( expArea, rect.area(), .01 );
        }
    }
    
    private void testPerimeter()
    {
        int limit   = 10;
        for ( int inx = 0 ; inx < limit ; ++inx )
        {
            double          width   = inx;
            double          height  = 2 * width;
            double          expPeri = 2 * width + 2 * height;
            GeoRectangle    rect    = new GeoRectangle( width, height );
            assertEquals( expPeri, rect.perimeter(), .01 );
        }
    }
    
    private void testDraw()
    {
        GeoPlane        plane   = new GeoPlane();
        GeoPoint        origin  = new GeoPoint( 100, 100 );
        GeoRectangle    rect    = 
            new GeoRectangle( origin, Color.YELLOW, 200, 200 );
        rect.setEdgeColor( Color.ORANGE );
        rect.setEdgeWidth( 5 );
        plane.addShape( rect );
        plane.show();
        JOptionPane.showMessageDialog( null, "Rectangle OK?" );
    }
    
    private void testEquals()
    {
        final double    epsilon     = .0001;
        GeoPoint        origin      = new GeoPoint( 111.11, 222.22 );
        double          width       = 5.5;
        double          height      = 10.1;
        Color           color       = Color.RED;
        Color           edgeColor   = Color.YELLOW;
        double          edgeWidth   = 3.3;
        GeoRectangle    rect1       = 
            new GeoRectangle( origin, color, width, height );
        GeoRectangle    rect2       = 
            new GeoRectangle( origin, color, width, height );
        
        rect1.setEdgeColor( edgeColor );
        rect1.setEdgeWidth( edgeWidth );
        rect2.setEdgeColor( edgeColor );
        rect2.setEdgeWidth( edgeWidth );
        
        assertTrue( rect1.equals( rect2, epsilon ) );
        assertTrue( rect2.equals( rect1, epsilon ) );
        
        double  xco = origin.getXco() + .5;
        rect2.setOrigin( new GeoPoint( xco, origin.getYco() ) );
        assertFalse( rect1.equals( rect2, epsilon ) );
        assertFalse( rect2.equals( rect1, epsilon ) );
        rect2.setOrigin( origin );
        assertTrue( rect1.equals( rect2, epsilon ) );
        
        rect2.setWidth( width + .5 );
        assertFalse( rect1.equals( rect2, epsilon ) );
        assertFalse( rect2.equals( rect1, epsilon ) );
        rect2.setWidth(  width );
        assertTrue( rect1.equals( rect2, epsilon ) );
        
        rect2.setHeight( height + .5 );
        assertFalse( rect1.equals( rect2, epsilon ) );
        assertFalse( rect2.equals( rect1, epsilon ) );
        rect2.setHeight(  height );
        assertTrue( rect1.equals( rect2, epsilon ) );
        
        rect2.setEdgeWidth( edgeWidth + .5 );
        assertFalse( rect1.equals( rect2, epsilon ) );
        assertFalse( rect2.equals( rect1, epsilon ) );
        rect2.setEdgeWidth( edgeWidth );
        assertTrue( rect1.equals( rect2, epsilon ) );
        
        rect2.setColor( color.darker() );
        assertFalse( rect1.equals( rect2, epsilon ) );
        assertFalse( rect2.equals( rect1, epsilon ) );
        rect2.setColor( null );
        assertFalse( rect1.equals( rect2, epsilon ) );
        assertFalse( rect2.equals( rect1, epsilon ) );
        rect1.setColor( null );
        assertTrue( rect1.equals( rect2, epsilon ) );
        assertTrue( rect2.equals( rect1, epsilon ) );
        rect1.setColor( color );
        rect2.setColor( color );
        assertTrue( rect1.equals( rect2, epsilon ) );
        
        rect2.setEdgeColor( edgeColor.darker() );
        assertFalse( rect1.equals( rect2, epsilon ) );
        assertFalse( rect2.equals( rect1, epsilon ) );
        rect2.setEdgeColor( null );
        assertFalse( rect1.equals( rect2, epsilon ) );
        assertFalse( rect2.equals( rect1, epsilon ) );
        rect1.setEdgeColor( null );
        assertTrue( rect1.equals( rect2, epsilon ) );
        assertTrue( rect2.equals( rect1, epsilon ) );
        rect1.setEdgeColor( edgeColor );
        rect2.setEdgeColor( edgeColor );
        assertTrue( rect1.equals( rect2, epsilon ) );
    }
}
