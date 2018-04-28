package com.scg.domain;

import com.scg.util.Address;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;

/**
 * Formats an invoice header
 * @author jcrowley
 */
public class InvoiceHeader {
    private final String businessName;
    private final Address businessAddress;
    private final ClientAccount client;
    private final LocalDate invoiceDate;
    private final LocalDate invoiceForMonth;

    public InvoiceHeader(
            String businessName,
            Address businessAddress,
            ClientAccount client,
            LocalDate invoiceDate,
            LocalDate invoiceForMonth){
        this.businessName = businessName;
        this.businessAddress = businessAddress;
        this.client = client;
        this.invoiceDate = invoiceDate;
        this.invoiceForMonth = invoiceForMonth;
    }

    private String scgAddressCreator(String businessName,Address businessAddress){
        StringBuilder bld = new StringBuilder();
        bld.append(businessName + System.lineSeparator());
        bld.append(businessAddress.toString() + System.lineSeparator());
        bld.append(System.lineSeparator());

        return bld.toString();
    }

    private String IntroForLineItems(){
        StringBuilder bldr = new StringBuilder();
        Formatter formatter = new Formatter(bldr);
        String fmt = "%-10s  %-28s  %-18s  %-5s  %-10s";
        formatter.format(
                fmt,
                "Date",
                "Consultant",
                "Skill",
                "Hours",
                "Charge"
        );
        bldr.append(System.lineSeparator());
        formatter.format(
                fmt,
                "----------",
                "----------------------------",
                "------------------",
                "-----",
                "----------"
                );
        bldr.append(System.lineSeparator());
        return bldr.toString();
    }

    private String ClientAddressBuilder(){
        StringBuilder bld = new StringBuilder();
        bld.append("Invoice for: " + System.lineSeparator());
        bld.append(client.getName() + System.lineSeparator());

        DateTimeFormatter dateMonthFormatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        DateTimeFormatter invoiceDateFormatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
        String dm = dateMonthFormatter.format(invoiceForMonth);
        bld.append("Invoice For Month of: ");
        bld.append(dm + System.lineSeparator());
        String id = invoiceDateFormatter.format(invoiceDate);
        bld.append("Invoice Date: ");
        bld.append(id + System.lineSeparator() + System.lineSeparator());
        bld.append(IntroForLineItems());


        return bld.toString();
    }

    /**
     * Formats the header data as a string suitable for printing.
     * @return The header data as a string suitable for printing.
     */
    public String toString(){
        StringBuilder bldr = new StringBuilder();
        bldr.append(scgAddressCreator(businessName,businessAddress));
        bldr.append(ClientAddressBuilder());
        return bldr.toString();
    }
}
