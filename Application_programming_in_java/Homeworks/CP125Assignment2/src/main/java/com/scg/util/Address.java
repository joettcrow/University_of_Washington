package com.scg.util;

import java.util.Objects;

/**
 * This immutable class encapsulates an address. Encapsulated properties are:
 * Street address
 * City
 * State code
 * Postal code
 * @author jcrowley
 */
public class Address {
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
}
