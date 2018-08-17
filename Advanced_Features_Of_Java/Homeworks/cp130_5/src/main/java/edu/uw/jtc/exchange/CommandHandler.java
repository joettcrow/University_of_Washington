package edu.uw.jtc.exchange;

import edu.uw.ext.framework.exchange.StockExchange;
import edu.uw.ext.framework.exchange.StockQuote;
import edu.uw.ext.framework.order.MarketBuyOrder;
import edu.uw.ext.framework.order.MarketSellOrder;
import edu.uw.ext.framework.order.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;

import static edu.uw.jtc.exchange.MyProtocolConstants.*;

/**
 * @author jcrowley
 */

public class CommandHandler implements Runnable {
    private static final Logger log =
            LoggerFactory.getLogger(CommandHandler.class);
    private Socket socket;
    private StockExchange exchange;

    public CommandHandler(Socket sock,
                          StockExchange exchange){
        this.exchange = exchange;
        this.socket = sock;
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
        try {
            InputStream inputStream = socket.getInputStream();
            InputStreamReader reader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(reader);

            OutputStream outputStream = socket.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
            PrintWriter printWriter = new PrintWriter(outputStreamWriter);

            String message = bufferedReader.readLine();
            if (message == null){
                message = "";
            }
            log.info("Message is" + message);

            String[] splitMessage = message.split(ELEMENT_DELIMITER);
            String command = splitMessage[CMD_ELEMENT];

            switch (command){
                case GET_STATE_CMD:
                    doGetState(printWriter);
                case GET_TICKERS_CMD:
                    doGetTickers(printWriter);
                case GET_QUOTE_CMD:
                    doGetQuote(printWriter,splitMessage);
                case EXECUTE_TRADE_CMD:
                    doExecuteTrade(printWriter,splitMessage);
                default:
                    log.info("IDK what you wanted the switch to find, but it didn't");
            }

        } catch (IOException e) {
            log.warn("", e);

        }

//        try input stream, reader, and buffered reader
//        continue trying output stream, writer and print writer

//        message is the readline, if message is null set message to empty string

//        log the message, split it and run the command

//        switch on cmd
//        case get state, get tickers, get quote, execute trade, and default for weird things
//        for the non weird cases do doGetCommand(printWrtr) and elements if needed
    }

    private void doGetState(PrintWriter writer){
        String response = exchange.isOpen() ? OPEN_STATE : CLOSED_STATE;
        writer.print(response);
    }

    private void doGetTickers(PrintWriter writer){
        String[] tickers = exchange.getTickers();
        String tickerString = String.join(ELEMENT_DELIMITER,tickers);
        writer.println(tickerString);
    }

    private void doGetQuote(PrintWriter writer, String[] commandAndQuote){
        StockQuote quote = exchange.getQuote(commandAndQuote[QUOTE_CMD_TICKER_ELEMENT]);
        writer.println(quote.getPrice());
    }

    private void doExecuteTrade(PrintWriter writer, String[] tradeInfo){
        int price = 0;

        if (exchange.isOpen()){
            String tradeType = tradeInfo[EXECUTE_TRADE_CMD_TYPE_ELEMENT];
            String ticker = tradeInfo[EXECUTE_TRADE_CMD_TICKER_ELEMENT];
            int shares = Integer.parseInt(tradeInfo[EXECUTE_TRADE_CMD_SHARES_ELEMENT]);
            String accountId = tradeInfo[EXECUTE_TRADE_CMD_ACCOUNT_ELEMENT];


            Order order;

            if (tradeType.equals("MarketBuyOrder")){
                order = new MarketBuyOrder(
                        accountId,
                        shares,
                        ticker);
            } else {
                order = new MarketSellOrder(
                        accountId,
                        shares,
                        ticker);
            }
            price = exchange.executeTrade(order);
        }
        writer.print(price);
    }


//    make methods to run the commands that are valid,  get state tells you if it's open
//    neat thing, String response = realExchange.isOpen() ? OPEN_STATE: CLOSED_STATE;
//    final form is prntWrter.println(thing!);

//    for get Tickers do a String[] tickers, and make a tickerstring that joins it using String
// .join(element_delimiter, tickers)

//    for Execute trade you need to do all the execute things:
//    check if the ecxhange is open, make an order type, acctId, ticker and shares if it is
//    make a qty = -1 and try to parse the int
//    make an order, if it's a buy order make a new marketBuyOrder, if it's sell make a sell
//    set the price to the execution price and send the price
//    if the exchange is closed send 0
}
