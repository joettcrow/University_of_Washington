package edu.uw.jtc.broker;

import edu.uw.ext.framework.broker.OrderManager;
import edu.uw.ext.framework.broker.OrderQueue;
import edu.uw.ext.framework.order.StopBuyOrder;
import edu.uw.ext.framework.order.StopSellOrder;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * Maintains queues of different types of orders and requests the execution of orders when price conditions allow their execution.
 * @author jcrowley
 */
public class MyOrderManager implements OrderManager {
    private String stockTickerSymbol;

    private OrderQueue<Integer, StopBuyOrder> stopBuyOrderQueue;
    private OrderQueue<Integer, StopSellOrder> stopSellOrderQueue;

    /**
     * Constructor for the order manager
     * @param stockTickerSymbol the stock ticker to queue for
     * @param price the price of the stock
     */
    public MyOrderManager(final String stockTickerSymbol, final int price){
        this.stockTickerSymbol = stockTickerSymbol;
        stopBuyOrderQueue =
                new MyOrderQueue<>(price,
                        (t,o) -> o.getPrice() <= t,
                        Comparator.comparing(StopBuyOrder::getPrice)
                                .thenComparing(StopBuyOrder::compareTo));

        stopSellOrderQueue =
                new MyOrderQueue<>(price,
                        (t,o) -> o.getPrice() >= t,
                        Comparator.comparing(StopSellOrder::getPrice)
                                .reversed()
                                .thenComparing(StopSellOrder::compareTo));
    }

    /**
     * Sets new stockTickerSymbol.
     *
     * @param stockTickerSymbol New value of stockTickerSymbol.
     */
    public void setStockTickerSymbol(String stockTickerSymbol) {
        this.stockTickerSymbol = stockTickerSymbol;
    }

    /**
     * Adjusts the threshold prices
     * @param prices the price to change to
     */
    public final void adjustPrices(int prices){
        stopSellOrderQueue.setThreshold(prices);
        stopSellOrderQueue.setThreshold(prices);
    }

    /**
     * Getter for the stock ticker symbol
     * @return the stock ticker symbol
     */
    public String getSymbol() {
        return stockTickerSymbol;
    }

    /**
     * Adjuster for the price
     * @param i the price to change for the threshold
     */
    public void adjustPrice(int i) {
        stopBuyOrderQueue.setThreshold(i);
        stopSellOrderQueue.setThreshold(i);
    }

    /**
     * Queue a stop buy order.
     * @param order the order to be queued
     */
    public final void queueOrder(StopBuyOrder order){
        stopBuyOrderQueue.enqueue(order);
    }

    /**
     * Queue a stop sell order
     * @param stopSellOrder the order to be queued
     */
    public void queueOrder(StopSellOrder stopSellOrder) {
        stopSellOrderQueue.enqueue(stopSellOrder);
    }

    /**
     * Registers the processor to be used during buy order processing.
     * This will be passed on to the order queues as the dispatch callback.
     * @param processor the callback to be registered
     */
    public final void setBuyOrderProcessor(Consumer<StopBuyOrder> processor){
        stopBuyOrderQueue.setOrderProcessor(processor);
    }

    /**
     * Registers the processor to be used during sell order processing.
     * This will be passed on to the order queues as the dispatch callback.
     * @param processor the callback to be registered
     */
    public void setSellOrderProcessor(Consumer<StopSellOrder> processor) {
        stopSellOrderQueue.setOrderProcessor(processor);
    }
}

