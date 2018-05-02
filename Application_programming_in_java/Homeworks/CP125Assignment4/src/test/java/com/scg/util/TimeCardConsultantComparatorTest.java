package com.scg.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.scg.domain.*;
import org.junit.Test;

import java.time.LocalDate;

/**
 * JUnit test for the TimeCardConsultantComparator class.
 *
 * @author jack
 */
public final class TimeCardConsultantComparatorTest {
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

    TimeCard timeCard1 = new TimeCard(consultant,weekStartingDate);
    TimeCard timeCard2 = new TimeCard(consultant2,weekStartingDate);

    TimeCardConsultantComparator    comparator  =
            new TimeCardConsultantComparator();

    /**
     * Tests the compare method.
     */
    @Test
    public void compareSameTest()
    {

        timeCard1.addConsultantTime(consultantTimeBillable);
        timeCard1.addConsultantTime(consultantTimeNonBillable);
        TimeCard timeCard3 = new TimeCard(consultant,weekStartingDate);
        timeCard3.addConsultantTime(consultantTimeBillable);
        timeCard3.addConsultantTime(consultantTimeNonBillable);

        assertEquals( 0, comparator.compare( timeCard1, timeCard1 ) );
        assertEquals( 0, comparator.compare( timeCard1, timeCard3 ) );
        assertEquals( 0, comparator.compare( timeCard3, timeCard1 ) );
    }

    @Test
    public void compareConsultantNameHigherTest(){
        PersonalName earlierContact = new PersonalName(
                "Anterior",
                "First",
                "Middle"
        );
        Consultant earlierConsultant = new Consultant(earlierContact);
        TimeCard timeCard3 = new TimeCard(earlierConsultant,weekStartingDate);
        assert comparator.compare(timeCard1,timeCard3) > 0;
        assert comparator.compare(timeCard3,timeCard1) < 0;
    }

    @Test
    public void compareDateHigherTest(){
        LocalDate earlierWeekStartingDate = LocalDate.of(
                2018,
                5,
                15
        );

        TimeCard timeCard3 = new TimeCard(consultant,earlierWeekStartingDate);
        assert comparator.compare(timeCard1,timeCard3) < 0;
        assert comparator.compare(timeCard3,timeCard1) > 0;
    }

    @Test
    public void compareHigherBillableTest(){
        TimeCard timeCard3 = new TimeCard(consultant,weekStartingDate);
        timeCard3.addConsultantTime(consultantTimeBillable);
        timeCard1.addConsultantTime(consultantTimeNonBillable);
        assert comparator.compare(timeCard1,timeCard3) < 0;
        assert comparator.compare(timeCard3,timeCard1) > 0;
    }

    @Test
    public void compareHigherNonBillableTest(){
        TimeCard timeCard3 = new TimeCard(consultant,weekStartingDate);
        timeCard1.addConsultantTime(consultantTimeNonBillable);
        assert comparator.compare(timeCard1,timeCard3) > 0;
        assert comparator.compare(timeCard3,timeCard1) < 0;
    }

    @Test
    public void nullCompareTest()
    {
        TimeCardConsultantComparator comparator  =
                new TimeCardConsultantComparator();

        try
        {
            comparator.compare( timeCard1, null );
            fail( "Expected exception non thrown" );
        }
        catch ( NullPointerException exc )
        {
            assertEquals("Card cannot be null",exc.getMessage());
        }

        try
        {
            comparator.compare( null, timeCard1 );
            fail( "Expected exception non thrown" );
        }
        catch ( NullPointerException exc )
        {
            assertEquals("Card cannot be null",exc.getMessage());
        }
    }
}
