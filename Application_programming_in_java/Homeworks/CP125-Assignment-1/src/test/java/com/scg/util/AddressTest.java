package com.scg.util;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author jcrowley
 */

public class AddressTest {
    private final String streetNum1 = "1235 Street Name";
    private String streetNum2;
    private final String city1 = "City Name";
    private String city2;
    private final StateCode state1 = StateCode.WA;
    private StateCode state2;
    private final String postCode1 = "98125";
    private String postCode2;


    Address address1 = new Address(streetNum1,city1,state1,postCode1);

    @Test
    public void createAddressTest() {
        Address address = new Address(streetNum1,city1,state1,postCode1);
        assertEquals(streetNum1,address.getStreetNumber());
        assertEquals(city1,address.getCity());
        assertEquals(state1,address.getState());
        assertEquals(postCode1,address.getPostalCode());
    }

    @Test
    public void tryNullAddressTest(){
        try {
            Address address = new Address(streetNum2,city1,state1,postCode1);
        }
        catch (IllegalArgumentException exc){
            assertEquals("Value cannot be null",exc.getMessage());
        }
    }

    @Test
    public void addressToStringTest(){
        String addressString = "1235 Street Name" +
                System.lineSeparator() +
                "City Name, WA 98125";
        assertEquals(addressString, address1.toString());
    }

}