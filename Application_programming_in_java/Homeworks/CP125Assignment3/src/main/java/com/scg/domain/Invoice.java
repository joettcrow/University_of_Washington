package com.scg.domain;

import com.scg.util.Address;
import com.scg.util.StateCode;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * This class encapsulates the properties of an invoice:
 * Client account to be invoiced
 * Invoice month
 * Invoice year
 * Invoice line items
 * @author jcrowley
 */
public class Invoice {
    private final ClientAccount clientAccount;
    private final java.time.Month invoiceMonth;
    private final int invoiceYear;
    private Address scgAddress = scgAddressCreator();
    private String scgName = scgNameCreator();
    private List<InvoiceLineItem> lineItems = new ArrayList<>();


    /**
     * Constructor; sets the client account name, and the month and year being invoiced.
     * It immediately creates an invoice header,
     * and an invoice footer.
     * @param client The Client Account as a Client Account
     * @param month The month being invoiced as a Month
     * @param year the year being invoiced as an int
     */
    public Invoice(ClientAccount client, java.time.Month month, int year){
        this.clientAccount = client;
        this.invoiceMonth = month;
        this.invoiceYear = year;

        InvoiceHeader header = new InvoiceHeader(
            scgName,
            scgAddress,
            clientAccount,
            LocalDate.now(),
            getStartDate()
        );
        InvoiceFooter footer = new InvoiceFooter(scgName);
    }

    private Address scgAddressCreator(){

        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String scgPropertiesPath = rootPath + "invoice.properties";
        Properties scgProperties = new Properties();
        try {
            scgProperties.load(new FileInputStream(scgPropertiesPath));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        String scgStreet = scgProperties.getProperty("business.street");
        String scgCity = scgProperties.getProperty("business.city");
        String scgState = scgProperties.getProperty("business.state");
        String scgZip = scgProperties.getProperty("business.zip");

        Address scgAddress = new Address(
                scgStreet,
                scgCity,
                StateCode.valueOf(scgState),
                scgZip);

        return scgAddress;
    }

    private String scgNameCreator(){
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String scgPropertiesPath = rootPath + "invoice.properties";
        Properties scgProperties = new Properties();
        try {
            scgProperties.load(new FileInputStream(scgPropertiesPath));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        String scgName = scgProperties.getProperty("business.name");

        return scgName;

    }

    public void addLineItems(InvoiceLineItem item) {
        lineItems.add(item);
    }

    public void extractLineItems( TimeCard timeCard ){
        List<ConsultantTime> tmp_lists = new ArrayList<>();
        tmp_lists = timeCard.getBillableHoursForClient(clientAccount.getName());
        for (ConsultantTime item: tmp_lists){
            InvoiceLineItem lineItem = new InvoiceLineItem(
                    item.getDate(),
                    timeCard.getConsultant(),
                    item.getSkill(),
                    item.getHours());
            addLineItems(lineItem);
        }
    }

    /**
     * Returns a string representation of this Invoice. Does not include line items.
     * @return A string representation of this Invoice.
     */
    @Override
    public String toString() {
        String str = "client=" +
                scgName +
                ",invoiceYear=" +
                invoiceYear +
                ",invoiceMonth=" +
                invoiceMonth;
        return str;
    }

    /**
     * Returns the client account associated with this Invoice.
     * @return The client account associated with this Invoice.
     */
    public ClientAccount getClientAccount() {
        return clientAccount;
    }

    /**
     * Returns the month associated with this Invoice.
     * @return The month associated with this Invoice.
     */
    public Month getInvoiceMonth() {
        return invoiceMonth;
    }

    /**
     * Returns the year associated with this Invoice.
     * @return The year associated with this Invoice.
     */
    public int getInvoiceYear() {
        return invoiceYear;
    }

    /**
     * Returns a date that encapsulates the invoice month and year; the day is explicitly set to 1.
     * @return A date that encapsulates the invoice month and year.
     */
    public LocalDate getStartDate(){
        LocalDate date = LocalDate.of(invoiceYear,invoiceMonth,1);
        return date;
    }
}

