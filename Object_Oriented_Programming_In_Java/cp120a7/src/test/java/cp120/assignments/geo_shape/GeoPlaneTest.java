package cp120.assignments.geo_shape;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

/**
 * @author jcrowley
 */

public class GeoPlaneTest {

    @Test
    public void addShapeTest() {
        GeoPlane plane = new GeoPlane();
        GeoRectangle rect = new GeoRectangle(10,10);
        GeoOval oval = new GeoOval(10,10);
        plane.addShape(rect);
        plane.addShape(oval);

        String ovalVal = "Drawing oval:" +
                " origin=(0.0000,0.0000)," +
                "color=#0000ff," +
                "width=10.0000," +
                "height=10.0000\n";
        String rectVal = "Drawing rectangle:" +
                " origin=(0.0000,0.0000)," +
                "color=#0000ff," +
                "width=10.0000," +
                "height=10.0000\n";

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);
        plane.redraw();

        assertEquals(baos.toString(), rectVal + ovalVal);

    }

    @Test
    public void removeShapeTest() {
        GeoPlane plane = new GeoPlane();
        GeoRectangle rect = new GeoRectangle(10,10);
        GeoOval oval = new GeoOval(10,10);
        plane.addShape(rect);
        plane.addShape(oval);
        plane.removeShape(rect);

        String ovalVal = "Drawing oval:" +
                " origin=(0.0000,0.0000)," +
                "color=#0000ff," +
                "width=10.0000," +
                "height=10.0000\n";

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);
        plane.redraw();

        assertEquals(baos.toString(), ovalVal);
    }

    @Test
    public void emptyRedrawTest(){
        GeoPlane plane = new GeoPlane();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);
        plane.redraw();

        assertEquals(baos.toString(), "");

    }
}