package a_list;

import java.util.Arrays;

/**
 * Implements a list as an internal array. When the internal array 
 * is full, extends it. The user may specify the initial size of
 * the list's array, and the amount to extend it by, when necessary.
 */
public class AList
{
    /** The default initial size of the list. */
    public static final int     DEF_INIT_SIZE   = 100;
    
    /** The default extend size of the list. */
    public static final int     DEF_EXTEND_SIZE = 10;
    
    /** The actual initial size of the list. */
    private final int   initSize;
    
    /** The actual extend size of the list. */
    private final int   extendSize;
    
    /** The next index at which append an element. */
    private int         next;
    
    /** The list's core array. */
    private Object[]    alist;
    
    /**
     * Instantiates a new a list, using default
     * initial and extend sizes.
     */
    public AList()
    {
        this( DEF_INIT_SIZE, DEF_EXTEND_SIZE );
    }
    
    /**
     * Instantiates a new a list, using the default extend size.
     *
     * @param initSize the initial size of the list
     */
    public AList( int initSize )
    {
        this( initSize, DEF_EXTEND_SIZE );
    }
    
    /**
     * Instantiates a new a list.
     *
     * @param initSize      the initial size of the list
     * @param extendSize    the extend size of the list
     */
    public AList( int initSize, int extendSize )
    {
        this.initSize = initSize;
        this.extendSize = extendSize;
        alist = new Object[initSize];
        next = 0; 
    }
    
    /**
     * Adds an item to the list at the next free index.
     * The size of the list will increment by 1.
     *
     * @param obj the obj
     */
    public void add( Object obj )
    {
        add( next, obj );
    }
    
    /**
     * Adds an item to the list at a specific index.
     * The size of the list will increment by 1.
     *
     * @param index     the index to add to
     * @param obj       the item to add
     * 
     * @throws IndexOutOfBoundsException if index is greater
     *         than the next free index.
     */
    public void add( int index, Object obj )
    {
        if ( index > next || index < 0 )
            throw new IndexOutOfBoundsException();
        
        if ( next == alist.length )
        {
            int newLen  = alist.length + extendSize;
            alist = Arrays.copyOf( alist, newLen );
        }
        
        if ( index < next )
            for ( int inx = next ; inx > index ; --inx )
                alist[inx] = alist[inx - 1];
        alist[index] = obj;
        next++;
    }
    
    /**
     * Changes the value at a specific index in the list.
     * The original value stored at that index is returned.
     *
     * @param index     the index change
     * @param obj       the value to set at the given index
     * 
     * @throws IndexOutOfBoundsException if index is &gt;=
     *         the next free index.
     *         
     * @return the original value stored at the given index
     */
    public Object set( int index, Object obj )
    {
        if ( index >= next || index < 0 )
            throw new IndexOutOfBoundsException();
        
        Object  prev    = alist[index];
        alist[index] = obj;
        
        return prev;
    }
    
    /**
     * Gets the object at a given index in the list.
     *
     * @param index     the given index
     * 
     * @throws IndexOutOfBoundsException if index is &gt;=
     *         the next free index.
     *         
     * @return the object stored at the given index
     */
    public Object get( int index )
    {
        if ( index >= next || index < 0 )
            throw new IndexOutOfBoundsException();
        
        return alist[index];
    }
    
    /**
     * Removes the object at a given index in the list.
     * The removed item is returned.
     * The size of the list will decrease by 1.
     *
     * @param index     the given index
     * 
     * @throws IndexOutOfBoundsException if index is &gt;=
     *         the next free index.
     *         
     * @return the object previously stored at the given index
     */
    public Object remove( int index )
    {
        if ( index >= next || index < 0 )
            throw new IndexOutOfBoundsException();
        
        Object  obj     = alist[index];
        
        int     limit   = next - 1;
        for ( int inx = index ; inx < limit ; ++inx )
            alist[inx] = alist[inx + 1];
        
        --next;
        
        return obj;
    }
    
    /**
     * Gets the current size of the list.
     *
     * @return the current size of the list
     */
    public int size()
    {
        return next;
    }
    
    /**
     * Gets the state of this list.
     * Useful for load analysis and testing.
     *
     * @return the state encapsulated by this list
     * 
     * @see State
     */
    protected State getState()
    {
        State   state   = new State();
        return state;
    }
    
    /**
     * Stores general information about the current state
     * of this list.
     * <p>
     * <b>Note:</b> in the following discussion,
     * "current size" refers to the actual number of items
     * stored in the array. "Buffer size" refers to the size
     * of the list's core array, which must be &gt;= current size.
     * </p>
     * <p>
     * The state include:
     * </p>
     * <ol>
     * <li>The initial buffer size of the associated list.</li>
     * <li>The initial extend size of the associated list.</li>
     * <li>The current buffer size of the associated list.</li>
     * <li>The current size of the associated list.</li>
     * </ol>
     */
    protected class State
    {
        
        /** The initial buffer size. */
        private final int   initBuffSize;
        
        /** The extend size. */
        private final int   extBuffSize;
        
        /** The current buffer size. */
        private final int   currBuffSize;
        
        /** The current size of the list.  */
        private final int   currSize;

        /**
         * Instantiates a new State object.
         */
        public State()
        {
            initBuffSize = initSize;
            extBuffSize = extendSize;
            currBuffSize = alist.length;
            currSize = next;
        }
        
        /**
         * Gets the initial buffer size.
         *
         * @return the initial buffer size
         */
        public int getInitBuffSize()
        {
            return initBuffSize;
        }

        /**
         * Gets the extend size.
         *
         * @return the extend size
         */
        public int getExtendBuffSize()
        {
            return extBuffSize;
        }

        /**
         * Gets the buffer size.
         *
         * @return the buffer size
         */
        public int getCurrBuffSize()
        {
            return currBuffSize;
        }

        /**
         * Gets the current size.
         *
         * @return the current size
         */
        public int getCurrSize()
        {
            return currSize;
        }
    }
}
