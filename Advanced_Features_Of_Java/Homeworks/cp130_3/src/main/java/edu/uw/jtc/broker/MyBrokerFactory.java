package edu.uw.jtc.broker;

import edu.uw.ext.framework.account.AccountManager;
import edu.uw.ext.framework.broker.Broker;
import edu.uw.ext.framework.broker.BrokerFactory;
import edu.uw.ext.framework.exchange.StockExchange;

/**
 * Factory to create brokers
 * @author jcrowley
 */
public class MyBrokerFactory implements BrokerFactory {

    /**
     * Creator for the broker
     * @param brokerName the broker name
     * @param accountManager the account manager for the broker to use
     * @param stockExchange the stock exchange for the broker
     * @return a broker object
     */
    @Override
    public Broker newBroker(String brokerName, AccountManager accountManager, StockExchange stockExchange) {
        return new MyBroker(brokerName, stockExchange, accountManager);
    }
}
