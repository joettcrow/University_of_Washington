package com.scg.domain;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author jcrowley
 */

public class InvoiceFooterTest {

//    @Test
//    public void incrementPageNumberTest() {
//
//    }

    @Test
    public void toStringTest() {
        InvoiceFooter footer = new InvoiceFooter("The Small Consulting Group");
        footer.incrementPageNumber();
        String footerExpected = "The Small Consulting Group" +
                "                                            " +
                "Page:   " +
                "2" +
                System.lineSeparator() +
                "========================================" +
                "=======================================" +
                System.lineSeparator();
        assertEquals(footerExpected, footer.toString());
    }
}