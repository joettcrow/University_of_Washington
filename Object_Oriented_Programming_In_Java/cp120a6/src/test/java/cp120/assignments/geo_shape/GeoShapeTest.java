package cp120.assignments.geo_shape;

import org.junit.Test;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

/**
 * @author jcrowley
 */



public class GeoShapeTest extends GeoShape{
    public void draw(Graphics2D gtx){
        System.out.println("Dummy Value");
    }

    @Test
    public void getColorTest() {
        GeoShapeTest shape = new GeoShapeTest();
        shape.setColor(Color.RED);
        assertEquals(Color.RED, shape.getColor());

    }

    @Test
    public void getOriginTest() {
        GeoShapeTest shape = new GeoShapeTest();
        GeoPoint origin = new GeoPoint();
        origin.setXco(5);
        origin.setYco(10);
        shape.setOrigin(origin);
        assertEquals(origin, shape.getOrigin());
    }

    @Test
    public void drawShapeTest(){
        GeoShapeTest shape = new GeoShapeTest();
        shape.draw(null);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);
        shape.draw(null);
        assertEquals(baos.toString(), "Dummy Value\n");
    }

//    @Test
//    public void draw() {
//
//    }
}