package com.scg.util;

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
}
