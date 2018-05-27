package com.scg.domain;

import com.scg.util.Address;
import com.scg.util.PersonalName;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;

/**
 * This class encapsulates the properties of a client account:
 * The name of the account
 * The name of the principal contact at the account
 * The address of the account
 * The account is billable
 * @author jcrowley
 */
public class ClientAccount implements Account, Comparable<ClientAccount>, Serializable {
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
        if (o==null) eq = false;
        else if (getClass() != o.getClass()) eq = false;
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


    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     * <p>The implementor must ensure <tt>sgn(x.compareTo(y)) ==
     * -sgn(y.compareTo(x))</tt> for all <tt>x</tt> and <tt>y</tt>.  (This
     * implies that <tt>x.compareTo(y)</tt> must throw an exception iff
     * <tt>y.compareTo(x)</tt> throws an exception.)
     * <p>The implementor must also ensure that the relation is transitive:
     * <tt>(x.compareTo(y)&gt;0 &amp;&amp; y.compareTo(z)&gt;0)</tt> implies
     * <tt>x.compareTo(z)&gt;0</tt>.
     * <p>Finally, the implementor must ensure that <tt>x.compareTo(y)==0</tt>
     * implies that <tt>sgn(x.compareTo(z)) == sgn(y.compareTo(z))</tt>, for
     * all <tt>z</tt>.
     * <p>It is strongly recommended, but <i>not</i> strictly required that
     * <tt>(x.compareTo(y)==0) == (x.equals(y))</tt>.  Generally speaking, any
     * class that implements the <tt>Comparable</tt> interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     * <p>In the foregoing description, the notation
     * <tt>sgn(</tt><i>expression</i><tt>)</tt> designates the mathematical
     * <i>signum</i> function, which is defined to return one of <tt>-1</tt>,
     * <tt>0</tt>, or <tt>1</tt> according to whether the value of
     * <i>expression</i> is negative, zero or positive.
     *
     * @param account the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(ClientAccount account) {
        int rcode = Comparator.comparing(ClientAccount::getName)
                .thenComparing(ClientAccount::getContact)
                .thenComparing(ClientAccount::getAddress)
                .compare(this,account);
        return rcode;
    }
}
