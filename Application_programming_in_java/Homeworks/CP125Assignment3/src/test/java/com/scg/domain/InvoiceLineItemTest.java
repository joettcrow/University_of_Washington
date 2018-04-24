package com.scg.domain;

import com.scg.util.PersonalName;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * @author jcrowley
 */

public class InvoiceLineItemTest {

    LocalDate date = LocalDate.of(
            2018,
            04,
            20
            );
    PersonalName personalName = new PersonalName(
            "Brubeck",
            "Dave",
            "middle");
    Consultant consultant = new Consultant(personalName);
    Skill skill = Skill.PROJECT_MANAGER;
    int hours = 20;
    InvoiceLineItem lineItem = new InvoiceLineItem(
            date,
            consultant,
            skill,
            hours);

    @Test
    public void getChargeTest() {
        assertEquals(20*250,lineItem.getCharge());
    }

    @Test
    public void getDateTest() {
        assertEquals(date,lineItem.getDate());
    }

    @Test
    public void getConsultantTest() {
        assertEquals(consultant,lineItem.getConsultant());
    }

    @Test
    public void getSkillTest() {
        assertEquals(skill,lineItem.getSkill());
    }

    @Test
    public void getHoursTest() {
        assertEquals(hours,lineItem.getHours());
    }

    @Test
    public void toStringTest() {
        String str = "04/20/2018  " +
                "Brubeck, Dave middle         " +
                "Project Manager      " +
                "20     " +
                "5,000.00  ";
        assertEquals(str,lineItem.toString());
    }
}