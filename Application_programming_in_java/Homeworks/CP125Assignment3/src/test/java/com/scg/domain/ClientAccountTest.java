package com.scg.domain;

import com.scg.util.Address;
import com.scg.util.PersonalName;
import com.scg.util.StateCode;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author jcrowley
 */

public class ClientAccountTest {
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
    ClientAccount acct1 = new ClientAccount(name,contact,address);

    @Test
    public void getNameTest() {
        assertEquals(name,acct1.getName());
    }

    @Test
    public void getContactTest() {
        assertEquals(contact,acct1.getContact());
    }

    @Test
    public void setContactTest() {
        PersonalName contact2 = new PersonalName(
                "Last2",
                "First2",
                "Middle2"
        );
        ClientAccount acct2 = new ClientAccount(name,contact,address);
        acct2.setContact(contact2);
        assertEquals(contact2,acct2.getContact());
    }

    @Test
    public void getAddressTest() {
        assertEquals(address,acct1.getAddress());
    }

    @Test
    public void setAddressTest() {
        Address address2 = new Address(
                "1234 DiffStreetNum",
                "Diff City",
                StateCode.TX,
                "Diff 1234"
        );
        ClientAccount acct2 = new ClientAccount(name,contact,address);
        acct2.setAddress(address2);
        assertEquals(address2,acct2.getAddress());
    }

    @Test
    public void isBillableTest() {
        assert acct1.isBillable();
    }

    @Test
    public void equalsSameTest() {
        ClientAccount acct2 = acct1;
        assert acct1.equals(acct2);
    }

    @Test
    public void equalsDiffTest(){
        PersonalName contact2 = new PersonalName(
                "Last2",
                "First2",
                "Middle2"
        );
        Address address2 = new Address(
                "1234 DiffStreetNum",
                "Diff City",
                StateCode.TX,
                "Diff 1234"
        );
        ClientAccount acct2 = new ClientAccount(
                "name2",
                contact2,
                address2);
        assert !acct1.equals(acct2);
    }

    @Test
    public void hashCodeEqualTest() {
        ClientAccount acct2 = new ClientAccount(
                name,
                contact,
                address
        );
        assertEquals(acct1.hashCode(), acct2.hashCode());
    }

    @Test
    public void hashCodeUnequalTest(){
        PersonalName contact2 = new PersonalName(
                "Last2",
                "First2",
                "Middle2"
        );
        Address address2 = new Address(
                "1234 DiffStreetNum",
                "Diff City",
                StateCode.TX,
                "Diff 1234"
        );
        ClientAccount acct2 = new ClientAccount(
                "name2",
                contact2,
                address2);
        assertNotEquals(acct1.hashCode(),acct2.hashCode());
    }
}