package edu.uw.jtc.exchange;

import edu.uw.ext.framework.exchange.ExchangeAdapter;
import edu.uw.ext.framework.exchange.NetworkExchangeAdapterFactory;
import edu.uw.ext.framework.exchange.StockExchange;

/**
 * @author jcrowley
 */

public class MyNetworkExchangeAdapterFactory implements NetworkExchangeAdapterFactory {
    public ExchangeAdapter newAdapter(StockExchange stockExchange, String s, int i, int i1) {
        return null;
    }
}
