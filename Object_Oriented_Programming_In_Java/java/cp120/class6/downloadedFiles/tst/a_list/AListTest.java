package a_list;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tester for AList.
 * <p>
 * General strategy: generate string objects from sequential integers.
 * Add the strings to an AList in a pattern that allows
 * for easy computation of the strings' final indices.
 * Verify that the strings are in the expected locations.
 * </p>
 * 
 * @author jack
 */
public class AListTest
{
    
    /** Error message; indicates that the test has malfunctioned. */
    private static String   TEST_MALF   = "Test malfunction";
    
    /**
     * Test add/remove operations at the tail of the list.
     * To aid in testing the result, the objects added to the list are
     * generated from sequential integers.
     * <ol>
     * <li>
     * Add sequentially numbered strings at the tail of the list,
     * producing a list that is sequentially ordered from 0 to <em>n</em>.
     * </li>
     * <li>Verify that the list is ordered sequentiall.</li>
     * <li>
     * Tear down the list starting at the tail. Verify that Items
     * are removed in reverse sequential order.
     * </ol>
     */
    @Test
    public void testAddRemoveTail()
    {
        AList   list    = new AList( 5, 2 );
        
        for ( int inx = 0 ; inx < 35 ; ++inx )
        {
            String  newObj  = "" + inx;
            list.add( newObj );
            assertEquals( inx + 1, list.size() );
            System.out.println( "Tail add: " + newObj );
        }
        
        int limit   = list.size();
        for ( int inx = 0 ; inx < limit ; ++inx )
        {
            Object  obj = list.get( inx );
            System.out.println( "Tail get: " + obj );
            
            int     act = Integer.parseInt( (String)obj );
            assertEquals( inx, act );
        }
        
        for ( int inx = list.size() - 1 ; inx >= 0 ; --inx )
        {
            Object  obj     = list.remove( inx );
            int     act     = Integer.parseInt( (String)obj );
            System.out.println( "Tail rem: " + obj );
            
            assertEquals( inx, act );
        }
    }

    /**
     * Test add/remove operations at the head of the list.
     * To aid in testing the result, the objects added to the list are
     * generated from sequential integers.
     * <ol>
     * <li>
     * Add sequentially numbered strings at the head of the list,
     * producing a list that is sequentially ordered in reverse,
     * from <em>n</em> to 0.
     * </li>
     * <li>Verify that the list is in reverse sequential order.</li>
     * <li>
     * Tear down the list starting at the head. Verify that Items
     * are removed in reverse sequential order.
     * </ol>
     */
    @Test
    public void testAddRemoveHead()
    {
        int     max     = 35;
        AList   list    = new AList( 5, 2 );
        
        for ( int inx = 0 ; inx < max ; ++inx )
        {
            String  newObj  = "" + inx;
            System.out.println(  "Head add: " + newObj );
            list.add( 0, newObj );
            assertEquals( inx + 1, list.size() );
        }
        
        int limit   = list.size();
        for ( int inx = 0 ; inx < limit ; ++inx )
        {
            Object  obj = list.get( inx );
            System.out.println( "Head get: " + obj );
            
            int     act = Integer.parseInt( (String)obj );
            assertEquals( max - inx - 1, act );
        }

        for ( int inx = 0 ; inx < limit ; ++inx )
        {
            Object  obj = list.remove( 0 );
            System.out.println( "Head rem: " + obj );
            
            int     act = Integer.parseInt( (String)obj );
            assertEquals( max - inx - 1, act );
        }
    }


    /**
     * Test add/remove operations in the middle of the list.
     * To aid in testing the result, the objects added to the list are
     * generated from sequential integers.
     * <ol>
     * <li>Add strings "0" and "1" to the list.</li>
     * <li>Sequentially add remaining strings at index 2 of the list.</li>
     * <li>Verify that:
     * <ol type="a">
     * <li>The first two items in the list are "0" and "1"</li>
     * <li>
     * The remaining items in the list are ordered in reverse,
     * from <em>n</em> to 2.
     * </li>
     * </ol>
     * </li>
     * <li>
     * Delete the entire tail of the list by iteratively removing the
     * object at index 2, until only the first two strings ("0" and "1")
     * remain. Verify that the objects are removed in reverse order,
     * from <em>n</em> to 2.
     * </li>
     * <li>
     * Remove the final two items from the head of the list;
     * verify that they are "0" and "1".
     * </ol>
     */
    @Test
    public void testAddRemoveMid()
    {
        final int   initSize    = 5;
        final int   extendSize  = 2;
        final int   max         = 35;
        final int   offset      = 2;
        final AList list        = new AList( initSize, extendSize );
        
        assertTrue( TEST_MALF, max > 5 );
        assertTrue( TEST_MALF, offset < max );
        assertTrue( TEST_MALF, offset > 0 );        
        
        // Initialize the list with a couple of items
        for ( int inx = 0 ; inx < offset ; ++inx )
        {
            String  newObj  = "" + inx;
            System.out.println(  "Mid add: " + newObj );
            list.add( inx, newObj );
        }
        
        // Add all remaining objects via a single index
        for ( int inx = offset ; inx < max ; ++inx )
        {
            String  newObj  = "" + inx;
            System.out.println(  "Mid add: " + newObj );
            list.add( offset, newObj );
            assertEquals( inx + 1, list.size() );
        }

        // Verify that the first couple of items are stored in order
        for ( int inx = 0 ; inx < offset ; ++inx )
        {
            Object  obj = list.get( inx );
            System.out.println( "Mid get: " + obj );
            
            int     act = Integer.parseInt( (String)obj );
            assertEquals( inx, act );
        }
        
        // Verify that the tail of the list is in reverse order
        for ( int inx = offset, jnx = max - 1 ; inx < max ; ++inx, --jnx )
        {
            Object  obj = list.get( inx );
            System.out.println( "Mid get: " + obj );
            
            int     act = Integer.parseInt( (String)obj );
            assertEquals( jnx, act );
        }

        // Delete the tail of the list; sequentially remove items from
        // the list via the same index they were originally stored at.
        // Validate that they are removed in reverse order.
        for ( int inx = offset, jnx = max - 1 ; inx < max ; ++inx, --jnx )
        {
            Object  obj = list.remove( offset );
            System.out.println( "Mid rem: " + obj );
            
            int     act = Integer.parseInt( (String)obj );
            assertEquals( jnx, act );
        }

        // Remove the couple of items that were originally stored
        // in sequential order. Verify that they are removed
        // sequentially.
        for ( int inx = 0 ; inx < offset ; ++inx )
        {
            Object  obj = list.remove( 0 );
            System.out.println( "Mid rem: " + obj );
            
            int     act = Integer.parseInt( (String)obj );
            assertEquals( inx, act );
        }
    }
    
    /**
     * Test the two parameter constructor.
     */
    @Test
    public void testCtorIntInt()
    {
        int     initSize    = AList.DEF_INIT_SIZE + 1;
        int     extendSize  = 2;
        
        assertNotEquals( TEST_MALF, extendSize, AList.DEF_EXTEND_SIZE );
        
        AList       list    = new AList( initSize, extendSize );
        AList.State specs   = list.getState();
        
        assertEquals( initSize, specs.getInitBuffSize() );
        assertEquals( extendSize, specs.getExtendBuffSize() );
        assertEquals( initSize, specs.getCurrBuffSize() );
        assertEquals( 0, specs.getCurrSize() );
    }
    
    /**
     * Test the one parameter constructor.
     */
    @Test
    public void testCtorInt()
    {
        int     initSize    = AList.DEF_INIT_SIZE + 1;
        
        AList       list    = new AList( initSize );
        AList.State specs   = list.getState();
        
        assertEquals( initSize, specs.getInitBuffSize() );
        assertEquals( AList.DEF_EXTEND_SIZE, specs.getExtendBuffSize() );
        assertEquals( initSize, specs.getCurrBuffSize() );
        assertEquals( 0, specs.getCurrSize() );
    }
    
    /**
     * Test the default constructor.
     */
    @Test
    public void testCtor()
    {
        AList       list    = new AList();
        AList.State specs   = list.getState();
        
        assertEquals( AList.DEF_INIT_SIZE, specs.getInitBuffSize() );
        assertEquals( AList.DEF_EXTEND_SIZE, specs.getExtendBuffSize() );
        assertEquals( AList.DEF_INIT_SIZE, specs.getCurrBuffSize() );
        assertEquals( 0, specs.getCurrSize() );
    }
    
    /**
     * Test the AList extend logic. Strategy:
     * <ol>
     * <li>Create the list, and exactly fill the list's buffer.</li>
     * <li>Verify that no extension has taken place.</li>
     * <li>Add one item to the list.</li>
     * <li>Verify that one extension has taken place.</li>
     * <li>Continue to add items to the list unti it is exactly full.</li>
     * <li>Verify that a second extension has <em>not</em> taken place.
     * <li>
     * Add one more item to the list; verify that a second extension
     * has taken place.
     * </li>
     * </ol>
     */
    @Test
    public void testExtend()
    {
        final int   initSize        = 5;
        final int   extendSize      = 2;
        final AList list            = new AList( initSize, extendSize );
        
        // Add exactly enough objects to fill the initial buffer.
        // Expected: no extension will take place
        for ( int inx = 0 ; inx < initSize ; ++inx )
            list.add( "" + inx );
        
        // We added initSize objects; no extension should have taken place
        AList.State specs   = list.getState();
        assertEquals( initSize, specs.getInitBuffSize() );
        assertEquals( extendSize, specs.getExtendBuffSize() );
        assertEquals( initSize, specs.getCurrBuffSize() );
        assertEquals( initSize, specs.getCurrSize() );
        
        // Add one more object to the list; since the buffer is full,
        // this should cause an extension to take place.
        list.add( "" + initSize );
        
        // One extension should have taken place, giving a buff size
        // of initSize + extendSize. Current size should be one more
        // than initSize.
        int expBuffSize = initSize + extendSize;
        int expCurrSize = initSize + 1;
        
        specs   = list.getState();
        assertEquals( expBuffSize, specs.getCurrBuffSize() );
        assertEquals( expCurrSize, specs.getCurrSize() );
        
        // Add exactly enough objects to fill the extended buffer.
        // Expectation: no extension will take place.
        int currSize    = list.size();
        int limit       = initSize + extendSize;
        for ( int inx = currSize ; inx < limit ; ++inx )
            list.add( "" + inx );
        
        // List buffer size should still reflect exactly one extension.
        // Current size should be the same as the buffer size.
        specs = list.getState();
        expCurrSize = initSize + extendSize;
        expBuffSize = expCurrSize;
        
        assertEquals( expBuffSize, specs.getCurrBuffSize() );
        assertEquals( expCurrSize, specs.getCurrSize() );
        
        // Add one more object to the list. This should cause a 
        // second extension to take place.
        list.add( "" + expCurrSize );
        specs   = list.getState();
        
        // A second extension should have taken place, giving a buff size
        // of initSize + 2 * extendSize. Current size should be one more
        // than previously.
        ++expCurrSize;
        expBuffSize = initSize + 2 * extendSize;
        
        assertEquals( expBuffSize, specs.getCurrBuffSize() );
        assertEquals( expCurrSize, specs.getCurrSize() );
    }
    
    /**
     * Test the set method. Build a list with one set of values,
     * then verify that the list was correctly built. Next, change
     * all the values in the list, and verify that the changes
     * took place correctly.
     */
    @Test
    public void testSet()
    {
        final int   initSize    = 5;
        final int   max         = 35;
        final AList list        = new AList( initSize, max );
        
        // Build list...
        for ( int inx = 0 ; inx < max ; ++inx )
            list.add( "" + inx );
        
        // Verify build
        for ( int inx = 0 ; inx < max ; ++inx )
        {
            Object  act = list.get( inx );
            String  exp = "" + inx;
            assertEquals( exp, act );
        }
        
        // Change list...
        for ( int inx = 0 ; inx < max ; ++inx )
            list.set( inx, "" + 2 * inx );
        
        // Verify changes
        for ( int inx = 0 ; inx < max ; ++inx )
        {
            Object  act = list.get( inx );
            String  exp = "" + 2 * inx;
            assertEquals( exp, act );
        }
    }
    
    /**
     * Verify that out-of-bounds operations produce an exceptions.
     * There's one tricky boundary condition:
     * <p>
     * If a list contains MAX items, then:
     * </p>
     * <ul>
     * <li>add( MAX, obj ) should succeed</li>
     * <li>passing MAX to get, add and remove should fail</li>
     * </ul>
     */
    @Test
    public void testOutOfBounds()
    {
        final int   max     = 5;
        final AList list    = new AList();
        
        for ( int inx = 0, limit = max - 1 ; inx < limit ; ++inx )
            list.add( "" + inx );
        
        // This should work
        list.add( max - 1, "" + (max - 1) );
        
        // The rest of these should produce an exception
        try
        {
            list.add( max + 1, "" );
            // if we get here, the test failed
            fail( "Expected exception not thrown" );
        }
        catch ( IndexOutOfBoundsException exc )
        {
            // If we get here the test succeeded.
        }

        try
        {
            list.add( -1, "" );
            // if we get here, the test failed
            fail( "Expected exception not thrown" );
        }
        catch ( IndexOutOfBoundsException exc )
        {
            // If we get here the test succeeded.
        }

        try
        {
            list.set( max, "" );
            // if we get here, the test failed
            fail( "Expected exception not thrown" );
        }
        catch ( IndexOutOfBoundsException exc )
        {
            // If we get here the test succeeded.
        }

        try
        {
            list.set( -1, "" );
            // if we get here, the test failed
            fail( "Expected exception not thrown" );
        }
        catch ( IndexOutOfBoundsException exc )
        {
            // If we get here the test succeeded.
        }

        try
        {
            list.get( max );
            // if we get here, the test failed
            fail( "Expected exception not thrown" );
        }
        catch ( IndexOutOfBoundsException exc )
        {
            // If we get here the test succeeded.
        }

        try
        {
            list.get( -1 );
            // if we get here, the test failed
            fail( "Expected exception not thrown" );
        }
        catch ( IndexOutOfBoundsException exc )
        {
            // If we get here the test succeeded.
        }

        try
        {
            list.remove( max );
            // if we get here, the test failed
            fail( "Expected exception not thrown" );
        }
        catch ( IndexOutOfBoundsException exc )
        {
            // If we get here the test succeeded.
        }

        try
        {
            list.remove( -1 );
            // if we get here, the test failed
            fail( "Expected exception not thrown" );
        }
        catch ( IndexOutOfBoundsException exc )
        {
            // If we get here the test succeeded.
        }
    }
}
