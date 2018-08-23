package edu.uw.jtc.exchange;

import edu.uw.ext.framework.exchange.ExchangeAdapter;
import edu.uw.ext.framework.exchange.NetworkExchangeAdapterFactory;
import edu.uw.ext.framework.exchange.StockExchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * @author jcrowley
 */

public class MyNetworkExchangeAdapterFactory implements NetworkExchangeAdapterFactory {
    private static final Logger log =
            LoggerFactory.getLogger(MyNetworkExchangeAdapterFactory.class);
    public ExchangeAdapter newAdapter(
            final StockExchange stockExchange,
            final String multicastIp,
            final int multicastPort,
            final int commandPort) {
        MyExchangeAdapter exchangeAdapter = null;
        try {
            exchangeAdapter = new MyExchangeAdapter(
                    stockExchange,
                    multicastIp,
                    multicastPort,
                    commandPort);
        } catch (UnknownHostException e) {
            log.warn("Unknown Host Exception thrown", e);

        } catch (SocketException e) {
            log.warn("Socket Exception thrown", e);

        }
        return exchangeAdapter;
    }
}
