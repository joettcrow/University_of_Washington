package com.scg.domain;

import com.scg.util.Address;
import com.scg.util.StateCode;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
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
            LocalDate.of(invoiceYear,invoiceMonth,1)
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
        private List<InvoiceLineItem> tmp_lists = new ArrayList<>();
        for (item: timeCard.getBillableHoursForClient(clientAccount.getName())){

        }
    }


}

