package app;

import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author jcrowley
 */

public class QuoteApp extends HttpServlet {

    private static void exec(final String tickerSymbol, final String format) {
        HttpURLConnection conn = null;
        String baseUrl = "http://localhost:8080/StockQuote/welcome";
        try {
            String urlStr = String.format(
                    "%s?tickerSymbol=%s&format=%s",
                    baseUrl,
                    tickerSymbol,
                    format
            );
            URL url = new URL(urlStr);
            conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");

            InputStream in = conn.getInputStream();
            Reader rdr = new InputStreamReader(in);
            char buf[] = new char[1024];
            int len = 0;
            while ((len = rdr.read(buf)) != -1) {
                System.out.print(new String(buf, 0, len));
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args)  {
        String stockQuote = "bac";

        System.out.println("JSON:");
        exec(stockQuote, "json");
        System.out.println();

        System.out.println();
        System.out.println("plain:");
        exec(stockQuote, "plain");

        System.out.println();
        System.out.println("HTML:");
        exec(stockQuote, "html");

        System.out.println();
        System.out.println("XML:");
        exec(stockQuote, "xml");

        System.out.println();
        System.out.println();
    }
}
