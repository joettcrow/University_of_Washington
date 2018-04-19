package com.scg.domain;

import com.scg.util.Address;
import com.scg.util.PersonalName;
import com.scg.util.StateCode;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * @author jcrowley
 */

public class ConsultantTimeTest {
    private final String name = "AccountName";
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
            name,
            contact,
            address
    );
    private Integer hours = 10;
    private Skill skill = Skill.SOFTWARE_TESTER;
    private ConsultantTime consultantTime = new ConsultantTime(
            date,
            account,
            skill,
            hours);

    @Test
    public void getDateTest() {
        assertEquals(date, consultantTime.getDate());
    }

    @Test
    public void setDateTest() {
        LocalDate date2 = LocalDate.of(2018,04,16);
        consultantTime.setDate(date2);
        assertNotEquals(date,consultantTime.getDate());
        assertEquals(date2,consultantTime.getDate());
    }

    @Test
    public void getAccountTest() {
        assertEquals(account,consultantTime.getAccount());
    }

    @Test
    public void setAccountTest() {
        Account account2 = new ClientAccount(
                "name2",
                contact,
                address
        );
        consultantTime.setAccount(account2);
        assertNotEquals(account,consultantTime.getAccount());
        assertEquals(account2,consultantTime.getAccount());
    }

    @Test
    public void getHoursTest() {
        assertEquals(hours,consultantTime.getHours());
    }

    @Test
    public void setHoursTest() {
        Integer hours2 = 99;
        consultantTime.setHours(hours2);
        assertNotEquals(hours,consultantTime.getHours());
        assertEquals(hours2,consultantTime.getHours());
    }

    @Test
    public void getSkillTest() {
        assertEquals(skill,consultantTime.getSkill());
    }

    @Test
    public void setSkillTest() {
        Skill skill2 = Skill.PROJECT_MANAGER;
        consultantTime.setSkill(skill2);
        assertNotEquals(skill,consultantTime.getSkill());
        assertEquals(skill2,consultantTime.getSkill());
    }

    @Test
    public void isBillableTest() {
        assert consultantTime.isBillable();
    }

    @Test
    public void isNotBillableTest() {
        ConsultantTime consultantTime2 = new ConsultantTime(
                date,
                account,
                Skill.UNKNOWN_SKILL,
                hours);
        assert !consultantTime2.isBillable();
    }

    @Test
    public void invalidHourTest(){
        String msg = "Hours Cannot be Zero or Negative";
        Integer negHour = -10;
        try {
            ConsultantTime cons2 = new ConsultantTime(
                    date,
                    account,
                    skill,
                    negHour);
        }
        catch (IllegalArgumentException exc){
            assertEquals(msg,exc.getMessage());
        }
    }

    @Test
    public void equalsSameTest() {
        ConsultantTime consultantTime2 = consultantTime;
        assert consultantTime.equals(consultantTime2);
    }

    @Test
    public void notEqualsDiffTest(){
        ConsultantTime consultantTime2 = new ConsultantTime(
                date,
                account,
                skill,
                55);
        assert !consultantTime.equals(consultantTime2);
    }

    @Test
    public void hashCodeEqualsTest() {
        ConsultantTime consultantTime2 = new ConsultantTime(
                date,
                account,
                skill,
                hours);
        assertEquals(consultantTime.hashCode(),consultantTime2.hashCode());
    }

    @Test
    public void hashCodeNotEqualsTest() {
        ConsultantTime consultantTime2 = new ConsultantTime(
                date,
                account,
                skill,
                55);
        assertNotEquals(consultantTime.hashCode(),consultantTime2.hashCode());
    }
}