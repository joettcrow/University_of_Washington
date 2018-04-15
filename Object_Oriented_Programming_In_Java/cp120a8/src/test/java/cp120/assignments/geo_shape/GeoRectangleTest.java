package cp120.assignments.geo_shape;

import org.junit.Test;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

/**
 * @author jcrowley
 */

public class GeoRectangleTest {

    @Test
    public void toStringTest() {
        GeoRectangle rect = new GeoRectangle(1,2);
        String val = "origin=(0.0000,0.0000)," +
                "color=#0000ff," +
                "edgeColor=#0000ff," +
                "edgeWidth=1.0000," +
                "width=1.0000," +
                "height=2.0000";
        assertEquals(val,rect.toString());
    }

    @Test
    public void getWidthAndHeightTest() {
        GeoRectangle rect = new GeoRectangle(9,10);
        double height = 9;
        double width = 10;
        rect.setHeight(height);
        rect.setWidth(width);
        assertEquals(rect.getHeight(), height, 0.0);
        assertEquals(rect.getWidth(), width, 0.0);
    }

    @Test
    public void rectConstructThreeVariablesTest(){
        GeoPoint point = new GeoPoint(1,1);
        GeoRectangle rect = new GeoRectangle(point, 10, 10);
        String val = "origin=(1.0000,1.0000)," +
                "color=#0000ff," +
                "edgeColor=#0000ff," +
                "edgeWidth=1.0000," +
                "width=10.0000," +
                "height=10.0000";
        assertEquals(val,rect.toString());
    }

    @Test
    public void areaTest(){
        GeoRectangle rect = new GeoRectangle(5,10);
        assertEquals(50,rect.area(),0.0);
    }

    @Test
    public void perimiterTest(){
        GeoRectangle rect = new GeoRectangle(5,10);
        assertEquals(30,rect.perimeter(),0.0);
    }

    @Test
    public void drawRectNullColorTest(){
        GeoRectangle rect = new GeoRectangle(5,10);
        GeoPlane plane = new GeoPlane();
        plane.run();
        plane.addShape(rect);
        plane.show();
    }

    @Test
    public void drawRectColorTest(){
        GeoPoint point = new GeoPoint(1,1);
        Color color = Color.RED;
        GeoRectangle rect = new GeoRectangle(point,color,5,5);
        GeoPlane plane = new GeoPlane();
        plane.run();
        plane.addShape(rect);
        plane.show();
    }

}