package edu.uw.jtc.exchange;

import edu.uw.ext.framework.exchange.NetworkExchangeProxyFactory;
import edu.uw.ext.framework.exchange.StockExchange;

/**
 * @author jcrowley
 */

public class MyExchangeNetworkProxyFactory implements NetworkExchangeProxyFactory {
    public StockExchange newProxy(String s, int i, String s1, int i1) {
        MyExchangeProxy proxy = new MyExchangeProxy(s,i,s1,i1);
        return proxy;
    }
}
