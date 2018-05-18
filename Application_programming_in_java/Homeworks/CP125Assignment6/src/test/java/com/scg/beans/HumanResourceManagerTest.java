package com.scg.beans;

import com.scg.domain.Consultant;
import com.scg.util.PersonalName;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.Assert.*;

/**
 * @author jcrowley
 */

public class HumanResourceManagerTest {
    HumanResourceManager humanResourceManager = new HumanResourceManager();
    Eeoc terminationListener = new Eeoc();
    BenefitManager benefitListener = new BenefitManager();
    PersonalName name = new PersonalName(
            "Last",
            "First",
            "Middle");
    Consultant consultant = new Consultant(name);
    LocalDate date = LocalDate.of(2018,Month.MAY,1);
    double payRate = 50000.00;
    double payRate2 = 50500.00;
    double payRate3 = 90000.00;
    int sickLeaveHours = 20;
    int sickLeaveHours2 = 10;
    int vacationHours = 30;
    int vacationHours2 = 90;
    StaffConsultant staffConsultant = new StaffConsultant(
            name,
            payRate,
            sickLeaveHours,
            vacationHours);

    @Test
    public void acceptResignationTest() {
        humanResourceManager.addTerminationListener(terminationListener);
//        You should see a log about resignation
        humanResourceManager.acceptResignation(consultant);

        humanResourceManager.removeTerminationListener(terminationListener);
//      You should no longer see a log about resignation
        humanResourceManager.acceptResignation(consultant);
    }

    @Test
    public void terminateTest() {
        humanResourceManager.addTerminationListener(terminationListener);
//        You should see a log about termination
        humanResourceManager.terminate(consultant);

        humanResourceManager.removeTerminationListener(terminationListener);
//      You should no longer see a log about termination
        humanResourceManager.terminate(consultant);
    }

    @Test
    public void adjustPayRateTest() {
//        Should see a log about adjusting property
        staffConsultant.addPropertyChangeListener(benefitListener);
        humanResourceManager.adjustPayRate(staffConsultant,payRate2);
        assertEquals(payRate2,staffConsultant.getPayRate(),0.00);
    }

    @Test
    public void adjustSickLeaveHoursTest() {
        humanResourceManager.adjustSickLeaveHours(staffConsultant,sickLeaveHours2);
        assertEquals(sickLeaveHours2,staffConsultant.getSickLeaveHours());
    }

    @Test
    public void adjustVacationHoursTest() {
        humanResourceManager.adjustVacationHours(staffConsultant,vacationHours2);
        assertEquals(vacationHours2,staffConsultant.getVacationHours());
    }

    @Test
    public void enrollCancelMedicalDentalTest() {
        humanResourceManager.addBenefitListener(benefitListener);
//        You should see a log about enrolling abd cancelling
//        Will Look like
//          May 17, 2018 9:29:06 PM com.scg.beans.BenefitManager enrollDental
//          INFO: Dental, enrolled: Last, First Middle; effective date: 2018-05-17
//          May 17, 2018 9:29:06 PM com.scg.beans.BenefitManager cancelDental
//          INFO: Dental, cancelled: Last, First Middle; effective date: 2018-05-17
//          May 17, 2018 9:29:06 PM com.scg.beans.BenefitManager enrollMedical
//          INFO: Medical, enrolled : Last, First Middle; effective date: 2018-05-17
//          May 17, 2018 9:29:06 PM com.scg.beans.BenefitManager cancelMedical
//          INFO: Medical, cancelled: Last, First Middle; effective date: 2018-05-17
        humanResourceManager.enrollDental(staffConsultant);
        humanResourceManager.cancelDental(staffConsultant);
        humanResourceManager.enrollMedical(staffConsultant);
        humanResourceManager.cancelMedical(staffConsultant);

        humanResourceManager.removeBenefitListener(benefitListener);
//        You should see no more logs
        humanResourceManager.enrollDental(staffConsultant);
        humanResourceManager.cancelDental(staffConsultant);
        humanResourceManager.enrollMedical(staffConsultant);
        humanResourceManager.cancelMedical(staffConsultant);
    }

    @Test
    public void catchVetoedPayRateTest() {
        humanResourceManager.addBenefitListener(benefitListener);
        CompensationManager comMgr = new CompensationManager();
        staffConsultant.addVetoableChangeListener(comMgr);
        humanResourceManager.adjustPayRate(staffConsultant,payRate3);
        assertEquals(payRate,staffConsultant.getPayRate(),0.00);
    }
}