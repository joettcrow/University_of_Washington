package edu.uw.jtc.exchange;

import edu.uw.ext.framework.exchange.ExchangeListener;
import edu.uw.ext.framework.exchange.StockQuote;
import edu.uw.ext.framework.order.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static edu.uw.jtc.exchange.MyProtocolConstants.*;

/**
 * Client for interacting with a network accessible exchange.
 * This SocketExchange methods encode the method request as a string,
 * per ProtocolConstants, and send the command to the ExchangeNetworkAdapter,
 * receive the response decode it and return the result.
 * @author jcrowley
 */

public class MyExchangeProxy extends Object
        implements edu.uw.ext.framework.exchange.StockExchange {

    private static final Logger log =
            LoggerFactory.getLogger( MyExchangeProxy.class );
    private String cmdIpAddress;
    private NetworkEventProcessor processor;
    private String eventIpAddress;
    private int commandPort;

//    make command ip address, processor, and eventIp address

    /**
     * Exchange proxy created
     * @param eventIpAddress eventIpAddress to use
     * @param eventPort event port to use
     * @param cmdIpAddress ip address to send commands
     * @param cmdPort command port to use
     */
    public MyExchangeProxy(String eventIpAddress,
                                int eventPort,
                                String cmdIpAddress,
                                int cmdPort){
        this.cmdIpAddress = cmdIpAddress;
        this.eventIpAddress = eventIpAddress;
        this.commandPort = cmdPort;
        this.processor = new NetworkEventProcessor(eventIpAddress, eventPort);
        Executors.newSingleThreadExecutor().execute(processor);
    }

//    constructor that sets all those, uses eventIp and eventport for NewEventProessor
//    make an executor

    /**
     * The state of the exchange.
     * @return true if the exchange is open otherwise false
     */
    public boolean isOpen() {
        String response = sendTcpCmd(GET_STATE_CMD);
        boolean state = OPEN_STATE.equals(response);
        return state;
    }

    /**
     * Gets the ticker symbols for all of the stocks in the traded on the exchange.
     * @return the stock ticker symbols
     */
    public String[] getTickers() {
        String response = sendTcpCmd(GET_TICKERS_CMD);
        String[] tickers = response.split(ELEMENT_DELIMITER);
        return tickers;
    }

    public StockQuote getQuote(String stockQuote) {
        String command = String.join(ELEMENT_DELIMITER, GET_QUOTE_CMD, stockQuote);
        String response = sendTcpCmd(command);
        int price = INVALID_STOCK;
        try {
            price = Integer.parseInt(response);
        } catch (NumberFormatException ex){
//            return log
        }

        StockQuote quote = null;
        if (price >= 0){
            quote = new StockQuote(stockQuote,price);
        }
        return quote;
    }

    public void addExchangeListener(ExchangeListener exchangeListener) {
        processor.addExchangeListener(exchangeListener);

    }

    public void removeExchangeListener(ExchangeListener exchangeListener) {
//        processor.removeExchangeListener(exchangeListener);

    }

    public int executeTrade(Order order) {
        String orderType = (order.isBuyOrder()) ? BUY_ORDER: SELL_ORDER;
        String command = String.join(
                ELEMENT_DELIMITER,
                EXECUTE_TRADE_CMD,
                orderType,
                order.getAccountId(),
                order.getStockTicker(),
                Integer.toString(order.getNumberOfShares()));
        String response = sendTcpCmd(command);
        int execPrice= 0;

//        try {
//            execPrice = Integer.parseInt()
//        }
//        String cmd = String.join(things)
        return execPrice;
    }

    private String sendTcpCmd(String cmd){
        PrintWriter printWriter = null;
        BufferedReader bufferedReader = null;
        String response = "";

        try (Socket socket = new Socket(cmdIpAddress, commandPort)) {
//            log
            InputStream inputStream = socket.getInputStream();
            Reader reader = new InputStreamReader(inputStream, ENCODING);
            bufferedReader = new BufferedReader(reader);

        } catch (UnknownHostException e) {
            log.warn("Exception due to unknown host", e);

        } catch (IOException e) {
            log.warn("IO exception", e);

        }

//            log
//            input stream
//            set streams
//            set outs too
//            }
        return null;
    }
}
