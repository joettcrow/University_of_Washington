package cp120.assignments.geo_shape;

import org.junit.Test;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

/**
 * @author jcrowley
 */



public class GeoShapeTest{
    GeoPoint point = new GeoPoint(1,1);
    GeoShape shape = new GeoShape(point, Color.RED) {
        @Override
        public void draw(Graphics2D gtx) {
            System.out.println("Dummy Value");
        }
    };

    @Test
    public void getColorTest() {
        GeoPoint point = new GeoPoint(1,1);
        shape.setColor(Color.RED);
        assertEquals(Color.RED, shape.getColor());

    }

    @Test
    public void getOriginTest() {
        GeoPoint origin = new GeoPoint(5,10);
        shape.setOrigin(origin);
        assertEquals(origin, shape.getOrigin());
    }

    @Test
    public void drawShapeTest(){
        shape.draw(null);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);
        shape.draw(null);
        assertEquals(baos.toString(), "Dummy Value\n");
    }

    @Test
    public void toStringTest(){
        String val = "origin=(1.0000,1.0000),color=#ff0000";
        assertEquals(shape.toString(),val);
    }

    @Test
    public void toStringNullColorTest(){
        shape.setColor(null);
        String val = "origin=(1.0000,1.0000),color=null";
        assertEquals(shape.toString(),val);
    }

    @Test
    public void toStringFiveDigitColorTest(){
        Color myBlue = new Color(15, 255, 255);
        shape.setColor(myBlue);
        String val = "origin=(1.0000,1.0000),color=#0fffff";
        assertEquals(shape.toString(),val);
    }

    @Test
    public void toStringFourDigitColorTest(){
        Color myBlue = new Color(0, 255, 255);
        shape.setColor(myBlue);
        String val = "origin=(1.0000,1.0000),color=#00ffff";
        assertEquals(shape.toString(),val);
    }

    @Test
    public void toStringThreeDigitColorTest(){
        Color myBlue = new Color(0, 15, 255);
        shape.setColor(myBlue);
        String val = "origin=(1.0000,1.0000),color=#000fff";
        assertEquals(shape.toString(),val);
    }

    @Test
    public void toStringTwoDigitColorTest(){
        Color myBlack = new Color(0, 0, 255);
        shape.setColor(myBlack);
        String val = "origin=(1.0000,1.0000),color=#0000ff";
        assertEquals(shape.toString(),val);
    }

    @Test
    public void toStringOneDigitColorTest(){
        Color myBlack = new Color(0, 0, 15);
        shape.setColor(myBlack);
        String val = "origin=(1.0000,1.0000),color=#00000f";
        assertEquals(shape.toString(),val);
    }

    @Test
    public void toStringNoneDigitColorTest(){
        Color myBlack = new Color(0, 0, 0);
        shape.setColor(myBlack);
        String val = "origin=(1.0000,1.0000),color=#000000";
        assertEquals(shape.toString(),val);
    }

    @Test
    public void setOriginNullTest(){
        String err;
        err = "java.lang.IllegalArgumentException: Origin cannot be null";
        try {
            shape.setOrigin(null);
        }
        catch (IllegalArgumentException exp){
            assertEquals(exp.toString(), err);
        }


    }

}