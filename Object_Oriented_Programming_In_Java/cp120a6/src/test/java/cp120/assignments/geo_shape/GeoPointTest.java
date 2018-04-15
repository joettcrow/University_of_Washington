package cp120.assignments.geo_shape;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author jcrowley
 */

public class GeoPointTest {

    @Test
    public void getXcoTest() {
        double x = 5;
        double y = 10;
        GeoPoint dave = new GeoPoint();
        dave.setXco(x);
        dave.setYco(y);
        assertEquals(dave.getXco(), x, 0.0);
        assertEquals(dave.getYco(), y, 0.0);
    }

}