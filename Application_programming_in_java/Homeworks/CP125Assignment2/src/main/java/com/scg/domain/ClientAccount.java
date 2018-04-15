package com.scg.domain;

import com.scg.util.Address;
import com.scg.util.PersonalName;

import java.util.Objects;

/**
 * This class encapsulates the properties of a client account:
 * The name of the account
 * The name of the principal contact at the account
 * The address of the account
 * The account is billable
 * @author jcrowley
 */

public class ClientAccount implements Account {
    private final String name;
    private PersonalName contact;
    private Address address;


    /**
     * Getter for the Client Account Name
     * @return the client account name as a string
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the client account contact
     * @return the client account contact as a personal name
     */
    public PersonalName getContact() {
        return contact;
    }

    /**
     * Setter for the client account contact
     * @param contact as a personal name to set
     */
    public void setContact(PersonalName contact) {
        this.contact = contact;
    }

    /**
     * Getter for the address of the Client Account
     * @return the address as an Address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Setter for the Address of the client account
     * @param address as an Address
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * Constructor for a client account
     * @param name the name of the account as a string
     * @param contact the contact for the account as a PersonalName
     * @param address the address for the account as an Address
     */
    public ClientAccount(String name, PersonalName contact, Address address ){
        this.name = name;
        this.contact = contact;
        this.address = address;
    }

    /**
     * Boolean method denoting if the value is billiable
     * @return true
     */
    public boolean isBillable(){
        return true;
    }

    /**
     * Equals method for the ClientAccounts
     * @param o the other account to check
     * @return the boolean truth value for if they are a match
     */
    @Override
    public boolean equals(Object o) {
        boolean eq = false;
        if (this == o) eq = true;
        if (o == null || getClass() != o.getClass()) eq = false;
        ClientAccount that = (ClientAccount) o;
        if (Objects.equals(name, that.name)) if (Objects.equals(contact, that.contact))
            if (Objects.equals(address, that.address)) eq = true;
        return eq;
    }

    /**
     * The has method for client accounts
     * @return a hash value for this account
     */
    @Override
    public int hashCode() {

        return Objects.hash(name, contact, address);
    }
}
