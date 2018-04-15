package cp120.assignments.geo_shape;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

/**
 * @author jcrowley
 */

public class GeoRectangleTest {

    @Test
    public void drawTest() {
        GeoRectangle rect = new GeoRectangle(1,2);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);
        rect.draw(null);
        String val = "Drawing rectangle:" +
                " origin=(0.0000,0.0000)," +
                "color=#0000ff," +
                "width=1.0000," +
                "height=2.0000\n";
        assertEquals(baos.toString(), val);
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
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);
        rect.draw(null);
        String val = "Drawing rectangle:" +
                " origin=(1.0000,1.0000)," +
                "color=#0000ff," +
                "width=10.0000," +
                "height=10.0000\n";
        assertEquals(baos.toString(), val);
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

}