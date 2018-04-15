package cp120.assignments.geo_shape;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

/**
 * @author jcrowley
 */

public class GeoOvalTest {

    @Test
    public void drawTest() {
        GeoOval oval = new GeoOval();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);
        oval.draw(null);

        assertEquals(baos.toString(), "Drawing oval\n");
    }
}