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
        GeoRectangle rect = new GeoRectangle();
        GeoOval oval = new GeoOval();
        plane.addShape(rect);
        plane.addShape(oval);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);
        plane.redraw();

        assertEquals(baos.toString(), "Drawing rectangle\nDrawing oval\n");

    }

    @Test
    public void removeShapeTest() {
        GeoPlane plane = new GeoPlane();
        GeoRectangle rect = new GeoRectangle();
        GeoOval oval = new GeoOval();
        plane.addShape(rect);
        plane.addShape(oval);
        plane.removeShape(rect);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);
        plane.redraw();

        assertEquals(baos.toString(), "Drawing oval\n");
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