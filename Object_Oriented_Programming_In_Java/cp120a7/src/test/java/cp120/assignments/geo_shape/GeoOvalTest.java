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
        GeoOval oval = new GeoOval(1,2);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);
        oval.draw(null);
        String val = "Drawing oval:" +
                " origin=(0.0000,0.0000)," +
                "color=#0000ff," +
                "width=1.0000," +
                "height=2.0000\n";
        assertEquals(baos.toString(), val);
    }

    @Test
    public void ovalConstructThreeVariablesTest(){
        GeoPoint point = new GeoPoint(1,1);
        GeoOval oval = new GeoOval(point, 10, 10);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);
        oval.draw(null);
        String val = "Drawing oval:" +
                " origin=(1.0000,1.0000)," +
                "color=#0000ff," +
                "width=10.0000," +
                "height=10.0000\n";
        assertEquals(baos.toString(), val);
    }
}