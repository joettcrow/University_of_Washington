package cp120.assignments.assignment002;

import org.junit.Test;

import static org.junit.Assert.*;

public class BitMasksTest {

    @Test
    public void testDecode(){
        int bits=0b0101110110011010;
        String decodedBits = BitMasks.decode(bits);

        int origin=2;
        int security=3;
        int data=12;
        int count=2;
        int start=1;
        int control=5;
        String str = "origin=" + origin +
                ",security=" + security +
                ",data=" + data +
                ",count=" + count +
                ",start=" + start +
                ",control=" + control;

        assertEquals(decodedBits, str);
    }

    @Test
    public void testDecodeZeros(){
        int bits=0b0000000000000000;
        String decodedBits = BitMasks.decode(bits);

        int origin=0;
        int security=0;
        int data=0;
        int count=0;
        int start=0;
        int control=0;
        String str = "origin=" + origin +
                ",security=" + security +
                ",data=" + data +
                ",count=" + count +
                ",start=" + start +
                ",control=" + control;

        assertEquals(decodedBits, str);
    }
    @Test
    public void testDecodeSeventeen(){
        int bits=0b01011101100110101;
        String decodedBits = BitMasks.decode(bits);

        int origin=5;
        int security=2;
        int data=9;
        int count=1;
        int start=1;
        int control=11;
        String str = "origin=" + origin +
                ",security=" + security +
                ",data=" + data +
                ",count=" + count +
                ",start=" + start +
                ",control=" + control;

        assertEquals(decodedBits, str);
    }

    @Test
    public void testDecodeOnes(){
        int bits=0b1111111111111111;
        String decodedBits = BitMasks.decode(bits);

        int origin=7;
        int security=3;
        int data=15;
        int count=3;
        int start=1;
        int control=15;
        String str = "origin=" + origin +
                ",security=" + security +
                ",data=" + data +
                ",count=" + count +
                ",start=" + start +
                ",control=" + control;

        assertEquals(decodedBits, str);
    }
}