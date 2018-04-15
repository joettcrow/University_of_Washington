package com.scg.util;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author jcrowley
 */

public class PersonalNameTest {

    private final String firstName = "Joseph";
    private final String lastName = "Crowley";
    private final String middleName = "Thomas";
    PersonalName name1 = new PersonalName();
    PersonalName name2 = new PersonalName(lastName,firstName);
    PersonalName name3 = new PersonalName(lastName,firstName,middleName);

    @Test
    public void createPersonEmptyTest(){
        PersonalName name = new PersonalName();
        assertEquals("",name.getFirstName());
        assertEquals("",name.getLastName());
        assertEquals("",name.getMiddleName());
    }

    @Test
    public void createPersonFirstLastTest(){
        PersonalName name = new PersonalName(lastName,firstName);
        assertEquals(firstName,name.getFirstName());
        assertEquals(lastName,name.getLastName());
        assertEquals("",name.getMiddleName());
    }

    @Test
    public void createPersonFirstLastMiddleTest(){
        PersonalName name = new PersonalName(lastName,firstName,middleName);
        assertEquals(firstName,name.getFirstName());
        assertEquals(lastName,name.getLastName());
        assertEquals(middleName,name.getMiddleName());
    }

    @Test
    public void setFirstNameTest() {
        name1.setFirstName(firstName);
        assertEquals(firstName,name1.getFirstName());
    }

    @Test
    public void setLastNameTest() {
        name1.setLastName(lastName);
        assertEquals(lastName,name1.getLastName());
    }

    @Test
    public void setMiddleNameTest() {
        name1.setMiddleName(middleName);
        assertEquals(middleName,name1.getMiddleName());
    }

    @Test
    public void noMiddletoStringTest() {
        String nameString = "Crowley, Joseph";
        assertEquals(nameString, name2.toString());
    }

    @Test
    public void middletoStringTest() {
        String nameString = "Crowley, Joseph Thomas";
        assertEquals(nameString, name3.toString());
    }
}