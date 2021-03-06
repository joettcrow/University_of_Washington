package com.scg.domain;

import com.scg.util.PersonalName;

import java.util.Objects;

/**
 * This immutable class encapsulates the name of a consultant
 * @author jcrowley
 */
public class Consultant {
    private final PersonalName name;

    /**
     * Getter method for the consultant name
     * @return the name as a PersonalName
     */
    public PersonalName getName() {
        return name;
    }

    /**
     * Constructor for the consultant
     * @param name the name for the consultant
     * @throws IllegalArgumentException if the name is null
     */
    public Consultant(PersonalName name ) throws IllegalArgumentException{
        if (name == null){
            throw new IllegalArgumentException("Name cannot be null");
        }
        else {
            this.name = name;
        }
    }

    /**
     * Overrides the toString value to return the name
     * @return the name of the consultant as a String.
     */
    @Override
    public String toString() {
        String str = name.toString();
        return str;
    }

    /**
     * Overrides Object.equals( Object ).
     * Determines whether this Consultant object is equal to a given object.
     * The two objects are equal if they are both concrete Consultant objects,
     * and the corresponding names are equal.
     * @param o The given object.
     * @return True if the this object is equal to the given object, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        boolean eq = false;
        if (this == o) eq = true;
        if (o == null || getClass() != o.getClass()) eq = false;
        Consultant that = (Consultant) o;
        eq = Objects.equals(name, that.name);
        return eq;
    }

    /**
     * Hashcode method for a consultant
     * @return the hash code of the consultant
     */
    @Override
    public int hashCode() {

        return Objects.hash(name);
    }
}
