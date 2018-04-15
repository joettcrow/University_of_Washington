package cp120.d_list;

/**
 * Implementation of a doubly-linked list. The list itself is implemented as
 * a Node; the flink and blink of the list point to the head and tail of the
 * list, respectively.
 * <p>
 * Definition:
 * <blockquote>
 * <dl>
 * <dt>Empty list</dt>
 * <dd>A list is <em>empty</em> if its flink and blink point to itself.</dd>
 * </dl>
 * </blockquote>
 * <p>
 * Note that methods such as getHead() do <em>not</em> return null for
 * an empty list; instead they return the list itself. Likewise, the flink
 * and blink of the tail and head nodes, respectively, are not null; they
 * point to the list, itself.
 * <p>
 * Sample usage:
 * <blockquote>
 * <pre><code>
 * DNode node = list.getHead();
 * while ( node != list )
 * {
 * System.out.println( node.getData() );
 * node = node.getNext();
 * }
 * </code></pre>
 * </blockquote>
 * <p>
 * @author jcrowley
 */
public class DList extends DNode{

    /**
     * Adds a new node to the head of the list.
     * @param node The node to add.
     */
    public void addHead(DNode node){
        addAfter(node);
    }

    /**
     * Adds a new node to the tail of the list.
     * @param node The node to add.
     */
    public void addTail(DNode node){
        addBefore(node);
    }

    /**
     * Removes and returns the head of the list. If the list is
     * empty, the list is returned.
     * <p>
     * Example:
     * </p>
     * <blockquote>
     * <pre><code>
     * DNode node = list.removeHead();
     * if ( node == list )
     * log( "the list is empty" );
     * else
     * process( node );
     * </code></pre>
     * </blockquote>
     * @return The head of the list (after removing it from the list).
     */
    public DNode removeHead(){
        DNode head = getNext();
        return head.remove();
    }

    /**
     * Removes and returns the tail of the list. If the list is
     * empty, the list is returned.
     * <p>
     * Example:
     * </p>
     * <blockquote>
     * <pre><code>
     * DNode node = list.removeTail();
     * if ( node == list )
     * log( "the list is empty" );
     * else
     * process( node );
     * </code></pre>
     * </blockquote>
     * @return The tail of the list (after removing it from the list).
     */
    public DNode removeTail(){
        DNode tail = getPrevious();
        return tail.remove();
    }

    /**
     * Returns the head of the list (without removing it). If the list is
     * empty, the list is returned.
     * <p>
     * Example:
     * </p>
     * <blockquote>
     * <pre><code>
     * DNode node = list.getHead();
     * if ( node == list )
     * log( "the list is empty" );
     * else
     * process( node );
     * </code></pre>
     * </blockquote>
     * @return The head of the list (without removing it).
     */
    public DNode getHead(){
        return getNext();
    }

    /**
     * Returns the tail of the list (without removing it). If the list is
     * empty, the list is returned.
     * <p>
     * Example:
     * </p>
     * <blockquote>
     * <pre><code>
     * DNode node = list.getTail();
     * if ( node == list )
     * log( "the list is empty" );
     * else
     * process( node );
     * </code></pre>
     * </blockquote>
     * @return The tail of the list (without removing it).
     */
    public DNode getTail(){
        return getPrevious();
    }

    /**
     * Removes all items from the list, leaving the list empty.
     */
    public void removeAll(){
        DNode node;
        while ((node = getNext()) != this) {
            node.remove();
        }
    }

    /**
     * Indicates whether or not the list is empty.
     * @return True if the list is empty, false otherwise.
     */
    public boolean isEmpty(){
        return (getNext() == this) && (getPrevious() == this);
    }

    /**
     * Calculates the number of nodes in the list.
     * @return The number of nodes in the list
     */
    public int size(){
        int count = 0;
        DNode node = getHead();
        while (node!=this){
            ++count;
            node = node.getNext();
        }
        return count;
    }

}
