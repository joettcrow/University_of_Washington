package com.scg.util;

import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.Assert.*;

/**
 * @author jcrowley
 */

public class DateRangeTest {
    private LocalDate date1 = LocalDate.of(2018,04,1);
    private LocalDate date2 = LocalDate.of(2018,04,30);
    private LocalDate date3 = LocalDate.of(2018,04,15);

    private Month month1 = Month.APRIL;
    private int year = 2018;
    private String dateAsString1 = "2018-04-01";
    private String dateAsString2 = "2018-04-30";

    @Test
    public void constructorWithLocalDates() {
        DateRange dateRange = new DateRange(date1,date2);
        assertEquals(date1,dateRange.getStartDate());
        assertEquals(date2,dateRange.getEndDate());
    }

    @Test
    public void constructorWithMonthYear() {
        DateRange dateRange = new DateRange(month1,year);
        assertEquals(date1,dateRange.getStartDate());
        assertEquals(date2,dateRange.getEndDate());
    }

    @Test
    public void constructorWithStrings(){
        DateRange dateRange = new DateRange(dateAsString1,dateAsString2);
        assertEquals(date1,dateRange.getStartDate());
        assertEquals(date2,dateRange.getEndDate());
    }

    @Test
    public void isInRange() {
        DateRange dateRange = new DateRange(dateAsString1,dateAsString2);
        assert dateRange.isInRange(date3);
    }
}