package cp120.assignments.geo_shape;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author jcrowley
 */

public class GeoPointTest {
    private double x = 5;
    private double y = 10;
    GeoPoint origin = new GeoPoint(x,y);

    @Test
    public void setCoTest() {
        x = 5;
        y = 10;
        GeoPoint dave = new GeoPoint(1,1);
        dave.setXco(x);
        dave.setYco(y);
        assertEquals(dave.getXco(), x, 0.0);
        assertEquals(dave.getYco(), y, 0.0);
    }

    @Test
    public void getCoTest() {
        GeoPoint dave = new GeoPoint(11,19);
        assertEquals(dave.getXco(), 11, 0.0);
        assertEquals(dave.getYco(), 19, 0.0);
    }

    @Test
    public void toStringTest(){
        GeoPoint dave = new GeoPoint(5.55555, 4.44444);
        assertEquals(dave.toString(), "(5.5556,4.4444)");
    }

    @Test
    public void equalityEpsilonTest(){
        GeoPoint first = new GeoPoint(1.1, 2.1);
        GeoPoint second = new GeoPoint(1,2);
        assertTrue(first.equals(second,.11));
    }

    @Test
    public void equalityEpsilonFalseTest(){
        GeoPoint first = new GeoPoint(1.1, 2.1);
        GeoPoint second = new GeoPoint(1,2);
        assertFalse(first.equals(second,.01));
    }

    @Test
    public void equalityZeroTest(){
        GeoPoint first = new GeoPoint(1, 2);
        GeoPoint second = new GeoPoint(1,2);
        assertTrue(first.equals(second,.01));
    }

    @Test
    public void equalityTest(){
        GeoPoint first = new GeoPoint(1, 2);
        GeoPoint second = first;
        assertTrue(first.equals(second,.01));
    }

    @Test
    public void equalityNullTest(){
        GeoPoint first = new GeoPoint(1, 2);
        GeoPoint second = null;
        assertFalse(first.equals(second,.01));
    }

}