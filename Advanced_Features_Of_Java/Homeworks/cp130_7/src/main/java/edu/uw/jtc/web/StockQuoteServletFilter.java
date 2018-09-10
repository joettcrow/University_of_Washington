package edu.uw.jtc.web;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * @author jcrowley
 */

public class StockQuoteServletFilter implements Filter {

    private ServletContext ctx;

    @Override
    public void init(
            FilterConfig filterConfig
    ) throws ServletException {
        ctx = filterConfig.getServletContext();

    }

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain
    ) throws IOException, ServletException {
        final CharResponseWrapper responseWrapper = new CharResponseWrapper(
                (HttpServletResponse) servletResponse);
        filterChain.doFilter(servletRequest, responseWrapper);

        ctx.log("initializing  : " + responseWrapper.toString());

        StringWriter output = new StringWriter();
        String format = servletRequest.getParameter("format");
        StringBuilder builder = new StringBuilder();
        ctx.log("format is set to " + format);

        String symbol = null;
        String price = null;
        try {
            ctx.log("entered the try ");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource src = new InputSource();
            src.setCharacterStream(new StringReader(responseWrapper.toString()));
            Document doc = db.parse(src);
            symbol = doc.getElementsByTagName("Symbol").item(0).getTextContent();
            price = doc.getElementsByTagName("Price").item(0).getTextContent();
        } catch (ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }
        ctx.log("We should have a node list?");
        builder.append(responseWrapper.toString());

//        if (!format.equals("xml")) {
            switch (format) {
                case "plain":
                    servletResponse.setContentType("text/plain");
                    builder.delete(0, responseWrapper.toString().length());
                    builder.append(
                            String.format("Stock %s is currently selling for: %s", symbol, price));
                    break;
                case "json":
                    builder.delete(0, responseWrapper.toString().length());
                    servletResponse.setContentType("application/json");
                    builder.append(
                            String.format("{\"Symbol\":\"%s\", \"Price\":\"%s\"}", symbol, price));
                    break;
                case "html":
                    builder.delete(0, responseWrapper.toString().length());
                    servletResponse.setContentType("text/html");
                    builder.append(
                            String.format("<strong>%s</strong> is selling for: %s", symbol, price));
                    break;
                default:
                    servletResponse.setContentType("text/xml");
            }
//        }
        output.append(builder.toString());
        String respStr = output.toString();
        servletResponse.setContentLength(respStr.length());
        servletResponse.getWriter().write(output.toString());
    }

    @Override
    public void destroy() {

    }
}
