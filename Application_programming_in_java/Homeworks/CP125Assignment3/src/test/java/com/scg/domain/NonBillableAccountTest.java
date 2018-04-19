package com.scg.domain;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author jcrowley
 */

public class NonBillableAccountTest {

    @Test
    public void getNameTest() {
        String str = "Business Development";
        assertEquals(str,NonBillableAccount.BUSINESS_DEVELOPMENT.getName());
    }

    @Test
    public void isBillableTest() {
        assert !NonBillableAccount.SICK_LEAVE.isBillable();
        assert !NonBillableAccount.BUSINESS_DEVELOPMENT.isBillable();
        assert !NonBillableAccount.VACATION.isBillable();
    }

    @Test
    public void nonBillableAccountEnumTest(){
        NonBillableAccount [] expValues = {
                NonBillableAccount.BUSINESS_DEVELOPMENT,
                NonBillableAccount.SICK_LEAVE,
                NonBillableAccount.VACATION
        };
        NonBillableAccount [] actValues = NonBillableAccount.values();
        assertEquals(expValues.length, actValues.length);
        assertArrayEquals(expValues,actValues);
    }
}