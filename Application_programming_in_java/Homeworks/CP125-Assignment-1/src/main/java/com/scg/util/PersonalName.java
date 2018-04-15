package com.scg.util;

/**
 * This class encapsulates the first, middle and last names of a person.
 * @author jcrowley
 */
public class PersonalName {
    private String firstName;
    private String lastName;
    private String middleName;

    /**
     * Getter for the first name value
     * @return the first name as a String
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Setter for the first name value
     * @param firstName a String value to set as the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Getter for the last name value
     * @return the last Name as a String
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Setter for the last name value
     * @param lastName a String value to set as the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Getter for the middle name value
     * @return the middle name as a String
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * Setter for the middle name value
     * @param middleName a String value to set as the middle name
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     * Default constructor for a personal name
     * Sets first middle and last names to empty strings
     */
    public PersonalName(){
        this.firstName = "";
        this.lastName = "";
        this.middleName = "";
    }

    /**
     * Two variable constructor for a personal name
     * Sets first and last name using parameters
     * sets middle name as an empty string
     * @param lastName String value to set as the last name
     * @param firstName String value to set as the first name
     */
    public PersonalName(String lastName, String firstName){
        this();
        this.lastName = lastName;
        this.firstName = firstName;
    }

    /**
     * Three variable constructor for a personal name
     * Sets first middle and last names using the parameters
     * @param lastName String value to set as the last name
     * @param firstName String value to set as the first name
     * @param middleName String value to set as the middle name
     */
    public PersonalName(String lastName, String firstName, String middleName){
        this(lastName,firstName);
        this.middleName = middleName;
    }

    /**
     * Overrides the to string to return name in last, first middle format
     * @return the last, first and middle name (if middle name is present)
     */
    @Override
    public String toString() {
        String str;
        if (middleName == ""){
            str = String.format("%s, %s", lastName,firstName);
        }
        else {
            str = String.format("%s, %s %s", lastName,firstName, middleName);
        }

        return str;
    }
}
