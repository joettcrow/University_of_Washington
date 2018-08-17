package edu.uw.jtc.exchange;

import edu.uw.ext.framework.exchange.ExchangeListener;
import edu.uw.ext.framework.exchange.StockQuote;
import edu.uw.ext.framework.order.Order;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Client for interacting with a network accessible exchange.
 * This SocketExchange methods encode the method request as a string,
 * per ProtocolConstants, and send the command to the ExchangeNetworkAdapter,
 * receive the response decode it and return the result.
 * @author jcrowley
 */

public class MyExchangeProxy extends Object
        implements edu.uw.ext.framework.exchange.StockExchange {

//    make command ip address, processor, and eventIp address


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
        String[] tickers = response.split(DELIMITER,rep)
        return new String[0];
    }

    public StockQuote getQuote(String stockQuote) {
//        make command, response, and price (set to invalid)
//        try to make set price to the Integer.parseINt(response)
//        Make quote null
//        if price is greather than zero set the quote to the new StockQuote of ticker and price
        return price;
    }

    public void addExchangeListener(ExchangeListener exchangeListener) {
        eventProcessor.addExchangeListener(exchangeListener);

    }

    public void removeExchangeListener(ExchangeListener exchangeListener) {
        eventProcessor.removeExchangeListener(exchangeListener);

    }

    public int executeTrade(Order order) {
        String orderType = (order.isBuyOrder()) ? BUY_ORDER: SELL_ORDER;
        String cmd = String.join(things)
        return 0;
    }

    private String sendTcpCmd(String cmd){
        PrintWriter printWriter = null;
        BufferedReader bufferedReader = null;
        String response = "";

        try (Socket socket = new Socket(commandIpAddress, commandPort)) {
//            log
//            input stream
//            set streams
//            set outs too
            }
    }
}
