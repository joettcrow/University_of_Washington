package cp120.d_list;

import static org.junit.Assert.*;

import org.junit.Test;

public class DListTest
{
    @Test
    public void addTailTest()
    {
        // Create an empty list. Verify that list.flink and list.blink -> list
        DList   list    = new DList();
        assertEquals( list, list.getNext() );
        assertEquals( list, list.getPrevious() );
        assertEquals( list, list.getHead() );
        assertEquals( list, list.getTail() );
        assertEquals( 0, list.size() );
        assertTrue( list.isEmpty() );

        // The first node added to the list will be both head and tail
        /*
         *        List root       head/tail
         *              +---+      +---+
         *              |   |--->  |   |---> to root
         *              +---+      +---+
         * to head <--- |   |  <---|   |
         *              +---+      +---+
         */
        DNode   head    = new DNode( 0 );
        list.addTail( head );
        assertEquals( list, head.getNext() );
        assertEquals( list, head.getPrevious() );
        assertEquals( head, list.getNext() );
        assertEquals( head, list.getPrevious() );
        assertEquals( head, list.getHead() );
        assertEquals( head, list.getTail() );
        assertEquals( 1, list.size() );
        assertFalse( list.isEmpty() );


        // Slowly build list, performing validations with each add
        for ( int inx = 1 ; inx <= 10 ; ++inx )
        {
            DNode   node    = new DNode( inx );
            list.addTail( node );

            int     expSize = inx + 1;

            assertNotEquals( list, head.getNext() );
            assertNotEquals( list, node.getPrevious() );
            assertEquals( list, head.getPrevious() );
            assertEquals( list, node.getNext() );
            assertEquals( head, list.getHead() );
            assertEquals( node, list.getTail() );
            assertEquals( head, list.getNext() );
            assertEquals( node, list.getPrevious() );
            assertEquals( expSize, list.size() );
            assertFalse( list.isEmpty() );
        }

        DNode   node    = list.getHead();
        while ( node != list )
        {
            System.out.println( node.getData() );
            node = node.getNext();
        }
    }

    @Test
    public void addHeadTest(){
        // Create an empty list. Verify that list.flink and list.blink -> list
        DList   list    = new DList();
        DNode   head    = new DNode( 0 );
        list.addHead( head );
        assertEquals( list, head.getNext() );
        assertEquals( list, head.getPrevious() );
        assertEquals( head, list.getNext() );
        assertEquals( head, list.getPrevious() );
        assertEquals( head, list.getHead() );
        assertEquals( head, list.getTail() );
        assertEquals( 1, list.size() );
        assertFalse( list.isEmpty() );


        // Slowly build list, performing validations with each add
        for ( int inx = 1 ; inx <= 10 ; ++inx )
        {
            DNode   node    = new DNode( inx );
            list.addHead( node );

            int     expSize = inx + 1;

            assertNotEquals( list, node.getNext() );
            assertNotEquals( list, head.getPrevious() );
            assertEquals( list, node.getPrevious() );
            assertEquals( list, head.getNext() );
            assertEquals( node, list.getHead() );
            assertEquals( head, list.getTail() );
            assertEquals( node, list.getNext() );
            assertEquals( head, list.getPrevious() );
            assertEquals( expSize, list.size() );
            assertFalse( list.isEmpty() );
        }

        DNode   node    = list.getHead();
        while ( node != list )
        {
            System.out.println( node.getData() );
            node = node.getNext();
        }
    }

    @Test
    public void removeHeadTest(){
        DList   list    = new DList();
        DNode   head    = new DNode( 0 );
        list.addHead( head );
        DNode head2 = new DNode(1);
        list.addHead(head2);
        assertEquals(head2,list.removeHead());
        assertEquals(head, list.getHead());
    }

    @Test
    public void removeTailTest(){
        DList   list    = new DList();
        DNode   tail1    = new DNode( 0 );
        list.addTail( tail1 );
        DNode tail2 = new DNode(1);
        list.addTail(tail2);
        assertEquals(tail2,list.removeTail());
        assertEquals(tail1, list.getTail());
    }

    @Test
    public void removeAllTest(){
        DList   list    = new DList();
        for ( int inx = 1 ; inx <= 10 ; ++inx )
        {
            DNode   node    = new DNode( inx );
            list.addHead( node );

            assertFalse( list.isEmpty() );
        }
        list.removeAll();
        assertTrue(list.isEmpty());
    }
}