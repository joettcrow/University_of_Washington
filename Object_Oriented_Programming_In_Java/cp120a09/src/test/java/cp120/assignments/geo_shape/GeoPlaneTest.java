package cp120.assignments.geo_shape;

import org.junit.Test;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * @author jcrowley
 */

public class GeoPlaneTest {

    /**
     * This Test Creates a plane,
     * adds in a rectangle, a line and an oval
     * It then shows all the images
     * Note that the oval will not show because it is not yet defined
     */
    @Test
    public void addShapeTest() {
        GeoPlane plane = new GeoPlane();
        GeoPoint origin = new GeoPoint(5,5);
        GeoPoint end = new GeoPoint(250,250);
        GeoRectangle rect = new GeoRectangle(100,100);
        GeoOval oval = new GeoOval(end,Color.GREEN, 500,400);
        GeoLine line = new GeoLine(origin,end);

        plane.run();
        plane.addShape(rect);
        plane.addShape(oval);
        plane.addShape(line);
        plane.show();
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {

        }
    }

    /**
     * This method removes a shape and then redraws everything.
     */
    @Test
    public void removeShapeTest() {
        GeoPlane plane = new GeoPlane();
        GeoRectangle rect = new GeoRectangle(10,10);
        GeoOval oval = new GeoOval(10,10);
        plane.run();
        plane.addShape(rect);
        plane.addShape(oval);
        plane.removeShape(rect);

        plane.redraw();
    }

    @Test
    public void emptyRedrawTest(){
        GeoPlane plane = new GeoPlane();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);
        plane.redraw();
        assertEquals(baos.toString(), "");
    }
}