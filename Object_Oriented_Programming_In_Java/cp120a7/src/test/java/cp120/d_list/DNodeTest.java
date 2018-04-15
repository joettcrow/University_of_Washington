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
}