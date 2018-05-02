package com.scg.util;


import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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

    @Test
    public void addressEqualTest(){
        Address address2 = new Address(
                streetNum1,
                city1,
                state1,
                postCode1);
        assert address1.equals(address2);
    }

    @Test
    public void addressSameEqualsTest(){
        Address address2 = address1;
        assert address1.equals(address2);
    }

    @Test
    public void addressNotEqualTest(){
        Address address2 = new Address(
                streetNum1,
                city1,
                StateCode.TX,
                postCode1);
        assert !address1.equals(address2);
    }

    @Test
    public void hashCodeEqualsTest(){
        Address address2 = new Address(
                streetNum1,
                city1,
                state1,
                postCode1);
        assertEquals(address1.hashCode(),address2.hashCode());
    }

    @Test
    public void hashCodeNotEqualsTest(){
        Address address2 = new Address(
                streetNum1,
                city1,
                StateCode.TX,
                postCode1);
        assertNotEquals(address1.hashCode(),address2.hashCode());
    }

    @Test
    public void compareAddressTest(){
        Address address2 = new Address(
                streetNum1,
                city1,
                StateCode.AZ,
                postCode1
        );
        assert address1.compareTo(address2) > 0;
    }

    @Test
    public void compareReverseAddressTest(){
        Address address2 = new Address(
                streetNum1,
                city1,
                StateCode.AZ,
                postCode1
        );
        assert address2.compareTo(address1) < 0;
    }

    @Test
    public void compareSameAddressTest(){
        Address address2 = new Address(
                streetNum1,
                city1,
                state1,
                postCode1
        );
        assertEquals(0,address1.compareTo(address2));
    }

}