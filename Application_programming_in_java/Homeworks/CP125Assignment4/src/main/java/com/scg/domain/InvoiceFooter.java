package com.scg.domain;

import java.util.Formatter;

/**
 * Formats an invoice header
 * @author jcrowley
 */
public class InvoiceFooter {
    private final String businessName;
    private int pageNumber = 1;

    /**
     * Constructor; initializes this InvoiceFooter object.
     * @param businessName The name the Small Consulting Group.
     */
    public InvoiceFooter(String businessName){
        this.businessName = businessName;
    }

    /**
     * Increments the page number to insert into the footer.
     */
    public void incrementPageNumber(){
        pageNumber += 1;
    }

    /**
     * Formats the footer as a string suitable for printing.
     * @return Formats the footer as a string suitable for printing.
     */
    public String toString(){
        StringBuilder builder = new StringBuilder();
        Formatter formatter = new Formatter(builder);
        String fmt = "%-70s%-5s%4s" + System.lineSeparator();
//        String name = "The Small Consulting Group";
        String page = "Page:";
        formatter.format(
                fmt,
                businessName,
                page,
                pageNumber);
        builder.append(
                        "=====================" +
                                "=================" +
                                "=======================" +
                                "==================" +
                                System.lineSeparator()
        );

        return builder.toString();
    }
}
