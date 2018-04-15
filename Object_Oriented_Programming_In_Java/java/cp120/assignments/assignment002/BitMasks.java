package cp120.assignments.assignment002;

public class BitMasks {
    public static String decode(int reg ){
        int temp = reg;
        int origin = temp & 0x7;
        temp = temp >> 3;

        int security = temp & 0x3;
        temp = temp >> 2;

        int data = temp & 0xF;
        temp = temp >> 4;

        int count = temp & 0x3;
        temp = temp >> 2;

        int start = temp & 0x1;
        temp = temp >> 1;

        int control = temp & 0xF;

        return "origin=" + origin + ",security=" + security + ",data=" + data + ",count=" +
                count + ",start=" + start + ",control=" + control;

    }

}
