package edu.uw.jtc.exchange;

//use static imporsts

import edu.uw.ext.framework.exchange.ExchangeAdapter;
import edu.uw.ext.framework.exchange.ExchangeEvent;
import edu.uw.ext.framework.exchange.StockExchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.IOException;
import java.net.*;
import java.util.concurrent.Executors;

import static edu.uw.jtc.exchange.MyProtocolConstants.*;

/**
 * @author jcrowley
 */

public class MyExchangeAdapter implements ExchangeAdapter {
    private static final Logger log =
            LoggerFactory.getLogger( MyExchangeAdapter.class );

    private int TTL = 4;
    private StockExchange stockExchange;
    private MulticastSocket eventSocket;
    private DatagramPacket datagramPacket;
    private CommandListener commandListener;

    /**
     * Constructor for exchange adaptor
     * @param exchange the exchange to add
     * @param multicastIp the ip to multicast to
     * @param multicastPort the port to multicast to
     * @param commandPort the command port to use
     * @throws UnknownHostException if the host is invalid
     * @throws SocketException if something happens in the socket
     */
    public MyExchangeAdapter(final StockExchange exchange,
                             final String multicastIp,
                             final int multicastPort,
                             final int commandPort)
            throws UnknownHostException, SocketException{
        stockExchange = exchange;
        InetAddress multicastGroup = InetAddress.getByName(multicastIp);
        byte[] buf = {};
        datagramPacket = new DatagramPacket(buf,0,multicastGroup,multicastPort);
        try {
            eventSocket = new MulticastSocket();
            eventSocket.setTimeToLive(TTL);
            log.info("Sending events via multicast to " + multicastIp + ":" + multicastPort);
        } catch (IOException e) {
            log.warn("Input and output are messed up, look into it", e);
        }
        commandListener = new CommandListener(commandPort, stockExchange);
        Executors.newSingleThreadExecutor().execute(commandListener);

        stockExchange.addExchangeListener(this);
    }

    /**
     * The exchange has opened and prices are adjusting.
     * @param exchangeEvent the event
     */
    public void exchangeOpened(final ExchangeEvent exchangeEvent) {
        try {
            sendMulticastEvent(OPEN_EVNT);
        } catch (IOException e) {
            log.warn("Open event Doesn't work", e);

        }

    }

    public void exchangeClosed(final ExchangeEvent exchangeEvent) {
        try {
            sendMulticastEvent(CLOSED_EVNT);
        } catch (Exception e) {
            log.warn("Closed event doesn't work", e);

        }

    }

    public void priceChanged(final ExchangeEvent exchangeEvent) {
        String symbol = exchangeEvent.getTicker();
        Integer price = exchangeEvent.getPrice();
        String priceChange = String.join(
                ELEMENT_DELIMITER,
                PRICE_CHANGE_EVNT,
                symbol,
                price.toString());
        try {
            sendMulticastEvent(priceChange);
        } catch (IOException e) {
            log.warn("Can't Send price change", e);
        }
    }

    /**
     * Closes this resource, relinquishing any underlying resources.
     * This method is invoked automatically on objects managed by the
     * {@code try}-with-resources statement.
     *
     * <p>While this interface method is declared to throw {@code
     * Exception}, implementers are <em>strongly</em> encouraged to
     * declare concrete implementations of the {@code close} method to
     * throw more specific exceptions, or to throw no exception at all
     * if the close operation cannot fail.
     *
     * <p> Cases where the close operation may fail require careful
     * attention by implementers. It is strongly advised to relinquish
     * the underlying resources and to internally <em>mark</em> the
     * resource as closed, prior to throwing the exception. The {@code
     * close} method is unlikely to be invoked more than once and so
     * this ensures that the resources are released in a timely manner.
     * Furthermore it reduces problems that could arise when the resource
     * wraps, or is wrapped, by another resource.
     *
     * <p><em>Implementers of this interface are also strongly advised
     * to not have the {@code close} method throw {@link
     * InterruptedException}.</em>
     * <p>
     * This exception interacts with a thread's interrupted status,
     * and runtime misbehavior is likely to occur if an {@code
     * InterruptedException} is {@linkplain Throwable#addSuppressed
     * suppressed}.
     * <p>
     * More generally, if it would cause problems for an
     * exception to be suppressed, the {@code AutoCloseable.close}
     * method should not throw it.
     *
     * <p>Note that unlike the {@link Closeable#close close}
     * method of {@link Closeable}, this {@code close} method
     * is <em>not</em> required to be idempotent.  In other words,
     * calling this {@code close} method more than once may have some
     * visible side effect, unlike {@code Closeable.close} which is
     * required to have no effect if called more than once.
     * <p>
     * However, implementers of this interface are strongly encouraged
     * to make their {@code close} methods idempotent.
     *
     * @throws Exception if this resource cannot be closed
     */
    public void close() throws Exception {
        stockExchange.removeExchangeListener(this);
        commandListener.terminate();
        eventSocket.close();
    }

    /**
     * Helper method for sending multicasts
     * @param message the message to send
     * @throws IOException
     */
    private synchronized void sendMulticastEvent(String message) throws IOException{
        final byte[] buf = message.getBytes(ENCODING);
        datagramPacket.setData(buf);
        datagramPacket.setLength(buf.length);
        eventSocket.send(datagramPacket);
    }
}
