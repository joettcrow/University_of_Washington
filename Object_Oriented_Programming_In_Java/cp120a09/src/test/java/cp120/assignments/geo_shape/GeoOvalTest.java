package cp120.assignments.geo_shape;

import org.junit.Test;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

/**
 * @author jcrowley
 */

public class GeoOvalTest {
    private GeoPoint point = new GeoPoint(1,1);
    private Color color = Color.RED;
    private GeoOval oval = new GeoOval(point,color,10,10);
    private GeoPlane plane = new GeoPlane();

    @Test
    public void drawRectColorTest(){
        plane.run();
        plane.addShape(oval);
        plane.show();
    }

    @Test
    public void ovalConstructThreeVariablesTest(){
        GeoPoint point = new GeoPoint(1,1);
        GeoOval newOval = new GeoOval(point, 10, 10);
        String val = "origin=(1.0000,1.0000)," +
                "color=#0000ff," +
                "edgeColor=#0000ff," +
                "edgeWidth=1.0000," +
                "width=10.0000," +
                "height=10.0000";
        assertEquals(val, newOval.toString());
    }

    @Test
    public void ovalPerimiterTest(){
        Double perimeter = 5*2*Math.PI;
        assertEquals(perimeter,oval.perimeter(),0.01);
    }

    @Test
    public void ovalAreaTest(){
        Double area = 5*5*Math.PI;
        assertEquals(area,oval.area(),0.01);
    }
}