package com.scg.domain;

import com.scg.util.Address;
import com.scg.util.PersonalName;
import com.scg.util.StateCode;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.*;

/**
 * @author jcrowley
 */
public class InvoiceTest {
    Month month = Month.APRIL;
    PersonalName personalName1 = new PersonalName(
            "Last1",
            "First1",
            "Middle1");
    PersonalName personalName2 = new PersonalName(
            "Last2",
            "First2",
            "Middle2");
    Address address = new Address(
            "1234 StreetNum",
            "City",
            StateCode.WA,
            "12345");
    ClientAccount clientAccount = new ClientAccount(
            "ClientName",
            personalName1,
            address);
    Invoice invoice = new Invoice(
            clientAccount,
            month,
            2018);
    LocalDate date1 = LocalDate.of(
            2018,
            4,
            1);
    LocalDate date2 = LocalDate.of(
            2018,
            4,
            1);
    LocalDate dateNow = LocalDate.now();
//    DateTimeFormatter dateMonthFormatter = DateTimeFormatter.ofPattern("MMMM yyyy");
    DateTimeFormatter invoiceDateFormatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
//    String dmy = dateMonthFormatter.format(dateNow);
    String dmdy = invoiceDateFormatter.format(dateNow);

    Consultant consultant1 = new Consultant(personalName1);
    Consultant consultant2 = new Consultant(personalName2);
    Skill skill1 = Skill.PROJECT_MANAGER;
    Skill skill2 = Skill.SOFTWARE_TESTER;
    int hours1 = 7;
    int hours2 = 3;

    InvoiceLineItem lineItem1 = new InvoiceLineItem(
            date1,
            consultant1,
            skill1,
            hours1);
    InvoiceLineItem lineItem2 = new InvoiceLineItem(
            date2,
            consultant1,
            skill1,
            hours1);
    InvoiceLineItem lineItem3 = new InvoiceLineItem(
            date1,
            consultant2,
            skill1,
            hours1);
    InvoiceLineItem lineItem4 = new InvoiceLineItem(
            date2,
            consultant2,
            skill1,
            hours1);
    InvoiceLineItem lineItem5 = new InvoiceLineItem(
            date1,
            consultant1,
            skill2,
            hours2);
    InvoiceLineItem lineItem6 = new InvoiceLineItem(
            date2,
            consultant1,
            skill2,
            hours2);

    private ConsultantTime consultantTimeBillable = new ConsultantTime(
            date1,
            clientAccount,
            skill1,
            hours1);

    TimeCard timeCard = new TimeCard(consultant1,date1);

    @Test
    public void toStringTest() {
        String exp = "client=ClientName,invoiceYear=2018,invoiceMonth=APRIL";
        assertEquals(exp,invoice.toString());
    }

    @Test
    public void getClientAccountTest() {
        assertEquals(clientAccount,invoice.getClientAccount());
    }

    @Test
    public void getInvoiceMonthTest() {
        assertEquals(month,invoice.getInvoiceMonth());
    }

    @Test
    public void getInvoiceYearTest() {
        assertEquals(2018,invoice.getInvoiceYear());
    }

    @Test
    public void getStartDateTest() {
        assertEquals(date1,invoice.getStartDate());
    }

    @Test
    public void toReportStringTest() {
        String expected = "The Small Consulting Group" +
                System.lineSeparator() +
                "1616 Index Ct." +
                System.lineSeparator() +
                "Renton, WA 98058" +
                System.lineSeparator() +
                "" +
                System.lineSeparator() +
                "Invoice for: "
                + System.lineSeparator() +
                "ClientName"
                + System.lineSeparator() +
                "Invoice For Month of: April 2018" +
                System.lineSeparator() +
                "Invoice Date: " + dmdy +
                System.lineSeparator() +
                "" + System.lineSeparator() +
                "Date        " +
                "Consultant                    " +
                "Skill               " +
                "Hours  " +
                "Charge    "
                + System.lineSeparator() +
                "----------  " +
                "----------------------------  " +
                "------------------  " +
                "-----  " +
                "----------" +
                System.lineSeparator() +
                "04/01/2018  " +
                "Last1, First1 Middle1         " +
                "Project Manager         " +
                "7    " +
                "1,750.00" +
                System.lineSeparator() +
                "04/01/2018  " +
                "Last1, First1 Middle1         " +
                "Project Manager         " +
                "7    " +
                "1,750.00" +
                System.lineSeparator() +
                "04/01/2018  " +
                "Last1, First1 Middle1         " +
                "Project Manager         " +
                "7    " +
                "1,750.00" +
                System.lineSeparator() +
                "04/01/2018  " +
                "Last2, First2 Middle2         " +
                "Project Manager         " +
                "7    " +
                "1,750.00" +
                System.lineSeparator() +
                "04/01/2018  " +
                "Last2, First2 Middle2         " +
                "Project Manager         " +
                "7    " +
                "1,750.00" +
                System.lineSeparator() +
                "" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "The Small Consulting Group" +
                "                                            " +
                "Page:   1" +
                System.lineSeparator() +
                "=========================" +
                "======================================================" +
                System.lineSeparator() +
                "" + System.lineSeparator() +
                "The Small Consulting Group" + System.lineSeparator() +
                "1616 Index Ct." + System.lineSeparator() +
                "Renton, WA 98058" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "Invoice for: " + System.lineSeparator() +
                "ClientName" + System.lineSeparator() +
                "Invoice For Month of: April 2018" + System.lineSeparator() +
                "Invoice Date: " + dmdy + System.lineSeparator() +
                "" + System.lineSeparator() +
                "Date        " +
                "Consultant                    " +
                "Skill               " +
                "Hours  " +
                "Charge    " + System.lineSeparator() +
                "----------  " +
                "----------------------------  " +
                "------------------  " +
                "-----  " +
                "----------" + System.lineSeparator() +
                "04/01/2018  " +
                "Last1, First1 Middle1         " +
                "Software Tester         " +
                "3      " +
                "300.00" + System.lineSeparator() +
                "04/01/2018  " +
                "Last1, First1 Middle1         " +
                "Software Tester         " +
                "3      " +
                "300.00" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "Total:                                                           " +
                "41    " +
                "9,350.00" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "The Small Consulting Group                                            " +
                "Page:   2" + System.lineSeparator() +
                "===========================================" +
                "====================================" + System.lineSeparator();
        timeCard.addConsultantTime(consultantTimeBillable);
        invoice.extractLineItems(timeCard);
        invoice.addLineItems(lineItem1);
        invoice.addLineItems(lineItem2);
        invoice.addLineItems(lineItem3);
        invoice.addLineItems(lineItem4);
        invoice.addLineItems(lineItem5);
        invoice.addLineItems(lineItem6);
        assertEquals(expected, invoice.toReportString());
    }
}