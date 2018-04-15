package cp120.d_list;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author jcrowley
 */
public class DNodeTest {

    @Test
    public void setData() {
        DNode node = new DNode();
        node.setData(1);
        assertEquals(1, node.getData());
    }

    @Test
    public void isNotEnqueuedTest() {
        DNode node = new DNode();
        assertFalse(node.isEnqueued());
    }

    @Test
    public void isEnqueuedTest() {
        DNode node = new DNode();
        DList list = new DList();
        list.addHead(node);
        assertTrue(node.isEnqueued());
    }

    @Test
    public void addBeforeEnquedErrorTest(){
        DNode node = new DNode();
        DList list = new DList();
        String err = "java.lang.IllegalArgumentException: Node already part of the List";
        list.addBefore(node);
        try {
            list.addBefore(node);
        }
        catch (IllegalArgumentException exp){
            assertEquals(exp.toString(), err);
        }
    }

    @Test
    public void addAfterEnquedErrorTest(){
        DNode node = new DNode();
        DList list = new DList();
        String err = "java.lang.IllegalArgumentException: Node already part of the List";
        list.addAfter(node);
        try {
            list.addAfter(node);
        }
        catch (IllegalArgumentException exp){
            assertEquals(exp.toString(), err);
        }
    }
}