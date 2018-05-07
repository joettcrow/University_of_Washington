package app;

import java.awt.Color;

import cp120.assignments.geo_shape.GeoLine;
import cp120.assignments.geo_shape.GeoOval;
import cp120.assignments.geo_shape.GeoPlane;
import cp120.assignments.geo_shape.GeoPoint;
import cp120.assignments.geo_shape.GeoRectangle;
import cp120.assignments.geo_shape.GeoShape;

/**
 * A simple program to exercise the geo_shape package.
 * 
 * @author jack
 */
public class ShapeDriver
{    
    /** The plane to draw on. */
    private GeoPlane    plane   = new GeoPlane();
    
    /**
     * Program entry point.
     *
     * @param args command line arguments; not used.
     */
    public static void main(String[] args)
    {
        new ShapeDriver().execute();
    }
    
    /**
     * Exercise the geo_shape package.
     */
    private void execute()
    {
        newOval( 1.1, 2.2, Color.RED, 10.3, 20.7 );
        newRect( 3.3, 4.4, Color.GREEN, 10.3, 20.7 );
        newOval( 5.5, 6.6, Color.BLUE, 15.2, 31.6 );
        newRect( 5.5, 6.6, Color.BLUE, 15.2, 31.6 );
        newOval( 9.9, 10.01, Color.CYAN, 91.4, 50.2 );
        newRect( 9.9, 10.01, Color.CYAN, 91.4, 50.2 );
        newRect( 12.02, 13.03, Color.BLACK, 23.6, 17.9 );
        newRect( 13.02, 14.03, Color.WHITE, 4.5, 6.2 );
        newOval( 12.02, 13.03, Color.BLACK, 23.6, 17.9 );
        newOval( 13.02, 14.03, Color.WHITE, 4.5, 6.2 );
        newLine( 1.1, 2.2, Color.RED, 10.3, 20.7 );
        newLine( 3.3, 4.4, Color.GREEN, 10.3, 20.7 );
        newLine( 5.5, 6.6, Color.BLUE, 15.2, 31.6 );
        plane.redraw();
        
        GeoPoint    point1  = new GeoPoint( 5, 6 );
        GeoPoint    point2  = new GeoPoint( 5, 6 );
        GeoPoint    point3  = new GeoPoint( 1, 5.11119 );
        GeoPoint    point4  = new GeoPoint( 1, 5.1112345);
        
        geoPointEqualsCheck( point1, point1 );
        geoPointEqualsCheck( point1, point2 );
        geoPointEqualsCheck( point2, point1 );
        geoPointEqualsCheck( point1, point3 );
        geoPointEqualsCheck( point1, null );
        geoPointEqualsCheck( point1, point3 );
        geoPointEqualsCheck( point3, point4 );
        
        GeoRectangle    rect1   = new GeoRectangle( point1, Color.RED, 5, 10 );
        GeoRectangle    rect2   = new GeoRectangle( point1, Color.RED, 5, 10 );
        GeoRectangle    rect3   = new GeoRectangle( point2, Color.RED, 5, 10 );
        geoRectangleEqualsCheck( rect1, rect2 );
        geoRectangleEqualsCheck( rect1, rect3 );
        geoRectangleEqualsCheck( rect1, null );
        
        GeoOval     oval1   = new GeoOval( point1, Color.RED, 5, 10 );
        GeoOval     oval2   = new GeoOval( point1, Color.RED, 5, 10 );
        GeoOval     oval3   = new GeoOval( point2, Color.RED, 5, 10 );
        geoOvalEqualsCheck( oval1, oval2 );
        geoOvalEqualsCheck( oval1, oval3 );
        geoOvalEqualsCheck( oval1, null );
        
        GeoLine     line1   = new GeoLine( point1, Color.RED, point2 );
        GeoLine     line2   = new GeoLine( point1, Color.RED, point2 );
        GeoLine     line3   = new GeoLine( point2, Color.RED, point3 );
        geoLineEqualsCheck( line1, line2 );
        geoLineEqualsCheck( line1, line3 );
        geoLineEqualsCheck( line1, null );
    }
    
    private void geoPointEqualsCheck( GeoPoint pointA, GeoPoint pointB )
    {
        final double    epsilon = .0001;
        System.out.print( "GeoPoint equality check:" );
        System.out.print(  pointA + "/" + pointB + ": " );
        System.out.println( pointA.equals( pointB, epsilon ) );
    }
    
    private void 
    geoRectangleEqualsCheck( GeoRectangle rectA, GeoShape rectB )
    {
        final double    epsilon = .0001;
        System.out.println( "GeoRectangle equality check:" );
        System.out.println(  "    " + rectA );
        System.out.println(  "    " + rectB );
        System.out.println(  "    " + rectA.equals( rectB, epsilon ) );
    }
    
    private void 
    geoOvalEqualsCheck( GeoOval ovalA, GeoShape ovalB )
    {
        final double    epsilon = .0001;
        System.out.println( "GeoOval equality check:" );
        System.out.println( "    " + ovalA );
        System.out.println( "    " + ovalB );
        System.out.println( "    " + ovalA.equals( ovalB, epsilon ) );
    }
    
    private void 
    geoLineEqualsCheck( GeoLine lineA, GeoShape lineB )
    {
        final double    epsilon = .0001;
        System.out.println( "GeoLine equality check:" );
        System.out.println( "    " + lineA );
        System.out.println( "    " + lineB );
        System.out.println( "    " + lineA.equals( lineB, epsilon ) );
    }
    
    /**
     * Create a new oval, and add it to the drawing plane.
     *
     * @param xco   the x-coordinate of the upper left hand corner of the oval
     * @param yco   the y-coordinate of the upper left hand corner of the oval
     * @param color the color of the oval
     */
    private void 
    newOval( double xco, double yco, Color color, double wid, double hei )
    {
        GeoPoint    origin  = new GeoPoint( xco, yco );
        GeoOval     oval    = new GeoOval( origin, color, wid, hei );
        plane.addShape( oval );
        System.out.println( "GeoOval: " + oval );
        System.out.printf(  "    area = %.04f\n", oval.area() );
        System.out.printf(  "    peri = %.04f\n", oval.perimeter() );
    }
    
    /**
     * Create a new rectangle, and add it to the drawing plane.
     *
     * @param xco   
     *     the x-coordinate of the upper left hand corner of the oval
     * @param yco
     *     the y-coordinate of the upper left hand corner of the oval
     * @param color the color of the oval
     */
    private void 
    newRect( double xco, double yco, Color color, double wid, double hei )
    {
        GeoPoint        origin  = new GeoPoint( xco, yco );
        GeoRectangle    rect    = new GeoRectangle( origin, color, wid, hei );
        plane.addShape( rect );
        System.out.println( "GeoRectangle: " + rect );
        System.out.printf(  "    area = %.04f\n", rect.area() );
        System.out.printf(  "    peri = %.04f\n", rect.perimeter() );
    }
    
    /**
     * Create a new rectangle, and add it to the drawing plane.
     *
     * @param xco   
     *     the x-coordinate of the upper left hand corner of the oval
     * @param yco
     *     the y-coordinate of the upper left hand corner of the oval
     * @param color the color of the oval
     */
    private void 
    newLine( double xco, double yco, Color color, double xco1, double yco1 )
    {
        GeoPoint    origin  = new GeoPoint( xco, yco );
        GeoPoint    end     = new GeoPoint( xco1, yco1 );
        GeoLine     line    = new GeoLine( origin, color, end );
        plane.addShape( line );
        System.out.println( "GeoLine: " + line );
        System.out.printf(  "    slo = %.04f\n", line.slope() );
        System.out.printf(  "    len = %.04f\n", line.length() );
    }
}
