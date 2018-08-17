package edu.uw.jtc.exchange;

/**
 * @author jcrowley
 */

public class NetworkEventProcessor implements Runnable {
//    ake buffer_size constant = 1024, create logger, make  String eventIpAddress, int eventPort
//    make eventListenerList

//    constructor stores those

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
    }

//    make addexchangelistener and remove that use the things from proxy
//    this class stores listener list and adds or removes it

//    make fireListeners class
//    this goes to the listener list and gets listeners of class type
//    for listeners in listeners make switch
//    events do open or close or price change and fires to the listener.event
}
