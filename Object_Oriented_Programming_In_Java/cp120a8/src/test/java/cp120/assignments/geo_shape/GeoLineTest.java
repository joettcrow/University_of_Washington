package cp120.assignments.geo_shape;

import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

/**
 * @author jcrowley
 */

public class GeoLineTest {
    private GeoPoint start = new GeoPoint(0,0);
    private GeoPoint end = new GeoPoint(4,3);
    private Color color = Color.RED;
    private GeoLine line = new GeoLine(start,color,end);

    @Test
    public void getStartTest() {
        assertTrue(start.equals(line.getStart()));
    }

    @Test
    public void setStartTest() {
        GeoPoint newStart = new GeoPoint(1,1);
        line.setStart(newStart);
        assertTrue(newStart.equals(line.getStart()));
    }

    @Test
    public void getEndTest() {
        assertTrue(end.equals(line.getEnd()));
    }

    @Test
    public void setEndTest() {
        GeoPoint newEnd = new GeoPoint(5,5);
        line.setEnd(newEnd);
        assertTrue(newEnd.equals(line.getEnd()));
    }

    @Test
    public void lengthTest() {
        double hyp = 5;
        assertEquals(hyp, line.length(),0.00);
    }

    @Test
    public void slopeTest() {
        double slope  = 0.75;
        assertEquals(slope,line.slope(),0.001);
    }

    @Test
    public void startEndLineTest() {
        start = new GeoPoint(5,5);
        end = new GeoPoint(20,20);
        GeoLine line = new GeoLine(start,end);
        assertEquals(Color.BLUE, line.getColor());
        assertEquals(start, line.getStart());
        assertEquals(end, line.getEnd());
    }

    @Test
    public void drawLineTest(){
        GeoPlane plane = new GeoPlane();
        plane.run();
        plane.addShape(line);
        plane.show();
        plane.redraw();
    }
}