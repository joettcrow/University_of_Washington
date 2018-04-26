package app;

import static org.junit.Assert.*;

/**
 * @author jcrowley
 */

public class DummyTest {

    @org.junit.Test
    public void test() {
        Dummy dumb1 = new Dummy(4);
        MyDummy dumb2 = new MyDummy(4);
        assertEquals(dumb1,dumb2);
    }

    private class MyDummy extends Dummy {
        public MyDummy(int count){
            super(count);
        }
    }
}