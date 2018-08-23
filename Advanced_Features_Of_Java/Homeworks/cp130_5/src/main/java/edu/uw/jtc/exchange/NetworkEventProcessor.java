package edu.uw.jtc.exchange;

import edu.uw.ext.framework.exchange.ExchangeEvent;
import edu.uw.ext.framework.exchange.ExchangeListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.EventListener;

/**
 * @author jcrowley
 */

public class NetworkEventProcessor implements Runnable {

    private static final Logger log =
            LoggerFactory.getLogger( NetworkEventProcessor.class );

    int BUFFER_SIZE = 1024;
    String  eventIpAddress;
    int eventport;
    EventListener listener;
//    ake buffer_size constant = 1024, create logger, make  String eventIpAddress, int eventPort
//    make eventListenerList

//    constructor stores those

    NetworkEventProcessor(String eventIpAddress, int eventPort){

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
    public void run() {
        try (MulticastSocket eventSocket = new MulticastSocket(eventport)){
            InetAddress eventGroup = InetAddress.getByName(eventIpAddress);
            eventSocket.joinGroup(eventGroup);
//            log
            byte[] buf = new byte[BUFFER_SIZE];
            DatagramPacket packet = new DatagramPacket(buf,buf.length);

            while (true){
                eventSocket.receive(packet);
                String message = new String(packet.getData(), packet.getOffset());


//                switch (eventType){
//                    case OPEN_EVENT
                }
            } catch (UnknownHostException e) {
            log.warn("", e);

        } catch (IOException e) {
            log.warn("", e);

        }

    }

//        makes multi cast socket in try w/ resources
//        make eventgroup = inet.getbyname
//        in try eventSocket.joinGroup of eventGroup
//        log the events
//        make a buffer = buffer size
//        datagram packet = buf

//        make infinite loop
//        socket.receive(packet)
//        make string from the packet
//        split the string into elements String[]
//        make event type from the event


//        make switch statement with the open or close or price change events
//        send fireListeners using exchangeEvent.newEventType
//        setup the price change
//        catch errors
//    }

    public void addExchangeListener(ExchangeListener listener){
//        listenerList =
    }

    public void fireListeners(ExchangeEvent event){
        ExchangeListener[] listeners = new ExchangeListener[0];
//        listeners = listenerList.getListeners(ExchangeListener.class);

        for (ExchangeListener listener : listeners){
            switch (event.getEventType()){
                case OPENED:
                    listener.exchangeOpened(event);
                    break;
                case CLOSED:
                    listener.exchangeClosed(event);
                    break;
                case PRICE_CHANGED:
                    listener.priceChanged(event);
                    break;
                default:
//                    log
            }
        }
    }

//    make addexchangelistener and remove that use the things from proxy
//    this class stores listener list and adds or removes it

//    make fireListeners class
//    this goes to the listener list and gets listeners of class type
//    for listeners in listeners make switch
//    events do open or close or price change and fires to the listener.event
}
