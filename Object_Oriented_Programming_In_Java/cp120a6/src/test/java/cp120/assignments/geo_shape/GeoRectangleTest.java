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
        GeoRectangle rect = new GeoRectangle();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);
        rect.draw(null);

        assertEquals(baos.toString(), "Drawing rectangle\n");
    }

    @Test
    public void getWidthAndHeightTest() {
        GeoRectangle rect = new GeoRectangle();
        double height = 9;
        double width = 10;
        rect.setHeight(height);
        rect.setWidth(width);
        assertEquals(rect.getHeight(), height, 0.0);
        assertEquals(rect.getWidth(), width, 0.0);
    }

}