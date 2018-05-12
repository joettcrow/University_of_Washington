package com.scg.domain;

import com.scg.util.Address;
import com.scg.util.PersonalName;
import com.scg.util.StateCode;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.Assert.*;

/**
 * @author jcrowley
 */

public class InvoiceHeaderTest {
    String businessName = "The Small Consulting Group";
    Address businessAddress = new Address(
            "1616 Index Ct.",
            "Renton",
            StateCode.WA,
            "98058");
    PersonalName name = new PersonalName(
            "first",
            "last",
            "middle");
    ClientAccount client = new ClientAccount(
            "Client Name",
            name,
            businessAddress);
    LocalDate date = LocalDate.of(
            2018,
            05,
            20
    );
    LocalDate invoiceForMonth = LocalDate.of(
            2018,
            Month.APRIL,
            1);

    @Test
    public void toStringTest() {
        InvoiceHeader header = new InvoiceHeader(
                businessName,
                businessAddress,
                client,
                date,
                invoiceForMonth);
        String comparison = "The Small Consulting Group" + System.lineSeparator() +
                "1616 Index Ct." + System.lineSeparator() +
                "Renton, WA 98058" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "Invoice for: " + System.lineSeparator() +
                "Client Name" + System.lineSeparator() +
                "Invoice For Month of: April 2018" + System.lineSeparator() +
                "Invoice Date: May 20, 2018" + System.lineSeparator() +
                System.lineSeparator() +
                "Date        " +
                "Consultant                    " +
                "Skill               " +
                "Hours  " +
                "Charge    " +
                System.lineSeparator() +
                "----------  " +
                "----------------------------  " +
                "------------------  " +
                "-----  " +
                "----------" + System.lineSeparator();
        assertEquals(comparison, header.toString());
    }
}