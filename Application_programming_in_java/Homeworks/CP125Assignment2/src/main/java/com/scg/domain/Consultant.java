package com.scg.domain;

import com.scg.util.PersonalName;

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
}
