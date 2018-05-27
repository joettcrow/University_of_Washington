package com.scg.util;

import com.scg.domain.*;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author jcrowley
 */

public class TimeCardListUtilTest {
    PersonalName name = new PersonalName(
            "Last",
            "First",
            "Middle"
    );
    PersonalName name2 = new PersonalName(
            "Last2",
            "First2",
            "Middle2"
    );
    Consultant consultant = new Consultant(name);
    Consultant consultant2 = new Consultant(name2);
    LocalDate weekStartingDate = LocalDate.of(
            2018,
            4,
            15
    );
    LocalDate weekStartingDate2 = LocalDate.of(
            2018,
            3,
            15
    );

    LocalDate dateRange1 = LocalDate.of(
            2018,
            4,
            1
    );
    LocalDate dateRange2 = LocalDate.of(
            2018,
            4,
            30
    );

    private final String accountName = "AccountName";
    private PersonalName contact = new PersonalName(
            "Last",
            "First",
            "Middle"
    );
    private Address address = new Address(
            "1234 StreetNum",
            "City",
            StateCode.WA,
            "1234"
    );
    private LocalDate date = LocalDate.of(2018,04,15);
    private Account account = new ClientAccount(
            accountName,
            contact,
            address
    );
    private Integer billableHours = 10;
    private Integer nonBillableHours = 7;
    private Skill billableSkill = Skill.SOFTWARE_TESTER;
    private Skill nonBillableSkill = Skill.UNKNOWN_SKILL;
    private ConsultantTime consultantTimeBillable = new ConsultantTime(
            date,
            account,
            billableSkill,
            billableHours);
    private ConsultantTime consultantTimeNonBillable = new ConsultantTime(
            date,
            account,
            nonBillableSkill,
            nonBillableHours);

    TimeCard timeCard = new TimeCard(consultant,weekStartingDate);
    TimeCard timeCard2 = new TimeCard(consultant2,weekStartingDate);
    TimeCard timeCard3 = new TimeCard(consultant,weekStartingDate2);


    @Test
    public void getTimeCardsForConsultant() {
        timeCard.addConsultantTime(consultantTimeBillable);
        timeCard.addConsultantTime(consultantTimeNonBillable);
        List<TimeCard> timeCards = Arrays.asList(timeCard,timeCard2);
        List<TimeCard> expTimeCards = Arrays.asList(timeCard);
        List<TimeCard> timeCardForConsultant = TimeCardListUtil
                .getTimeCardsForConsultant(timeCards,consultant);
        assertEquals(expTimeCards,timeCardForConsultant);
    }

    @Test
    public void getTimeCardsForDateRange() {
        DateRange dateRange = new DateRange(dateRange1,dateRange2);
        List<TimeCard> timeCards = Arrays.asList(timeCard,timeCard3);
        List<TimeCard> expTimeCards = Arrays.asList(timeCard);
        List<TimeCard> timeCardForDateRange = TimeCardListUtil
                .getTimeCardsForDateRange(timeCards,dateRange);
        assertEquals(expTimeCards,timeCardForDateRange);
    }

    @Test
    public void sortByConsultantName() {
        List<TimeCard> timeCards = Arrays.asList(timeCard2,timeCard);
        List<TimeCard> expTimeCards = Arrays.asList(timeCard,timeCard2);
        TimeCardListUtil.sortByConsultantName(timeCards);
        assertEquals(expTimeCards,timeCards);
    }

    @Test
    public void sortByStartDate() {
        List<TimeCard> timeCards = Arrays.asList(timeCard,timeCard3);
        List<TimeCard> expTimeCards = Arrays.asList(timeCard3,timeCard);
        TimeCardListUtil.sortByStartDate(timeCards);
        assertEquals(expTimeCards,timeCards);
    }
}