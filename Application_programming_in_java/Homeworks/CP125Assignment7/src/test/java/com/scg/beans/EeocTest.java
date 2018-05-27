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

public class EeocTest {
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
    public void forcedTerminationCountTest() {
        humanResourceManager.addTerminationListener(terminationListener);
//        You should see a log about resignation
        humanResourceManager.acceptResignation(consultant);

        humanResourceManager.removeTerminationListener(terminationListener);
//      You should no longer see a log about resignation
        humanResourceManager.acceptResignation(consultant);
        assertEquals(1,terminationListener.voluntaryTerminationCount());
        assertEquals(0,terminationListener.forcedTerminationCount());
    }

    @Test
    public void voluntaryTerminationCountTest() {
        humanResourceManager.addTerminationListener(terminationListener);
//        You should see a log about termination
        humanResourceManager.terminate(consultant);
        humanResourceManager.terminate(consultant);


        humanResourceManager.removeTerminationListener(terminationListener);
//      You should no longer see a log about termination
        humanResourceManager.terminate(consultant);
        humanResourceManager.terminate(consultant);
        assertEquals(0,terminationListener.voluntaryTerminationCount());
        assertEquals(2,terminationListener.forcedTerminationCount());
    }
}