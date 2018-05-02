package com.scg.util;

import java.util.Comparator;
import java.util.Objects;

/**
 * This class encapsulates the first, middle and last names of a person.
 * @author jcrowley
 */
public class PersonalName implements Comparable<PersonalName>{
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
        if (firstName == null){
            this.firstName = "";
        }
        else {
            this.firstName = firstName;
        }
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
        if (lastName == null){
            this.lastName = "";
        }
        else {
            this.lastName = lastName;
        }
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

        if (middleName == null){
            this.middleName = "";
        }
        else {
            this.middleName = middleName;
        }
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
        if (lastName == null){
            this.lastName = "";
        }
        else {
            this.lastName = lastName;
        }
        if (firstName == null){
            this.firstName = "";
        }
        else {
            this.firstName = firstName;
        }
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
        if (middleName == null){
            this.middleName = "";
        }
        else {
            this.middleName = middleName;
        }
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

    /**
     * Determines whether this object is equal to a given object.
     * The two objects are equal if they are both concrete PersonalName objects,
     * and all corresponding properties are equal.
     * @param o The given object
     * @return True if the this object is equal to the given object, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonalName that = (PersonalName) o;
        return Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(middleName, that.middleName);
    }

    /**
     * Overrides Object.hashCode(); required because equals is overridden.
     * @return The hash code for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, middleName);
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
     * @param name the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(PersonalName name) {
        int rcode = Comparator.comparing(PersonalName::getFirstName)
                .thenComparing(PersonalName::getLastName)
                .thenComparing(PersonalName::getMiddleName)
                .compare(this,name);
        return rcode;
    }
}
