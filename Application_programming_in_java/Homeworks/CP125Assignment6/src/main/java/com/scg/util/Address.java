package com.scg.util;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;

/**
 * This immutable class encapsulates an address. Encapsulated properties are:
 * Street address
 * City
 * State code
 * Postal code
 * @author jcrowley
 */
public class Address implements Comparable<Address>, Serializable{
    private final String streetNum;
    private final String city;
    private final StateCode state;
    private final String postalCode;

    /**
     * Getter for the streetNum value
     * @return the StreetNum as a String
     */
    public String getStreetNumber() {
        return streetNum;
    }

    /**
     * Getter for the city value
     * @return the City as a string
     */
    public String getCity() {
        return city;
    }

    /**
     * Getter for the state value
     * @return the State as a StateCode
     */
    public StateCode getState() {
        return state;
    }

    /**
     * Getter for the zip code
     * @return the zip code as a String
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Constructor for an address object
     * @param streetNum the Street Address as a String value
     * @param city the city as a String value
     * @param state the state as a State Code value
     * @param postalCode the zip code as a String value
     * @throws IllegalArgumentException if any of the parameters are null
     */
    public Address(
           String streetNum,
           String city,
           StateCode state,
           String postalCode
    ) throws IllegalArgumentException{
        if (streetNum == null || city == null || state == null || postalCode == null){
            throw new IllegalArgumentException("Value cannot be null");
        }
        else {
            this.streetNum = streetNum;
            this.city = city;
            this.state = state;
            this.postalCode = postalCode;
        }
    }

    /**
     * Overrides the two string to better describe this method
     * @return the address in postal format as a String
     */
    @Override
    public String toString() {
        String str;
        str = String.format(streetNum + System.lineSeparator() + "%s, %s %s",city,state, postalCode);
        return str;
    }

    /**
     * Determines whether this object is equal to a given object.
     * The two objects are equal if they are both concrete Address objects,
     * and all corresponding properties are equal.
     * @param o The given object
     * @return True if the this object is equal to the given object, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(streetNum, address.streetNum) &&
                Objects.equals(city, address.city) &&
                state == address.state &&
                Objects.equals(postalCode, address.postalCode);
    }

    /**
     * Overrides Object.hashCode(); required because equals is overridden.
     * @return The hash code for this object.
     */
    @Override
    public int hashCode() {

        return Objects.hash(streetNum, city, state, postalCode);
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
     * @param address the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(Address address) {
        int rcode = Comparator.comparing(Address::getState)
                .thenComparing(Address::getPostalCode)
                .thenComparing(Address::getCity)
                .thenComparing(Address::getStreetNumber)
                .compare(this,address);
        return rcode;
    }
}
