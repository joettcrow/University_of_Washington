package edu.uw.jtc.web;

import edu.uw.ext.quote.AlphaVantageQuote;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author jcrowley
 */

public class MyStockQuoteServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;
    private ServletContext ctx;

    /**
     * Default constructor.
     */
    public MyStockQuoteServlet() {
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        serviceRequest(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        serviceRequest(request, response);
    }

    void serviceRequest(
            HttpServletRequest request, HttpServletResponse response
    ) throws ServletException, IOException {
        String ticker = request.getParameter("stockQuote");
        String format = request.getParameter("format");

        AlphaVantageQuote quote = null;
        try {
            quote = AlphaVantageQuote.getQuote(ticker);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String symbol = quote.getSymbol();
        int price = quote.getPrice();

        StringBuilder xmlBuilder = new StringBuilder();
        xmlBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        xmlBuilder.append("<stockQuote>");
        xmlBuilder.append("<Symbol>").append(symbol).append("</Symbol>");
        xmlBuilder.append("<Price>").append(price).append("</Price>");
        xmlBuilder.append("</stockQuote>\r\n");

        String xmlString = xmlBuilder.toString();
        response.setContentType("text/xml");
        response.setContentLength(xmlString.length());
        response.getWriter().print(xmlString);
    }
}
