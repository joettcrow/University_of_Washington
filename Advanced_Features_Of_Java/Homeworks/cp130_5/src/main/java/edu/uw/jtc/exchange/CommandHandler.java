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

    /**
     * Constructor
     * @param sock socket to use
     * @param exchange exchange to use
     */
    public CommandHandler(final Socket sock,
                          final StockExchange exchange){
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
            InputStreamReader reader = new InputStreamReader(inputStream, ENCODING);
            BufferedReader bufferedReader = new BufferedReader(reader);

            OutputStream outputStream = socket.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, ENCODING);
            PrintWriter printWriter = new PrintWriter(outputStreamWriter,true);

            String message = bufferedReader.readLine();
            if (message == null){
                message = "";
            }
            log.info("Message is" + message);

            String[] splitMessage = message.split(ELEMENT_DELIMITER);
            String command = splitMessage[CMD_ELEMENT];

            log.info("Processing command :%s", command);

            switch (command){
                case GET_STATE_CMD:
                    doGetState(printWriter);
                    break;
                case GET_TICKERS_CMD:
                    doGetTickers(printWriter);
                    break;
                case GET_QUOTE_CMD:
                    doGetQuote(printWriter,splitMessage);
                    break;
                case EXECUTE_TRADE_CMD:
                    doExecuteTrade(printWriter,splitMessage);
                    break;
                default:
                    log.info("IDK what you wanted the switch to find, but it didn't");
                    break;
            }

        } catch (IOException e) {
            log.warn("IO exception thrown in switch section", e);
        } finally {
            try {
                if (socket != null){
                    socket.close();
                }
            } catch (IOException e) {
                log.warn("Error when closing socket", e);

            }
        }
    }

    private void doGetState(final PrintWriter writer){
        String response = exchange.isOpen() ? OPEN_STATE : CLOSED_STATE;
        writer.print(response);
    }

    private void doGetTickers(final PrintWriter writer){
        String[] tickers = exchange.getTickers();
        String tickerString = String.join(ELEMENT_DELIMITER,tickers);
        writer.println(tickerString);
    }

    private void doGetQuote(final PrintWriter writer,
                            final String[] commandAndQuote){
        String ticker = String.valueOf(commandAndQuote);
        StockQuote quote = exchange.getQuote(commandAndQuote[QUOTE_CMD_TICKER_ELEMENT]);
        int price = (quote == null) ? INVALID_STOCK : quote.getPrice();
        writer.println(price);
    }

    private void doExecuteTrade(final PrintWriter writer,
                                final String[] tradeInfo){
        int price = 0;
        String tradeType = tradeInfo[EXECUTE_TRADE_CMD_TYPE_ELEMENT];
        String ticker = tradeInfo[EXECUTE_TRADE_CMD_TICKER_ELEMENT];
        String shares = tradeInfo[EXECUTE_TRADE_CMD_SHARES_ELEMENT];
        String accountId = tradeInfo[EXECUTE_TRADE_CMD_ACCOUNT_ELEMENT];
        int qty = -1;
        try {
            qty = Integer.parseInt(shares);
        } catch (NumberFormatException e){
            log.info("idk, something about numbers");
        }

        if (exchange.isOpen()){


            Order order;

            if (tradeType.equals("MarketBuyOrder")){
                order = new MarketBuyOrder(
                        accountId,
                        qty,
                        ticker);
            } else {
                order = new MarketSellOrder(
                        accountId,
                        qty,
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
