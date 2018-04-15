package cp120.d_list;

/**
 * Encapsulation of a node in a doubly-linked list.
 * Encapsulation of a node in a doubly-linked list.
 * The state of a node includes three references:
 * <ul>
 * <li>A reference to the user data stored in the node.</li>
 * <li>A reference to the previous item in the list (if enqueued).</li>
 * <li>A reference to the next item in the list (if enqueued).</li>
 * </ul>
 * <p>
 * Definitions:
 * <blockquote>
 * <dl>
 * <dt>Enqueued node</dt>
 * <dd>A node is <em>enqueued</em> if its flink and blink point other items.
 * </dd>
 * <dt>Unenqueued node</dt>
 * <dd>A node is <em>unenqueued</em> if its flink and blink point to itself.
 * </dd>
 * </dl>
 * </blockquote>
 * @author jcrowley
 */
public class DNode {
    private DNode flink;
    private DNode blink;
    private Object data;

    /**
     * Constructs a new node with the given data.  The node is constructed in the unenqueued state.
     * @param data The given data.
     */
    public DNode(Object data) {
        flink = this;
        blink = this;
        this.data = data;
    }

    /**
     * Default constructor. The node is constructed in the unenqueued state.
     */
    public DNode() {
        this(null);
    }

    /**
     * The user data stored in this node.
     * @return The user data.
     */
    public Object getData() {
        return this.data;
    }

    /**
     * Sets the user data in this node.
     * @param data The user data.
     */
    public void setData(Object data) {
        this.data = data;
    }

    /**
     * Returns true if this node is enqueued.
     * @return True if this node is enqueued, false otherwise.
     */
    public boolean isEnqueued(){
        return this != this.flink;
    }

    /**
     * Adds a new node after this one. The new node must be in the
     * unenqueued state; if it is not, an exception will be thrown.
     * @param node The node to add.
     * @throws java.lang.IllegalArgumentException if <em>node</em> is in the
     * enqueued state.
     */
    public void addAfter (DNode node) throws java.lang.IllegalArgumentException{
        if (!node.isEnqueued()) {
            node.blink = this;
            node.flink = this.flink;
            this.flink = node;
            node.flink.blink = node;
        }
        else {
            throw new IllegalArgumentException("Node already part of the List");
        }
    }

    /**
     * Adds a new node before this one. The new node must be in the
     * unenqueued state; if it is not, an exception will be thrown.
     * @param node The node to add.
     * @throws java.lang.IllegalArgumentException Adds a new node before this one.
     * The new node must be in the
     * unenqueued state; if it is not, an exception will be thrown.
     */
    public void addBefore (DNode node) throws java.lang.IllegalArgumentException {
        if (!node.isEnqueued()) {
            node.flink = this;
            node.blink = this.blink;
            this.blink = node;
            node.blink.flink = node;
        }
        else {
            throw new IllegalArgumentException("Node already part of the List");
        }
    }

    /**
     * Removes this node from the list it belongs to.
     * If this node does not belong to a list, no action is taken.
     * This node is returned.
     * @return This node.
     */
    public DNode remove(){
        blink.flink = flink;
        flink.blink = blink;
        flink = this;
        blink = this;

        return this;
    }

    /**
     * Returns the next node. If the node is unenqueued the
     * node itself is returned. If the node is at the tail of a DList,
     * a reference to the DList is returned.
     * <p>
     * Sample usage:
     * <blockquote>
     * <pre><code>
     * DNode next = list.getHead();
     * while ( next != list )
     * {
     * System.out.println( next.getData() );
     * next = node.getNext();
     * }
     * </code></pre>
     * </blockquote>
     * @return The next node in the list, or itself if unenqueued.
     */
    public DNode getNext(){
        return flink;
    }

    /**
     * Returns the previous node. If the node is unenqueued the
     * node itself is returned. If the node is at the head of a DList,
     * a reference to the DList is returned.
     * <p>
     * Sample usage:
     * <blockquote>
     * <pre><code>
     * DNode next = list.getTail();
     * while ( next != list )
     * {
     * System.out.println( next.getData() );
     * next = node.getPrevious();
     * }
     * </code></pre>
     * </blockquote>
     * @return The previous node in the list, or itself if unenqueued.
     */
    public DNode getPrevious(){
        return blink;
    }

}
