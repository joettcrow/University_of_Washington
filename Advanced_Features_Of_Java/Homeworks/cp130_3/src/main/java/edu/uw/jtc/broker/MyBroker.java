package edu.uw.jtc.broker;

import edu.uw.ext.framework.account.Account;
import edu.uw.ext.framework.account.AccountException;
import edu.uw.ext.framework.account.AccountManager;
import edu.uw.ext.framework.broker.Broker;
import edu.uw.ext.framework.broker.BrokerException;
import edu.uw.ext.framework.broker.OrderManager;
import edu.uw.ext.framework.broker.OrderQueue;
import edu.uw.ext.framework.exchange.ExchangeEvent;
import edu.uw.ext.framework.exchange.ExchangeListener;
import edu.uw.ext.framework.exchange.StockExchange;
import edu.uw.ext.framework.exchange.StockQuote;
import edu.uw.ext.framework.order.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.function.Consumer;

/**
 * The broker interface.
 * This is the interface used by investors to create and access their account,
 * and to obtain quotes and place orders.
 * @author jcrowley
 */
public class MyBroker implements Broker, ExchangeListener {

    private static final Logger logger = LoggerFactory.getLogger(MyBroker.class);

    private String name;
    private AccountManager accountManager;
    private StockExchange stockExchange;
    private HashMap<String, OrderManager> orderManagerHashMap;
    public OrderQueue<Boolean, Order> marketOrders;

    /**
     * Constructor for the broker
     * @param brokerName the name of the broker
     * @param exchange the exchange for the broker
     * @param accountManager the account manager to manage accounts
     */
    public MyBroker(String brokerName, StockExchange exchange, AccountManager accountManager){
        this.name = brokerName;
        this.stockExchange = exchange;
        this.accountManager = accountManager;

        marketOrders = new MyOrderQueue<>(exchange.isOpen(), (t,o) -> t);
        Consumer<Order> consumer = (order -> {
            try {
                executeOrder(order);
            } catch (BrokerException e) {
                logger.warn("Cannot execute due to broker error", e);
            }
        });
        marketOrders.setOrderProcessor(consumer);
        initializeOrdermanagers();

        exchange.addExchangeListener(this);
    }

    /**
     * Initializes order managers
     */
    synchronized final void initializeOrdermanagers(){
        checkInvariants();
        orderManagerHashMap = new HashMap<>();
        Consumer<StopBuyOrder> moveBuy2MarketProc = (StopBuyOrder order) -> marketOrders
                .enqueue(order);
        Consumer<StopSellOrder> moveSell2MarketProc = (StopSellOrder order) -> marketOrders
                .enqueue(order);
        for (String ticker: stockExchange.getTickers()){
            int currPrices = stockExchange.getQuote(ticker).getPrice();
            OrderManager orderManager = createOrderManager(ticker, currPrices);
            orderManager.setBuyOrderProcessor(moveBuy2MarketProc);
            orderManager.setSellOrderProcessor(moveSell2MarketProc);
            orderManagerHashMap.put(ticker,orderManager);
            if (logger.isInfoEnabled()){
                logger.info(String.format("Created Order Manager for '%s' at '%d'",
                        ticker,
                        currPrices));
            }
        }
    }

    /**
     * Creates a new order manager
     * @param ticker the stock ticker for the order manager
     * @param initPrice the initial price for the manager
     * @return an order manager
     */
    public OrderManager createOrderManager(String ticker, int initPrice){
        checkInvariants();
        return new MyOrderManager(ticker, initPrice);
    }

    /**
     * Executes an order
     * @param order the order to execute
     * @throws BrokerException if the account to be accessed throws an error
     */
    public void executeOrder(Order order) throws BrokerException {
        checkInvariants();
        logger.info(String.format("Executing order for %d shares of %s",
                order.getNumberOfShares(),
                order.getStockTicker()));
        int sharePrices = stockExchange.executeTrade(order) * order.getNumberOfShares();
        try {
            Account account = accountManager.getAccount(order.getAccountId());
            if (order.isBuyOrder()){
                account.setBalance(account.getBalance() - sharePrices);
            }
            else if (!order.isBuyOrder()){
                account.setBalance(account.getBalance() + sharePrices);
            }
            accountManager.persist(account);
        } catch (AccountException e) {
            throw new BrokerException("Can't get account", e);

        }
    }

    /**
     * Getter for the name variable
     * @return the name of the candidate
     */
    public String getName() {
        return name;
    }

    /**
     * Creates an account using the account manager
     * @param name the name for the account
     * @param password the password for the account
     * @param balance the balance for the account
     * @return an account object
     * @throws BrokerException if the account cannot be created.
     */
    public Account createAccount(String name, String password, int balance) throws BrokerException {
        checkInvariants();
        try {
            return accountManager.createAccount(name,password,balance);
        } catch (AccountException e){
            throw new BrokerException("Can't create account", e);
        }
    }

    /**
     * Delete an account
     * @param accountName the account name to delete
     * @throws BrokerException if the account cannot be deleted
     */
    public void deleteAccount(String accountName) throws BrokerException {
        checkInvariants();
        try {
            accountManager.deleteAccount(accountName);
        } catch (AccountException e){
                throw new BrokerException("Can't delete account", e);
            }

    }

    /**
     * Getter for an account
     * @param accountName the name of the account to grab
     * @param password for the account
     * @return an account
     * @throws BrokerException if the account cannot be accessed
     */
    public Account getAccount(String accountName, String password) throws BrokerException {
        checkInvariants();
        Account account;
        try {
            if(accountManager.validateLogin(accountName, password)) {
                account = accountManager.getAccount(accountName);
            }
            else {
                throw new BrokerException("Account validation failed");
            }
        } catch (AccountException e) {
            throw new BrokerException("Account does not exist", e);

        }
        return account;
    }

    /**
     * Requests a quote for a stockName
     * @param stockName the stock to request the quote for
     * @return the stock quote
     * @throws BrokerException if something goes wrong
     */
    public StockQuote requestQuote(String stockName) throws BrokerException {
        checkInvariants();
        return stockExchange.getQuote(stockName);
    }

    /**
     * Places an order for a stock using a marketBuyOrder
     * @param marketBuyOrder to request
     * @throws BrokerException if something goes wrong
     */
    public void placeOrder(MarketBuyOrder marketBuyOrder) throws BrokerException {
        checkInvariants();
        marketOrders.enqueue(marketBuyOrder);

    }

    /**
     * Places an order for a stock using marketSellOrder
     * @param marketSellOrder to request
     * @throws BrokerException if something goes wrong
     */
    public void placeOrder(MarketSellOrder marketSellOrder) throws BrokerException {
        checkInvariants();
        marketOrders.enqueue(marketSellOrder);
    }

    /**
     * Places an order for a stock using a stopBuyOrder
     * @param stopBuyOrder to request
     * @throws BrokerException if something goes wrong
     */
    public void placeOrder(StopBuyOrder stopBuyOrder) throws BrokerException {
        checkInvariants();
        OrderManager orderManager = orderManagerHashMap.get(stopBuyOrder.getStockTicker());
        orderManager.queueOrder(stopBuyOrder);
    }

    /**
     * Places an order for a stock using a stopSellOrder
     * @param stopSellOrder to request
     * @throws BrokerException if something goes wrong
     */
    public void placeOrder(StopSellOrder stopSellOrder) throws BrokerException {
        checkInvariants();
        OrderManager orderManager = orderManagerHashMap.get(stopSellOrder.getStockTicker());
        orderManager.queueOrder(stopSellOrder);
    }

    /**
     * Closes the broker
     * @throws BrokerException if an account exception occurs
     */
    public void close() throws BrokerException {
        try {
            stockExchange.removeExchangeListener(this);
            accountManager.close();
            orderManagerHashMap = null;
        } catch (final AccountException ex){
            throw new BrokerException( "Can't close broker", ex);
        }

    }

    /**
     * Helper method to kill a run if we have invalid values
     */
    private void checkInvariants(){
        if (((name == null) || (accountManager == null) || (stockExchange == null))){
            throw new IllegalStateException("broker is not in a good state");
        }
    }

    /**
     * Opens the exchange and lets listeners know
     * @param exchangeEvent the exchange to open and announce
     */
    public void exchangeOpened(ExchangeEvent exchangeEvent) {
        checkInvariants();
        marketOrders.setThreshold(Boolean.TRUE);

    }

    /**
     * Closes the exchange and lets the listeners know
     * @param exchangeEvent the exchange to close and announce
     */
    public void exchangeClosed(ExchangeEvent exchangeEvent) {
        checkInvariants();
        marketOrders.setThreshold(Boolean.FALSE);
    }

    /**
     * Changes the price of a stock and let's listeners know
     * @param exchangeEvent the price change event to announce and perform
     */
    public void priceChanged(ExchangeEvent exchangeEvent) {
        OrderManager orderManager = orderManagerHashMap.get(exchangeEvent.getTicker());
        if (orderManager != null) {
            logger.info(String.format("Changing value of %s to %d",
                    exchangeEvent.getTicker(),
                    exchangeEvent.getPrice()));
            orderManager.adjustPrice(exchangeEvent.getPrice());
        }
    }
}
