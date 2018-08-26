package edu.uw.jtc.broker;

import edu.uw.ext.framework.broker.OrderQueue;
import edu.uw.ext.framework.order.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.TreeSet;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
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
public final class MyThreadedOrderQueue<T, E extends Order> implements OrderQueue<T, E>, Runnable{

    private static final Logger logger = LoggerFactory.getLogger(MyThreadedOrderQueue.class);

    private TreeSet<E> queue;

    private BiPredicate<T, E> filter;

    private Consumer<E> orderProcessor;

    private T threshold;

    private Thread thread;

    private ReentrantLock queueLock = new ReentrantLock();

    private Condition dispatchCondition = queueLock.newCondition();

    private ReentrantLock procLock = new ReentrantLock();

    /**
     * Constructor for the order queue
     * @param threshold the threshold for the order
     * @param filter the filter to use
     */
    public MyThreadedOrderQueue(final T threshold, final BiPredicate<T, E> filter){
        queue = new TreeSet<>();
        this.threshold = threshold;
        this.filter = filter;
        startDispatchThread();
    }

    /**
     * Constructor for the order queue
     * @param threshold the threshold to use for the orders
     * @param filter the filter for the orders
     * @param orderType the order type for the tree set
     */
    public MyThreadedOrderQueue(
            final T threshold,
            final BiPredicate<T, E> filter,
            Comparator<E> orderType){
        queue = new TreeSet<>(orderType);
        this.threshold = threshold;
        this.filter = filter;
        startDispatchThread();
    }

    /**
     * Private method to dispatch threads
     */
    private void startDispatchThread(){
        thread = new Thread(this);
        thread.setDaemon(true);
        thread.start();
    }

    /**
     * Adds the specified order to the queue.
     * Subsequent to adding the order dispatches any dispatchable orders.
     * @param order the order to be added to the queue
     */
    public void enqueue(E order) {
        queueLock.lock();
        try{
            if(queue.add(order)){
                dispatchOrders();
            }

        } finally {
            queueLock.unlock();
        }
    }

    /**
     * Removes the highest dispatchable order in the queue.
     * If there are orders in the queue
     * but they do not meet the dispatch threshold
     * an order will not be removed and null will be returned.
     * @return the highest order in the queue,
     * or null if there are no dispatchable orders in the queue
     */
    public E dequeue(){
        E order = null;

        queueLock.lock();
        try {
            if (!queue.isEmpty()){
                order = queue.first();

                if (filter.test(threshold,order)){
                    queue.remove(order);
                }
                else {
                    order = null;
                }
            }
        } finally {
            queueLock.unlock();
        }
        return order;
    }

    /**
     * Executes the callback for each dispatchable order.
     * Each dispatchable order is in turn removed from the queue and passed to the callback.
     * If no callback is registered the order is simply removed from the queue.
     */
    public void dispatchOrders() {
        queueLock.lock();
        try {
            dispatchCondition.signal();
        } finally {
            queueLock.unlock();
        }

    }

    /**
     * Registers the consumer to be used during order processing.
     * @param proc the consumer to be registered
     */
    public void setOrderProcessor(final Consumer<E> proc) {
        procLock.lock();
        try {
            orderProcessor = proc;
        } finally {
            procLock.unlock();
        }
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

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        while(true) {
            E order;

            queueLock.lock();
            try {
                while ((order = dequeue()) == null) {
                    try {
                        dispatchCondition.await();
                    } catch (InterruptedException e) {
                        logger.info("Thread interrupted");
                    }
                }
            } finally {
                queueLock.unlock();
            }

            procLock.lock();
            try {
                if (orderProcessor != null) {
                    orderProcessor.accept(order);
                }
            } finally {
                procLock.unlock();
            }
        }

    }
}
