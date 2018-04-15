package com.scg.util;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author jcrowley
 */

public class StateCodeTest {

    @Test
    public void stateEnumsTest(){
        StateCode [] expValues = {
                StateCode.AL, // Alabama
                StateCode.AK, // Alaska
                StateCode.AZ, // Arizona
                StateCode.AR, // Arkansas
                StateCode.CA, // California
                StateCode.CO, // Colorado
                StateCode.CT, // Connecticut
                StateCode.DE, // Delaware
                StateCode.FL, // Florida
                StateCode.GA, // Georgia
                StateCode.HI, // Hawaii
                StateCode.ID, // Idaho
                StateCode.IL, // Illinois
                StateCode.IN, // Indiana
                StateCode.IA, // Iowa
                StateCode.KS, // Kansas
                StateCode.KY, // Kentucky
                StateCode.LA, // Louisiana
                StateCode.ME, // Maine
                StateCode.MD, // Maryland
                StateCode.MA, // Massachusetts
                StateCode.MI, // Michigan
                StateCode.MN, // Minnesota
                StateCode.MS, // Mississippi
                StateCode.MO, // Missouri
                StateCode.MT, // Montana
                StateCode.NE, // Nebraska
                StateCode.NV, // Nevada
                StateCode.NH, // New Hampshire
                StateCode.NJ, // New Jersey
                StateCode.NM, // New Mexico
                StateCode.NY, // New York
                StateCode.NC, // North Carolina
                StateCode.ND, // North Dakota
                StateCode.OH, // Ohio
                StateCode.OK, // Oklahoma
                StateCode.OR, // Oregon
                StateCode.PA, // Pennsylvania
                StateCode.RI, // Rhode Island
                StateCode.SC, // South Carolina
                StateCode.SD, // South Dakota
                StateCode.TN, // Tennessee
                StateCode.TX, // Texas
                StateCode.UT, // Utah
                StateCode.VT, // Vermont
                StateCode.VA, // Virginia
                StateCode.WA, // Washington
                StateCode.WV, // West Virginia
                StateCode.WI, // Wisconsin
                StateCode.WY // Wyoming
        };

        StateCode [] actValues = StateCode.values();
        assertEquals(expValues.length, actValues.length);
        assertArrayEquals(expValues,actValues);
    }

}