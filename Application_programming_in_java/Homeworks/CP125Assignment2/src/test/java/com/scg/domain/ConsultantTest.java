package com.scg.domain;

import com.scg.util.PersonalName;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author jcrowley
 */

public class ConsultantTest {
    PersonalName name1 = new PersonalName(
            "Last",
            "First",
            "Middle");
    PersonalName name2;
    PersonalName name3 = new PersonalName(
            "Last3",
            "First3",
            "Middle3"
    );

    @Test
    public void makeValidConsutantTest(){
        Consultant cons = new Consultant(name1);
        assertEquals(name1.toString(),cons.getName().toString());
    }

    @Test
    public void makeInvalidConsultantTest(){
        try {
            Consultant cons = new Consultant(name2);
        }catch (IllegalArgumentException exc){
            assertEquals("Name cannot be null",exc.getMessage());
        }
    }

    @Test
    public void consutantToStringTest(){
        Consultant cons = new Consultant(name1);
        assertEquals(name1.toString(),cons.toString());
    }

    @Test
    public void consultantSameEqualsTest(){
        Consultant cons1 = new Consultant(name1);
        Consultant cons2 = cons1;
        assert cons1.equals(cons2);
    }

    @Test
    public void consultantEqualsTest(){
        Consultant cons1 = new Consultant(name1);
        Consultant cons2 = new Consultant(name1);
        assert cons1.equals(cons2);
    }

    @Test
    public void consultantNotEqualsTest(){
        Consultant cons1 = new Consultant(name1);
        Consultant cons3 = new Consultant(name3);
        assert !cons1.equals(cons3);
    }

    @Test
    public void consultantHashEqualstest(){
        Consultant cons1 = new Consultant(name1);
        Consultant cons2 = new Consultant(name1);
        assertEquals(cons1.hashCode(),cons2.hashCode());
    }

    @Test
    public void consultantHashNotEqualstest(){
        Consultant cons1 = new Consultant(name1);
        Consultant cons3 = new Consultant(name3);
        assertNotEquals(cons1.hashCode(),cons3.hashCode());
    }

}