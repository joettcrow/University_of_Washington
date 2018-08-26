package edu.uw.jtc.broker;

import edu.uw.ext.framework.broker.OrderQueue;
import edu.uw.ext.framework.order.Order;

import java.util.Comparator;
import java.util.TreeSet;
import java.util.function.BiPredicate;
import java.util.function.Consumer;

/**
 * A priority queue of orders,
 * with the additional semantics of a dispatch threshold.
 * Only dispatchable orders,
 * orders that meet the dispatch threshold,
 * may be dequeued.
 * Dispatchable orders are orders that meet some externally
 * defined criteria in addition to being at the top of the queue.
 * OrderQueues are configured with a dispatch filter, upon construction,
 * the dispatch filter will implement the dispatch criteria. and is of type BiPredicate.
 * @author jcrowley
 */
public final class MyOrderQueue<T, E extends Order> implements OrderQueue<T, E> {

    private TreeSet<E> queue;

    private BiPredicate<T, E> filter;

    private Consumer<E> orderProcessor;

    private T threshold;

    /**
     * Constructor for the order queue
     * @param threshold the threshold for the order
     * @param filter the filter to use
     */
    public MyOrderQueue(final T threshold, final BiPredicate<T, E> filter){
        queue = new TreeSet<>();
        this.threshold = threshold;
        this.filter = filter;
    }

    /**
     * Constructor for the order queue
     * @param threshold the threshold to use for the orders
     * @param filter the filter for the orders
     * @param orderType the order type for the tree set
     */
    public MyOrderQueue(final T threshold,
                        final BiPredicate<T, E> filter,
                        Comparator<E> orderType){
        queue = new TreeSet<>(orderType);
        this.threshold = threshold;
        this.filter = filter;
    }

    /**
     * Adds the specified order to the queue.
     * Subsequent to adding the order dispatches any dispatchable orders.
     * @param order the order to be added to the queue
     */
    public void enqueue(E order) {
        queue.add(order);
        dispatchOrders();
    }

    /**
     * Removes the highest dispatchable order in the queue.
     * If there are orders in the queue
     * but they do not meet the dispatch threshold
     * an order will not be removed and null will be returned.
     * @return the highest order in the queue,
     * or null if there are no dispatchable orders in the queue
     */
    public E dequeue() {
        E order = null;

        if (!queue.isEmpty()){
            order = queue.first();

            if (filter.test(threshold,order)){
                queue.remove(order);
            }
            else {
                order = null;
            }
        }
        return order;
    }

    /**
     * Executes the callback for each dispatchable order.
     * Each dispatchable order is in turn removed from the queue and passed to the callback.
     * If no callback is registered the order is simply removed from the queue.
     */
    public void dispatchOrders() {
        E order;

        while ((order = dequeue()) != null){
            if (orderProcessor != null){
                orderProcessor.accept(order);
            }
        }
    }

    /**
     * Registers the consumer to be used during order processing.
     * @param proc the consumer to be registered
     */
    public void setOrderProcessor(final Consumer<E> proc) {
        orderProcessor = proc;

    }

    /**
     * Adjusts the threshold and subsequently dispatches any dispatchable orders.
     * @param threshold the new threshold
     */
    public void setThreshold(T threshold) {
        this.threshold = threshold;
        dispatchOrders();
    }

    /**
     * Obtains the current threshold value.
     * @return the current threshold
     */
    public T getThreshold() {
        return threshold;
    }
}
