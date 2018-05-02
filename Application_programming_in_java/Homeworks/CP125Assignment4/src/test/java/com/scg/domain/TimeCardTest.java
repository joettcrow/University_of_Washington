package com.scg.domain;

import com.scg.util.Address;
import com.scg.util.PersonalName;
import com.scg.util.StateCode;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author jcrowley
 */

public class TimeCardTest {
    PersonalName name = new PersonalName(
            "Last",
            "First",
            "Middle"
    );
    Consultant consultant = new Consultant(name);
    LocalDate weekStartingDate = LocalDate.of(
            2018,
            4,
            15
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

    @Test
    public void getConsultantTest() {
        assertEquals(consultant,timeCard.getConsultant());
    }

    @Test
    public void getWeekStartingDateTest() {
        assertEquals(weekStartingDate,timeCard.getWeekStartingDate());
    }

    @Test
    public void getBillableHoursForClientTest() {
        timeCard.addConsultantTime(consultantTimeBillable);
        timeCard.addConsultantTime(consultantTimeNonBillable);
        List<ConsultantTime> givenClient = new ArrayList<>();
        givenClient.add(consultantTimeBillable);
        givenClient.add(consultantTimeNonBillable);

        assertEquals(givenClient,timeCard.getBillableHoursForClient("AccountName"));

    }

    @Test
    public void getTotalBillableHoursTest() {
        timeCard.addConsultantTime(consultantTimeBillable);
        timeCard.addConsultantTime(consultantTimeNonBillable);
        assertEquals(10, timeCard.getTotalBillableHours());
    }

    @Test
    public void getTotalNonBillableHoursTest() {
        timeCard.addConsultantTime(consultantTimeBillable);
        timeCard.addConsultantTime(consultantTimeNonBillable);
        assertEquals(7, timeCard.getTotalNonBillableHours());
    }

    @Test
    public void getTotalHoursTest() {
        timeCard.addConsultantTime(consultantTimeBillable);
        timeCard.addConsultantTime(consultantTimeNonBillable);
        assertEquals(17, timeCard.getTotalHours());
    }

    @Test
    public void toReportStringTest() {
        timeCard.addConsultantTime(consultantTimeBillable);
        timeCard.addConsultantTime(consultantTimeNonBillable);
        String reportString = "===============================" +
                "======================================== " + System.lineSeparator() +
                "Consultant: Last, First Middle" +
                "             Week Starting: Apr 15, 2018" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "Billable Time:" + System.lineSeparator() +
                "Account                         " +
                "Date        Hours  Skill               " + System.lineSeparator() +
                "------------------------------  " +
                "----------  -----  --------------------" + System.lineSeparator() +
                "AccountName                     " +
                "2018/04/15     10  Software Tester     " + System.lineSeparator() +
                "" + System.lineSeparator() +
                "Non-Billable Time:" + System.lineSeparator() +
                "Account                         " +
                "Date        Hours" + System.lineSeparator() +
                "------------------------------  " +
                "----------  -----  " + System.lineSeparator() +
                "AccountName                     " +
                "2018/04/15      7" + System.lineSeparator() +
                "" + System.lineSeparator() +
                "Summary:" + System.lineSeparator() +
                "Total Billable:                                " +
                "10" + System.lineSeparator() +
                "Total Non-Billable:                             " +
                "7" + System.lineSeparator() +
                "Total Hours:                                   " +
                "17" + System.lineSeparator() +
                "==============================================" +
                "========================= " + System.lineSeparator();
        assertEquals(reportString,timeCard.toReportString());
    }

    @Test
    public void toStringTest() {
        String timeCardString = name.toString() + " 15/04/2018";
        assertEquals(timeCardString,timeCard.toString());
    }

    @Test
    public void compareTimeCardTest(){
        LocalDate weekStartingDate2 = LocalDate.of(
                2018,
                3,
                15
        );
        TimeCard timeCard2 = new TimeCard(consultant,weekStartingDate2);
        assert timeCard2.compareTo(timeCard) < 0;
    }

    @Test
    public void compareReverseTimeCardTest(){
        LocalDate weekStartingDate2 = LocalDate.of(
                2018,
                3,
                15
        );
        TimeCard timeCard2 = new TimeCard(consultant,weekStartingDate2);
        assert timeCard.compareTo(timeCard2) > 0;
    }

    @Test
    public void compareSameTimeCardTest(){
        TimeCard timeCard2 = new TimeCard(consultant,weekStartingDate);
        assertEquals(0,timeCard2.compareTo(timeCard));
    }

    @Test
    public void compareNullTimeCardTest(){
        TimeCard timeCard2 = null;
        try {
            timeCard.compareTo(timeCard2);
        }
        catch (NullPointerException e){
            assertEquals("Card cannot be null", e.getMessage());
        }
    }
}