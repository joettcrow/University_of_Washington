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
     * Constructor; sets the name, principal contact and address.
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
     * Returns the name of the account.
     * @return The name of the account.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the name of the principal contact.
     * @return the name of the principal contact
     */
    public PersonalName getContact() {
        return contact;
    }

    /**
     * Sets the name of the principal contact.
     * @param contact The new name of the principal contact.
     */
    public void setContact(PersonalName contact) {
        this.contact = contact;
    }

    /**
     * Returns the address of the account.
     * @return the name of address of the account
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Sets the address of the account.
     * @param address The new address of the account
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * Returns true, indicating that the account is billable.
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
