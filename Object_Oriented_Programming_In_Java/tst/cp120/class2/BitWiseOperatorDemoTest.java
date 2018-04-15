package cp120.class2;

import org.junit.Test;

import static cp120.class2.BitWiseOperatorDemo.buildAddressRegister;
import static org.junit.Assert.assertEquals;

public class BitWiseOperatorDemoTest {
//    @Test
//    public void testCtor(){
//        new BitWiseOperatorDemo();
//    }

    @Test
    public void testEncode(){
        int drive = 5;
        int cyl = 122;
        int head = 1;
        int sector = 13;
        int expReg = drive << 13 | cyl << 5 | head << 4 | sector;
        int actReg = buildAddressRegister( drive, cyl, head, sector);
        assertEquals( expReg, actReg);
    }

}