package com.scg.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * This class is useful for determining whether a date falls in a particular range.
 * @author jcrowley
 */
public class DateRange {
    private final LocalDate startDate;
    private final LocalDate endDate;

    /**
     * Constructor. Initializes the start and end dates from the given arguments.
     * @param startDate Start date for the check
     * @param endDate End date for the check
     */
    public DateRange(LocalDate startDate, LocalDate endDate ){
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Constructor.
     * Initializes the start date to the first day of the given month and year;
     * initialize the end date to the last day of the given month and year.
     * @param month The month to find range day for
     * @param year The year to find range date for
     */
    public DateRange(Month month, int year ){
        this.startDate = LocalDate.of(year,month,1);
        this.endDate = LocalDate.of(
                year,
                month,
                YearMonth.of(year,month).lengthOfMonth());
    }

    /**
     * Constructor.
     * Initializes the start and end dates by parsing the given strings.
     * The strings will be formatted as YYYY-MM-DD, for example 2016-03-01.
     * @param start Start date as a string
     * @param end End date as a string
     */
    public DateRange( String start, String end ){
        this.startDate = LocalDate.parse(start);
        this.endDate = LocalDate.parse(end);
    }

    /**
     * Getter for the start date.
     * @return the start date
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Getter for the end date.
     * @return the end date.
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Returns true if the given date falls between this object's start and end dates, inclusive.
     * @param date the date to check against the range
     * @return the boolean truth value if the value is in the range
     */
    public boolean isInRange( LocalDate date ){
        return (!date.isBefore(startDate) && !date.isAfter(endDate));
    }
}
