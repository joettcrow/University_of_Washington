package com.scg.util;

import com.scg.domain.Consultant;
import com.scg.domain.TimeCard;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class contains four class methods for processing time cards:
 * @author jcrowley
 */
public class TimeCardListUtil {
    /**
     * Given a list of time cards and a consultant,
     * create and return a new list containing only time cards for the given consultant.
     * If there are no time cards for the given consultant,
     * an empty list must be returned.
     * @param timeCards the timecard list to pull from
     * @param consultant the consultant to compare
     * @return a list
     */
    static List<TimeCard>
    getTimeCardsForConsultant(List<TimeCard> timeCards, Consultant consultant){
        return timeCards.stream()
                .filter(timecard -> consultant.equals(timecard.getConsultant()))
                .collect(Collectors.toList());
    }

    /**
     * Given a list of time cards and a range of dates,
     * create and return a new list
     * containing only time cards with start dates that fall between the two dates, inclusive.
     * @param timeCards the list of timecards to pull from
     * @param dateRange the date range to compare against
     * @return a list of timecards that fall in the range
     */
    static List<TimeCard>
    getTimeCardsForDateRange(List<TimeCard> timeCards, DateRange dateRange){
        return timeCards.stream()
                .filter(timecard -> dateRange.isInRange(timecard.getWeekStartingDate()))
                .collect(Collectors.toList());
    }

    /**
     * Given a list of time cards, sort it by consultant name.
     * @param timeCards the timecards to sort
     */
    static void sortByConsultantName( List<TimeCard> timeCards ){
        timeCards.sort(Comparator.comparing(TimeCard::getConsultant));

    }

    /**
     * Given a list of time cards, sort it by start date.
     * @param timeCards the timecards to sort
     */
    static void sortByStartDate( List<TimeCard> timeCards )
    {
        timeCards.sort(Comparator.comparing(TimeCard::getWeekStartingDate));
    }
}
