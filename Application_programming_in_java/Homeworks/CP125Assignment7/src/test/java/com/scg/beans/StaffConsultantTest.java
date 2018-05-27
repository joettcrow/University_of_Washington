package com.scg.beans;

import com.scg.util.PersonalName;
import org.junit.Test;

import java.beans.PropertyVetoException;

import static org.junit.Assert.*;

/**
 * @author jcrowley
 */

public class StaffConsultantTest {
    PersonalName name = new PersonalName("Last", "First", "Middle");
    int payRate = 50000;
    int payRate2 = 50500;
    int payRate3 = 90000;
    int sickLeaveHours = 20;
    int sickLeaveHours2 = 10;
    int vacationHours = 30;
    int vacationHours2 = 90;

    StaffConsultant staffConsultant = new StaffConsultant(
            name,
            payRate,
            sickLeaveHours,
            vacationHours);

    CompensationManager comMgr = new CompensationManager();


    @Test
    public void getPayRateTest() {
        assertEquals(payRate,staffConsultant.getPayRate(), 0.00);
    }

    @Test
    public void setPayRateSmallTest() {
        try {
            staffConsultant.setPayRate(payRate2);
        } catch (PropertyVetoException e) {
        }
        assertEquals(payRate2, staffConsultant.getPayRate(),0.00);
    }

    @Test
    public void setPayRateLargeNoListenerTest() {
//        Won't fail because we didn't add any listeners
        try {
            staffConsultant.setPayRate(payRate3);
        } catch (PropertyVetoException e) {
            System.out.println("Failed unexpectedly");
        }
        assertEquals(payRate3, staffConsultant.getPayRate(),0.00);
    }

    @Test
    public void setPayRateLargeYesListenerTest() {
        staffConsultant.addVetoableChangeListener(comMgr);
        staffConsultant.addPropertyChangeListener(comMgr);
        try {
            staffConsultant.setPayRate(payRate3);
        } catch (PropertyVetoException e) {
            System.out.println("Failed as expected");
        }
        assertEquals(payRate, staffConsultant.getPayRate(),0.00);
    }

    @Test
    public void removeAddedListenersTest(){
        staffConsultant.addVetoableChangeListener(comMgr);
        staffConsultant.addPropertyChangeListener(comMgr);
        try {
            staffConsultant.setPayRate(payRate3);
        } catch (PropertyVetoException e) {
            System.out.println("Failed as expected");
        }
        assertEquals(payRate, staffConsultant.getPayRate(),0.00);

        staffConsultant.removeVetoableChangeListener(comMgr);
        staffConsultant.removeProperyChangeListener(comMgr);
        try {
            staffConsultant.setPayRate(payRate3);
        } catch (PropertyVetoException e) {
            System.out.println("Failed unexpectedly");
        }
        assertEquals(payRate3, staffConsultant.getPayRate(),0.00);
    }

    @Test
    public void getSickLeaveHoursTest() {
        assertEquals(sickLeaveHours,staffConsultant.getSickLeaveHours());
    }

    @Test
    public void setSickLeaveHoursTest() {
        staffConsultant.setSickLeaveHours(sickLeaveHours2);
        assertEquals(sickLeaveHours2,staffConsultant.getSickLeaveHours());
    }

    @Test
    public void getVacationHoursTest() {
        assertEquals(vacationHours,staffConsultant.getVacationHours());
    }

    @Test
    public void setVacationHoursTest() {
        staffConsultant.setVacationHours(vacationHours2);
        assertEquals(vacationHours2,staffConsultant.getVacationHours());
    }
}